package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    private Nodo _raiz;
    private T _max;
    // private T _next_max;
    private T _min;
    // private T _next_min;
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
        _raiz = null; // Podria obviarlo? Pues, por defecto se inicializa en null
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

    public void insertar(T elem){
        Nodo to_add = new Nodo(elem);
        Nodo _last_searched = buscar_nodo(_raiz, elem);
        if (_last_searched == null){
            _raiz = to_add;
            _cardinal ++;
        } else if (elem.compareTo(_last_searched.value) != 0){
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
    
    public boolean pertenece(T elem){
        Nodo searched = buscar_nodo(_raiz, elem);
          return (!(searched == null || elem.compareTo(searched.value) != 0));
    }

    private Nodo buscar_nodo(Nodo desde, T elem){
        Boolean is_greater = (desde != null && (elem.compareTo(desde.value) > 0));
        if (desde == null){ 
            return null; // Si el arbol está vacio devuelve NULL
        } else if (elem.compareTo(desde.value) == 0 || (desde.right == null && is_greater) || (desde.left == null && !(is_greater))){
            return desde; // Devuelve el nodo del elemento. Si no está devuelve el que sería (si se quiere agregar elem) su padre
        } else if (is_greater){
            return buscar_nodo(desde.right, elem);
        } else {
            return buscar_nodo(desde.left, elem);
        }
    }

    public void eliminar(T elem){
        Nodo to_delete = buscar_nodo(_raiz, elem);
        if (to_delete != null && elem.compareTo(to_delete.value) == 0){ // Chequeo si el elemento pertenece
            Nodo father = to_delete.father;
            Nodo right = to_delete.right;
            Nodo left = to_delete.left;
            Boolean is_raiz = (elem.compareTo(_raiz.value) == 0);

            // Actualizo min y max (si corresponde)
            update_min_max(to_delete, is_raiz);


            // CASO SIN HIJOS
            if (right == null && left == null){ 
                if (is_raiz){ // Si es la raiz
                    _raiz = null;
                } else { // Si no es la raiz
                    if (elem.compareTo(father.value) > 0){
                        father.right = null;
                    } else {
                        father.left = null;
                    }
                }
                to_delete = null;

                // CASO 1 HIJO (derecho)
            } else if (left == null){ 
                if (is_raiz){ // Si es la raiz
                    _raiz = right;
                } else { // Si no es la raiz
                    if (elem.compareTo(father.value) > 0){
                        father.right = right;
                    } else {
                        father.left = right;
                    }
                }
                right.father = father;
                to_delete = null;

                // CASO 1 HIJO (izquierdo)
            } else if (right == null){ 
                if (is_raiz){ // Si es la raiz
                    _raiz = left;
                } else { // Si no es la raiz
                    if (elem.compareTo(father.value) > 0){
                        father.right = left;
                    } else {
                        father.left = left;
                    }
                }
                left.father = father;
                to_delete = null;

                // CASO 2 HIJOS
            } else {
                Nodo successor = search_successor(to_delete);             
                if (_max.compareTo(successor.value) == 0){ // VER (por ser max y sucesor, esta inmediatamente a la derecha de to_delete y no tiene hijo der ni izq)
                    to_delete.value = successor.value;
                    to_delete.right = null;
                    successor = null;
                } else {
                    eliminar(successor.value);
                    _cardinal++;
                    to_delete.value = successor.value;
                }  
            }
            _cardinal --;
        }
    }

    private void update_min_max(Nodo to_delete, Boolean is_raiz){
        if (_max.compareTo(to_delete.value) == 0){
            Nodo predecessor = search_predecessor(to_delete);
            if(!(is_raiz)){
                if (predecessor != null){
                    _max = predecessor.value;
                } else {
                    _max = (to_delete.father).value;
                }
            } else if (_cardinal > 1){
                _max = predecessor.value;
            } else {
                _max = null;
            }
        }
        if (_min.compareTo(to_delete.value) == 0){
            Nodo succesor = search_successor(to_delete);
            if (!(is_raiz)){
                if (succesor != null){
                    _min = succesor.value;
                } else {
                    _min = (to_delete.father).value;
                }
            } else if (_cardinal > 1){
                _min = succesor.value;
            } else {
                _min = null;
            }
        }
    }

    private Nodo search_successor(Nodo actual){
        Nodo successor = null;
        //Nodo actual_copy = actual;
        if(actual != null){
            if (actual.right != null){
                successor = actual.right;
                while(successor.left != null){
                    successor = successor.left;
                }
            }
        }
        return successor;
    }

    private Nodo search_predecessor(Nodo actual){
        Nodo predecessor = null;
        //Nodo actual_copy = actual;
        if(actual != null){
            if (actual.left != null){
                predecessor = actual.left;
                while(predecessor.right != null){
                    predecessor = predecessor.right;
                }  
            }  
        }
        return predecessor;
    }

    public String toString(){
        StringBuffer sBuffer = new StringBuffer("{");
        sBuffer = sBuffer.append(in_order(_raiz));
        sBuffer = sBuffer.append("}");
        return sBuffer.toString();
    }

    private StringBuffer in_order(Nodo raiz){
        StringBuffer sBuffer = new StringBuffer();
        if (raiz.left == null && raiz.right == null){
            sBuffer.append(raiz.value);
        } else if (raiz.right == null){
            sBuffer = sBuffer.append(in_order(raiz.left) + "," + raiz.value);
        } else if (raiz.left == null){
            sBuffer = sBuffer.append(raiz.value + "," + in_order(raiz.left));
        } else {
            sBuffer = sBuffer.append(in_order(raiz.left));
            sBuffer = sBuffer.append("," + raiz.value + ",");
            sBuffer = sBuffer.append(in_order(raiz.right));
        }
        return sBuffer;
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;
        private Nodo _next;

        private ABB_Iterador(){
            _actual = buscar_nodo(_raiz, _min);
            update_next(_actual);
            }

        public boolean haySiguiente() {
            return _actual != null;
        }
    
        public T siguiente() {
             T _actual_value = _actual.value;
            _actual = _next;
            update_next(_actual);
            return _actual_value;
        }

        private void update_next(Nodo actual){
            _next = search_successor(actual);
            if (_next == null){
                _next = actual.father;
                if(_next != _raiz){
                    Nodo father = _next.father;
                    Boolean is_right_branch = ((_next.value).compareTo(father.value) > 0);
                    while (father != null &&  is_right_branch && (actual.value).compareTo(father.value) < 0){
                        _next = father;
                        father = _next.father;
                    }
                    if ((_next.value).compareTo(actual.value) < 0){
                        _next = father;
                    }
                }
            }        
        }
    }   


    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
