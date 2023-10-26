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
            return desde; // Devuelve el nodo del elemento. Si no está devuelve el que sería (si se quiere agregar elem) su padre
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

    private Nodo buscar_nodo(Nodo desde, T elem){
    Boolean is_greater = (desde != null && (elem.compareTo(desde.value) > 0));
    if (desde == null){ 
        return null; // Si el arbol está vacio devuelve NULL
    } else if (desde.value == elem || (desde.right == null && is_greater) || (desde.left == null && !(is_greater))){
        return desde; // Devuelve el nodo del elemento. Si no está devuelve el que sería (si se quiere agregar elem) su padre
    } else if (elem.compareTo(desde.value) > 0){
        return buscar_nodo(desde.right, elem);
    } else {
        return buscar_nodo(desde.left, elem);
    }
}
*/

    public void eliminar(T elem){
        Nodo to_delete = buscar_nodo(_origin, elem);
        if (to_delete != null && to_delete.value == elem){ // Chequeo si el elemento pertenece
            Nodo father = to_delete.father;
            Nodo right = to_delete.right;
            Nodo left = to_delete.left;
            Boolean is_origin = (elem == _origin.value);

            // Actualizo min y max (si corresponde)
            if (to_delete.value == _max){
                if(!(is_origin)){
                    _max = to_delete.father.value;
                } else if (_cardinal > 1){
                    _max = search_predecessor(to_delete).value;
                } else {
                    _max = null;
                }
            }
            if (to_delete.value == _min){
                if(!(is_origin)){
                    _min = to_delete.father.value;
                } else if (_cardinal > 1){
                    _min = search_successor(to_delete).value;
                } else {
                    _min = null;
                }
            }
            // CASO SIN HIJOS
            if (to_delete.right == null && to_delete.left == null){ 
                if (is_origin){ // Si es la raiz
                    _origin = null;
                } else { // Si no es la raiz
                    if (elem.compareTo(father.value) > 0){
                        father.right = null;
                    } else {
                        father.left = null;
                    }
                }
                to_delete = null;

                // CASO 1 HIJO (derecho)
            } else if (to_delete.left == null){ 
                if (is_origin){ // Si es la raiz
                    _origin = to_delete.right;
                } else { // Si no es la raiz
                    if (elem.compareTo(father.value) > 0){
                        father.right = to_delete.right;
                    } else {
                        father.left = to_delete.left;
                    }
                }
                right.father = father;
                to_delete = null;

                // CASO 1 HIJO (izquierdo)
            } else if (to_delete.right == null){ 
                if (is_origin){ // Si es la raiz
                    _origin = to_delete.left;
                } else { // Si no es la raiz
                    if (elem.compareTo(father.value) > 0){
                        father.left = to_delete.left;
                    } else {
                        father.left = to_delete.left;
                    }
                }
                left.father = father;
                to_delete = null;

                // CASO 2 HIJOS
            } else {
                Nodo successor = search_successor(to_delete);
                if (elem == _origin.value){
                    this.eliminar(successor.value); // Elimino del arbol al nodo original del sucesor
                    to_delete.value = successor.value;
                    _cardinal ++;
                } else {
                    if (elem.compareTo(father.value) > 0){
                        this.eliminar(successor.value); // Elimino del arbol al nodo original del sucesor
                        _cardinal ++;
                        to_delete.value = successor.value; // Copio el sucesor en el nodo a borrar
                        /*
                        (successor.right).father = successor.father; // Elimino del arbol al nodo original del sucesor
                        (successor.father).left = successor.right;
                        successor = null; // Elimino el nodo original del sucesor
                        */
                    } else {
                        Nodo predecessor = search_predecessor(to_delete);
                        this.eliminar(predecessor.value); // Elimino del arbol al nodo original del predecesor
                        _cardinal ++;
                        to_delete.value = predecessor.value; // Copio el predecesor en el nodo a borrar
                        /*
                        (predecessor.left).father = predecessor.father; // Elimino del arbol al nodo original del predecesor
                        (predecessor.father).right = predecessor.left;
                        predecessor = null; // Elimino el nodo original del predecesor
                        */
                    }
                }    
            }
            _cardinal --;
        }
    }


/*
    public void eliminar(T elem){
        // VER ELIMINAR RAIZ
        Nodo to_delete = buscar_nodo(_origin, elem);
        if (to_delete != null && to_delete.value == elem){ // Chequeo si el elemento pertenece
            if(to_delete.value != _origin.value){
                int right_left = elem.compareTo(to_delete.father.value); // Mayor a 0 si elem está en la rama der de su padre. Menor a 0 si está en la izq
                
                if (to_delete.right != null && to_delete.left != null){ // CASO 2 HIJOS
                    if (right_left > 0){
                        Nodo sucessor = search_successor(to_delete);
                        this.eliminar(sucessor.value);
                        to_delete.value = sucessor.value;
                    } else {
                        Nodo predecessor = search_predecessor(to_delete);
                        this.eliminar(predecessor.value);
                        to_delete.value = predecessor.value;
                    }
                    _cardinal ++; // Para mantener el cardinal al borrar el suc/pred


                } else if (to_delete.right != null){ // CASO 1 HIJO (DERECHO)
                    if (right_left > 0){
                        (to_delete.father).right = to_delete.right;
                    } else {
                        (to_delete.father).left = to_delete.right;
                    }
                    (to_delete.right).father = to_delete.father;
                    to_delete = null;

                } else if (to_delete.left != null){ // CASO 1 HIJO (IZQUIERDO)
                    if (right_left > 0){
                        (to_delete.father).right = to_delete.right;
                    } else {
                        (to_delete.father).left = to_delete.right;
                    }
                    (to_delete.left).father = to_delete.father;
                    to_delete = null;

                } else { // CASO SIN HIJOS
                    if (right_left > 0){
                        (to_delete.father).right = null;
                    } else {
                        to_delete.father.left = null;
                    }
                    to_delete = null;
                }
            }
            _cardinal --;
            //////////// VER MAXIMOS Y MINIMOS!!!!!
        }
    }
 */
    private Nodo search_successor(Nodo actual){
        Nodo successor = actual.right;
        while(successor.left != null){
            successor = successor.left; 
        }
        return successor;
    }

    private Nodo search_predecessor(Nodo actual){
        Nodo predecessor = actual.left;
        while(predecessor.right != null){
           predecessor = predecessor.right;
        }
        return predecessor;
    }

    public String toString(){
        StringBuffer sBuffer = new StringBuffer(); // VER creo un sbuffer por cada recursion
        sBuffer = sBuffer.append("{");
        sBuffer = sBuffer.append(toString_recursivo(sBuffer, _origin));
        sBuffer = sBuffer.append("}");
        return sBuffer.toString();
    }

    private String toString_recursivo(StringBuffer sBuffer, Nodo origin){
        StringBuffer tempBuffer = new StringBuffer();
        if (origin.left == null && origin.right == null){
            return origin.value.toString();
        } else if (origin.right == null){
            tempBuffer = tempBuffer.append(toString_recursivo(sBuffer, origin.left) + "," + origin.value);
            return(tempBuffer.toString());
        } else if (origin.left == null){
            tempBuffer = tempBuffer.append(origin.value + "," + toString_recursivo(sBuffer, origin.left));
            return(tempBuffer.toString());
        } else {  
            tempBuffer = tempBuffer.append(toString_recursivo(sBuffer, origin.left));
            tempBuffer = tempBuffer.append("," + origin.value + ",");
            tempBuffer = tempBuffer.append(toString_recursivo(sBuffer, origin.right));
            return tempBuffer.toString();
        }
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
