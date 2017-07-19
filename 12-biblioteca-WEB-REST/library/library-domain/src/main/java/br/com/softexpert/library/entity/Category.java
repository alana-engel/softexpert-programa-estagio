package br.com.softexpert.library.entity;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Category implements Serializable{
	private static final long serialVersionUID = -9098599194068402992L;
	@Id
	@GeneratedValue
	private int sequentialCode;
	private String description;

	public Category(){

	}
	public int getSequentialCode() {
		return sequentialCode;
	}
	public void setSequentialCode(int sequentialCode) {
		this.sequentialCode = sequentialCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String toString() {
		String c="";
		c=c+"Código da Categoria: "+getSequentialCode()
		+"\n"+"Descrição da Categoria: "+getDescription();
		return c;
	}
}
