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

	private Connection connection;

	public AuthorDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	@Override
	public boolean create(Author author) throws Exception {	
		String sql = "insert into tbl_author " +
				"(name,birthday,nationality)" +
				" values (?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1,author.getName());
			if(author.getBirthday() == null){
				stmt.setDate(2,null);	
			}else{
				stmt.setDate(2, new Date(author.getBirthdayInTime()));	
			}
			stmt.setString(3,author.getNationality());

			stmt.execute();
			stmt.close();
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
			e1.printStackTrace();
		}
		try {

			PreparedStatement stmt = connection
					.prepareStatement("delete from tbl_author where sequentialCode=?");
			stmt.setInt(1, a.getSequentialCode());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
		return true;
	}

	@Override
	public void update(Author author, Author nAuthor) throws Exception {
		if(author.getName().isEmpty()){
			throw new RecordException("Não foi possível alterar o autor. Preencha o campo Nome.");
		}
		String sql = "update tbl_author set name=?, birthday=?, nationality=? where sequentialCode=?";

		try {
			PreparedStatement stmt = connection
					.prepareStatement(sql);
			stmt.setString(1,nAuthor.getName());
			if(author.getBirthday() == null){
				stmt.setDate(2,null);	
			}else{
				stmt.setDate(2, new Date(nAuthor.getBirthdayInTime()));	
			}
			stmt.setString(3,nAuthor.getNationality());
			stmt.setInt(4, author.getSequentialCode());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Author search(String name) throws Exception {
		boolean found = false;
		try {
			PreparedStatement stmt = this.connection.
					prepareStatement("select * from tbl_author where name=?");
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			Author a = new Author();
			while (rs.next()) {
				a.setSequentialCode(rs.getInt("sequentialCode"));
				a.setName(rs.getString("name"));
				a.setBirthday(rs.getDate("birthday"));
				a.setNationality(rs.getString("nationality"));
				found = true;
			}

			rs.close();
			stmt.close();
			if(found== false) 
				throw new RecordException("Não foi possível encontrar o autor.");
			return a;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
