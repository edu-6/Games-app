import { PaisEnum } from "./pais-enum";

export interface GamerRegistro {
    telefono: number,
    correo: String,
    nickname: String,
    constraseña: String,
    fechaNacimiento: Date,
    pais: PaisEnum

}