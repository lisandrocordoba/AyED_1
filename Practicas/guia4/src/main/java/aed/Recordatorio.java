package aed;

public class Recordatorio {
    private String _mensaje;
    private Fecha _fecha;
    private Horario _horario;
    private StringBuffer _sBuffer;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        _mensaje = mensaje;
        _fecha = new Fecha(fecha.dia(), fecha.mes());
        _horario = new Horario(horario.minutos(), horario.minutos());
    }

    public Horario horario() {
        return _horario;
    }

    public Fecha fecha() {
        return _fecha;
    }

    public String mensaje() {
        return _mensaje;
    }

    @Override
    public String toString() {
        _sBuffer.append(_mensaje + " @" + _fecha.toString() + " " + _horario.toString());
        return _sBuffer.toString();
    }

}
