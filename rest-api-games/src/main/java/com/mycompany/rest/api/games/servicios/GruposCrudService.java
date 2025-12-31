/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.servicios;

import com.mycompany.rest.api.games.db.GruposDB;
import com.mycompany.rest.api.games.exceptions.DatosInvalidosException;
import com.mycompany.rest.api.games.exceptions.IdentidadRepetidaException;
import com.mycompany.rest.api.games.exceptions.NoEncontradoException;
import com.mycompany.rest.api.games.modelos.Entidad;
import com.mycompany.rest.api.games.modelos.grupos.Grupo;
import com.mycompany.rest.api.games.modelos.grupos.GrupoCompleto;
import com.mycompany.rest.api.games.modelos.grupos.participantes.ParticipanteGrupoRequest;
import com.mycompany.rest.api.games.modelos.grupos.participantes.ParticipanteGrupoResponse;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author edu
 */
public class GruposCrudService extends CrudService {

    private GruposDB gruposDB = new GruposDB();

    @Override
    public void crearEntidad(Entidad entidad) throws IdentidadRepetidaException, DatosInvalidosException, SQLException {
        Grupo grupo = (Grupo) entidad;
        if (!grupo.valido()) {
            throw new DatosInvalidosException("Los datos del grupo son inválidos o exceden el límite de caracteres.");
        }
        if (gruposDB.existeEntidad(grupo.getNombre())) {
            throw new IdentidadRepetidaException("Ya existe un grupo registrado con el nombre: " + grupo.getNombre());
        }
        gruposDB.crearEntidad(grupo);
    }

    @Override
    public void editarEntidad(Entidad entidad) throws IdentidadRepetidaException, DatosInvalidosException, NoEncontradoException, SQLException {
        GrupoCompleto grupo = (GrupoCompleto) entidad;

        if (!grupo.valido()) {
            throw new DatosInvalidosException("Datos inválidos: Verifique el nombre o que los integrantes no superen 6.");
        }
        if (gruposDB.existeEntidad(grupo.getNombre())) {
            throw new IdentidadRepetidaException("el grupo con nombre " + grupo.getNombre() + " ya existe");
        }
        if (gruposDB.buscarGrupo(String.valueOf(grupo.getId())) == null) {
            throw new NoEncontradoException();
        }

        gruposDB.editarEntidad(grupo);
    }

    @Override
    public void eliminarEntidad(String parametro) throws DatosInvalidosException, NoEncontradoException, SQLException {
        try {
            if (gruposDB.buscarGrupo(parametro) == null) {
                throw new NoEncontradoException();
            }
            gruposDB.eliminarEntidad(parametro);

        } catch (NumberFormatException e) {
            throw new DatosInvalidosException(" datos invalidos o campos muy grandes");
        }
    }

    public GrupoCompleto obtenerPorId(String id) throws SQLException, NoEncontradoException {
        GrupoCompleto grupo = gruposDB.buscarGrupo(id);
        if (grupo == null) {
            throw new NoEncontradoException();
        }
        return grupo;
    }

    public List<GrupoCompleto> obtenerGruposPorAdmin(String correo) throws SQLException {

        return gruposDB.buscarGruposPorAdmin(correo);
    }

    public void añadirParticipante(ParticipanteGrupoRequest req) throws SQLException, DatosInvalidosException, IdentidadRepetidaException, NoEncontradoException {
        if (req == null || !req.valido()) {
            throw new DatosInvalidosException("Datos del participante inválidos.");
        }

        if (!gruposDB.existeGamer(req.getCorreo())) {
            throw new NoEncontradoException();
        }
        if (gruposDB.existeParticipante(req.getCorreo(), req.getIdGrupo())) {
            throw new IdentidadRepetidaException("El usuario ya es integrante de este grupo.");
        }

        GrupoCompleto grupo = gruposDB.buscarGrupo(String.valueOf(req.getIdGrupo()));
        if (grupo.getNumeroIntegrantes() >= 6) {
            throw new DatosInvalidosException("El grupo ya alcanzó el límite máximo de 6 integrantes.");
        }
        gruposDB.insertarParticipante(req);
        gruposDB.sumarIntegrante(req.getIdGrupo());
    }

    public List<ParticipanteGrupoResponse> obtenerTodosLosParticipantes(int idGrupo) throws SQLException {
        return gruposDB.obtenerParticipantes(idGrupo);
    }

    public void eliminarParticipante(String correo, int idGrupo) throws SQLException, NoEncontradoException {
        if (!gruposDB.existeParticipante(correo, idGrupo)) {
            throw new NoEncontradoException();
        }

        gruposDB.eliminarParticipante(correo, idGrupo);
    }
}
