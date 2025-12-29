/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.resources;

import com.mycompany.rest.api.games.dtos.compras.CompraExistenciaResponse;
import com.mycompany.rest.api.games.dtos.compras.CompraRequest;
import com.mycompany.rest.api.games.exceptions.IdentidadRepetidaException;
import com.mycompany.rest.api.games.exceptions.NoEncontradoException;
import com.mycompany.rest.api.games.exceptions.SaldoInsuficienteException;
import com.mycompany.rest.api.games.modelos.compras.CompraExistencia;
import com.mycompany.rest.api.games.servicios.ComprasCrudService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edu
 */
@Path("compras")
public class ComprasResource {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarVenta(CompraRequest request){
        ComprasCrudService crudService = new ComprasCrudService();
        try {
            crudService.registrarCompra(request);
            return Response.status(Response.Status.CREATED).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();
        } catch (SaldoInsuficienteException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();
        } catch (NoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse(e.getMessage())).build();
        } catch (IdentidadRepetidaException e) {
            return Response.status(Response.Status.CONFLICT).entity(new ErrorResponse(e.getMessage())).build();
        }      
    }
    
    @POST
    @Path("existencia")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response existeVenta(CompraExistencia request){
        ComprasCrudService crudService = new ComprasCrudService();
        try {
            boolean existe = crudService.existeCompra(request);
            CompraExistenciaResponse respuesta = new CompraExistenciaResponse(existe);
            return Response.ok(respuesta).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();
        }    
    }
    
    
}
