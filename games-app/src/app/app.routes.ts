import { Routes } from '@angular/router';
import { LoginForm } from '../components/login-form/login-form';
import { RegistroForm } from '../components/registro-form/registro-form';

export const routes: Routes = [

    {
        path: "",
        component: LoginForm,
    },
    {
        path: "registro",
        component: RegistroForm,
    }


];
