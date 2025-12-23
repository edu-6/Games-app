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


@Injectable({
  providedIn: 'root'
})
export class EmpresasService {
  constantesRest = new ConstantesRest();

  constructor(private httpCliente: HttpClient) { }


  public crearEmpresa(empresa: Empresa): Observable<void> {
    return this.httpCliente.post<void>(this.constantesRest.getApiURL() + 'empresas', empresa);
  }
  public obtenerEmpresas(): Observable<Empresa[]> {
    return this.httpCliente.get<Empresa[]>(this.constantesRest.getApiURL() + 'empresas');
  }

  public buscarEmpresas(nombre: string): Observable<AdminSistemaSimple []>{
    return this.httpCliente.get<AdminSistemaSimple []>(`${this.constantesRest.getApiURL()}empresas/${nombre}`);
  }

  public buscarEmpresaUnica(correo: string): Observable<Empresa>{
    return this.httpCliente.get<Empresa >(`${this.constantesRest.getApiURL()}empresas/${correo}`);
  }

  public editarEmpresa(empresa: Empresa): Observable<void>{
    return this.httpCliente.put<void>(this.constantesRest.getApiURL() + 'empresas', empresa);
  }

  public eliminarEmpresa(nombre: string): Observable<void>{
    return this.httpCliente.delete<void>(`${this.constantesRest.getApiURL()}empresas/${nombre}`);
  }


  public subirImagen(imagen: File, correo: string): Observable<void> {
    let formData: FormData = new FormData(); // clase que permite enviar los archivos en binarios
    formData.append('correo', correo);
    formData.append('imagen', imagen);
    return this.httpCliente.put<void>(this.constantesRest.API_URL + 'empresas', formData);
  }


  public obtenerImagen(nombre: string): Observable<Blob> {
  return this.httpCliente.get(`${this.constantesRest.getApiURL()}empresas/imagenes/${nombre}`, {
    responseType: 'blob'
  });
}


}