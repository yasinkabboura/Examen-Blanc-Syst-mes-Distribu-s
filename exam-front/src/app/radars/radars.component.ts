import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-radars',
  templateUrl: './radars.component.html',
  styleUrls: ['./radars.component.css']
})
export class RadarsComponent implements OnInit {
  radars : any;

  constructor(private http:HttpClient) { }

  ngOnInit(): void {
    this.http.get("http://localhost:8888/RADAR-QUERY-SERVICE/query/radars/all").subscribe({
      next : (data)=>{
        this.radars = data;
      },
      error : (err) =>{

      }
    });
  }

}
