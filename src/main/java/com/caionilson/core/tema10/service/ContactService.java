package com.caionilson.core.tema10.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.caionilson.core.tema10.ConnectionFactory;
import com.caionilson.core.tema10.DAO.ContactDAO;
import com.caionilson.core.tema10.exception.NotebookException;
import com.caionilson.core.tema10.model.Contact;

public class ContactService {
	private Connection connection = new ConnectionFactory().getConnection();
	private ContactDAO contactDAO = new ContactDAO();

	public ContactService(ContactDAO contactDAO) {
		this.contactDAO = contactDAO;
	}

	public ContactService() {
	}

	public ContactDAO getContactService() {
		return contactDAO;
	}

	public void testConnection() throws SQLException {
		System.out.println("Conexao aberta");
		connection.close();
	}

	public void addContact(String name, String lastName, String phoneNumber, String email) {

		if (name.isEmpty())
			throw new NotebookException("O nome deve ser preenchido");
		if (lastName.isEmpty())
			throw new NotebookException("O sobrenome deve ser preenchido");
		if (phoneNumber.isEmpty())
			throw new NotebookException("O telefone deve ser preenchido");
		if (email.isEmpty())
			throw new NotebookException("O email deve ser preenchido");

		contactDAO.add(new Contact(name, lastName, phoneNumber, email));
	}

	public List<Contact> contactListId() {
		return contactDAO.getListById();
	}

	public List<Contact> contactListName() {
		return contactDAO.getListByName();
	}

	public void remove(int id) {
		contactDAO.remove(id);
	}

	public List<Contact> searchId(int id) {
		return contactDAO.searchById(id);
	}

	public List<Contact> searchName(String name) {
		return contactDAO.searchByName(name);
	}
}
