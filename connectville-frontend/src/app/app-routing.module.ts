import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HomepageComponent } from './components/homepage/homepage.component';


const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'homepage', component: HomepageComponent}
]

@NgModule({
    declarations: [],
    imports: [
        [RouterModule.forRoot(routes)]
    ],
    exports:[RouterModule]
})

export class AppRoutingModule { }