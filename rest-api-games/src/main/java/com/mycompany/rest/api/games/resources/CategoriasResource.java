/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.resources;
import jakarta.ws.rs.Path;
/**
 *
 * @author edu
 */
@Path ("categorias")
public class CategoriasResource {
    
    @Context
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearNuevoGamer(NuevoGamerRequest gamerRequest) {
        GamersCrudService crudService = new GamersCrudService();
        try {
        crudService.crearGamer(gamerRequest);

        return Response.status(Response.Status.CREATED).build();

    } catch (DatosInvalidosException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();

    } catch (IdentidadRepetidaException e) {
        return Response
                .status(Response.Status.CONFLICT).entity(new ErrorResponse(e.getMessage())).build();
    }
    }
    
}
