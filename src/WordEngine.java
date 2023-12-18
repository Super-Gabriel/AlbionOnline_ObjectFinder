/**
 * clase WordEngine
 * @author - G4BR13L t(>.<t)
 * @version - 1.0 | 16/03/03
 */
import rsrc.BinarySearchTree;
import rsrc.DoubleLinkedList;
    
public class WordEngine<T>{
    private T list;
    
    //metodo constructor por omisión
    public WordEngine(){
	this.list = null;
    }
    
    //metodo constructor
    public WordEngine(T li){	
	this.list = li;
    }

    /**
     * metodo para buscar una palabra en la lista
     * @param word - palabra a buscar
     */
    public void search(String word)throws Exception{
	/*cambiar el tipo de lista que uses aquí*/
	ItemList l =(ItemList) this.list;
	DoubleLinkedList items = new DoubleLinkedList();
	DoubleLinkedList coinCList = new DoubleLinkedList();
	
	BinarySearchTree tree = new BinarySearchTree();
	String w = word.toUpperCase();//palabra en mayus
	String thisW = "";//variable para las palabras de la lista
	
	
	if(this.list==null){
	    System.out.println("lista vacía");	    
	}else{
	    int s =(int) l.size();//tamaño de la lista
	    int wS = w.length();//tamaño de la palabra a buscar
	    int coinC = 0;//numero de coincidencias
	    int majorCoinC = 0;
	    int contW = 0;//contador de la palabra


	    for(int i=0; i<s; i++){
		thisW =(String) l.get(i);
		thisW = thisW.toUpperCase();//convirtiendo a mayus
		
		for(int j=0; j<thisW.length(); j++){
		    if(contW>=wS){//si se terminaron las comparaciones
			break;
		    }else{
			char cw = w.charAt(contW);
			char lw = thisW.charAt(j);
			if(cw==lw){
			    coinC++;//coincidencias
			    contW++;
			}
		    }
		}
		//System.out.println("palabra: "+i+" coincidencias: "+coinC);
		//System.out.println("\n");
		contW = 0;
		//int coinCinv = wS-coinC;//coincidencias inversas
		//tree.insert(i+" - "+l.get(i),coinC);
		if(coinC>=majorCoinC){//guardando el numero mayor de coinc
		    majorCoinC = coinC;
		}
		items.add(0,i+" - "+l.get(i));
		coinCList.add(0,coinC);
		coinC = 0;
	    }
	    //mostrando las palabras con mayor conc en la lista
	    showMajorCoinC(items,coinCList,majorCoinC);
	    //System.out.println(tree.inOrderList());
	}
	
    }

    /**
     * metodo para mostrar los items con mayor coincidencia
     * @param items - los items en la lista
     * @param coinCList - la lista con las coincidencias de los items
     * @param majorCoinC - el número de coincidencias máximo
     */
    public void showMajorCoinC(DoubleLinkedList items,DoubleLinkedList coinCList, int majorCoinC){
	//DoubleLinkedList result = new DoubleLinkedList();
	for(int i=0; i<items.size(); i++){
	    if((int)coinCList.get(i)==majorCoinC){
		System.out.println(items.get(i));
		//result.add(0,items.get(i));
	    }
	}
    }
    
    /**
     * metodo para mostrar la lista
     */
    public void showList(){
	System.out.println(this.list);
    }

    /**
     * metodo para mostrar la lista inversa
     */
    public void showInvList(){
	/*cambia el tipo de lista que uses aquí*/
        ItemList l = (ItemList) this.list;
	l.showInv();
	System.out.println();
    }
    
}
