package br.com.softexpert.library.operations.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.softexpert.library.entity.Author;
import br.com.softexpert.library.exception.RecordException;
import br.com.softexpert.library.interfaces.Operations;

public class AuthorDao implements Operations<Author>{
	static final String SQL_CREATE = "insert into tbl_author (name,birthday,nationality) values (?,?,?)";
	static final String SQL_DELETE = "delete from tbl_author where sequentialCode=?";
	static final String SQL_UPDATE = "update tbl_author set name=?, birthday=?, nationality=? where sequentialCode=?";
	static final String SQL_SEARCH = "select * from tbl_author where name=?";
	
	public AuthorDao() {
	
	}

	@Override
	public boolean create(Author author) throws Exception {	
		try (Connection connection = new ConnectionFactory().getConnection();
			PreparedStatement stmt = connection.prepareStatement(SQL_CREATE);){
			stmt.setString(1,author.getName());
			if(author.getBirthday() == null){
				stmt.setDate(2,null);	
			}else{
				stmt.setDate(2, new Date(author.getBirthdayInTime()));	
			}
			stmt.setString(3,author.getNationality());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
	}
	
	@Override
	public boolean delete(String author) {
		Author a = null;
		try {
			a = search(author);
		} catch (Exception e1) {
			return false;
		}
		try (Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection
				.prepareStatement(SQL_DELETE);){
			stmt.setInt(1, a.getSequentialCode());
			stmt.execute();
		} catch (SQLException e) {
			return false;
		} 
		return true;
	}

	@Override
	public void update(Author author) throws Exception {
		if(author.getName().isEmpty()){
			throw new RecordException("Não foi possível alterar o autor. Preencha o campo Nome.");
		}
		try (Connection connection = new ConnectionFactory().getConnection();
			PreparedStatement stmt = connection
				.prepareStatement(SQL_UPDATE);){
			stmt.setString(1,author.getName());
			if(author.getBirthday() == null){
				stmt.setDate(2,null);	
			}else{
				stmt.setDate(2, new Date(author.getBirthdayInTime()));	
			}
			stmt.setString(3,author.getNationality());
			stmt.setInt(4, author.getSequentialCode());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Author search(String name) throws Exception {
		boolean found = false;
		try (Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.
				prepareStatement(SQL_SEARCH);){
			stmt.setString(1, name);
			Author a = new Author();
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				a.setSequentialCode(rs.getInt("sequentialCode"));
				a.setName(rs.getString("name"));
				a.setBirthday(rs.getDate("birthday"));
				a.setNationality(rs.getString("nationality"));
				found = true;
			}
			if(found== false) 
				throw new RecordException("Não foi possível encontrar o autor.");
			return a;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
	}
}
