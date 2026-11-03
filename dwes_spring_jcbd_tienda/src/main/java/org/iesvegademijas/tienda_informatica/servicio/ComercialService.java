package org.iesvegademijas.tienda_informatica.servicio;

import java.util.List;
import java.util.Optional;

import org.iesvegademijas.tienda_informatica.dao.ComercialDAO;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.springframework.stereotype.Service;

@Service
public class ComercialService {

	private final ComercialDAO comercialDAO;

	public ComercialService(ComercialDAO comercialDAO) {
		this.comercialDAO = comercialDAO;
	}

	public List<Comercial> listAll() { return comercialDAO.findAll(); }
	public Optional<Comercial> findById(int id) { return comercialDAO.findById(id); }
	public void create(Comercial comercial) { comercialDAO.create(comercial); }
	public void update(Comercial comercial) { comercialDAO.update(comercial); }
	public void delete(int id) { comercialDAO.delete(id); }
}