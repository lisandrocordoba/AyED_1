package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo _primero;
    private Nodo _ultimo;
    private int _size;

    private class Nodo {
        T valor;
        Nodo proximo;
        Nodo anterior;            

        Nodo(T v){
            valor = v;
        }
    }

    public ListaEnlazada() {
        _primero = null;
    }

    public int longitud() {
        return _size;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (_size == 0) {
            _primero = nuevo;
            _ultimo = nuevo;

        } else {
            _primero.anterior = nuevo;
            nuevo.proximo = _primero;
            _primero = nuevo;
        }
        _size ++;
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (_size == 0){
            _primero = nuevo;
            _ultimo = nuevo;            
        } else {
            Nodo actual = _primero;
            while(actual.proximo != null){
                actual = actual.proximo;
            }
            actual.proximo = nuevo;
            nuevo.anterior = actual;
            _ultimo = nuevo;
        }
        _size ++;
    }

    public T obtener(int i) {
        Nodo actual = _primero;
        for (int j = 0; j != i; j ++){
            actual = actual.proximo;
        }
        return actual.valor;
    }

    public void eliminar(int i) {
        Nodo actual = _primero;
        for (int j = 0; j != i; j ++){
            actual = actual.proximo;
        }
        if(_size == 1){
            _primero = null;
            _ultimo = null;
        } else if (_size == 2 && actual == _primero){
            _ultimo.anterior = null;
            _primero = _ultimo;
        } else if (_size == 2 && actual == _ultimo){
            _primero.proximo = null;
            _ultimo = _primero;
        } else {
            if (i == 0){
                (_primero.proximo).anterior = null;
            }
            else if (i == _size - 1){
                (_ultimo.anterior).proximo = null;
            } else {
                (actual.anterior).proximo = actual.proximo;
                (actual.proximo).anterior = actual.anterior;
            }
        }
        _size --;

    }

    public void modificarPosicion(int indice, T elem) {
        Nodo actual = _primero;
        for (int j = 0; j != indice; j ++){
            actual = actual.proximo;
        }
        actual.valor = elem;
    }


    public ListaEnlazada<T> copiar() {
        ListaEnlazada<T> copiaLista = new ListaEnlazada<T>();
        int length = this.longitud();
        for(int i = 0; i < length; i++){
            copiaLista.agregarAtras(this.obtener(i));
        }
        return copiaLista;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        throw new UnsupportedOperationException("No implementada aun");
    }
    
    @Override
    public String toString() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ListaIterador implements Iterador<T> {
    	// Completar atributos privados

        public boolean haySiguiente() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
        
        public boolean hayAnterior() {
	        throw new UnsupportedOperationException("No implementada aun");
        }

        public T siguiente() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
        

        public T anterior() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
	    throw new UnsupportedOperationException("No implementada aun");
    }

}
