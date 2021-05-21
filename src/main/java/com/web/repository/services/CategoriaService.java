package com.web.repository.services;

import java.util.List;

import com.web.entities.Categoria;

public interface CategoriaService {

	public List<Categoria> findAll();
	
	public Categoria save(Categoria categoria);
	
	public Categoria findById(int idCategoria);
}
