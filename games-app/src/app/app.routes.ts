import { Routes } from '@angular/router';
import { LoginForm } from '../components/login-form/login-form';
import { RegistroForm } from '../components/registro-form/registro-form';
import { InicioPage } from '../pages/inicio-page/inicio-page';
import { CategoriasPage } from '../pages/admin-sistema/categorias/categorias-page/categorias-page';
import { NuevaCategoriaPage } from '../pages/admin-sistema/categorias/nueva-categoria-page/nueva-categoria-page';

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
    },
    {
        path:"categorias",
        component:CategoriasPage,
    },
    {
        path: "categorias/nueva",
        component: NuevaCategoriaPage,
    }



];
