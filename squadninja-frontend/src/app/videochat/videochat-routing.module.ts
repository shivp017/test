import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { VideoRoomComponent } from './video-room/video-room.component';

const routes: Routes = [
  { path: '', component: DashboardComponent },
  { path: ':roomName', component: VideoRoomComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VideochatRoutingModule { }
