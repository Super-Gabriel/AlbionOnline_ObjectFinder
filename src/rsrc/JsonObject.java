/**
 * clase que representa un objeto json
 * @author - G4BR13L t(>.<t)
 * @version - 1.0 | 24/03/23
 */
package rsrc;
import rsrc.JsonArray;
public class JsonObject{
    private String jsonStr;
    private String jsonFormat;
    
    /*metodo constructor por omision*/
    public JsonObject(){
	this.jsonStr = "vacío";
	this.jsonFormat = "vacío : vacío";
    }

    /**
     * metodo constructor
     * @param str - objeto json en String
     */
    public JsonObject(String str){
	this.jsonStr = str;
	this.jsonFormat = getJsonFormat(str);
    }

    /**
     * metodo para convertir un string json en formato legible
     * @param str - el json String
     * @return - un formato Json legible
     */
    private String getJsonFormat(String str){
	String form = "";	
	for(int i=1; i<str.length()-1; i++){
	    if(str.charAt(i)=='['){
		String aux="";
		while(str.charAt(i)!=']'){
		    aux += str.charAt(i);
		    i++;
		}
		aux += str.charAt(i);
		i++;
		form += aux;
	    }
	    if(str.charAt(i)==','){
		form += str.charAt(i);
		form += "\n";
	    }else if(str.charAt(i)!='"'){
		form += str.charAt(i);
	    }
	}
	form += ',';
	return form;
    }

    /**
     * metodo para obtener un valor String dada una etiqueta en el obj
     * @param tag - etiqueta del valor que solicitamos
     * @return - valor solicitado
     */
    public String getString(String tag){
	String form = "";
	String pivote = "";
	boolean coincidences = false;
	boolean isArray = false; //si el valor buscado contiene un array json
	int cont = 0;
	
	for(int i=0; i<this.jsonFormat.length(); i++){
	    if(pivote.equals(tag)){//cuando la palabra es igual al pivote
		coincidences = true;
		i++;
		cont = i;
		if(this.jsonFormat.charAt(i)=='['){//si es un array
		    isArray = true;
		}
		break;
	    }if(this.jsonFormat.charAt(i)==','){//cuando termina un atributo
		pivote = "";
		i++;
	    }else{//sumando los caracteres
		pivote += this.jsonFormat.charAt(i);
	    }
	}
	
	if(coincidences){//si se encontró la tag
	    if(isArray){//si el resultado es un arrayjson
		while(this.jsonFormat.charAt(cont)!=']'){
		    form += this.jsonFormat.charAt(cont);
		    cont++;
		}
		form += this.jsonFormat.charAt(cont);
		return form;
		//System.out.println("JJJ"+form+"JJJ");
	    }
	    while(this.jsonFormat.charAt(cont)!=','){		
		form += this.jsonFormat.charAt(cont);		
		cont++;
	    }
	}else{//si no se encontró la tag
	    return null;
	}
	
	return form;
    }

    /**
     * metodo para obtener un valor Double dada una etiqueta en el obj
     * @param tag - etiqueta del valor que solicitamos
     * @return - valor solicitado
     */
    public double getDouble(String str){
	String strValue = getString(str);
	double result = 0;
	try{
	    result = Double.parseDouble(strValue);
	}catch(Exception e){
	    System.out.println("el resultado no es un numero");
	}
	return result;
    }
    
    /**
     * metodo para obtener un valor ArrayString  dada una etiqueta en el obj
     * @param tag - etiqueta del valor que solicitamos
     * @return - valor solicitado
     */
    public JsonArray getJsonArray(String tag){
	JsonArray result = null;
	try{
	    result = new JsonArray(getString(tag));
	}catch(Exception e){
	    System.out.println("etiqueta inválida");
	}
	return result;
    }
    
    @Override
    public String toString(){
	return this.jsonFormat;
    }
    
}
