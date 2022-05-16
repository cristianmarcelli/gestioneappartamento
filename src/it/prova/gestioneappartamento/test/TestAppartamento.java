package it.prova.gestioneappartamento.test;

import it.prova.gestioneappartamento.dao.AppartamentoDAO;
import it.prova.gestioneappartamento.model.Appartamento;

public class TestAppartamento {
	public static void main(String[] args) {

		testList();

	}

	public static void testList() {
		System.out.println("testList inizio......");

		AppartamentoDAO appartamentoDAOInstance = new AppartamentoDAO();

		System.out
				.println("Nella tabella appartamento ci sono " + appartamentoDAOInstance.list().size() + " elementi.");

		System.out.println("testList fine......");
	}

}