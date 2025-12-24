export interface AdminEmpresa {
    nombre: string,
    fechaNacimiento: Date,
    constrasena: string,
    correo: string,
    correoAdminCreador: string // es el correo del admin  que lo crea o el nombre de la empresa
}