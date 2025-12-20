import { Routes } from '@angular/router';
import { LoginForm } from '../components/login-form/login-form';
import { RegistroForm } from '../components/registro-form/registro-form';
import { InicioPage } from '../pages/inicio-page/inicio-page';
import { CategoriasPage } from '../pages/admin-sistema/categorias/categorias-page/categorias-page';
import { NuevaCategoriaPage } from '../pages/admin-sistema/categorias/nueva-categoria-page/nueva-categoria-page';
import { EditarCategoriaPage } from '../pages/admin-sistema/categorias/editar-categoria-page/editar-categoria-page';
import { AdminSistemaForm } from '../components/admin-sistema/admin-sistema-form/admin-sistema-form';
import { AdminsSistemaPage } from '../pages/admin-sistema/admin-sistema-pages/admins-sistema-page/admins-sistema-page';

export const routes: Routes = [

    {path: "", component: LoginForm,},
    {  path: "registro",component: RegistroForm },
    { path: "inicio",component: InicioPage },

    
    { path:"categorias",component:CategoriasPage },
    { path: "categorias/nueva", component: NuevaCategoriaPage},
    { path: "categorias/edicion/:categoria", component: EditarCategoriaPage},

    //Admins sistema
    { path: "admins-sistema", component:  AdminsSistemaPage},
    { path: "admins-sistema/form", component:  AdminSistemaForm}

    
    
    


];
