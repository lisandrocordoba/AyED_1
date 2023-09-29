package aed;

public class Horario {
    private int _hora;
    private int _minutos;
    private StringBuffer _sBuffer;
    private Horario _otroHorario;

    public Horario(int hora, int minutos) {
        _hora = hora;
        _minutos = minutos;
    }

    public int hora() {
        return _hora;
    }

    public int minutos() {
        return _minutos;
    }

    @Override
    public String toString() {
        _sBuffer = new StringBuffer();
        _sBuffer.append(_hora + ":" + _minutos);
        return _sBuffer.toString();
    }

    @Override
    public boolean equals(Object otro) {
        if (otro.getClass() == null || otro.getClass() != this.getClass()){
            return false;
        } else {
            _otroHorario = (Horario) otro;
            return (_hora == _otroHorario.hora() && _minutos == _otroHorario.minutos());
        }
    }

}
