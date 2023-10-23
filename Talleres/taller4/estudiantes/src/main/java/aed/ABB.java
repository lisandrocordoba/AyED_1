package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    private Nodo _origin;
    private T _max;
    private T _min;
    private int _cardinal;

    private class Nodo {
        T value;
        Nodo left;
        Nodo right;
        Nodo father;

        Nodo(T v){
            value = v;
            left = null;
            right = null;
            father = null;
        }
}

    public ABB() {     
        _origin = null; // Podria obviarlo? Pues, por defecto se inicializa en null
    }

    public int cardinal() {
        return _cardinal;
    }

    public T minimo(){
        return _min;
    }
    public T maximo(){
        return _max;
    }
///////////////// PRUEBA //////
    private Nodo buscar_nodo(Nodo desde, T elem){
        Boolean is_greater = (desde != null && (elem.compareTo(desde.value) > 0));
        if (desde == null){ 
            return null; // Si el arbol está vacio devuelve NULL
        } else if (desde.value == elem || (desde.right == null && is_greater) || (desde.left == null && !(is_greater))){
            return desde; // Devuelve el nodo del elemento. Si no está devuelve el que será su padre
        } else if (elem.compareTo(desde.value) > 0){
            return buscar_nodo(desde.right, elem);
        } else {
            return buscar_nodo(desde.left, elem);
        }
    }

    public void insertar(T elem){
        Nodo to_add = new Nodo(elem);
        Nodo _last_searched = buscar_nodo(_origin, elem);
        if (_last_searched == null){
            _origin = to_add;
            _cardinal ++;
        } else if (_last_searched.value != elem){
            to_add.father = _last_searched;
            if (elem.compareTo(_last_searched.value) > 0){
                _last_searched.right = to_add;
            } else {
                _last_searched.left = to_add;
            }
            _cardinal ++;
        }
        if (_max == null || elem.compareTo(_max) > 0){
            _max = elem;
        }
        if (_min == null || elem.compareTo(_min) < 0){
            _min = elem;
        }
    }
    
/*
    public void insertar(T elem){
        if (!(this.pertenece(elem))){
            Nodo to_add = new Nodo(elem);
            if (_cardinal == 0){
                _origin = to_add;
            } else {
                Nodo _last_searched = buscar_nodo(_origin, elem);
                to_add.father = _last_searched;
                if (elem.compareTo(_last_searched.value) > 0){
                    _last_searched.right = to_add;
                } else {
                    _last_searched.left = to_add;
                }
            }
                if (_max == null || elem.compareTo(_max) > 0){
                    _max = elem;
                }
                if (_min == null || elem.compareTo(_min) < 0){
                    _min = elem;
                }
            _cardinal ++;
            }
    } */
///////////////// PRUEBA //////

    
    /*
    private Nodo buscar_nodo(Nodo desde, T elem){
        Nodo next;
        Boolean is_greater = (elem.compareTo(desde.value) > 0);
        if ((desde.right == null && is_greater) || (desde.left == null && !(is_greater))){
            return desde;
        } else {
            if (is_greater){
                next = desde.right;
            } else {
                next = desde.left;
            }
            return buscar_nodo(next, elem);
        }
    }
 */
    public boolean pertenece(T elem){
        Nodo searched = buscar_nodo(_origin, elem);
        return (!(searched == null || searched.value != elem));
    }

    /*
    private boolean busqueda_recursiva(Nodo desde, T elem){
        Nodo next;
        if (desde == null){
            return false;
        } else if (desde.value == elem){
            return true;
        } else {
            if (elem.compareTo(desde.value) > 0){
                next = desde.right;
            } else {
                next = desde.left;
            }
            return busqueda_recursiva(next, elem);
        }
    }
 */
    public void eliminar(T elem){
        
    }

    public String toString(){
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public boolean haySiguiente() {            
            throw new UnsupportedOperationException("No implementada aun");
        }
    
        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
