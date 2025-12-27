import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { GamerRegistro } from "../../models/usuarios/usuario-gamer/gamer-registro";
import { ConstantesRest } from "../../shared/restapi/constantes-rest";
import { SaldoTarjeta } from "../../models/usuarios/tarjeta/saldo-tarjeta";
import { RecargoTarjeta } from "../../models/usuarios/tarjeta/recargo-tarjeta";

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


  public verSaltoTarjeta(correo: string): Observable<SaldoTarjeta>{
    return this.httpClient.get<SaldoTarjeta>(`${this.constantesRest.getApiURL()}gamers/saldo/${correo}`);
  }


  public recargarTarjeta(recargo: RecargoTarjeta): Observable<void>{
    return this.httpClient.put<void>(this.constantesRest.API_URL + 'gamers/tarjeta', recargo);
  }
}