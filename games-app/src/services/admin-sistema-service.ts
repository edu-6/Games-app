import { HttpClient } from "@angular/common/http";
import { ConstantesRest } from "../shared/restapi/constantes-rest";
import { Observable } from "rxjs/internal/Observable";
import { Categoria } from "../models/categorias/categoria";
import { Injectable } from "@angular/core";
import { core } from "@angular/compiler";
import { EdicionCategoria } from "../models/categorias/edicion-categoria";
import { AdminSistema } from "../models/admins-sistema/admin-creacion";
import { AdminSistemaSimple } from "../models/admins-sistema/admin-simple";


@Injectable({
  providedIn: 'root'
})
export class AdminsSistemaService {
  constantesRest = new ConstantesRest();

  constructor(private httpCliente: HttpClient) { }


  public crearAdmin(admin: AdminSistema): Observable<void> {
    return this.httpCliente.post<void>(this.constantesRest.getApiURL() + 'admins-sistema', admin);
  }
  public obtenerAdmins(): Observable<AdminSistemaSimple[]> {
    return this.httpCliente.get<AdminSistema[]>(this.constantesRest.getApiURL() + 'admins-sistema');
  }

  public buscarAdmins(nombre: string): Observable<AdminSistemaSimple []>{
    return this.httpCliente.get<AdminSistemaSimple []>(`${this.constantesRest.getApiURL()}admins-sistema/${nombre}`);
  }

  public editarAdmin(admin: AdminSistemaSimple): Observable<void>{
    return this.httpCliente.put<void>(this.constantesRest.getApiURL() + 'admins-sistema', admin);
  }

  public eliminarAdmin(correo: string): Observable<void>{
    return this.httpCliente.delete<void>(`${this.constantesRest.getApiURL()}admins-sistema/${correo}`);
  }


  public subirImagen(imagen: File, correo: string): Observable<void> {
    let formData: FormData = new FormData(); // clase que permite enviar los archivos en binarios
    formData.append('correo', correo);
    formData.append('imagen', imagen);
    return this.httpCliente.put<void>(this.constantesRest.API_URL + 'admins-sistema', formData);
  }


  public obtenerImagen(correo: string): Observable<Blob> {
  return this.httpCliente.get(`${this.constantesRest.getApiURL()}admins-sistema/imagenes/${correo}`, {
    responseType: 'blob'
  });
}


}