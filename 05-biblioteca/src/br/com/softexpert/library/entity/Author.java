package br.com.softexpert.library.entity;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Author implements Serializable{
	private static final long serialVersionUID = 1L;
	private int sequentialCode;
	private String name;
	private Date birthday;
	private String nationality;
	
	public Author(){
		
	}
	public int getSequentialCode() {
		return sequentialCode;
	}
	public void setSequentialCode(int sequentialCode) {
		this.sequentialCode = sequentialCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date date) {
		this.birthday = date;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String birthday=sdf.format(getBirthday());
		if(sdf.format(getBirthday()).equals("01/01/0001"))
			birthday="";
		String author="";
		author=author+"Código do Autor: "+getSequentialCode()
		+"\n"+"Nome do Autor: "+getName()+"\n"
		+"Data de Nascimento: "+birthday
		+"\n"+"Nacionalidade: "+getNationality();
		return author;
	}

}

