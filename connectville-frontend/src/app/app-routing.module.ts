import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/pages/login/login.component';
import { HomepageComponent } from './news/pages/homepage/homepage.component';
import { IsAuthenticatedGuard } from './auth/helpers/is-authenticated.guard';

const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'homepage', component: HomepageComponent, canActivate: [IsAuthenticatedGuard]}
]

@NgModule({
    declarations: [],
    imports: [
        [RouterModule.forRoot(routes)]
    ],
    exports:[RouterModule]
})

export class AppRoutingModule { }