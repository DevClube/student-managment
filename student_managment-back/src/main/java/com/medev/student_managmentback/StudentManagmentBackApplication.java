package com.medev.student_managmentback;

import com.medev.student_managmentback.entities.Payment;
import com.medev.student_managmentback.entities.Student;
import com.medev.student_managmentback.enums.PaymentStatus;
import com.medev.student_managmentback.enums.PaymentType;
import com.medev.student_managmentback.repository.PaymentRepository;
import com.medev.student_managmentback.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class StudentManagmentBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagmentBackApplication.class, args);
	}


	@Bean
	CommandLineRunner init(StudentRepository studentRepository, PaymentRepository paymentRepository) {

		return args -> {
			studentRepository.save(
					Student.builder().
							id(UUID.randomUUID().toString())
							.firstName("hamid")
							.code("1523600")
							.programId("SDIA")
							.build());

			studentRepository.save(
					Student.builder().
							id(UUID.randomUUID().toString())
							.firstName("Iman")
							.code("8596600")
							.programId("SDIA")
							.build());

			studentRepository.save(
					Student.builder().
							id(UUID.randomUUID().toString())
							.firstName("mouhammed")
							.code("1893600")
							.programId("FAAP")
							.build());

			studentRepository.save(
					Student.builder().
							id(UUID.randomUUID().toString())
							.firstName("najat")
							.code("1965600")
							.programId("BDDC")
							.build());


			PaymentType[] paymentTypes = PaymentType.values();
			Random random = new Random();
			studentRepository.findAll().forEach(st ->
			{
				for (int i = 0; i < 10; i++) {
					int index = random.nextInt(paymentTypes.length);
					Payment payment = Payment.builder()
							.amount(1000+(int)(Math.random()*20000))
							.type(paymentTypes[index])
							.status(PaymentStatus.CREATED)
							.date(LocalDate.now())
							.student(st)
							.build();
					paymentRepository.save(payment);
				}
			});
		};
	}
}
