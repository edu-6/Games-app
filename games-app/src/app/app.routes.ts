import { Routes } from '@angular/router';
import { LoginForm } from '../components/login-form/login-form';
import { RegistroForm } from '../components/registro-form/registro-form';
import { InicioPage } from '../pages/inicio-page/inicio-page';
import { CategoriasPage } from '../pages/admin-sistema/categorias/categorias-page/categorias-page';
import { NuevaCategoriaPage } from '../pages/admin-sistema/categorias/nueva-categoria-page/nueva-categoria-page';
import { EditarCategoriaPage } from '../pages/admin-sistema/categorias/editar-categoria-page/editar-categoria-page';
import { AdminSistemaForm } from '../components/admin-sistema/admin-sistema-form/admin-sistema-form';
import { AdminsSistemaPage } from '../pages/admin-sistema/admin-sistema-pages/admins-sistema-page/admins-sistema-page';
import { AdminsSistemaEditarPage } from '../pages/admin-sistema/admin-sistema-pages/admins-sistema-editar-page/admins-sistema-editar-page';
import { EmpresasPage } from '../pages/empresas-pages/empresas-page/empresas-page';
import { EmpresaForm } from '../components/empresas/empresa-form/empresa-form';
import { EmpresasEditarPage } from '../pages/empresas-pages/empresas-editar-page/empresas-editar-page';
import { AmdinEmpresaPage } from '../pages/admin-empresa/amdin-empresa-page/amdin-empresa-page';
import { AmdinEmpresaForm } from '../components/admin-empresa/amdin-empresa-form/amdin-empresa-form';
import { AmdinEmpresaEditarPage } from '../pages/admin-empresa/amdin-empresa-editar-page/amdin-empresa-editar-page';
import { GuardiaRolesServicio } from '../services/seguridad/GuardiaDeRolesServicio';
import { NoPermitidoPage } from '../pages/no-permitido-page/no-permitido-page';
import { JuegosPage } from '../pages/juegos/juegos-page/juegos-page';
import { JuegoForm } from '../components/juegos/juego-form/juego-form';


export const routes: Routes = [

    { path: "", component: LoginForm, },
    {
        path: "registro",
        component: RegistroForm,
    },
    { path: "inicio", component: InicioPage },

    {
        path: "no-permitido-page", component: NoPermitidoPage

    },
    //Categorias
    {
        path: "categorias",
        component: CategoriasPage,
        canActivate: [GuardiaRolesServicio],
        data: { allowedRoles: ['ADMIN_SISTEMA'] },
    },
    {
        path: "categorias/nueva",
        component: NuevaCategoriaPage,
        canActivate: [GuardiaRolesServicio],
        data: { allowedRoles: ['ADMIN_SISTEMA'] },
    },
    {
        path: "categorias/edicion/:categoria",
        component: EditarCategoriaPage,
        canActivate: [GuardiaRolesServicio],
        data: { allowedRoles: ['ADMIN_SISTEMA'] },
    },



    //Admins sistema
    {
        path: "admins-sistema",
        component: AdminsSistemaPage,
        canActivate: [GuardiaRolesServicio],
        data: { allowedRoles: ['ADMIN_SISTEMA'] },
    },
    {
        path: "admins-sistema/form",
        component: AdminSistemaForm,
        canActivate: [GuardiaRolesServicio],
        data: { allowedRoles: ['ADMIN_SISTEMA'] },
    },
    {
        path: "admins-sistema/edicion/:correo",
        component: AdminsSistemaEditarPage,
        canActivate: [GuardiaRolesServicio],
        data: { allowedRoles: ['ADMIN_SISTEMA'] },
    },




    //Empresas 
    {
        path: "empresas",
        component: EmpresasPage,
        canActivate: [GuardiaRolesServicio],
        data: { allowedRoles: ['ADMIN_SISTEMA'] },
    },
    {
        path: "empresas/form",
        component: EmpresaForm,
        canActivate: [GuardiaRolesServicio],
        data: { allowedRoles: ['ADMIN_SISTEMA'] },
    },
    {
        path: "empresas/edicion/:nombre",
        component: EmpresasEditarPage,
        canActivate: [GuardiaRolesServicio],
        data: { allowedRoles: ['ADMIN_SISTEMA'] },
    },

    // Admins empresas 
    {
        path: "admins-empresa",
        component: AmdinEmpresaPage,
        canActivate: [GuardiaRolesServicio],
        data: { allowedRoles: ['ADMIN_EMPRESA'] },
    },
    {
        path: "admins-empresa/form/:nombre",
        component: AmdinEmpresaForm,
        canActivate: [GuardiaRolesServicio],
        data: { allowedRoles: ['ADMIN_SISTEMA', 'ADMIN_EMPRESA'] },
    },
    {
        path: "admins-empresa/form",
        component: AmdinEmpresaForm,
        canActivate: [GuardiaRolesServicio],
        data: { allowedRoles: ['ADMIN_SISTEMA', 'ADMIN_EMPRESA'] },
    },
    {
        path: "admins-empresa/edicion/:correo",
        component: AmdinEmpresaEditarPage,
        canActivate: [GuardiaRolesServicio],
        data: { allowedRoles: ['ADMIN_EMPRESA'] },
    },


    {

        path:"juegos",
        component: JuegosPage,
        canActivate: [GuardiaRolesServicio],
        data: { allowedRoles: ['ADMIN_EMPRESA', 'ADMIN_SISTEMA', 'GAMER'] },
    },

    /// Juegos

    {
        path:"juegos/form",
        component: JuegoForm,
        canActivate: [GuardiaRolesServicio],
        data: { allowedRoles: ['ADMIN_EMPRESA'] },
    }









];
