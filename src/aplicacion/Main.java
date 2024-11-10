package aplicacion;

import interfaz.Interfaz;
import dominio.Agenda;

public class Main {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        Interfaz interfaz = new Interfaz(agenda);
        interfaz.menu();
    }
}