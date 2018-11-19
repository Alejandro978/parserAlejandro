package parser.persona;

import java.util.ArrayList;

public class Libro {
private String titulo;
private String editor;
private int paginas; 
private int anyos;
//EN ESTE CASO SI PONEMOS AUTOR NO NOS SACARÁ NADA , PUES LOS DATOS QUE QUEREMOS SACAR ESTAN EN NOMBRE
//AL TENER MÁS DE 1 NOMBRE NECESITAREMOS UN ARRAY LIST , EN CASO DE SER COMPUESTO (NOMBRE APELLIDO) PODRÍAMOS CREARNOS 
//UNA CLASE NUEVA PARA SACAR LA INFO
//SE INICIALIZA PARA QUE NO APUNTE A NULL
private ArrayList <String> autores = new ArrayList<String>();

//Creamos un constructor de libros vacio para utilizarlo en el parser
public Libro() {}
//GETTERS Y SETTERS
public String getTitulo() {
	return titulo;
}
public void setTitulo(String titulo) {
	this.titulo = titulo;
}
public String getEditor() {
	return editor;
}
public void setEditor(String editor) {
	this.editor = editor;
}
public int getPaginas() {
	return paginas;
}
public void setPaginas(int paginas) {
	this.paginas = paginas;
}
public int getAnyos() {
	return anyos;
}
public void setAnyos(int anyos) {
	this.anyos = anyos;
}
public ArrayList<String> getAutores() {
	return autores;
}
public void setAutores(ArrayList<String> autores) {
	this.autores = autores;
} 

public void print() {
	System.out.println("Titulo: " +titulo);
	System.out.println("Editor: " + editor);
	System.out.println("Año: " + anyos);
	System.out.println("Paginas: "+ paginas);
	for(int i = 0; i < autores.size();i++) {
	System.out.println("Nombre: "+ autores.get(i));
	}
}
}
