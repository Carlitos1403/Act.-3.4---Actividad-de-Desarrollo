package org.iesvegademijas.tienda_informatica.dao;

import java.util.List;
import java.util.Optional;

import org.iesvegademijas.tienda_informatica.modelo.Comercial;

public interface ComercialDAO {

	List<Comercial> findAll();
	Optional<Comercial> findById(int id);
	void create(Comercial comercial);
	void update(Comercial comercial);
	void delete(int id);
}