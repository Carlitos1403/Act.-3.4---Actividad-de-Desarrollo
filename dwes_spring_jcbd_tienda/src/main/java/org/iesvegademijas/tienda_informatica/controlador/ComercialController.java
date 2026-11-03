package org.iesvegademijas.tienda_informatica.controlador;

import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.servicio.ComercialService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class ComercialController {

	private final ComercialService comercialService;

	public ComercialController(ComercialService comercialService) {
		this.comercialService = comercialService;
	}

	@GetMapping("/comerciales")
	public String listar(Model model) {
		model.addAttribute("comerciales", comercialService.listAll());
		return "comerciales";
	}

	@GetMapping("/comerciales/{id}")
	public String detalle(@PathVariable int id, Model model) {
		model.addAttribute("comercial", obtenerComercial(id));
		return "detalle-comercial";
	}

	@GetMapping("/comerciales/crear")
	public String crear(Model model) {
		model.addAttribute("comercial", new Comercial());
		model.addAttribute("edicion", false);
		return "formulario-comercial";
	}

	@PostMapping("/comerciales/crear")
	public String guardar(@ModelAttribute Comercial comercial) {
		comercialService.create(comercial);
		return "redirect:/comerciales";
	}

	@GetMapping("/comerciales/editar/{id}")
	public String editar(@PathVariable int id, Model model) {
		model.addAttribute("comercial", obtenerComercial(id));
		model.addAttribute("edicion", true);
		return "formulario-comercial";
	}

	@PostMapping("/comerciales/editar/{id}")
	public String actualizar(@PathVariable int id, @ModelAttribute Comercial comercial) {
		comercial.setId(id);
		comercialService.update(comercial);
		return "redirect:/comerciales";
	}

	@PostMapping("/comerciales/borrar/{id}")
	public String borrar(@PathVariable int id) {
		comercialService.delete(id);
		return "redirect:/comerciales";
	}

	private Comercial obtenerComercial(int id) {
		return comercialService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comercial no encontrado"));
	}
}