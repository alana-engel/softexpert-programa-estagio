package br.com.softexpert.library.operations.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.softexpert.library.entity.Category;
import br.com.softexpert.library.exception.RecordException;
import br.com.softexpert.library.interfaces.Categories;

public class CategoryDao implements Categories{
	private Connection connection;

	public CategoryDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	@Override
	public boolean create(Category category) throws Exception {
	
		String sql = "insert into tbl_category " +
				"(description)" +
				" values (?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1,category.getDescription());
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public boolean delete(String category) {
		Category c = null;
		try {
			c = search(category);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {

			PreparedStatement stmt = connection
					.prepareStatement("delete from tbl_category where sequentialCode=?");
			stmt.setInt(1, c.getSequentialCode());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
		return true;
	}

	@Override
	public void update(Category c, Category nc) throws Exception { 
		if(nc.getDescription().isEmpty())
			throw new RecordException("Não foi possível alterar a Categoria. Verifique os campos preenchidos.");
		
		String sql = "update tbl_category set description=? where sequentialCode=?";

		try {
			PreparedStatement stmt = connection
					.prepareStatement(sql);
			stmt.setString(1, nc.getDescription());
			stmt.setInt(2, c.getSequentialCode());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public Category search(String name) throws Exception {
		boolean found = false;
		
		try {
			PreparedStatement stmt = this.connection.
					prepareStatement("select * from tbl_category where description=?");
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			Category c = new Category();
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
			rs.close();
			stmt.close();
			if(found== false) 
				throw new RecordException("Não foi possível encontrar a categoria.");
			return c;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
