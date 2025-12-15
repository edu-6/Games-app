/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.resources;

import com.mycompany.rest.api.games.dtos.gamers.NuevoGamerRequest;
import com.mycompany.rest.api.games.exceptions.DatosInvalidosException;
import com.mycompany.rest.api.games.exceptions.IdentidadRepetidaException;
import com.mycompany.rest.api.games.servicios.clase.GamersCrudService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author edu
 */
@Path("gamers")
public class GamersResource {
    
    @Context
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearNuevoGamer(NuevoGamerRequest gamerRequest) {
        GamersCrudService crudService = new GamersCrudService();
        try {
            crudService.crearGamer(gamerRequest);

            return Response.status(Response.Status.CREATED).build();
        } catch (DatosInvalidosException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (IdentidadRepetidaException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
    
}
