import rsrc.JsonObject;
import rsrc.JsonArray;
import rsrc.DoubleLinkedList;
import java.util.Scanner;
public class Main{
    static String itemID;
    public static void main(String[] holi){
	ItemList itemList = new ItemList();
	try{
	    itemList.read("src/rsrc/items.txt");//leyendo lista de items
	}catch(Exception e){
	    System.out.println("no se encontró el archivo de ID's\n");
	}
	WordEngine wEngine = new WordEngine(itemList);
	
	HttpManager man = new HttpManager();
	String req = "";
	String m = "Martlock";
	String mp = "Martlock%20Portal";
	String t = "Thetford";
	String tp = "Thetford%20Portal";
	String f = "Fort%20Sterling";
	String fp = "Fort%20Sterling%20Portal";
	String l = "Lymhurst";
	String lp = "Lymhurst%20Portal";
	String b = "Bridgewatch";
	String bp = "Bridgewatch%20Portal";
	String c = "Caerleon";
	char v = ',';	
	String cities = ""+m+v+t+v+f+v+b+v+l+v+c;
	itemID = "T3_MOUNT_HORSE";
	int x = 1;
	int err = 1;
	Scanner intRd = new Scanner(System.in);
	Scanner strRd = new Scanner(System.in);
	do{
	    System.out.println("*** menú ***");
	    System.out.println("1 - buscar objeto");
	    System.out.println("2 - detalles del objeto");
	    System.out.println("3 - buscar ID del objeto");
	    System.out.println("0 - salir");
	    do{
		try{
		    x = intRd.nextInt();
		    err=0;
		}catch(Exception ex){
		    System.out.println("parametro inválido");
		    err=1;
		    intRd.nextLine();
		}
	    }while(err==1);    
	    
	    if(x==1){//buscar objeto (precio minimo)
		System.out.println("ID del objeto:");
		itemID = strRd.nextLine();
		
		System.out.println("calidad:");
		int qual = 0;
		do{
		    try{
			qual = intRd.nextInt();
			err=0;
		    }catch(Exception ex){
			System.out.println("parametro inválido");
			err=1;
			intRd.nextLine();
		    }
		}while(err==1);   
		
		String url = "https://www.albion-online-data.com/api/v2/stats/prices/"+itemID+"?locations="+cities+"&qualities="+qual;		    
		System.out.println(url+"\n");
		try{
		    req = man.getRequest(""+url+cities);
		    getMinValue(req);
		}catch(Exception e){
		    System.out.println(e+"\n");
		}	        
	    }else if(x==2){//detalles del objeto
		if(req.equals("")){//si aun no hay una solicitud de objeto
		    System.out.println("aun no buscas un item :c\n");
		}else{
		    System.out.println("índice del objeto:");		    
		    do{
			try{
			    int indice = intRd.nextInt();
			    getDetails(indice,req);
			    err=0;
			}catch(Exception ex){
			    System.out.println("parametro inválido");
			    err=1;
			    intRd.nextLine();
			}
		    }while(err==1); 
		}
	    }else if(x==3){		
		String item = "";
		System.out.println("buscar: ");
		item = strRd.nextLine();
		try{
		    wEngine.search(item);
		}catch(Exception e){
		    System.out.println("ocurrió un error xc : "+e);
		}
		System.out.println("presiona Enter");
		strRd.nextLine();
		
	    }else if(x==0){//salir
		System.out.println("adios :c");
	    }else{//número invalido
		System.out.println("número inválido");
	    }
	    
	}while(x!=0);
    }
    
    /**
     * metodo para regresar un objeto json dado un JsonArray
     * @param i - indice del objeto en el JsonArray
     * @param jsA - JsonArray en String
     */
    public static void getDetails(int i, String jsA){
	JsonArray report = new JsonArray(jsA);
	JsonObject obj = report.get(i);
	System.out.println("*** Detalles del Objeto ***");
	System.out.println("\n"+obj+"\n");
	System.out.println("Enter para continuar");
	Scanner rd = new Scanner(System.in);
	rd.nextLine();
    }

    /**
     * metodo que dado un reporte json en String calcula el valor minimo
     * @param req - reporte json
     */
    public static void getMinValue(String req){
	JsonArray request = new JsonArray(req);
	int i = 0;
	double precio=0;
	String ciudad = "";
	DoubleLinkedList list = new DoubleLinkedList();
	DoubleLinkedList list1 = new DoubleLinkedList();
	while(request.get(i)!=null){
	    ciudad = request.get(i).getString("city");
	    precio = request.get(i).getDouble("sell_price_min");	    
	    list.add(list.size(),ciudad);
	    list1.add(list1.size(),precio);
	    i++;
	}

	/*obteniendo el precio más alto*/
	double min = 0;
	double max = 0;
	for(int k=0; k<list1.size(); k++){
	    if((double)list1.get(k)!=0){
		min =(double) list1.get(k);
		max =(double) list1.get(k);
	    }	    
	}

	String form = "";
	String form2 = "";
	String form3 = "";
	double promedio = 0;
	int noObjects = 0;
	System.out.println("BUSCANDO: "+itemID+"\n");
	System.out.println("*** TODOS LOS PRECIOS ***\n");
	System.out.println(list.get(0)+": "+list1.get(0));
	form = ""+list.get(0)+": "+list1.get(0);
	for(int j=0; j<list.size(); j++){
	    System.out.println(j+" - "+list.get(j)+": "+list1.get(j));
	    double minaux = (double) list1.get(j);
	    double maxaux = (double) list1.get(j);
	    //System.out.println(minaux);
	    if(minaux!=0){
		if(minaux<=min){
		    min = minaux;
		    form = "";
		    form = "índice :"+j+": "+list.get(j)+": "+list1.get(j);
		    //System.out.println(form);
		}
		if(maxaux>=max){
		    max = maxaux;
		    form2 = "indice :"+j+": "+list.get(j)+": "+list1.get(j);
		}
		if(j>=(int)(list.size()/2)){
		    if(minaux<(min*2)){
			promedio +=(double) list1.get(j);
			noObjects++;
		    }
		}
	    }
	}
	promedio =(double) promedio/noObjects;
	System.out.println("\n*** PROMEDIO ***\n");
	System.out.println(""+promedio+"\n");
	System.out.println("\n*** PRECIO MÁS ALTO ***\n");
	System.out.println(""+form2+"\n");
	System.out.println("\n*** PRECIO MÁS BAJO ***\n");
	System.out.println(""+form+"\n");
	System.out.println("Enter para continuar");
	Scanner rd = new Scanner(System.in);
	rd.nextLine();
    }
    
}
