package aed;

class VectorDeInts implements SecuenciaDeInts {
    private static final int CAPACIDAD_INICIAL = 1;
    private int _length;
    private int[] _array;

    public VectorDeInts() {
        _length = 0;
        _array = new int [CAPACIDAD_INICIAL];
    }

    public VectorDeInts(VectorDeInts vector) {
        _length = vector.longitud();
        _array = new int[_length];
        for (int i = 0; i < _length; i++){
            _array[i] = vector.obtener(i);
        }
    }

    public int longitud() {
        return _length;
    }

    public void agregarAtras(int i) {
        if (_length < _array.length){
            _array[_length] = i;
        }
        else {
            // Hago una copia transitoria de _array con una posicion extra.
            int[] copyArray = new int [_length + 1];
            for (int j = 0; j < _length; j++){
                copyArray[j] = _array[j];
            }
            // Apunto a la misma dirección que copyArray y agrego "i" al final.
            _array = copyArray;
            _array[_length] = i;
        }
        _length ++;
        
    }

    public int obtener(int i) {
        return _array[i];
    }

    public void quitarAtras() {
        _length --;
        int[] copyArray = new int [_length];
        for (int j = 0; j < _length; j++){
            copyArray[j] = _array[j];
        }
        _array = copyArray;
    }

    public void modificarPosicion(int indice, int valor) {
        _array[indice] = valor; 
    }

    public VectorDeInts copiar() {
        VectorDeInts copiaVector = new VectorDeInts();
        for (int i = 0; i < _length; i++){
            copiaVector.agregarAtras(this.obtener(i));
        }
        return copiaVector;
    }

}
