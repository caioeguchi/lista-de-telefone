package com.caionilson.core.tema10;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.caionilson.core.tema10.exception.NotebookException;
import com.caionilson.core.tema10.model.Contact;
import com.caionilson.core.tema10.service.ContactService;

public class Notebook {
	private static ContactService contactService = new ContactService();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {

		contactService.testConnection();

		int option = 0;
		do {
			try {
				System.out.println("\n\n--------Lista de telefone do Caio--------\n");
				System.out.println("	1 - Adicionar contato");
				System.out.println("	2 - Listar contatos por ID");
				System.out.println("	3 - Listar contatos por Nome");
				System.out.println("	4 - Remover contato por ID");
				System.out.println("	5 - Procurar contato por ID");
				System.out.println("	6 - Procurar contato por nome");
				System.out.println("	7 - Finalizar programa");

				option = Integer.parseInt(sc.nextLine());
				switch (option) {
				case 1:
					System.out.println("Digite o nome do contato");
					String name = sc.nextLine();
					System.out.println("Digite o sobrenome do contato");
					String lastName = sc.nextLine();
					System.out.println("Digite o telefone do contato");
					String phoneNumber = sc.nextLine();
					System.out.println("Digite o email do contato");
					String email = sc.nextLine();

					try {
						contactService.addContact(name, lastName, phoneNumber, email);
						System.out.println("Contato adicionado");
					} catch (NotebookException e) {
						System.out.println(e.getMessage());
					}
					break;

				case 2:
					System.out.println("Lista de contatos ordenados pelo ID");
					for (Contact contact : contactService.contactListId()) {
						System.out.println("----------------------");
						System.out.println("ID: " + contact.getId());
						System.out.println("Nome: " + contact.getName() + " " + contact.getLastName());
						System.out.println("Telefone: " + contact.getPhoneNumber());
						System.out.println("Email: " + contact.getEmail());
					}
					break;

				case 3:
					System.out.println("Lista de contatos ordenados em ordem alfabetica");
					for (Contact contact : contactService.contactListName()) {
						System.out.println("----------------------");
						System.out.println("ID: " + contact.getId());
						System.out.println("Nome: " + contact.getName() + " " + contact.getLastName());
						System.out.println("Telefone: " + contact.getPhoneNumber());
						System.out.println("Email: " + contact.getEmail());
					}
					break;

				case 4:
					System.out.println("Digite o ID do contato que sera removido");
					int id = sc.nextInt();

					try {
						contactService.remove(id);
					} catch (NotebookException e) {
						System.out.println(e.getMessage());
					}
					break;

				case 5:
					System.out.println("Digite o ID do contato a ser buscado");
					int searchId = sc.nextInt();
					contactService.searchId(searchId);
					List<Contact> findContactId = contactService.searchId(searchId);
					if (findContactId.isEmpty()) {
						System.out.println("ID nao encontrado");
					} else {
						for (Contact contact : findContactId) {
							System.out.println("ID: " + contact.getId());
							System.out.println("Nome: " + contact.getName() + " " + contact.getLastName());
							System.out.println("Telefone: " + contact.getPhoneNumber());
							System.out.println("Email: " + contact.getEmail());
						}
					}
					break;

				case 6:
					System.out.println("Digite o primeiro nome do contato a ser buscado");
					String searchName = sc.nextLine();
					contactService.searchName(searchName);
					List<Contact> findContactName = contactService.searchName(searchName);
					if (findContactName.isEmpty()) {
						System.out.println("Nome não encontrado");
					} else {
						for (Contact contact : findContactName) {
							System.out.println("ID: " + contact.getId());
							System.out.println("Nome: " + contact.getName() + " " + contact.getLastName());
							System.out.println("Telefone: " + contact.getPhoneNumber());
							System.out.println("Email: " + contact.getEmail());
						}
					}
					break;

				case 7:
					System.out.println("Programa finalizado");
					break;

				default:
					System.out.println("Opcao invalida");
					break;
				}
			} catch (Exception e) {
			}
		} while (option != 7);
	}
}
