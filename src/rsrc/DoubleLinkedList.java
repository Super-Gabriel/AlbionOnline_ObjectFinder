package rsrc;
import rsrc.TDAList;

import java.util.Iterator;
/**
 * Clase de lista doblemente ligada
 * @author - Angel Gabriel Sánchez Pavia
 * @version - 1.0 octubre 2021
 */
public class DoubleLinkedList<T> implements TDAList<T>{
    /**
     * nodo de una lista
     */
    protected class Node{
	
	/** elemento del nodo */
	private T element;

	/** el nodo siguiente */
	private Node next;

	/** el nodo anterior */
	private Node previous;

	/**
	 * crea un nuevo nodo
	 * @param element - el elemento que almacena el nodo
	 */
	public Node(T element){
	    this.element = element;
	}

	/**
	 * permite cambiar el nodo siguiente.
	 * @param newNode - el nuevo nodo siguiente.
	 */
	public void setNext(Node newNode){
	    this.next = newNode;
	}

	/**
	 * permite cambiar el nodo anterior.
	 * @param newNode - el nuevo nodo anterior.
	 */
	public void setPrevious(Node newNode){
	    this.previous = newNode;
	}
	
	/**
	 * accede a la información del nodo.
	 * @return - element.
	 */
	public T getElement(){
	    return element;
	}

	/**
	 * accede al nodo siguiente
	 * @return next.
	 */
	public Node getNext(){
	    return next;
	}

	/**
	 * accede al nodo anterior
	 * @return previous.
	 */
	public Node getPrevious(){
	    return previous;
	}

	/**
	 * metodo para modificar el elemento que contiene un nodo
	 */
	public void setElement(T elem){
	    this.element = elem;
	}
	
    }

    /** cabeza de la lista */
    protected Node head;

    /** ultimo nodo de la lista */
    protected Node tail;

    /** longitud de la lista */
    protected int size;

    /** identifica a la cola */
    private void setTail(){
	
    }

    @Override
    public void add(int i, T e) throws IndexOutOfBoundsException{
	//cuando i no está en los rangos dafinidos.
	if(i>size || i<0){
	    throw new IndexOutOfBoundsException();
	}

	Node nuevo = new Node(e);

	//cuando es vacía
	if(head==null){
	    head = nuevo;
	    tail = nuevo;
	    size++;
	    return;
	}

	//cuando se agrega al inicio
	if(i==0){
	    nuevo.setNext(head);
	    head.setPrevious(nuevo);
	    head = nuevo;	    
	    size++;
	    return;
	}

	//cuando se agrega al final
	if(i==size){
	    nuevo.setNext(tail.getNext());
	    nuevo.setPrevious(tail);
	    tail.setNext(nuevo);
	    tail = nuevo;
	    size++;
	    return;
	}

	//cuando se agrega en una posición mas cercana a la cabeza
	int pos = size - i;
	if(pos>=i){
	    Node iterador = head;
	    for(int j=1; j<i; j++){
		iterador = iterador.getNext();
	    }
	    nuevo.setNext(iterador.getNext());
	    nuevo.setPrevious(iterador);
	    iterador.getNext().setPrevious(nuevo);
	    iterador.setNext(nuevo);
	    size++;
	    return;
	}
	//cuando se agrega en una posición mas cercana a la cola
	if(pos<i){
	    Node iterador = tail;
	    for(int j=size; j>i; j--){
		iterador = iterador.getPrevious();
	    }
	    nuevo.setNext(iterador.getNext());
	    nuevo.setPrevious(iterador);
	    iterador.getNext().setPrevious(nuevo);
	    iterador.setNext(nuevo);
	    size++;
	}
	
    }

    /**
     * Limpia la lista. Elimina todos los elementos.
     */
    @Override
    public void clear(){
	head = null;
	size = 0;
    }

    public T getHead(){return head.getElement();}
    public T getTail(){return tail.getElement();}

    /**
     * Verifica si un elemento está contenido en la lista.
     * @param e el elemento a verificar si está contenido.
     * @return true si el elemento está contenid, false en otro caso.
     */
    @Override
    public boolean contains(T e){	
	int cont = (size/2)+1;
	Node iterador1 = head;
	Node iterador2 = tail;
	if(size==0){
	    return false;
	}else{
	    for(int i=0; i<cont; i++){
		if(iterador1.getElement().equals(e)){
		    return true;
		}
		if(iterador2.getElement().equals(e)){
		    return true;
		}
		iterador1 = iterador1.getNext();
		iterador2 = iterador2.getPrevious();
	    }
	}
	
	return false;
    }
    
