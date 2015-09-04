package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Utilizador;

public interface UtilizadorDAO {
	
    int saveOrUpdate(Utilizador utilizador);
    
    void delete(int utilizadorId);
     
    Utilizador get(int utilizadorId);
     
    List<Utilizador> list();
    
    Utilizador getFromEmail(String email);
	
    Utilizador getFromUserName(String user);
    
    Utilizador matchUser(String username, String password) ;
}
