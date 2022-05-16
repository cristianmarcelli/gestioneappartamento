package it.prova.gestioneappartamento.test;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import it.prova.gestioneappartamento.dao.AppartamentoDAO;
import it.prova.gestioneappartamento.model.Appartamento;

public class TestAppartamento {
	public static void main(String[] args) throws ParseException {

		// TEST METODO list()##########################################
		testList();

		// TEST METODO insert()()##########################################
		AppartamentoDAO appartamentoDAOInstance = new AppartamentoDAO();
		testInsert(appartamentoDAOInstance);

	}

	public static void testList() {
		System.out.println("testList inizio......");

		AppartamentoDAO appartamentoDAOInstance = new AppartamentoDAO();

		System.out
				.println("Nella tabella appartamento ci sono " + appartamentoDAOInstance.list().size() + " elementi.");

		System.out.println("testList fine......");
	}

	public static void testInsert(AppartamentoDAO appartamentoDAOInstance) throws ParseException {
		System.out.println("testInsert() inizio......");

		Date dataDaInserire = null;

		try {
			dataDaInserire = new SimpleDateFormat("dd/MM/yyyy").parse("05/10/1999");
		} catch (ParseException exc) {
		}

		int quantiAppartamentiInseriti = appartamentoDAOInstance
				.insert(new Appartamento("torre spaccata", 80, 60000, dataDaInserire));
		if (quantiAppartamentiInseriti < 1)
			throw new RuntimeException("testInserimentoAppartamento : FAILED");

		System.out.println("testInsert() fine......");
	}

}