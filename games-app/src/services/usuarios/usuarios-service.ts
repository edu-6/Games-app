import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { GamerRegistro } from "../../models/usuarios/usuario-gamer/gamer-registro";
import { ConstantesRest } from "../../shared/restapi/constantes-rest";

@Injectable({
  providedIn: 'root'
})

export class UsuarioServicios{
    constantesRest =  new ConstantesRest();

    constructor(private httpClient: HttpClient) { }

    public crearNuevoGamer(gamer: GamerRegistro): Observable<void> {
    return this.httpClient.post<void>(`${this.constantesRest.getApiURL()}gamers`, gamer);
  }
}