import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

    public LoginForm!: FormGroup;

    constructor(private fb: FormBuilder,
                private authService: AuthService,
                private router: Router) {
    }

    ngOnInit(): void {
        this.LoginForm = this.fb.group({
          email: ['', [Validators.required, Validators.email]],
          password: ['', [Validators.required, Validators.minLength(6)]],
        })
    }


  Login() {
    let email = this.LoginForm.value.email;
    let password = this.LoginForm.value.password;

    let auth:boolean = this.authService.Login(email, password);

    if(auth==true){
      this.router.navigateByUrl('/admin');
    }
  }
}
