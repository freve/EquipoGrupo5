package com.web.repository.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.entities.Equipo;
import com.web.repository.dao.IEquipoDao;
import com.web.repository.services.EquipoService;

@Service
public class EquipoServiceImpl implements EquipoService {

	@Autowired
	private IEquipoDao equipoDao;
	
	@Override
	public List<Equipo> findAll() {
		try {
			 List<Equipo> equipos = (List<Equipo>) equipoDao.findAll();
			 return equipos;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Equipo save(Equipo equipo) {
		try {
			return equipoDao.save(equipo);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Equipo findById(int id) {
		try {
			return equipoDao.findById(id).orElse(null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public String delete(int id) {
		try {
			equipoDao.deleteById(id);
			return "Equipo eliminado";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
