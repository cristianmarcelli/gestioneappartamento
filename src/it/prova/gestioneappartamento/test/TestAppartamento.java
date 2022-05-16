package it.prova.gestioneappartamento.test;

import java.util.Date;
import java.util.List;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import it.prova.gestioneappartamento.dao.AppartamentoDAO;
import it.prova.gestioneappartamento.model.Appartamento;

public class TestAppartamento {
	public static void main(String[] args) throws ParseException, SQLException {

		// TEST METODO list()##########################################
		testList();

		// TEST METODO insert()########################################
		AppartamentoDAO appartamentoDAOInstance = new AppartamentoDAO();
		testInsert(appartamentoDAOInstance);

		// TEST METODO update()########################################
		AppartamentoDAO appartamentoDAOInstance2 = new AppartamentoDAO();
		testUpdate(appartamentoDAOInstance2);

		// TEST METODO delete()########################################
		AppartamentoDAO appartamentoDAOInstance3 = new AppartamentoDAO();
		testDelete(appartamentoDAOInstance3);

		// TEST METODO findById()######################################
		AppartamentoDAO appartamentoDAOInstance4 = new AppartamentoDAO();
		testFindById(appartamentoDAOInstance4);
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

	private static void testUpdate(AppartamentoDAO appartamentoDAOInstance) {
		System.out.println("testUpdateAppartamento inizio.......");

		Appartamento appartamentoDaModificare = appartamentoDAOInstance.list().get(0);
		appartamentoDaModificare.setQuartiere("Parioli");

		appartamentoDAOInstance.update(appartamentoDaModificare);

		System.out.println("testUpdateAppartamento fine.......");
	}

	private static void testDelete(AppartamentoDAO appartamentoDAOInstance) {
		System.out.println(".......testDelete inizio.............");

		Appartamento appartamentoDaEliminare = appartamentoDAOInstance.list().get(0);

		int quantiAppartamentiEliminati = appartamentoDAOInstance.delete(appartamentoDaEliminare);
		if (quantiAppartamentiEliminati < 1)
			throw new RuntimeException("testDeleteNegozio : FAILED");

		System.out.println(".......testDelete fine: PASSED.............");
	}

	private static void testFindById(AppartamentoDAO appartamentoDAOInstance) throws SQLException {

		System.out.println(".......testfindById inizio.............");

		AppartamentoDAO appartamentoDaTrovare = new AppartamentoDAO();
		Appartamento result = appartamentoDaTrovare.findById(2L);

		if (result == null) {
			throw new RuntimeException("........testFindById failed.....");
		}

		System.out.println(result.getQuartiere() + " " + result.getPrezzo() + " " + result.getMetriQuadrati() + " "
				+ result.getDataCostruzione());
		System.out.println(".......testfindById fine.............");
	}

}