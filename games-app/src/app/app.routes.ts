import { Routes } from '@angular/router';
import { LoginForm } from '../components/login-form/login-form';
import { RegistroForm } from '../components/registro-form/registro-form';
import { InicioPage } from '../pages/inicio-page/inicio-page';

export const routes: Routes = [

    {
        path: "",
        component: LoginForm,
    },
    {
        path: "registro",
        component: RegistroForm,
    },
    {
        path: "inicio",
        component: InicioPage,
    }



];
