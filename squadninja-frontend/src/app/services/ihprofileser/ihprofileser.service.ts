import { Injectable } from '@angular/core';
import { HttpBackend, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IhProfile } from '../ihprofileser/ihprofile.model';
import { Register } from 'src/app/register/register.model';
// import { ServiceProvider } from './my-profile-model';
@Injectable({
 providedIn: 'root'
})
export class IhprofileserService {
private_url: string = "http://13.235.10.115:8095/api/v1/ihdto"
 constructor(private http: HttpClient) { }
createUser(user:any):Observable<any> {
 return this.http.post<Register>(this.private_url,user);
}
getByEmailIdForIdeaHamster(emailId):any{
 return this.http.get(`http://13.235.10.115:8095/api/v1/ideahamster/${emailId}`);
}
updateTheProfile(profile: any):Observable<any> {
 return this.http.put(`http://13.235.10.115:8095/api/v1/ideahamster`,(profile));
}

getIdea(postedBy):any{
    return this.http.get<any>(`http://13.235.10.115:8090/api/v1/postedIdeas/${postedBy}`);
 }

}

