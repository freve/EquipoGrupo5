package com.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.entities.Categoria;
import com.web.repository.services.CategoriaService;

@Controller
@CrossOrigin
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("")
	public ResponseEntity<?> listar(){
		try {
			List<Categoria> categoria = (List<Categoria>) categoriaService.findAll();
			return new ResponseEntity<List<Categoria>>(categoria,HttpStatus.OK);
		} catch (DataAccessException d) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("error", d.getMostSpecificCause().getMessage());		
			return new ResponseEntity<Map<String,Object>> (map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> guardar(@RequestBody Categoria categoria){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(categoria == null) {
				map.put("error", "error al guardar la categoria");
				return new ResponseEntity<Map<String, Object>>(map,HttpStatus.BAD_REQUEST);
			}
			System.out.println("categoria: " +categoria.getNombre());
			map.put("categoria", categoriaService.save(categoria));
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.CREATED);
			
		} catch (DataAccessException | InternalError e) {
			map.put("mensaje", "hubo un error inesperado!");
			map.put("error", e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{idCategoria}")
	public ResponseEntity<?> listarCategoria(@PathVariable int idCategoria){
		Map<String, Object> map= new HashMap<String, Object>();
		try {
			Categoria c = categoriaService.findById(idCategoria);
			if(c == null) {
				map.put("error", "categoria inexistente!");
				return new ResponseEntity<Map<String, Object>>(map,HttpStatus.BAD_REQUEST);
			}
			map.put("categoria", c);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (DataAccessException | InternalError e) {
			map.put("mensaje", "Equipo no encontrado!");
			map.put("error", e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{idCategoria}")
	public ResponseEntity<?> eliminar(@PathVariable int idCategoria){
		Map<String, Object> map= new HashMap<String, Object>();
		try {
			Categoria c = categoriaService.findById(idCategoria);
			if(c == null) {
				map.put("error", "categoria inexistente!");
				return new ResponseEntity<Map<String, Object>>(map,HttpStatus.BAD_REQUEST);
			}
			categoriaService.delete(idCategoria);
			map.put("mensaje", "Equipo eliminado!");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}  catch (DataAccessException | InternalError e) {
			map.put("mensaje", "Equipo no pudo ser eliminado!");
			map.put("error", e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		}
	}
}
