/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rest.api.games.servicios.clase;

import com.mycompany.rest.api.games.db.usuarios.GamersDB;
import com.mycompany.rest.api.games.dtos.gamers.NuevoGamerRequest;
import com.mycompany.rest.api.games.exceptions.DatosInvalidosException;
import com.mycompany.rest.api.games.exceptions.IdentidadRepetidaException;
import com.mycompany.rest.api.games.modelos.gamers.AvatarGamer;
import com.mycompany.rest.api.games.modelos.gamers.Gamer;

/**
 *
 * @author edu
 */
public class GamersCrudService {
    
    public Gamer crearGamer(NuevoGamerRequest nuevoGamerRequest) throws DatosInvalidosException, IdentidadRepetidaException
             {
        GamersDB gamersDb = new GamersDB();
        
        Gamer gamer = extraerGamer(nuevoGamerRequest);
      
        
        if(gamersDb.existeGamerConCorreo(gamer.getCorreo())){
            throw new  IdentidadRepetidaException(
                    String.format("El correo %s ya está asociado a una cuenta", gamer.getCorreo()));
        }
        
        if(gamersDb.existeGamerConNickname(gamer.getNickname())){
            throw new  IdentidadRepetidaException(
                    String.format("El nickname %s ya está en uso", gamer.getNickname()));
        }
        
        gamersDb.crearNuevoGamer(gamer);
        
        return gamer;
    }

    private Gamer extraerGamer(NuevoGamerRequest request) throws DatosInvalidosException {
        try {
            Gamer gamer = new Gamer(
                request.getNombre(),
                request.getNombrePais(),
                request.getCorreo(),
                request.getNickname(),
                request.getContrasena(),
                request.getTelefono(),
                request.getFechaNacimiento()
            );
            
            if (!gamer.valido()) {
                throw new DatosInvalidosException("Error en los datos enviados");
            }
            
            return gamer;
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new DatosInvalidosException("Error en los datos enviados");
        }
    }
    
    public void agregarImagenGamer(AvatarGamer avatarGamer){
        GamersDB db = new GamersDB();
        try {
            db.agregarImagenGamer(avatarGamer);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
}
