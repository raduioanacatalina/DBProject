import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/service/auth.service';

@Component({
  selector: 'app-create-news',
  templateUrl: './create-news.component.html',
  styleUrls: ['./create-news.component.css']
})
export class CreateNewsComponent implements OnInit {
  form: FormGroup;

  constructor(private authService:AuthService, private router:Router, private fb: FormBuilder) {
    this.form = this.fb.group ({

    publisher: [null, [Validators.required]],
    
    text: [null, [Validators.required]],
    
    image: [null, [Validators.required]],
    
    topics: [null, [Validators.required]],
    
    }); }

  loginClicked() {
    this.authService.logout();
    this.router.navigate(['login']);
  }
  homepageClicked() {
    this.router.navigate(['homepage']);
  }
  ngOnInit(): void {
    
  }
  submitDetails(form: any) {  
      
  console.log(form.value);
    
    }

}
