import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/service/auth.service';
import { Role } from '../../model/user.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  form = new FormGroup({
    username: new FormControl(null, Validators.required),
    password: new FormControl(null, Validators.required),
  });

  constructor(
    private authService: AuthService,
    private router: Router,
    private _snackBar: MatSnackBar
  ) {}

  submitForm() {
    if (this.form.invalid) {
      return;
    }
    this.authService
      .login(
        this.form.get('username')?.value!,
        this.form.get('password')?.value!,
        Role.user
      )
      .subscribe({
        next: () => {
          this.router.navigate(['/homepage']);
        },
        error: () => {
          this.showInvalidCredentialsMessage();
        },
      });
  }

  showInvalidCredentialsMessage() {
    this._snackBar.open('Password incorrect!', 'Ok');
  }

  ngOnInit(): void {}
}
