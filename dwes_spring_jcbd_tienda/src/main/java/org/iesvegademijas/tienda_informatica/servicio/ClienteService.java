package org.iesvegademijas.tienda_informatica.servicio;

import java.util.List;
import java.util.Optional;

import org.iesvegademijas.tienda_informatica.dao.ClienteDAO;
import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	private final ClienteDAO clienteDAO;

	public ClienteService(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public List<Cliente> listAll() { return clienteDAO.findAll(); }
	public Optional<Cliente> findById(int id) { return clienteDAO.findById(id); }
	public void create(Cliente cliente) { clienteDAO.create(cliente); }
	public void update(Cliente cliente) { clienteDAO.update(cliente); }
	public void delete(int id) { clienteDAO.delete(id); }
}