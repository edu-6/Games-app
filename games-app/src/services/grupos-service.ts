import { HttpClient } from "@angular/common/http";
import { ConstantesRest } from "../shared/restapi/constantes-rest";
import { Observable } from "rxjs/internal/Observable";
import { Categoria } from "../models/categorias/categoria";
import { Injectable } from "@angular/core";
import { core } from "@angular/compiler";
import { EdicionCategoria } from "../models/categorias/edicion-categoria";
import { AdminSistema } from "../models/admins-sistema/admin-creacion";
import { AdminSistemaSimple } from "../models/admins-sistema/admin-simple";
import { Empresa } from "../models/empresa/empresa";
import { EmpresaEdicion } from "../models/empresa/empresa-edicion";
import { GrupoCompleto } from "../models/grupos/grupo-completo";
import { Grupo } from "../models/grupos/grupo-request";
import { ParticipanteGrupoRequest } from "../models/grupos/participante-grupo-request";
import { ParticipanteGrupoResponse } from "../models/grupos/grupo-participante";


@Injectable({
  providedIn: 'root'
})
export class GruposService {
  constantesRest = new ConstantesRest();

  constructor(private httpCliente: HttpClient) { }


  public crearGrupo(entidad: Grupo): Observable<void> {
    return this.httpCliente.post<void>(this.constantesRest.getApiURL() + 'grupos', entidad);
  }

  public editarGrupo(entidad: GrupoCompleto): Observable<void> {
    return this.httpCliente.put<void>(this.constantesRest.getApiURL() + 'grupos', entidad);
  }

  public eliminarGrupo(id: string): Observable<AdminSistemaSimple[]> {
    return this.httpCliente.delete<AdminSistemaSimple[]>(`${this.constantesRest.getApiURL()}grupos/${id}`);
  }

  public buscarGruposDelAdmin(correo: string): Observable<GrupoCompleto[]> {
    return this.httpCliente.get<GrupoCompleto[]>(`${this.constantesRest.getApiURL()}grupos/grupos-admin/${correo}`
    );
  }

  public buscarGrupo(nombre: string): Observable<GrupoCompleto> {
    return this.httpCliente.get<GrupoCompleto>(`${this.constantesRest.getApiURL()}grupos/${nombre}`);
  }


  public añadirParticipante(entidad: ParticipanteGrupoRequest): Observable<void> {
    return this.httpCliente.post<void>(`${this.constantesRest.getApiURL()}grupos/añadir-participante`, entidad);
  }


  public obtenerParticipantes(idGrupo: number): Observable<ParticipanteGrupoResponse[]> {
    return this.httpCliente.get<ParticipanteGrupoResponse[]>(
      `${this.constantesRest.getApiURL()}grupos/participantes/${idGrupo}`
    );
  }
  
  public eliminarParticipante(idGrupo: number, correo: string): Observable<void> {
    return this.httpCliente.delete<void>(
      `${this.constantesRest.getApiURL()}grupos/eliminar-participante/${idGrupo}/${correo}`
    );
  }
}


