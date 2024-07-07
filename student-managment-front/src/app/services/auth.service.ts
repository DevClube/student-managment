import { Injectable } from '@angular/core';
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public users:any ={
    admin :{password:"admin",role:['ADMIN','STUDENT']},
    user1 :{password:"admin",role:['STUDENT']},
  }

  public email:any;
  public isAuthenticated:boolean = false;
  public roles:string[]=[];

  constructor(private router: Router) { }


  Login(username: string, password: string):boolean {
      if(this.users[username] && this.users[username].password == password){
        this.email = username;
        this.isAuthenticated = true;
        this.roles = this.users[username].roles;

        return true
      }else{
        return false
      }
  }

  logout():void{
    this.email = null;
    this.isAuthenticated = false;
    this.roles = [];
    this.router.navigateByUrl("/login")
  }
}
