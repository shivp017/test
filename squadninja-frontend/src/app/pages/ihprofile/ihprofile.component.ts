import { Component, OnInit } from '@angular/core';
import {IhprofileserService} from '../../services/ihprofileser/ihprofileser.service';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-ihprofile',
  templateUrl: './ihprofile.component.html',
  styleUrls: ['./ihprofile.component.scss']
})
export class IhprofileComponent implements OnInit {

  public ideaHamsterData: IhprofileserService;
  updated: any;
  constructor(private ihprofileserviceService: IhprofileserService ) { }
  public emailId = '';
  ngOnInit() {

    this.getTheProfile();
  }
  getTheProfile(){
    this.emailId =localStorage.getItem("emailId");
    this.ihprofileserviceService.getByEmailIdForIdeaHamster(this.emailId).subscribe((data:any)=>{
      console.log("data fetched.."+ data);
      this.ideaHamsterData=data;
      console.log("after getting back from service",this.ideaHamsterData);
    });
  }
  onSubmitUpdate(){
    console.log(this.ideaHamsterData);
    this.ihprofileserviceService.updateTheProfile(this.ideaHamsterData).subscribe((data)=> {
      console.log("data updated..", data);
      this.updated=data;
      console.log("after getting back from service",this.updated);
  });
  window.location.reload();
    //this.getTheProfile();
    }

}
