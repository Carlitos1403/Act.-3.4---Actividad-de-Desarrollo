package org.iesvegademijas.tienda_informatica.dao;

import java.util.List;
import java.util.Optional;

import org.iesvegademijas.tienda_informatica.modelo.Cliente;

public interface ClienteDAO {

	List<Cliente> findAll();
	Optional<Cliente> findById(int id);
	void create(Cliente cliente);
	void update(Cliente cliente);
	void delete(int id);
}