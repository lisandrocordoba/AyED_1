package aed;

import java.util.Vector;

public class Agenda {
    private Fecha _fechaActual;
    private Vector<Recordatorio> _recordatorios;
    private int _cantidadRecordatorios;

    public Agenda(Fecha fechaActual) {
        _fechaActual = fechaActual;
        _recordatorios = new Vector<Recordatorio>(0);
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        _recordatorios.add(recordatorio);
        _cantidadRecordatorios ++;
    }

    @Override
    public String toString() {
        StringBuffer sBuffer = new StringBuffer();
        sBuffer.append(_fechaActual.toString() + "\n=====\n" );
        // Imprimo solo los recordatorios de la fecha actual
        for(int i = 0; i < _cantidadRecordatorios; i++){
            if ((_recordatorios.get(i)).fecha().equals(_fechaActual)){
                sBuffer.append(_recordatorios.get(i).toString() + "\n");
            }
        }
        return sBuffer.toString();
    }

    public void incrementarDia() {
        _fechaActual.incrementarDia();
    }

    public Fecha fechaActual() {
        return new Fecha(_fechaActual.dia(), _fechaActual.mes());
    }

}
