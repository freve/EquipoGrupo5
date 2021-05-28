package com.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.entities.Categoria;
import com.web.entities.Equipo;
import com.web.repository.services.CategoriaService;
import com.web.repository.services.EquipoService;
import com.web.repository.services.IUploadFileService;

@RestController
@RequestMapping("/equipo")
public class EquipoController {

	@Autowired
	private EquipoService equipoService;

	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private IUploadFileService uploadService;

	@GetMapping("")
	public ResponseEntity<?> getEquipos() {
		try {
			List<Equipo> equipos = (List<Equipo>) equipoService.findAll();
			return new ResponseEntity<List<Equipo>>(equipos, HttpStatus.OK);
		} catch (DataAccessException d) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", d.getMostSpecificCause().getMessage());

			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/save/{idCategoria}")
	public ResponseEntity<?> guardar(@PathVariable int idCategoria, @RequestParam("imagen") MultipartFile escudo,
			@ModelAttribute Equipo equipo) {

		Categoria c = categoriaService.findById(idCategoria);

		if (c == null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mensaje", "El equipo no existe en la bd");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		}

		String uniqueFilename = null;
		if (!escudo.isEmpty()) {
			try {
				System.out.println("ENTRO ACA");
				uniqueFilename = uploadService.copy(escudo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
 
		try {
			equipo.setCategoria(c);
			equipo.setEscudo(uniqueFilename);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("equipo", equipoService.save(equipo));
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.CREATED);

		} catch (DataAccessException | InternalError e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mensaje", "hubo un error inesperado!");
			map.put("error", e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{idEquipo}")
	public ResponseEntity<?> listar(@PathVariable int idEquipo) {

		Map<String, Object> mapa = new HashMap<String, Object>();
		try {
			Equipo e = equipoService.findById(idEquipo);
			if(e==null) {
				mapa.put("mensaje", "Equipo no existe");
				return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.NOT_FOUND);
			}
			mapa.put("equipo", e);
			return new ResponseEntity<Map<String, Object>>(mapa, HttpStatus.OK);
		} catch (DataAccessException | InternalError e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mensaje", "Equipo no encontrado!");
			map.put("error", e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		}

	}
	
	@DeleteMapping("/{idEquipo}")
	public ResponseEntity<?> eliminar(@PathVariable int idEquipo) {

		try {
			Equipo e = equipoService.findById(idEquipo);
			equipoService.delete(idEquipo);
			Map<String, Object> map = new HashMap<String, Object>();
			uploadService.delete(e.getEscudo());
			map.put("mensaje", "Equipo eliminado!");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (DataAccessException | InternalError e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mensaje", "Equipo no pudo ser eliminado!");
			map.put("error", e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		}
	}

}
