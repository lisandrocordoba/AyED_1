package aed;

public class Recordatorio {
    private String _mensaje;
    private Fecha _fecha;
    private Horario _horario;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        _mensaje = mensaje;
        _fecha = new Fecha(fecha.dia(), fecha.mes());
        _horario = horario;
    }

    public Horario horario() {
        return _horario;
    }

    public Fecha fecha() {
        Recordatorio recordatorioCopia = new Recordatorio(_mensaje, _fecha, _horario);
        return recordatorioCopia._fecha;
    }

    public String mensaje() {
        return _mensaje;
    }

    @Override
    public String toString() {
        StringBuffer sBuffer = new StringBuffer();
        sBuffer.append(_mensaje + " @ " + _fecha.toString() + " " + _horario.toString());
        return sBuffer.toString();
    }

}
