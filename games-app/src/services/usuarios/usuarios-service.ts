import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { GamerRegistro } from "../../models/usuarios/usuario-gamer/gamer-registro";
import { ConstantesRest } from "../../shared/restapi/constantes-rest";
import { SaldoTarjeta } from "../../models/usuarios/tarjeta/saldo-tarjeta";
import { RecargoTarjeta } from "../../models/usuarios/tarjeta/recargo-tarjeta";
import { GamerSimple } from "../../models/usuarios/usuario-gamer/gamer-simple";

@Injectable({
  providedIn: 'root'
})

export class UsuarioServicios {
  constantesRest = new ConstantesRest();

  constructor(private httpClient: HttpClient) { }

  public subirImagen(imagen: File, correo: string): Observable<void> {
    let formData: FormData = new FormData();
    formData.append('correo', correo);
    formData.append('imagen', imagen);
    return this.httpClient.put<void>(this.constantesRest.API_URL + 'gamers', formData);
  }

  public crearNuevoGamer(gamer: GamerRegistro): Observable<void> {
    return this.httpClient.post<void>(`${this.constantesRest.getApiURL()}gamers`, gamer);
  }


  public verSaltoTarjeta(correo: string): Observable<SaldoTarjeta> {
    return this.httpClient.get<SaldoTarjeta>(`${this.constantesRest.getApiURL()}gamers/saldo/${correo}`);
  }


  public recargarTarjeta(recargo: RecargoTarjeta): Observable<void> {
    return this.httpClient.put<void>(this.constantesRest.API_URL + 'gamers/tarjeta', recargo);
  }


  public obtenerGamerPorCorreo(correo: string): Observable<GamerRegistro> {
    return this.httpClient.get<GamerRegistro>(`${this.constantesRest.getApiURL()}gamers/gamer-completo/${correo}`);
  }

  public obtenerGamerSimple(correo: string): Observable<GamerSimple> {
    return this.httpClient.get<GamerSimple>(`${this.constantesRest.getApiURL()}gamers/gamer-simple/${correo}`);
  }

  public eliminarGamer(correo: string): Observable<void> {
    return this.httpClient.delete<void>(`${this.constantesRest.getApiURL()}gamers/${correo}`);
  }

  public editarGamer(gamer: GamerRegistro): Observable<void> {
    return this.httpClient.put<void>(`${this.constantesRest.getApiURL()}gamers`, gamer);
  }

  public obtenerImagen(correo: string): Observable<Blob> {
    return this.httpClient.get(`${this.constantesRest.getApiURL()}gamers/imagenes/${correo}`, {
      responseType: 'blob'
    });
  }
}