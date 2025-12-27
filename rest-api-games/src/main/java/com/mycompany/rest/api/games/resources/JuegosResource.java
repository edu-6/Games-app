/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.resources;
import com.mycompany.rest.api.games.db.JuegosDB;
import com.mycompany.rest.api.games.dtos.JuegoResponse;
import com.mycompany.rest.api.games.dtos.NuevoJuegoRequest;
import com.mycompany.rest.api.games.exceptions.DatosInvalidosException;
import com.mycompany.rest.api.games.exceptions.IdentidadRepetidaException;
import com.mycompany.rest.api.games.exceptions.NoEncontradoException;
import com.mycompany.rest.api.games.modelos.AvatarEntidad;
import com.mycompany.rest.api.games.modelos.juegos.BusquedaJuego;
import com.mycompany.rest.api.games.modelos.juegos.Juego;
import com.mycompany.rest.api.games.servicios.JuegosCrudService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author edu
 */
@Path("juegos")
public class JuegosResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearNuevoJuego(NuevoJuegoRequest nuevo) {
        JuegosCrudService crudService = new JuegosCrudService();
        try {
            crudService.crearEntidad(nuevo);

            return Response.status(Response.Status.CREATED).build();

        } catch (DatosInvalidosException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();

        } catch (IdentidadRepetidaException e) {
            return Response
                    .status(Response.Status.CONFLICT).entity(new ErrorResponse(e.getMessage())).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    @POST
    @Path("/buscar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarJuegos(BusquedaJuego busqueda) {
        try {
            JuegosCrudService crudService = new JuegosCrudService();
            List<JuegoResponse> juegos = crudService.buscarJuegos(busqueda).stream().map(JuegoResponse::new).toList();
            return Response.ok(juegos).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editarJuego(Juego juego) {
        JuegosCrudService crudService = new JuegosCrudService();
        try {

            crudService.editarEntidad(juego);

            return Response.status(Response.Status.OK).build();
        } catch (DatosInvalidosException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(e.getMessage())).build();
        } catch (IdentidadRepetidaException e) {
            return Response.status(Response.Status.CONFLICT).entity(new ErrorResponse(e.getMessage())).build();
        } catch (NoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    @DELETE
    @Path("{correo}")
    public Response eliminarJuego(@PathParam("correo") String id) {
        JuegosCrudService crudService = new JuegosCrudService();
        try {
            crudService.eliminarEntidad(id);
            return Response.status(Response.Status.OK).build();
        } catch (DatosInvalidosException | NoEncontradoException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse(ex.getMessage())).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    @PUT
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response agregarImagen(@FormDataParam("nombre") String id, @FormDataParam("imagen") InputStream imagenCargada) {
        JuegosCrudService crudService = new JuegosCrudService();
        JuegosDB db = new JuegosDB();
        crudService.agregarImagen(new AvatarEntidad(id, imagenCargada), db);
        return Response.status(Response.Status.OK).build();

    }

    /**
     * Para devolver la imagen MediaType.APPLICATION_OCTET_STREAM porque no se
     * sabe el formato
     *
     * @return
     */
    @GET
    @Path("imagenes/{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response recuperarImagen(@PathParam("id") String correo) {
        JuegosCrudService crudService = new JuegosCrudService();

        try {
            StreamingOutput stream = crudService.recuperarImagen(correo, new JuegosDB());

            if (stream == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(stream).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponse(e.getMessage())).build();
        } catch (NoEncontradoException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse(ex.getMessage())).build();
        }
    }

}
