package aed;

public class Fecha {
    private int _dia;
    private int _mes;
    private StringBuffer _sBuffer;
    private Fecha otraFecha;

    public Fecha(int dia, int mes) {
        _dia = dia;
        _mes = mes;
    }

    public Fecha(Fecha fecha) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public Integer dia() {
        return _dia;
    }

    public Integer mes() {
        return _mes;
    }

    @Override
    public String toString() { 
        _sBuffer = new StringBuffer();
        _sBuffer.append(_dia);
        _sBuffer.append("/");
        _sBuffer.append(_mes);
        return _sBuffer.toString();
    }

    @Override
    public boolean equals(Object otra) {
        if (otra.getClass() == null || otra.getClass() != this.getClass()){
            return false;
        } else {
            otraFecha = (Fecha) otra;
            return (_dia == otraFecha.dia() && _mes == otraFecha.mes());
        }
    }

    public void incrementarDia() {
        if (_dia == diasEnMes(_mes)){
            _dia = 1;
            _mes = _mes + 1;
        } else {
            _dia ++;
        }
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}
