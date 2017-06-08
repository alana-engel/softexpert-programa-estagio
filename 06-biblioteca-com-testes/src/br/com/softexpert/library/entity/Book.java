
package br.com.softexpert.library.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.softexpert.library.library.DateOperations;

import java.util.Date;
public class Book  implements Comparable<Book>, Serializable{
	private static final long serialVersionUID = 1L;
	private int sequentialCode;
	private String title;
	private String summary;
	private String barcode;
	private int pages;
	private String location;
	private Date acquisition;
	private List<Author> authorsList = new ArrayList<>();
	private Category category = new Category();

	public Book(){
		category.setSequentialCode(0);
		category.setDescription(null);
	}
	public int getSequentialCode() {
		return sequentialCode;
	}
	public void setSequencialCode(int sequentialCode) {
		this.sequentialCode = sequentialCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String string) {
		this.barcode = string;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getAcquisition() {
		return acquisition;
	}
	public void setAcquisition(Date date) {
		this.acquisition = date;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category Category) {
		this.category = Category;
	}
	public List<Author> getAuthorsList() {
		return authorsList;
	}
	public void setAuthorsList(List<Author> authorsList) {
		this.authorsList = authorsList;
	}
	@Override
	public int compareTo(Book o) {
		return this.title.compareToIgnoreCase(o.getTitle());
	}
	@Override
	public String toString() {
		DateOperations op = new DateOperations();
		String acquisition = op.convertDateToString(getAcquisition());
		String print="";
		if(acquisition.equals("01/01/0001"))
			acquisition="";
		print=print+"Código do livro: "+getSequentialCode()+"\n"+"Titulo do livro: "+getTitle()
		+"\n"+"Resumo do livro: "+getSummary()+"\n"+"Código de Barras: "+getBarcode()
		+"\n"+"Quantidade de páginas: "+getPages()+"\n"+"Local: "+getLocation()
		+"\n"+"Data de Aquisição: "+acquisition
		+"\n"+infoDeAutores()+"\n"+getCategory()+
		"\n\n\n";
		return print;
	}
	private String infoDeAutores() {
		StringBuilder builder = new StringBuilder();
		for (Author author : authorsList) {
			builder.append("\n"+author);
		}
		return builder.toString();
	}
}
