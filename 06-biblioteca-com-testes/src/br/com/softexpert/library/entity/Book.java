
package br.com.softexpert.library.entity;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
public class Book  implements Comparable<Book>, Serializable{
	private static final long serialVersionUID = 1L;
	private int sequentialCode;
	private String title;
	private String summary;
	private int barcode;
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
	public int getBarcode() {
		return barcode;
	}
	public void setBarcode(int barcode) {
		this.barcode = barcode;
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
		Book e = (Book) o; return this.title.compareToIgnoreCase(e.getTitle());
	}
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String acquisition=sdf.format(getAcquisition());
		String print="";
		if(sdf.format(getAcquisition()).equals("01/01/0001"))
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
