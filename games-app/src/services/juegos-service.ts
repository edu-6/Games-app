import { HttpClient } from "@angular/common/http";
import { ConstantesRest } from "../shared/restapi/constantes-rest";
import { Observable } from "rxjs/internal/Observable";
import { Injectable } from "@angular/core";
import { AdminEmpresaSimple } from "../models/admin-empresa/admin-empresa-simple";
import { BusquedaAdmins } from "../models/admin-empresa/busqueda-admins";
import { Juego } from "../models/juegos/juego";


@Injectable({
  providedIn: 'root'
})
export class JuegosService {
  constantesRest = new ConstantesRest();

  constructor(private httpCliente: HttpClient) { }


  public crearJuego(juego: Juego): Observable<void> {
    return this.httpCliente.post<void>(this.constantesRest.getApiURL() + 'juegos', juego);
  }
  public obtenerAdmins(correo: string ): Observable<AdminEmpresaSimple[]> {
    return this.httpCliente.get<AdminEmpresaSimple[]>(`${this.constantesRest.getApiURL()}admins-empresa/lista-completa/${correo}`);
  }


  // post porque  en get solo se puede usar parametros 
  public buscarAdmins(busqueda: BusquedaAdmins): Observable<AdminEmpresaSimple []>{
    return this.httpCliente.post<AdminEmpresaSimple[]>(this.constantesRest.getApiURL() +"admins-empresa/busqueda", busqueda);
  }

  public buscarJuegoUnico(id: string): Observable<Juego>{
    return this.httpCliente.get<Juego >(`${this.constantesRest.getApiURL()}juegos/juegoUnico/${id}`);
  }

  public editarJuego(admin: Juego): Observable<void>{
    return this.httpCliente.put<void>(this.constantesRest.getApiURL() + 'juegos', admin);
  }

  public eliminarJuego(id: string): Observable<void>{
    return this.httpCliente.delete<void>(`${this.constantesRest.getApiURL()}admins-empresa/${id}`);
  }


  public subirImagen(imagen: File, id: string): Observable<void> {
    let formData: FormData = new FormData(); // clase que permite enviar los archivos en binarios
    formData.append('correo', id);
    formData.append('imagen', imagen);
    return this.httpCliente.put<void>(this.constantesRest.API_URL + 'admins-empresa', formData);
  }


  public obtenerImagen(correo: string): Observable<Blob> {
  return this.httpCliente.get(`${this.constantesRest.getApiURL()}juegos/imagenes/${correo}`, {
    responseType: 'blob'
  });
}


}