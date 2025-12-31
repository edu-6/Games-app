import { ParticipanteGrupoRequest } from "./participante-grupo-request";

export class ParticipanteGrupoResponse extends ParticipanteGrupoRequest {
  constructor(
    correo: string,id_grupo: number,public nombre: string) {
    super(correo, id_grupo);
  }
}