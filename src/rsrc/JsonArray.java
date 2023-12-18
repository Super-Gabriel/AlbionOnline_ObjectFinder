/**
 * implementación de la clase JsonArray
 * @author - G4BR13L t(>.<t)
 * @version - 1.0 | 24/03/23
 */
package rsrc;
import rsrc.JsonObject;
import rsrc.DoubleLinkedList;
public class JsonArray{
    DoubleLinkedList list;
    
    /*metodo constructor por omisión*/
    public JsonArray(){
	this.list = new DoubleLinkedList();
    }
    
    /**
     * metodo constructor
     * @param str - String con el formato arrayjson     
     */
    public JsonArray(String str){
	this.list = new DoubleLinkedList();
	String obj = "";
	String aux = "";
	for(int i=1; i<str.length()-1; i++){
	    if(str.charAt(i)=='['){	       
		while(str.charAt(i)!=']'){
		    aux += str.charAt(i);
		    i++;
		}
		aux += str.charAt(i);
		obj += aux;
		//System.out.println("JJJ"+aux+"JJJ");		
		JsonArray j = new JsonArray(aux);		
		//System.out.println(j);
		//try{Thread.sleep(60*1000);}catch(Exception e){}
		aux = "";
	    }
	    if(str.charAt(i)=='}'){
		obj += str.charAt(i);
		//añadiendo cada objeto una ves se cierra una llave
		this.list.add(this.list.size(), new JsonObject(obj));
		obj = "";
		i++;
	    }else{
		if(str.charAt(i)==']'){
		}else{
		    obj += str.charAt(i);
		}

	    }
	}
    }

    /**
     * metodo para obtener el objeto json en el indice i
     * @param i - indice a obtener
     * @return - objeto json en el indice i
     */
    public JsonObject get(int i){
	if(i>=this.list.size()){
	    return null;
	}else if(i<0){
	    return null;
	}
	return (JsonObject) this.list.get(i);
    }

    @Override
    public String toString(){
	String form = "";

	for(int i=0; i<this.list.size(); i++){
	    form += this.list.get(i)+"\n\n";
	}
	
	return form;
    }
    
}
