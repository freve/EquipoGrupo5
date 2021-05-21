package com.web.repository.services;

import java.util.List;

import com.web.entities.Equipo;

public interface EquipoService {

	public List<Equipo> findAll();	
	public Equipo save(Equipo equipo);
	public Equipo findById(int id);
	public String delete(int id);
}
