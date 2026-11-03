package org.iesvegademijas.tienda_informatica;

import java.util.List;
import java.util.Optional;

import org.iesvegademijas.tienda_informatica.controlador.FabricanteController;
import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.servicio.ClienteService;
import org.iesvegademijas.tienda_informatica.servicio.ComercialService;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {
		org.iesvegademijas.tienda_informatica.controlador.ClienteController.class,
		org.iesvegademijas.tienda_informatica.controlador.ComercialController.class,
		FabricanteController.class
})
class ClienteComercialControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClienteService clienteService;

	@MockBean
	private ComercialService comercialService;

	@Test
	void raizRedirigeAlListadoDeClientes() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().is3xxRedirection())
				.andExpect(header().string("Location", "/clientes"));
	}

	@Test
	void rutasDeFabricantesDevuelvenNoEncontrado() throws Exception {
		mockMvc.perform(get("/fabricantes"))
				.andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResponse().getContentAsString()
						.contains("La base de ventas no incluye fabricantes")));
		mockMvc.perform(get("/fabricantes/editar/1"))
				.andExpect(status().isNotFound());
	}

	@Test
	void renderizaPaginasDeClientes() throws Exception {
		Cliente cliente = new Cliente(1, "Ana", "García", "López", "Málaga");
		when(clienteService.listAll()).thenReturn(List.of(cliente));
		when(clienteService.findById(1)).thenReturn(Optional.of(cliente));

		mockMvc.perform(get("/clientes"))
				.andExpect(status().isOk())
				.andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains("Ana")));
		mockMvc.perform(get("/clientes/crear"))
				.andExpect(status().isOk())
				.andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains("/clientes/crear")));
		mockMvc.perform(get("/clientes/editar/1"))
				.andExpect(status().isOk())
				.andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains("/clientes/editar/1")));
		mockMvc.perform(get("/clientes/1"))
				.andExpect(status().isOk())
				.andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains("Málaga")));
	}

	@Test
	void renderizaPaginasDeComerciales() throws Exception {
		Comercial comercial = new Comercial(1, "Luis", "Pérez", "Ruiz", 0.15);
		when(comercialService.listAll()).thenReturn(List.of(comercial));
		when(comercialService.findById(1)).thenReturn(Optional.of(comercial));

		mockMvc.perform(get("/comerciales"))
				.andExpect(status().isOk())
				.andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains("Luis")));
		mockMvc.perform(get("/comerciales/crear"))
				.andExpect(status().isOk())
				.andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains("/comerciales/crear")));
		mockMvc.perform(get("/comerciales/editar/1"))
				.andExpect(status().isOk())
				.andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains("/comerciales/editar/1")));
		mockMvc.perform(get("/comerciales/1"))
				.andExpect(status().isOk())
				.andExpect(result -> assertTrue(result.getResponse().getContentAsString().contains("0.15")));
	}
}