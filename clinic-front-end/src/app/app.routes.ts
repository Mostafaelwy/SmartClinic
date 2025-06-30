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
import { DoctordashchangepasswordComponent } from './dashboard/doctor/doctordashchangepassword/doctordashchangepassword.component';
import { AuthGuardService, AuthRedirectGuard } from './services/auth-service.service';
import { RegisterComponent } from './register/register.component';
import { DoctorRegisterComponent } from './register/doctor-register.component';
import { PatientComponent } from './dashboard/patient/patient.component';
import { PatientdashhomeComponent } from './dashboard/patient/patientdashhome/patientdashhome.component';
import { PatientdashappointmentComponent } from './dashboard/patient/patientdashappointment/patientdashappointment.component';
import { PatientdashbookappointmentComponent } from './dashboard/patient/patientdashbookappointment/patientdashbookappointment.component';
import { PatientdashmedicalrecordsComponent } from './dashboard/patient/patientdashmedicalrecords/patientdashmedicalrecords.component';
import { PatientdashprescriptionsComponent } from './dashboard/patient/patientdashprescriptions/patientdashprescriptions.component';
import { PatientdashpaymentsComponent } from './dashboard/patient/patientdashpayments/patientdashpayments.component';
import { PatientdashprofileComponent } from './dashboard/patient/patientdashprofile/patientdashprofile.component';
import { PatientdashfavouritesComponent } from './dashboard/patient/patientdashfavourites/patientdashfavourites.component';
import { PatientdashdependantsComponent } from './dashboard/patient/patientdashdependants/patientdashdependants.component';
import { PatientdashwalletComponent } from './dashboard/patient/patientdashwallet/patientdashwallet.component';
import { PatientdashinvoicesComponent } from './dashboard/patient/patientdashinvoices/patientdashinvoices.component';
import { PatientdashmessagesComponent } from './dashboard/patient/patientdashmessages/patientdashmessages.component';
import { PatientdashvitalsComponent } from './dashboard/patient/patientdashvitals/patientdashvitals.component';
import { DoctorSearchComponent } from './search/doctors/doctor-search.component';
import { DoctorProfileComponent } from './doctor-profile.component';
import { PatientdashsettingsComponent } from './dashboard/patient/patientdashsettings/patientdashsettings.component';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'doctor-register', component: DoctorRegisterComponent },
    { path: 'doctor-search', component: DoctorSearchComponent, canActivate: [AuthGuardService], data: { roles: ['PATIENT'] } },
    { path: 'dashboard', component: DashboardComponent,
        children:[
            { path: 'doctor', component: DoctorComponent, canActivate: [AuthGuardService], data: { roles: ['DOCTOR'] },
                children:[
                    {path:'home', component:DoctordashhomeComponent},
                    {path:'requests', component:DoctordashrequestsComponent},
                    {path:'appointments', component:DoctordashappointmentComponent},
                    {path:'available-timings', component:DoctordashavailabletimingsComponent},
                    {path:'speciality-service', component:DoctordashspecandservComponent},
                    {path:'reviews', component:DoctordashreviewsComponent},
                    {path:'profile', component:DoctordashprofileComponent},
                    {path:'change-password', component:DoctordashchangepasswordComponent},
                    {
                        path: '', // default to home if no child is specified
                        redirectTo: 'home',
                        pathMatch: 'full'
                    }
                ]
            },
            {
                path: 'patient', component: PatientComponent,
                children: [
                    { path: 'home', component: PatientdashhomeComponent },
                    { path: 'appointments', component: PatientdashappointmentComponent },
                    { path: 'book-appointment', component: PatientdashbookappointmentComponent },
                    { path: 'medical-records', component: PatientdashmedicalrecordsComponent },
                    { path: 'prescriptions', component: PatientdashprescriptionsComponent },
                    { path: 'payments', component: PatientdashpaymentsComponent },
                    { path: 'profile', component: PatientdashprofileComponent },
                    { path: 'favourites', component: PatientdashfavouritesComponent },
                    { path: 'dependants', component: PatientdashdependantsComponent },
                    { path: 'wallet', component: PatientdashwalletComponent },
                    { path: 'invoices', component: PatientdashinvoicesComponent },
                    { path: 'messages', component: PatientdashmessagesComponent },
                    { path: 'vitals', component: PatientdashvitalsComponent },
                    { path: 'settings', component: PatientdashsettingsComponent },
                    {
                        path: '', // default to home if no child is specified
                        redirectTo: 'home',
                        pathMatch: 'full'
                    }
                ]
             },
        ]
     },
    { path: 'doctor/:id', component: DoctorProfileComponent },
    { path: 'reserve-appointment/:doctorId',
      loadComponent: () => import('./patient-appointment-reservation.component').then(m => m.PatientAppointmentReservationComponent),
      canActivate: [AuthGuardService],
      data: { roles: ['PATIENT'] }
    },
    { path: '', canActivate: [AuthRedirectGuard], component: LoginComponent },
];
