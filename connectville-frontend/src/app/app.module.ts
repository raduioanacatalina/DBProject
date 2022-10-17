import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import {
  MatFormFieldModule,
  MAT_FORM_FIELD_DEFAULT_OPTIONS,
} from '@angular/material/form-field';
import { MatChipsModule } from '@angular/material/chips';
import { MatBadgeModule } from '@angular/material/badge';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatSliderModule } from '@angular/material/slider';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatListModule } from '@angular/material/list';
import { MatDialogModule } from '@angular/material/dialog';
import { MatBottomSheetModule } from '@angular/material/bottom-sheet';
import { MatTooltipModule } from '@angular/material/tooltip';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './auth/pages/login/login.component';
import { HomepageComponent } from './news/pages/homepage/homepage.component';
import { AuthenticationInterceptor } from './auth/helpers/authentication.interceptor';
import { CreateNewsComponent } from './admin/pages/create-news/create-news.component';
import { DialogOverviewComponent } from './news/components/dialog-overview/dialog-overview.component';
import { NewsCardComponent } from './news/components/news-card/news-card.component';
import { CommentsPopUpComponent } from './news/components/comments-pop-up/comments-pop-up.component';
import { PermissionDirective } from './news/directives/permission.directive';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomepageComponent,
    CreateNewsComponent,
    DialogOverviewComponent,
    NewsCardComponent,
    CommentsPopUpComponent,
    PermissionDirective,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatChipsModule,
    MatInputModule,
    MatSelectModule,
    MatRadioModule,
    FormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatIconModule,
    MatButtonModule,
    MatTooltipModule,
    MatBadgeModule,
    MatSliderModule,
    MatCardModule,
    MatToolbarModule,
    MatSnackBarModule,
    MatListModule,
    FlexLayoutModule,
    AppRoutingModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatBottomSheetModule,
    HttpClientModule,
    FontAwesomeModule,
  ],

  providers: [
    {
      provide: MAT_FORM_FIELD_DEFAULT_OPTIONS,
      useValue: { appearance: 'fill' },
    },

    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthenticationInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
