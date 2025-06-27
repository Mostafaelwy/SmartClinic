import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DoctordashhomeComponent } from './dashboard/doctor/doctordashhome/doctordashhome.component';
import { DoctorComponent } from './dashboard/doctor/doctor.component';
import { DoctordashrequestsComponent } from './dashboard/doctor/doctordashrequests/doctordashrequests.component';
import { DoctordashappointmentComponent } from './dashboard/doctor/doctordashappointment/doctordashappointment.component';
import { DoctordashavailabletimingsComponent } from './dashboard/doctor/doctordashavailabletimings/doctordashavailabletimings.component';
import { DoctordashspecandservComponent } from './dashboard/doctor/doctordashspecandserv/doctordashspecandserv.component';
import { DoctordashreviewsComponent } from './dashboard/doctor/doctordashreviews/doctordashreviews.component';
import { DoctordashprofileComponent } from './dashboard/doctor/doctordashprofile/doctordashprofile.component';

export const routes: Routes = [

    { path: 'login', component: LoginComponent },
    { path: 'dashboard', component: DashboardComponent,
        children:[
            { path: 'doctor', component: DoctorComponent,
                children:[
                    {path:'home', component:DoctordashhomeComponent},
                    {path:'requests', component:DoctordashrequestsComponent},
                    {path:'appointments', component:DoctordashappointmentComponent},
                    {path:'available-timings', component:DoctordashavailabletimingsComponent},
                    {path:'speciality-service', component:DoctordashspecandservComponent},
                    {path:'reviews', component:DoctordashreviewsComponent},
                    {path:'profile', component:DoctordashprofileComponent},
                    {
                        path: '', // default to home if no child is specified
                        redirectTo: 'home',
                        pathMatch: 'full'
                    }
                ]
             },
        ]
     },
];
