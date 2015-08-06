package com.mem.app.dao;

import java.util.List;

import com.mem.app.model.Atividade;

public interface AtividadeDAO {
	
    public void saveOrUpdate(Atividade atividade);
    
    public void delete(int atividadeId);
     
    public Atividade get(int atividadeId);
     
    public List<Atividade> list();
}
