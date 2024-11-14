package interfaz;
import dominio.*;
import java.util.Scanner;

public class Interfaz {
    private Agenda agenda;
    private Scanner sc;

    public Interfaz(Agenda agenda) {
        this.agenda = Agenda.leer();
        sc = new Scanner(System.in);
    }

    public String[] instruccion(){
        String instruccion = sc.nextLine();
        return instruccion.split(",");
    }

    public void menu() {
        help();

        while (true){
            System.out.print("\nEscriba la instrucción: ");
            String[] instr = instruccion();

            switch (instr[0].toLowerCase()) {
                case "help":
                    help();
                    break;
                case "list":
                    list();
                    break;
                case "listfavorito":
                    listfavorito();
                    break;
                case "add":
                    if (instr.length == 5) {
                        aniadir(instr[1], instr[2], instr[3], instr[4]);
                    } else {
                        System.out.println("Error para comando (add). Ejemplo: add,Nombre,Apellido,Telefono,Favorito");
                    }
                    break;
                case "remove":
                    if (instr.length == 3) {
                        remove(instr[1], instr[2]);
                    }else{
                        System.out.println("Error para comando (remove). Ejemplo: remove,Nombre,Apellido");
                    }
                    break;
                case "modify":
                    if (instr.length == 5) {
                        modificar(instr[1], instr[2], instr[3], instr[4]);
                    } else {
                        System.out.println("Error para comando (modify). Ejemplo: modify,Nombre,Apellido,Atributo,NuevoValor");
                    }
                    break;
                case "exit":
                    agenda.guardar();
                    System.out.println("Saliendo");
                    return;
                default:
                    System.out.println("\nInstruccion no valida\n");
                    help();
                    break;
            }
        }
    }

    private void list() {
        System.out.println(agenda.toString());
    }

    private void listfavorito() {
        System.out.println(agenda.favorito());
    }

    private void help(){
        System.out.println("Comandos disponibles:\n" +
                "- help: Muestra este menú con informacion de los comandos.\n" +
                "- list: Muestra los contactos de la agenda.\n" +
                "- lisfavorito: Muestra los contactos favoritos de la agenda.\n" +
                "- add: Añade un nuevo contacto:\n    add,<Nombre>,<NumeroDeTelefono>\n" +
                "- remove: Elimina un contacto por nombre y apellido:\n    remove,<Nombre>,<Apellido>\n" +
                "- modify: Modifica un contacto:\n    modify,<Nombre>,<Apellido>,<Atributo>,<NuevoValor>\n" +
                "\tAtributos modificables: nombre, apellido, numeroDeTelefono y favorito.\n" +
                "- exit: Guarda y cierra la agenda.\n");
    }

    private void aniadir(String nombre, String apellido, String numeroDeTelefono, String favorito) {
        try{
            agenda.add(new Contacto(nombre, apellido, numeroDeTelefono, favorito));
            System.out.println("Contacto añadido.");
        }catch (ErrorContactoDuplicado e) {
            System.out.println("El contacto " + e.getContacto().getNombre() + " " + e.getContacto().getApellido() + " ya existe");
        }
    }

    private void remove(String nombre, String apellido) {
        try {
            agenda.borrar(new Contacto(nombre, apellido));
        } catch (ErrorContactoNoEncontrado e) {
            System.out.println("El contacto " + e.getContacto().getNombre()+" no se ha encontrado");
        }
    }

    private void modificar(String nombre, String apellido, String atributo, String nuevo) {
        if (atributo.equals("nombre")||atributo.equals("apellido")||atributo.equals("telefono")||atributo.equals("favorito")) {
            agenda.modificarContacto(new Contacto(nombre, apellido), atributo, nuevo);
            System.out.println("Contacto modificado.");
        } else {
            System.out.println("Atributo no valido");
        }
    }
}