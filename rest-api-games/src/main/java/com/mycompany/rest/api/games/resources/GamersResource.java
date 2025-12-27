/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.resources;

import com.mycompany.rest.api.games.db.usuarios.RecargoTarjeta;
import com.mycompany.rest.api.games.db.usuarios.SaldoTarjeta;
import com.mycompany.rest.api.games.dtos.gamers.NuevoGamerRequest;
import com.mycompany.rest.api.games.exceptions.DatosInvalidosException;
import com.mycompany.rest.api.games.exceptions.IdentidadRepetidaException;
import com.mycompany.rest.api.games.modelos.gamers.AvatarGamer;
import com.mycompany.rest.api.games.servicios.GamersCrudService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;
import java.sql.SQLException;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author edu
 */
@Path("gamers")
public class GamersResource {

    @Context
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearNuevoGamer(NuevoGamerRequest gamerRequest) {
        GamersCrudService crudService = new GamersCrudService();
        try {
            crudService.crearGamer(gamerRequest);
            return Response.status(Response.Status.CREATED).build();

        } catch(SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(e.getMessage())).build();
        } 
        catch (DatosInvalidosException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();

        } catch (IdentidadRepetidaException e) {
            return Response
                    .status(Response.Status.CONFLICT).entity(new ErrorResponse(e.getMessage())).build();
        }
    }
    
    
    @PUT
    @Path("tarjeta")
    public Response recargarTarjeta(RecargoTarjeta recargo) {
        GamersCrudService crudService = new GamersCrudService();
        try {
            crudService.recargarTarjeta(recargo);
            return Response.ok().build(); // se recarga la tarjeta
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(e.getMessage())).build();
        } 
    }
    
    
    
    @GET
    @Path("saldo/{correo}")
    public Response verSaldoTarjeta(@PathParam("correo") String correo) {
        GamersCrudService crudService = new GamersCrudService();
        try {
            double saldo = crudService.obtenerSaldo(correo);
            return Response.ok(new SaldoTarjeta(saldo)).build(); // se devuelve el nuevo saldo
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(e.getMessage())).build();
        } 
    }

    @PUT
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response agregarFotoGamer(@FormDataParam("correo") String correo, @FormDataParam("imagen") InputStream imagenCargada) {

        GamersCrudService crudService = new GamersCrudService();
        crudService.agregarImagenGamer(new AvatarGamer(correo, imagenCargada));
        return Response.status(Response.Status.CREATED).build();

    }

}
