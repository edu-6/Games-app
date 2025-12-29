import { HttpClient } from "@angular/common/http";
import { ConstantesRest } from "../shared/restapi/constantes-rest";
import { Observable } from "rxjs/internal/Observable";
import { Injectable } from "@angular/core";
import { AdminEmpresa } from "../models/admin-empresa/admin-empresa";
import { AdminEmpresaSimple } from "../models/admin-empresa/admin-empresa-simple";
import { BusquedaAdmins } from "../models/admin-empresa/busqueda-admins";
import { CompraJuego } from "../models/compras/compra-juego";
import { CompraExistencia } from "../models/compras/CompraExistencia";
import { CompraExistenciaResponse } from "../models/compras/existencia-response";


@Injectable({
  providedIn: 'root'
})
export class ComprasService {
  constantesRest = new ConstantesRest();

  constructor(private httpCliente: HttpClient) { }


  public realizarCompra(compra: CompraJuego): Observable<void> {
    return this.httpCliente.post<void>(this.constantesRest.getApiURL() + 'compras', compra);
  }

  public buscarCompraAnterior(anterior: CompraExistencia): Observable<CompraExistenciaResponse>{
    return this.httpCliente.post<CompraExistenciaResponse>(this.constantesRest.getApiURL() + 'compras/existencia', anterior);
  }

}