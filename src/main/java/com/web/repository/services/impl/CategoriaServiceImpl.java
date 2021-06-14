package com.web.repository.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.entities.Categoria;
import com.web.repository.dao.ICategoriaDao;
import com.web.repository.services.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private ICategoriaDao categoriaDao;
	
	@Override
	public List<Categoria> findAll() {
		try {			
			List<Categoria> categoria = (List<Categoria>) categoriaDao.findAll();
			return categoria;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Categoria save(Categoria categoria) {
		try {
			return categoriaDao.save(categoria);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Categoria findById(int idCategoria) {
		try {
			return categoriaDao.findById(idCategoria).orElse(null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

	@Override
	public String delete(int idCategoria) {
		try {
			categoriaDao.deleteById(idCategoria);
			return "Categoria eliminada";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