    /**
     * Obtiene el elemento en la posición <i>i</i>.
     * @param i el índice a obtener elemento.
     * @throws IndexOutOfBoundException si el índice está fuera de rango.
     */
    @Override
    public T get(int i) throws IndexOutOfBoundsException{
	//cuando i no está dentro de los parámetros
	if(i>size-1 || i<0){
	    throw new IndexOutOfBoundsException();
	}

	//cuando i==0
	if(i==0){return head.getElement();}
	//cuando i== size-1 (tail)
	if(i==(size-1)){return tail.getElement();}
	
	int pos = size-i;
	//cuando i está mas cerca de la cabeza
	if(pos>=i){
	    Node iterador = head;
	    for(int j=1; j<=i; j++){
		iterador = iterador.getNext();
	    }
	    return iterador.getElement();
	}

	//cuando i está mas cerca de la cola
	if(pos<i){
	    Node iterador = tail;
	    for(int j=1; j<pos; j++){
		iterador = iterador.getPrevious();
	    }
	    return iterador.getElement();
	}
	return head.getElement();
    }
    
    /**
     * Verifica si la lista está vacía.
     * @return true si la lista no contiene elementos, false en otro caso.
     */
    @Override
    public boolean isEmpty(){
	if(head==null){return true;}
	return false;
    }
    
    /**
     * Elimina el elemento en la posición <i>i</i>.
     * @param i el índice del elemento a eliminar.
     * @return el elemento eliminado.
     * @throws IndexOutOfBoundException si el índice está fuera de rango.
     */
    @Override
    public T remove(int i) throws IndexOutOfBoundsException{
	T elem=null;
	//cuando i no está dentro de los parámetros	
	if(i>(size-1) || i<0){
	    throw new IndexOutOfBoundsException();
	}
	//cuando i == 0
	if(i==0){
	    if(size==1){
		elem = head.getElement();
		head = null;
		size--;
		return elem;
	    }else{
		elem = head.getElement();
		head.getNext().setPrevious(null);
		head=head.getNext();
		size--;
		return elem;
	    }
	}
	//cuando i es == size-1 (tail)
	if(i==(size-1)){
	    elem = tail.getElement();
	    tail.getPrevious().setNext(null);
	    tail = tail.getPrevious();
	    size--;
	    return elem;
	}

	int pos = size-i;
	//cuando i está mas cerca de la cabeza
	if(pos>=i){
	    Node iterador = head;
	    for(int j=1; j<=i; j++){
		iterador = iterador.getNext();
	    }
	    elem = iterador.getElement();
	    iterador.getPrevious().setNext(iterador.getNext());
	    iterador.getNext().setPrevious(iterador.getPrevious());
	    size--;
	    return elem;
	}

	//cuando i está mas cerca de la cola
	if(pos<i){
	    Node iterador = tail;
	    for(int j=1; j<pos; j++){
		iterador = iterador.getPrevious();
	    }
	    elem = iterador.getElement();
	    iterador.getPrevious().setNext(iterador.getNext());
	    iterador.getNext().setPrevious(iterador.getPrevious());
	    size--;
	    return elem;
	}	
	
	return elem;
    }
    
    /**
     * Regresa la cantidad de elementos contenidos en la lista.
     * @return la cantidad de elementos contenidos.
     */
    @Override
    public int size(){
	return size;
    }
    
    /**
     * Revierte los elementos de la lista. Esto es, da la reversa de la lista.
     */
    public void revert(){
	Node iterador1 = head;
	Node iterador2 = tail;
	T aux = null;
	for(int i=1; i<=(size)/2; i++){
	    aux = iterador1.getElement();
	    iterador1.setElement(iterador2.getElement());
	    iterador2.setElement(aux);

	    iterador1 = iterador1.getNext();
	    iterador2 = iterador2.getPrevious();
	}
	
    }
    
    /**
     * Da la mitad derecha o izquierda de una lista.
     * @param side la mitad que recortar de la lista original.
     * true - mitad derecha.
     * false - mitad izquierda.
     * @return una nueva lista con la mitad de los elementos.
     */
    public TDAList cut(boolean side){	
	return this;
    }

    /**
     * Da un iterador para la lista.
     * @return un iterador para la estructura.
     */
    //public Iterator listIterador(){}
    
    @Override
    public String toString(){
	String formato="";
	Node iterador = head;
	while(iterador!=null){
	    formato += iterador.getElement()+",";
	    iterador =  iterador.getNext();
	}
	return formato;
    }
    
}
