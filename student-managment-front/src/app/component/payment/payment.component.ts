import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.css'
})
export class PaymentComponent implements OnInit ,AfterViewInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  public payments: any;
  public dataSource:any;
  public displayedColumns = ['id','amount','type','date','status','firstName']

  constructor(private httpClient: HttpClient) {}

    ngOnInit(): void {
        this.httpClient.get("http://localhost:8021/payments").subscribe(
          {
            next: data => {
              this.payments = data;
              this.dataSource = new MatTableDataSource(this.payments);
              this.dataSource.sort = this.sort;
              this.dataSource.paginator = this.paginator;

            },
            error: err =>{
              console.log(err);
            }
          }
        );


    }



  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }



}
