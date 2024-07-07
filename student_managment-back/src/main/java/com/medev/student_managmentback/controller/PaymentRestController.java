package com.medev.student_managmentback.controller;

import com.medev.student_managmentback.entities.Payment;
import com.medev.student_managmentback.entities.Student;
import com.medev.student_managmentback.enums.PaymentStatus;
import com.medev.student_managmentback.enums.PaymentType;
import com.medev.student_managmentback.repository.PaymentRepository;
import com.medev.student_managmentback.repository.StudentRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentRestController {

    private PaymentRepository paymentRepository;
    private StudentRepository studentRepository;

    public PaymentRestController(PaymentRepository paymentRepository, StudentRepository studentRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping(path = "/payments")
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @GetMapping(path = "/students/{code}/payments")
    public List<Payment> getAllPaymentsByStudent(@PathVariable String code) {
        return paymentRepository.findByStudentCode(code);
    }

    @GetMapping(path = "/payment/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentRepository.findById(id).get();
    }

    @GetMapping(path = "/payments/status")
    public List<Payment> getAllPaymentsByStatus(@RequestParam PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }

    @GetMapping(path = "/payments/type")
    public List<Payment> getAllPaymentsByType(@RequestParam PaymentType type) {
        return paymentRepository.findByType(type);
    }

    @GetMapping(path = "/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping(path = "/students/{code}")
    public Student getStudentByCOde(@PathVariable String code) {
        return studentRepository.findByCode(code);
    }

    @GetMapping(path = "/studentsByprogramId/{programId}")
    public List<Student> getStudentByProgramId(@RequestParam String programId) {
        return studentRepository.findByProgramId(programId);
    }

    @PutMapping(path = "/payments/{id}")
    public Payment updatePaymentStatus(@PathVariable Long id,@RequestParam PaymentStatus status) {
        Payment payment =  paymentRepository.findById(id).get();
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }

    @PostMapping(path="/payments/",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment SavePayment(@RequestParam MultipartFile file, LocalDate date, double amount,

                               PaymentType paymentType, String studentCode) throws IOException {

        Path folderPath = Paths.get(System.getProperty("user.home") , "student-managment","payment");
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);

        }
        String fileName = UUID.randomUUID().toString();
        Path filepath = Paths.get(System.getProperty("user.home") , "student-managment","payment",fileName+".pdf");
        Files.copy(file.getInputStream(), filepath);

        Student student = studentRepository.findByCode(studentCode);
        Payment payment = Payment.builder()
                .date(date)
                .amount(amount)
                .status(PaymentStatus.CREATED)
                .type(paymentType)
                .student(student)
                .file(filepath.toUri().toString())
                .build();

        return paymentRepository.save(payment);
    }

    @GetMapping(path = "paymentFile/{paymentId}",produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable Long paymentId) throws IOException {
        Payment payment = paymentRepository.findById(paymentId).get();
        return Files.readAllBytes(Path.of(URI.create(payment.getFile() )));
    }


}
