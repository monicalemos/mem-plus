package com.mem.app.services;

import java.util.List;

import com.mem.app.model.Atividade;

public interface AtividadeService {
	
    public void saveOrUpdate(Atividade atividade);
    
    public void delete(int atividadeId);
     
    public Atividade get(int atividadeId);
     
    public List<Atividade> list();
}
