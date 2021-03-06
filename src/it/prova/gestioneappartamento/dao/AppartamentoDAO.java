package it.prova.gestioneappartamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import it.prova.gestioneappartamento.connection.*;
import it.prova.gestioneappartamento.model.*;

public class AppartamentoDAO {

	public List<Appartamento> list() {

		List<Appartamento> result = new ArrayList<Appartamento>();
		Appartamento appartamentoTemp = null;

		try (Connection c = MyConnection.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery("select * from appartamento;")) {

			while (rs.next()) {
				appartamentoTemp = new Appartamento();
				appartamentoTemp.setId(rs.getLong("id"));
				appartamentoTemp.setQuartiere(rs.getString("quartiere"));
				appartamentoTemp.setMetriQuadrati(rs.getInt("metriquadrati"));
				appartamentoTemp.setPrezzo(rs.getInt("prezzo"));
				appartamentoTemp.setDataCostruzione(rs.getDate("datacostruzione"));

				result.add(appartamentoTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;

	}

	public int insert(Appartamento input) {

		if (input == null)
			throw new RuntimeException("Impossibile inserire Appartamento: input mancante!");

		int result = 0;
		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement(
						"INSERT INTO appartamento (quartiere, metriquadrati, prezzo, datacostruzione) VALUES (?, ?, ?, ?);")) {

			ps.setString(1, input.getQuartiere());
			ps.setInt(2, input.getMetriQuadrati());
			ps.setInt(3, input.getPrezzo());
			ps.setDate(4, new java.sql.Date(input.getDataCostruzione().getTime()));

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;

	}

	public int update(Appartamento input) {

		if (input == null || input.getId() < 1)
			throw new RuntimeException("Impossibile modificare appartamento: input mancante!");

		int result = 0;
		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement(
						"UPDATE appartamento SET quartiere=?, metriquadrati=?, prezzo=?, datacostruzione=? where id=?;")) {

			ps.setString(1, input.getQuartiere());
			ps.setInt(2, input.getMetriQuadrati());
			ps.setInt(3, input.getPrezzo());
			ps.setDate(4, new java.sql.Date(input.getDataCostruzione().getTime()));

			ps.setLong(5, input.getId());

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;

	}

	public int delete(Appartamento input) {

		if (input == null)
			throw new RuntimeException("Impossibile eliminare Appartamento: input mancante!");

		int result = 0;
		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("delete from appartamento WHERE id=?;")) {

			ps.setLong(1, input.getId());
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;

	}

	public Appartamento findById(Long idAppartamentoInput) throws SQLException {

		if (idAppartamentoInput == null || idAppartamentoInput < 1)
			throw new RuntimeException("Impossibile trovare appartamento: id mancante!");

		Appartamento result = null;
		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("select * from appartamento i where i.id=?")) {

			ps.setLong(1, idAppartamentoInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Appartamento();
					result.setId(rs.getLong("id"));
					result.setQuartiere(rs.getString("quartiere"));
					result.setMetriQuadrati(rs.getInt("metriquadrati"));
					result.setPrezzo(rs.getInt("prezzo"));
					result.setDataCostruzione(rs.getDate("datacostruzione"));
				} else {
					result = null;
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			return result;
		}

	}

	public List<Appartamento> findByExample(Appartamento example) throws SQLException {

		List<Appartamento> result = new ArrayList<Appartamento>();
		Appartamento temp = null;

		String query = "select * from appartamento a where";

		if (!(example.getQuartiere().equals("")) || example.getQuartiere() != null) {
			query += " and a.quartiere like " + example.getQuartiere() + '%';
		}
		if (example.getPrezzo() > 0) {
			query += " and a.prezzo = ?";
		}
		if (example.getMetriQuadrati() != 0) {
			query += " and a.metriquadrati = " + example.getMetriQuadrati();
		}
		if (example.getDataCostruzione() != null) {
			query += " and a.datacostruzione = " + example.getDataCostruzione();
		}
		query += ";";

		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
//				temp = new Appartamento();
//				temp.setId(rs.getLong("id"));
//				temp.setQuartiere(rs.getString("quartiere"));
//				temp.setMetriQuadrati(rs.getInt("metriquadrati"));
//				temp.setPrezzo(rs.getInt("prezzo"));
//				temp.setDataCostruzione(new java.sql.Date(example.getDataCostruzione().getTime()));

				result.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

}
