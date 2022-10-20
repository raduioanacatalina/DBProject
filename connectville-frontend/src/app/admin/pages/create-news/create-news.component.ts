import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { NewsService } from 'src/app/shared/service/news.service';

@Component({
  selector: 'app-create-news',
  templateUrl: './create-news.component.html',
  styleUrls: ['./create-news.component.css'],
})
export class CreateNewsComponent implements OnInit {
  selectedFiles?: FileList;
  selectedFileNames: string[] = [];

  progressInfos: any[] = [];
  message: string[] = [];

  previews: string[] = [];
  imageInfos?: Observable<any>;

  localUrl!: any[];

  form: FormGroup;

  constructor(
    private newsService: NewsService,
    private router: Router,
    private fb: FormBuilder,

    private _snackBar: MatSnackBar
  ) {
    this.form = this.fb.group({
      text: [null, [Validators.required, Validators.maxLength(288)]],

      image: [null],

      topics: [null, [Validators.required]],

      cop: [null, [Validators.required]],
    });
  }

  homepageClicked() {
    this.router.navigate(['homepage']);
  }

  ngOnInit(): void {}

  submitDetails(form: any) {
    console.log('submit details');
    this.newsService
      .createNews(
        this.form.controls['text'].value,
        this.form.controls['topics'].value.split(','),
        this.form.controls['cop'].value,
        this.selectedFileNames[0]
      )
      .subscribe({
        next: () => {
          this._snackBar.open('News created with success!', 'Ok');
          this.router.navigate(['homepage']);
        },
        error: () => {
          this._snackBar.open('News creation failed!', 'Ok');
        },
      });
  }

  selectFiles(event: any): void {
    this.message = [];
    this.progressInfos = [];
    this.selectedFileNames = [];
    this.selectedFiles = event.target.files;

    this.previews = [];
    if (this.selectedFiles && this.selectedFiles[0]) {
      const numberOfFiles = this.selectedFiles.length;
      for (let i = 0; i < numberOfFiles; i++) {
        const reader = new FileReader();

        reader.onload = (e: any) => {
          console.log(e.target.result);
          this.previews.push(e.target.result);
        };

        reader.readAsDataURL(this.selectedFiles[i]);

        this.selectedFileNames.push(this.selectedFiles[i].name);
      }
    }
  }

  // upload(idx: number, file: File): void {
  //   this.progressInfos[idx] = { value: 0, fileName: file.name };

  //   if (file) {
  //     this.uploadService.upload(file).subscribe(
  //       (event: any) => {
  //         if (event.type === HttpEventType.UploadProgress) {
  //           this.progressInfos[idx].value = Math.round(
  //             (100 * event.loaded) / event.total
  //           );
  //         } else if (event instanceof HttpResponse) {
  //           const msg = 'Uploaded the file successfully: ' + file.name;
  //           this.message.push(msg);
  //           this.imageInfos = this.uploadService.getFiles();
  //         }
  //       },
  //       (err: any) => {
  //         this.progressInfos[idx].value = 0;
  //         const msg = 'Could not upload the file: ' + file.name;
  //         this.message.push(msg);
  //       }
  //     );
  //   }
  // }

  // uploadFiles(): void {
  //   this.message = [];

  //   if (this.selectedFiles) {
  //     for (let i = 0; i < this.selectedFiles.length; i++) {
  //       this.upload(i, this.selectedFiles[i]);
  //     }
  //   }
  // }
}
