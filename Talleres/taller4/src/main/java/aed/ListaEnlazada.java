package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo _first;
    private Nodo _last;
    private int _size;

    private class Nodo {
        T value;
        Nodo next;
        Nodo prev;            
        Nodo(T v){
            value = v;
        }
    }

    public ListaEnlazada() {
        _first = null;
    }

    public int longitud() {
        return _size;
    }

    public void agregarAdelante(T elem) {
        Nodo newNode = new Nodo(elem);
        if (_size == 0) {
            _first = newNode;
            _last = newNode;

        } else {
            _first.prev = newNode;
            newNode.next = _first;
            _first = newNode;
        }
        _size ++;
    }

    public void agregarAtras(T elem) {
        Nodo newNode = new Nodo(elem);
        if (_size == 0){
            _first = newNode;
            _last = newNode;            
        } else {
            Nodo actual = _first;
            while(actual.next != null){
                actual = actual.next;
            }
            actual.next = newNode;
            newNode.prev = actual;
            _last = newNode;
        }
        _size ++;
    }

    public T obtener(int i) {
        Nodo actual;
        // Para ser mas eficiente, recorro la lista de izq a der o de der a izq dependiendo que tan grande es i con respecto a la lista.
        if (i < (_size / 2)){
            actual = _first;
            for (int j = 0; j != i; j ++){
                actual = actual.next;
            }
        } else {
            actual = _last;
            for (int j = _size - 1; j != i; j --){
                actual = actual.prev;
            } 
        }
        return actual.value;   
    }

    public void eliminar(int i) {
        Nodo actual = _first;
        for (int j = 0; j != i; j ++){
            actual = actual.next;
        }
        if(_size == 1){
            _first = null;
            _last = null;
        // Si elimino el ultimo, el anterior apunta a null
        } else if (i == _size - 1){
            (actual.prev).next = null;
            _last = actual.prev;
        // Si elimino el primero, el proximo apunta a null (como previo)
        } else if (i == 0){
            (actual.next).prev = null;
            _first = actual.next;
        } else {
            (actual.prev).next = actual.next;
            (actual.next).prev = actual.prev;
        }
        _size --;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo actual;
        if (indice < (_size / 2)){
            actual = _first;
            for (int j = 0; j != indice; j ++){
                actual = actual.next;
            }
        } else {
            actual = _last;
            for (int j = _size - 1; j != indice; j --){
                actual = actual.prev;
            } 
        }
        actual.value = elem;
    }

    public ListaEnlazada<T> copiar() {
        ListaEnlazada<T> copyList = new ListaEnlazada<T>();
        Nodo actual = this._first;
        while(actual != null){
            copyList.agregarAtras(actual.value);
            actual = actual.next;
        }
        return copyList;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        ListaEnlazada<T> copyList = lista.copiar();
        _first = copyList._first;
        _last = copyList._last;
        _size = lista._size;
    }
    
    @Override
    public String toString() {
        StringBuffer sBuffer = new StringBuffer();
        sBuffer.append("[");
        Nodo actual = this._first;
        while(actual.next != null){
            sBuffer.append(actual.value + ", ");
            actual = actual.next;
        }
        sBuffer.append(actual.value + "]");
        return sBuffer.toString();
    }

    private class ListaIterador implements Iterador<T> {
        private int _it;

        public ListaIterador(){
            _it = 0;
        }

        public boolean haySiguiente() {
            return (_it < _size);
        }
        
        public boolean hayAnterior() {
            return (_it > 0);
        }

        public T siguiente() {
            T actual = obtener(_it);
            _it ++;
            return (actual);
        }
        

        public T anterior() {
            _it --;
            T actual = obtener(_it);
            return (actual);
        }
    }

    public Iterador<T> iterador() {
        Iterador<T> iterador = new ListaIterador();
        return iterador;
    }

}
