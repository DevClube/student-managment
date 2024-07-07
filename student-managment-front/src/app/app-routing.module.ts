import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./component/home/home.component";
import {ProfileComponent} from "./component/profile/profile.component";
import {LoginComponent} from "./component/login/login.component";
import {PaymentComponent} from "./component/payment/payment.component";
import {StudentComponent} from "./component/student/student.component";
import {DashboardComponent} from "./component/dashboard/dashboard.component";
import {LoadpaymentComponent} from "./component/loadpayment/loadpayment.component";
import {LoadstudentComponent} from "./component/loadstudent/loadstudent.component";
import {AdminTemplateComponent} from "./component/admin-template/admin-template.component";
import {AuthGuard} from "./guards/auth.guard";
import {AuthorizationGuard} from "./guards/authorization.guard";

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'admin', component: AdminTemplateComponent , canActivate:[AuthGuard],children:[
      { path: 'home', component: HomeComponent },
      { path: 'profile', component:ProfileComponent },
      { path: 'loadstudent', component: LoadstudentComponent ,
      canActivate:[AuthorizationGuard],
      data:{role:['ADMIN']}
      },
      { path: 'loadpayment', component: LoadpaymentComponent },
      { path: 'dashboard', component: DashboardComponent },
      { path: 'student', component: StudentComponent },
      { path: 'payment', component: PaymentComponent },
    ]},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
