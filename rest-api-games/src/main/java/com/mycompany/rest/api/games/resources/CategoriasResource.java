/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.resources;
import com.mycompany.rest.api.games.exceptions.DatosInvalidosException;
import com.mycompany.rest.api.games.exceptions.IdentidadRepetidaException;
import com.mycompany.rest.api.games.exceptions.NoEncontradoException;
import com.mycompany.rest.api.games.modelos.categorias.Categoria;
import com.mycompany.rest.api.games.modelos.categorias.EdicionCategoria;
import com.mycompany.rest.api.games.servicios.CategoriasCrudService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edu
 */
@Path("categorias")
public class CategoriasResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearCategoria(Categoria categoria) {
        CategoriasCrudService crudService = new CategoriasCrudService();
        try {
            crudService.crearCategoria(categoria);

            return Response.status(Response.Status.CREATED).build();

        } catch (DatosInvalidosException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();

        } catch (IdentidadRepetidaException e) {
            return Response
                    .status(Response.Status.CONFLICT).entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editarCategoria(EdicionCategoria edicion) {
        CategoriasCrudService crudService = new CategoriasCrudService();
        try {
            crudService.editarCategoria(edicion);

            return Response.status(Response.Status.CREATED).build();

        } catch (DatosInvalidosException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();

        } catch (IdentidadRepetidaException e) {
            return Response
                    .status(Response.Status.CONFLICT).entity(new ErrorResponse(e.getMessage())).build();
        } catch (NoEncontradoException ex) {
             return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse(ex.getMessage())).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCategorias() {
        CategoriasCrudService crudService = new CategoriasCrudService();
        ArrayList<Categoria> categorias = crudService.obtenerCategorias();
        return Response.ok(categorias).build();

    }

    @GET
    @Path("{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCategoria(@PathParam("nombre") String nombre) {
        System.out.println(nombre);
        CategoriasCrudService crudService = new CategoriasCrudService();

        try {
           Categoria categoria =  crudService.buscarCategoria(nombre);
            return Response.ok(categoria).build();
        } catch (DatosInvalidosException |NoEncontradoException  ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse(ex.getMessage())).build();
        } 
    }

}
