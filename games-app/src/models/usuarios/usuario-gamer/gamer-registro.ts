import { PaisEnum } from "./pais-enum";

export interface GamerRegistro {
    telefono: number,
    correo: string,
    nickname: string,
    contrasena: string,
    fechaNacimiento: Date,
    pais: PaisEnum

}