package aed;

class VectorDeInts implements SecuenciaDeInts {
    private static final int CAPACIDAD_INICIAL = 1;
    private int _length;
    private int[] _array;
    private int[] _copy_array;
    private VectorDeInts _copy_vector;


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
            // Hago una copia transitoria de _array.
            _copy_array = new int [_length];
            for (int j = 0; j < _length; j++){
                _copy_array[j] = _array[j];
            }
            // Asigno una longitud mayor al _array, vuelvo a copiar los elementos que tenia más el nuevo al final.
            _array = new int [_length + 1];
            for (int j = 0; j < _length; j++){
                _array[j] = _copy_array[j];
            }
            _array[_length] = i;
        }
        _length ++;
        
    }

    public int obtener(int i) {
        return _array[i];
    }

    public void quitarAtras() {
        _length --;
        _copy_array = new int [_length];
        for (int j = 0; j < _length; j++){
            _copy_array[j] = _array[j];
        }
        _array = new int [_length];
        for (int j = 0; j < _length; j++){
            _array[j] = _copy_array[j];
        }
    }

    public void modificarPosicion(int indice, int valor) {
        _array[indice] = valor; 
    }

    public VectorDeInts copiar() {
        _copy_vector = new VectorDeInts();
        for (int i = 0; i < _length; i++){
            _copy_vector.agregarAtras(this.obtener(i));
        }
        return _copy_vector;
    }

}
