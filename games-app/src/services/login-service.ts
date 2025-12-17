import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ConstantesRest } from "../shared/restapi/constantes-rest";
import { UsuarioSesion } from "../models/login/UsuarioSesion";
import { UsuarioLogin } from "../models/login/UsuarioLogin";

@Injectable({
  providedIn: 'root'
})


export class LoginServicio{
    constantesRest =  new ConstantesRest();

    constructor(private httpCliente: HttpClient){}

  
    public loguearUsuario(usuarioLogin: UsuarioLogin):  Observable<UsuarioSesion>{
      return this.httpCliente.post<UsuarioSesion>(this.constantesRest.getApiURL() + 'login',usuarioLogin);
    }

}

