package parser.persona;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {

	private Document dom = null;
	private ArrayList<Libro> libros = null;

	public Parser() {
		libros = new ArrayList<Libro>();
	}

	public void parseFicheroXml(String fichero) {
		// 1� Instanciamos un objeto document builder factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// 2� Para obtener un objeto document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parseamos el XML y obtenemos una representaci�n DOM , en este punto tenemos
			// toda la estructura
			// ahora hay que ir sacando lo necesario:
			dom = db.parse(fichero);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	// FUNCION QUE PARSEA EL DOCUMENTO:
	public void parseDocument() {
	
		// obtenemos el elemento ra�z
		Element docEle = dom.getDocumentElement();

		// obtenemos el nodelist de elementos (creamos una lista de nodos "libros", en
		// este caso nos sacar� 2 nodos)
		NodeList nl = docEle.getElementsByTagName("libro");
		// si al menos hay 1 elemento para recorrer:
		if (nl != null && nl.getLength() > 0) {
			// como puede que tengamos m�s de 1 elemento libro hacemos un for
			for (int i = 0; i < nl.getLength(); i++) {

				// obtenemos un elemento de la lista (libro , primero coger� el item 0 , luego
				// el item 2)
				// nos devolver�a el primer libro , luego el segundo...
				Element elLibro = (Element) nl.item(i);

				// sobre el 1er libro obtenemos datos ,empezamos por el titulo:
//				NodeList nl2 = elLibro.getElementsByTagName("titulo");
//				// si al menos hay 1 elemento
//				if (nl2 != null && nl2.getLength() > 0) {
//					// como solo hay 1 t�tulo nos quedamos simplemente con la posici�n 0:
//					Element elTitulo = (Element) nl2.item(0);
//					// �Qu� tiene el elemento t�tulo? solo un String por lo que:
//					String titulo = elTitulo.getFirstChild().getNodeValue();
//					
//				}
				// ESTO SUSTITUYE EL C�DIGO DE ARRIBA
				String titulo = getTexto(elLibro, "titulo");
				//System.out.println("El t�tulo es " + titulo);

				// Crear�amos otra lista de nodos y sacar�amos editores:
//				NodeList nl3 = elLibro.getElementsByTagName("editor");
//				// si al menos hay 1 elemento
//				if (nl3 != null && nl3.getLength() > 0) {
//					// como solo hay 1 t�tulo nos quedamos simplemente con la posici�n 0:
//					Element elEditor = (Element) nl3.item(0);
//					// �Qu� tiene el elemento editor? solo un String por lo que:
//					String editor = elEditor.getFirstChild().getNodeValue();
//					System.out.println("El Editor es " + editor);
//				}
				// ESTO SUSTITUYE EL C�DIGO DE ARRIBA
				String editor = getTexto(elLibro, "editor");
				//System.out.println("El Editor es " + editor);

				// AQU� TAMBI�N SUSTITU�A....
				String paginas = getTexto(elLibro, "paginas");
			//	System.out.println("P�ginas son " + paginas);

				ArrayList<String> nombres = new ArrayList<String> ();
				// Sacamos los nodos que coincidan con autor
				NodeList nlAutor = elLibro.getElementsByTagName("autor");
				// Mientras sea distinto de nulo
				if (nlAutor != null && nlAutor.getLength() > 0) {
					Element elAutor = (Element) nlAutor.item(0);
					// Este elemento no es terminal ya que dentro tiene m�s nodos
					// Pues se repite el proceso:
					NodeList nl3 = elAutor.getElementsByTagName("nombre");
					if (nl3 != null && nl3.getLength() > 0) {
						for (int x = 0; x < nl3.getLength(); x++) {
							Element elNombreAutores = (Element) nl3.item(x);
							String nombre = elNombreAutores.getFirstChild().getNodeValue();
							nombres.add(nombre);
						}
					}
				}
				//Sobre el elemento libro , buscamos el subelemento titulo y sobre este subelemento el atributo anyo
				String anyo = getAtributo(elLibro,"titulo","anyo");
				
				
				
				
				//Creamos un libro , le ponemos los datos que vamos sacando y lo a�adimos al array list
				Libro l = new Libro();
				l.setTitulo(titulo);
				l.setEditor(editor);
				l.setPaginas(Integer.parseInt(paginas));
				l.setAutores(nombres);
				l.setAnyos(Integer.parseInt(anyo));
				libros.add(l);
			}
		}
	}

	//M�todo para sacar texto que hay en el nodo:
	private String getTexto(Element e, String etiqueta) {

		NodeList nl = e.getElementsByTagName(etiqueta);
		if (nl != null && nl.getLength() > 0) {
			Element subElemento = (Element) nl.item(0);
			String valor = subElemento.getFirstChild().getNodeValue();
			return valor;
		}
		return null;

	}
	//M�todo para sacar el atributo que hay en los nodos:
	private String getAtributo(Element e, String etiqueta,String anyo) {
		NodeList nl = e.getElementsByTagName(etiqueta);
		if (nl != null && nl.getLength() > 0) {
			Element subElemento = (Element) nl.item(0);
			String valor = subElemento.getAttribute("anyo");
			return valor;
		}
		return null;

	}
	public void print() {
		Iterator it = libros.iterator();
		while (it.hasNext()) {
			Libro p = (Libro) it.next();
			p.print();
		}
	}
}
