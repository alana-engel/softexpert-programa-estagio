package br.com.softexpert.library.operations.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.exception.RecordException;
import br.com.softexpert.library.interfaces.Categories;

public class CategoryDao implements Categories{
	static final String SQL_CREATE = "insert into tbl_category (description) values (?)";
	static final String SQL_DELETE = "delete from tbl_category where sequentialCode=?";
	static final String SQL_UPDATE = "update tbl_category set description=? where sequentialCode=?";
	static final String SQL_SEARCH = "select * from tbl_category where sequentialCode=?";
	static final String SQL_SEARCH_BY_DESCRIPTION = "select * from tbl_category where description=?";
	public CategoryDao() {
		
	}

	@Override
	public boolean create(Category category) throws Exception {
		try (Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.prepareStatement(SQL_CREATE);) {
			stmt.setString(1,category.getDescription());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public boolean delete(int id) {
		try (Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection
				.prepareStatement(SQL_DELETE);){
			stmt.setInt(1, id);
			stmt.execute();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	@Override
	public void update(Category c) throws Exception { 
		if(c.getDescription().isEmpty())
			throw new RecordException("Não foi possível alterar a Categoria. Verifique os campos preenchidos.");
		try (Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection
				.prepareStatement(SQL_UPDATE);){
			stmt.setString(1, c.getDescription());
			stmt.setInt(2, c.getSequentialCode());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public Category search(int id) throws Exception {
		boolean found = false;
		
		try (Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.
				prepareStatement(SQL_SEARCH);
				){
			stmt.setInt(1, id);
			Category c = new Category();
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				c.setSequentialCode(rs.getInt("sequentialCode"));
				c.setDescription(rs.getString("description"));
				if(c.getSequentialCode()==id){
					found = true;
					break;
				}else{
					c.setDescription(null);
				}
			}
			if(found== false) 
				throw new RecordException("Não foi possível encontrar a categoria.");
			return c;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Category searchByDescription(String name) throws Exception {
		boolean found = false;
		try (Connection connection = new ConnectionFactory().getConnection();
				PreparedStatement stmt = connection.
				prepareStatement(SQL_SEARCH_BY_DESCRIPTION);
				){
			stmt.setString(1, name);
			Category c = new Category();
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				c.setSequentialCode(rs.getInt("sequentialCode"));
				c.setDescription(rs.getString("description"));
				if(c.getDescription().equals(name)){
					found = true;
					break;
				}else{
					c.setDescription(null);
				}
			}
			if(found== false) 
				throw new RecordException("Não foi possível encontrar a categoria.");
			return c;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
