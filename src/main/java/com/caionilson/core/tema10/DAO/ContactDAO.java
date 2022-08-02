package com.caionilson.core.tema10.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.caionilson.core.tema10.ConnectionFactory;
import com.caionilson.core.tema10.model.Contact;

public class ContactDAO {
	
	private ConnectionFactory connectionFactory;
	private Connection connection;

	public ContactDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void add(Contact contact) {
		String sql = "INSERT INTO contact (name, lastName, phoneNumber, email) values (?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, contact.getName());
			stmt.setString(2, contact.getLastName());
			stmt.setString(3, contact.getPhoneNumber());
			stmt.setString(4, contact.getEmail());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			connectionFactory.closeConnection(connection);
		}
	}

	public List<Contact> getListById() {
		try {
			List<Contact> contacts = new ArrayList<Contact>();
			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM contact ORDER BY id ASC");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Contact contact = new Contact();
				contact.setId(rs.getInt("id"));
				contact.setName(rs.getString("name"));
				contact.setLastName(rs.getString("lastName"));
				contact.setPhoneNumber(rs.getString("phoneNumber"));
				contact.setEmail(rs.getString("email"));

				contacts.add(contact);
			}
			rs.close();
			stmt.close();
			return contacts;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			connectionFactory.closeConnection(connection);
		}
	}

	public List<Contact> getListByName() {
		try {
			List<Contact> contacts = new ArrayList<Contact>();
			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM contact ORDER BY name ASC");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Contact contact = new Contact();
				contact.setId(rs.getInt("id"));
				contact.setName(rs.getString("name"));
				contact.setLastName(rs.getString("lastName"));
				contact.setPhoneNumber(rs.getString("phoneNumber"));
				contact.setEmail(rs.getString("email"));

				contacts.add(contact);
			}
			rs.close();
			stmt.close();
			return contacts;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			connectionFactory.closeConnection(connection);
		}
	}

	public void remove(int id) {
		try {
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM contact WHERE id=?");
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			connectionFactory.closeConnection(connection);
		}
	}

	public List<Contact> searchById(int id) {
		List<Contact> contacts = new ArrayList<>();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM contact WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String lastName = rs.getString("lastName");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");

				contacts.add(new Contact(name, lastName, phoneNumber, email));
			}
			return contacts;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			connectionFactory.closeConnection(connection);
		}
	}

	public List<Contact> searchByName(String userName) {
		List<Contact> contacts = new ArrayList<>();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM contact WHERE name = ?");
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String lastName = rs.getString("lastName");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");

				contacts.add(new Contact(name, lastName, phoneNumber, email));
			}
			return contacts;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			connectionFactory.closeConnection(connection);
		}
	}

}
