import { HttpClient } from "@angular/common/http";
import { ConstantesRest } from "../shared/restapi/constantes-rest";
import { Observable } from "rxjs/internal/Observable";
import { Injectable } from "@angular/core";
import { ComentarioResponse } from "../models/comentarios/comentario-response";
import { Comentario } from "../models/comentarios/comentario-request";
import { SubcomentarioRequest } from "../models/comentarios/subcomentario-request";
import { SubcomentarioResponse } from "../models/comentarios/subcomentario-response";


@Injectable({
  providedIn: 'root'
})
export class ComentariosService {
  constantesRest = new ConstantesRest();

  constructor(private httpCliente: HttpClient) { }
  public crearComentario(comentario: Comentario): Observable<void> {
    return this.httpCliente.post<void>(this.constantesRest.getApiURL() + 'comentarios', comentario);
  }


  public obtenerComentariosPorJuego(nombre: String): Observable<ComentarioResponse[]> {
    return this.httpCliente.get<ComentarioResponse[]>(`${this.constantesRest.getApiURL()}comentarios/${nombre}`);
  }

  public crearSubcomentario(subcomentario: SubcomentarioRequest): Observable<void> {
    return this.httpCliente.post<void>(
      `${this.constantesRest.getApiURL()}comentarios/respuesta`,
      subcomentario
    );
  }

  public obtenerSubcomentarios(idPadre: number): Observable<SubcomentarioResponse[]> {
    return this.httpCliente.get<SubcomentarioResponse[]>(
      `${this.constantesRest.getApiURL()}comentarios/respuestas/${idPadre}`
    );
  }

  public verificarPermisoComentarios(nombreJuego: string): Observable<{ permitido: boolean }> {
    return this.httpCliente.get<{ permitido: boolean }>(
      `${this.constantesRest.getApiURL()}comentarios/permisos/${nombreJuego}`
    );
  }


}


