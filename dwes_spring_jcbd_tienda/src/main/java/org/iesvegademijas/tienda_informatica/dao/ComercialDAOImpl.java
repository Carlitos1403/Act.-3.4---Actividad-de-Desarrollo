package org.iesvegademijas.tienda_informatica.dao;

import java.util.List;
import java.util.Optional;

import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ComercialDAOImpl implements ComercialDAO {

	private final JdbcTemplate jdbcTemplate;

	public ComercialDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Comercial> findAll() {
		return jdbcTemplate.query("SELECT id, nombre, apellido1, apellido2, `comisión` FROM comercial ORDER BY id",
				(rs, rowNum) -> new Comercial(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido1"),
						rs.getString("apellido2"), rs.getDouble("comisión")));
	}

	@Override
	public Optional<Comercial> findById(int id) {
		List<Comercial> comerciales = jdbcTemplate.query(
				"SELECT id, nombre, apellido1, apellido2, `comisión` FROM comercial WHERE id = ?",
				(rs, rowNum) -> new Comercial(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido1"),
						rs.getString("apellido2"), rs.getDouble("comisión")), id);
		return comerciales.stream().findFirst();
	}

	@Override
	public void create(Comercial comercial) {
		jdbcTemplate.update("INSERT INTO comercial (nombre, apellido1, apellido2, `comisión`) VALUES (?, ?, ?, ?)",
				comercial.getNombre(), comercial.getApellido1(), comercial.getApellido2(), comercial.getComision());
	}

	@Override
	public void update(Comercial comercial) {
		jdbcTemplate.update("UPDATE comercial SET nombre = ?, apellido1 = ?, apellido2 = ?, `comisión` = ? WHERE id = ?",
				comercial.getNombre(), comercial.getApellido1(), comercial.getApellido2(), comercial.getComision(),
				comercial.getId());
	}

	@Override
	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM comercial WHERE id = ?", id);
	}
}