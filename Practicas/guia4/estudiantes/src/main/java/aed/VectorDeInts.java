package aed;

class VectorDeInts implements SecuenciaDeInts {
    private static final int CAPACIDAD_INICIAL = 1;
    private int _longitud;
    private int[] _array;
    private int[] _copy_array;



    public VectorDeInts() {
        _longitud = 0;
        _array = new int [CAPACIDAD_INICIAL];
        
    }

    public VectorDeInts(VectorDeInts vector) {

    }

    public int longitud() {
        return _longitud;
    }

    public void agregarAtras(int i) {
        if (_longitud < _array.length){
            _array[_longitud] = i;
        }
        else {
            _copy_array = new int [_longitud + 1];
            for (int j = 0; j < _longitud - 1; j++){
                _copy_array[j] = _array[j];
            }
            _copy_array[_longitud] = i;
        }
        _longitud ++;
        
    }

    public int obtener(int i) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public void quitarAtras() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public void modificarPosicion(int indice, int valor) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public VectorDeInts copiar() {
        throw new UnsupportedOperationException("No implementada aun");
    }

}
