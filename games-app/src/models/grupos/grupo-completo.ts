import { Grupo } from "./grupo-request";

export class GrupoCompleto extends Grupo {
  constructor(
    public id: number,
    nombre: string,
    correoAdmin: string,
    public numeroIntegrantes: number
  ) {
    super(nombre, correoAdmin);
  }
}