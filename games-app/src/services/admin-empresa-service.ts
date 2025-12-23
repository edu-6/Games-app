import { HttpClient } from "@angular/common/http";
import { ConstantesRest } from "../shared/restapi/constantes-rest";
import { Observable } from "rxjs/internal/Observable";
import { Categoria } from "../models/categorias/categoria";
import { Injectable } from "@angular/core";
import { core } from "@angular/compiler";
import { EdicionCategoria } from "../models/categorias/edicion-categoria";
import { AdminSistema } from "../models/admins-sistema/admin-creacion";
import { AdminSistemaSimple } from "../models/admins-sistema/admin-simple";
import { AdminEmpresa } from "../models/empresa/admin-empresa";
import { AdminEmpresaSimple } from "../models/empresa/admin-empresa-simple";


@Injectable({
  providedIn: 'root'
})
export class AdminEmpresaService {
  constantesRest = new ConstantesRest();

  constructor(private httpCliente: HttpClient) { }


  public crearAdmin(admin: AdminEmpresa): Observable<void> {
    return this.httpCliente.post<void>(this.constantesRest.getApiURL() + 'admins-empresa', admin);
  }
  public obtenerAdmins(): Observable<AdminEmpresaSimple[]> {
    return this.httpCliente.get<AdminEmpresaSimple[]>(this.constantesRest.getApiURL() + 'admins-empresa');
  }

  public buscarAdmins(nombre: string): Observable<AdminEmpresaSimple []>{
    return this.httpCliente.get<AdminEmpresaSimple []>(`${this.constantesRest.getApiURL()}admins-empresa/${nombre}`);
  }

  public buscarAdminUnico(correo: string): Observable<AdminSistema>{
    return this.httpCliente.get<AdminSistema >(`${this.constantesRest.getApiURL()}admins-empresa/adminUnico/${correo}`);
  }

  public editarAdmin(admin: AdminEmpresa): Observable<void>{
    return this.httpCliente.put<void>(this.constantesRest.getApiURL() + 'admins-empresa', admin);
  }

  public eliminarAdmin(correo: string): Observable<void>{
    return this.httpCliente.delete<void>(`${this.constantesRest.getApiURL()}admins-empresa/${correo}`);
  }


  public subirImagen(imagen: File, correo: string): Observable<void> {
    let formData: FormData = new FormData(); // clase que permite enviar los archivos en binarios
    formData.append('correo', correo);
    formData.append('imagen', imagen);
    return this.httpCliente.put<void>(this.constantesRest.API_URL + 'admins-empresa', formData);
  }


  public obtenerImagen(correo: string): Observable<Blob> {
  return this.httpCliente.get(`${this.constantesRest.getApiURL()}admins-empresa/imagenes/${correo}`, {
    responseType: 'blob'
  });
}


}