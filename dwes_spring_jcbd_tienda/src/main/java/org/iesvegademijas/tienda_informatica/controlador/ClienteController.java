package org.iesvegademijas.tienda_informatica.controlador;

import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.servicio.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class ClienteController {

	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping("/")
	public String inicio() {
		return "redirect:/clientes";
	}

	@GetMapping("/clientes")
	public String listar(Model model) {
		model.addAttribute("clientes", clienteService.listAll());
		return "clientes";
	}

	@GetMapping("/clientes/{id}")
	public String detalle(@PathVariable int id, Model model) {
		model.addAttribute("cliente", obtenerCliente(id));
		return "detalle-cliente";
	}

	@GetMapping("/clientes/crear")
	public String crear(Model model) {
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("edicion", false);
		return "formulario-cliente";
	}

	@PostMapping("/clientes/crear")
	public String guardar(@ModelAttribute Cliente cliente) {
		clienteService.create(cliente);
		return "redirect:/clientes";
	}

	@GetMapping("/clientes/editar/{id}")
	public String editar(@PathVariable int id, Model model) {
		model.addAttribute("cliente", obtenerCliente(id));
		model.addAttribute("edicion", true);
		return "formulario-cliente";
	}

	@PostMapping("/clientes/editar/{id}")
	public String actualizar(@PathVariable int id, @ModelAttribute Cliente cliente) {
		cliente.setId(id);
		clienteService.update(cliente);
		return "redirect:/clientes";
	}

	@PostMapping("/clientes/borrar/{id}")
	public String borrar(@PathVariable int id) {
		clienteService.delete(id);
		return "redirect:/clientes";
	}

	private Cliente obtenerCliente(int id) {
		return clienteService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
	}
}