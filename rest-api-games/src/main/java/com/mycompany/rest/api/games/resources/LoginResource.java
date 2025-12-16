/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.resources;

import com.mycompany.rest.api.games.exceptions.CredencialesIncorrectasException;
import com.mycompany.rest.api.games.exceptions.DatosInvalidosException;
import com.mycompany.rest.api.games.exceptions.IdentidadRepetidaException;
import com.mycompany.rest.api.games.modelos.usuarios.UsuarioLogin;
import com.mycompany.rest.api.games.modelos.usuarios.UsuarioSesion;
import com.mycompany.rest.api.games.servicios.GamersCrudService;
import com.mycompany.rest.api.games.servicios.LoginService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author edu
 */
@Path("login")
public class LoginResource {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearNuevoGamer(UsuarioLogin gamerRequest) {
        LoginService service = new LoginService();
        try {
             UsuarioSesion usuarioSesion = service.loguearUsuario(gamerRequest);
            return Response.ok(usuarioSesion).build();
        } catch (DatosInvalidosException | CredencialesIncorrectasException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();
            
        }
    }
    
}
