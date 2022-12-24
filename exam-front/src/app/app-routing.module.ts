import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import * as path from "path";
import {RadarsComponent} from "./radars/radars.component";
import {VehiclesComponent} from "./vehicles/vehicles.component";
import {InfractionsComponent} from "./infractions/infractions.component";

const routes: Routes = [
  {
    path: "radars", component: RadarsComponent
  },{
    path: "vehicles", component: VehiclesComponent
  }
  ,{
    path: "infractions", component: InfractionsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
