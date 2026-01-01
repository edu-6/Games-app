export interface SubcomentarioRequest {
  id_comentario_padre: number;
  correoUsuario: string;
  texto: string;
  fecha: Date | string;
}