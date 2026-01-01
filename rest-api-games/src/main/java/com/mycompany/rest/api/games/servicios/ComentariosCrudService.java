/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.servicios;

import com.mycompany.rest.api.games.db.ComentariosDB;
import com.mycompany.rest.api.games.dtos.comentarios.ComentarioRequest;
import com.mycompany.rest.api.games.dtos.comentarios.ComentarioResponse;
import com.mycompany.rest.api.games.dtos.comentarios.SubcomentarioRequest;
import com.mycompany.rest.api.games.dtos.comentarios.SubcomentarioResponse;
import com.mycompany.rest.api.games.modelos.comentarios.Comentario;
import com.mycompany.rest.api.games.modelos.comentarios.ComentarioVista;
import com.mycompany.rest.api.games.modelos.comentarios.SubComentarioVista;
import com.mycompany.rest.api.games.modelos.comentarios.Subcomentario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edu
 */
public class ComentariosCrudService {
    public void registrarComentario(ComentarioRequest request) throws SQLException, IllegalArgumentException {
        Comentario nuevoComentario = new Comentario(request); 
        
        if (!nuevoComentario.valido()) {
            throw new IllegalArgumentException("La información del comentario no es válida o excede los 500 caracteres.");
        }
        
        
        ComentariosDB db = new ComentariosDB();
        int id_juego = db.obtenerIdJuegoPorNombre(nuevoComentario.getNombreJuego());
        nuevoComentario.setIdJuego(id_juego);
        db.registrarComentario(nuevoComentario);
    }

    public List<ComentarioResponse> obtenerComentariosPorJuego(String nombre) throws SQLException {
        ComentariosDB db = new ComentariosDB();
        int idJuego = db.obtenerIdJuegoPorNombre(nombre);
        List<ComentarioVista> comentariosVista = db.obtenerComentariosVista(idJuego);
        List<ComentarioResponse> respuesta = new ArrayList<>();
        for (ComentarioVista vista : comentariosVista) {
            respuesta.add(new ComentarioResponse(vista));
        }
        return respuesta;
    }
    
    
    
    public void registrarSubcomentario(SubcomentarioRequest request) throws SQLException, IllegalArgumentException {
        Subcomentario nuevoSub = new Subcomentario(request);
        if (!nuevoSub.valido()) {
            throw new IllegalArgumentException("La información de la respuesta no es válida o excede los 500 caracteres.");
        }
        
        ComentariosDB db = new ComentariosDB();
        db.registrarSubcomentario(nuevoSub);
    }

    public List<SubcomentarioResponse> obtenerRespuestasPorComentario(int idComentarioPadre) throws SQLException {
       ComentariosDB db = new ComentariosDB();
        List<SubComentarioVista> subcomentariosVista = db.obtenerSubcomentariosVista(idComentarioPadre);
        List<SubcomentarioResponse> respuesta = new ArrayList<>();
        for (SubComentarioVista vista : subcomentariosVista) {
            respuesta.add(new SubcomentarioResponse(vista));
        }
        return respuesta;
    }
    
    public boolean verificarPermisoComentarios(String nombreJuego) throws SQLException {
        ComentariosDB db = new ComentariosDB();
        return db.juegoPermiteComentarios(nombreJuego);
    }
    
}
