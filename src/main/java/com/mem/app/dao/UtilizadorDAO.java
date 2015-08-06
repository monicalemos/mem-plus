package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Utilizador;

public interface UtilizadorDAO {
	
    public void saveOrUpdate(Utilizador utilizador);
    
    public void delete(int utilizadorId);
     
    public Utilizador get(int utilizadorId);
     
    public List<Utilizador> list();
}
