/**
 * clase que representa una lista de juegos utilizando-
 * -DoubleLikedList
 * @author - G4BR13L t(>.<t)
 * @version - 1.0 | 15/03/03
 */
import rsrc.DoubleLinkedList;
import java.io.*;

public class ItemList<T>{
    private DoubleLinkedList list;

    //metodo constructor por omisión
    public ItemList(){
	this.list = new DoubleLinkedList();
    }

    //metodo constructor
    public ItemList(DoubleLinkedList l){
	this.list = l;
    }

    /**
     * metodo para agregar un item a la lista
     * el precio son los ultimos digitos
     * @param item - nombre del item con su precio al final
     */
    public void add(String item){
	this.list.add(0,item);
    }

    /**
     * metodo para obtener el elemnto i de la lista
     * @param i - indice del elemento
     * @return - el elemento en el indice i
     */    
    public T get(int i){
	T result = null;
	int s = this.list.size();
	if(s!=0){
	    if(i>=0 && i<s){
		result =(T) this.list.get(i);
	    }else{
		System.out.println("indice invalido :c");
	    }
	}else{
	    System.out.println("lista vacía :c");
	}
	return result;
    }
    
    /**
     * metodo size
     * @return - tamaño de la lista
     */    
    public int size(){
	return this.list.size();
    }

    /**
     * metodo para leer un documento dado un directorio
     * agrega elementos del documento a la lista
     * @param dir - directorio del documento
     */
    public void read(String dir) throws IOException{
	BufferedReader bf = new BufferedReader(new FileReader(dir));
	String entrie = "";
	while((entrie=bf.readLine()) != null){
	    this.list.add(list.size(),entrie+"\n");
	}
    }

    /**
     * metodo para mostrar la lista inversa
     */
    public void showInv(){
	int f = list.size()-1;
	for(int i=f; i>=0; i--){
	    System.out.println(i+" - "+this.list.get(i));
	}
    }
    
    @Override
    public String toString(){
	String result = "";
	if(this.list.size()!=0){//si la lista no es vacía
	    int s = this.list.size();
	    for(int i=0; i<s; i++){
		result += i+" - "+this.list.get(i)+"\n";
	    }
	}else{
	    result = "lista vacía :c";
	}
	return result;
    }
    
}
