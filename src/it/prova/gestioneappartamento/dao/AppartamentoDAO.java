package it.prova.gestioneappartamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.prova.gestioneappartamento.connection.*;
import it.prova.gestioneappartamento.model.*;

public class AppartamentoDAO {

	public List<Appartamento> list() {

		Connection c = null;
		ResultSet rs = null;
		Statement s = null;
		Appartamento temp = null;
		List<Appartamento> result = new ArrayList<Appartamento>();

		try {

			c = MyConnection.getConnection();
			s = c.createStatement();

			rs = s.executeQuery("select * from appartamento;");

			while (rs.next()) {
				temp = new Appartamento();
				temp.setQuartiere(rs.getString("quartiere"));
				temp.setMetriQuadrati(rs.getInt("metriquadrati"));
				temp.setId(rs.getLong("id"));
				temp.setPrezzo(rs.getInt("prezzo"));
				temp.setDataCostruzione(rs.getDate("datacostruzione"));
				result.add(temp);

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				rs.close();
				s.close();
				c.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
