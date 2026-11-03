package org.iesvegademijas.tienda_informatica.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class FabricanteController {

	@RequestMapping({"/fabricantes", "/fabricantes/**"})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String noDisponibleEnVentas() {
		return "fabricantes-no-disponibles";
	}
}
//Para utilizar una base de url, pero se visualiza mejor con toda la ruta en los métodos

