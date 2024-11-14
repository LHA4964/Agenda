package dominio;
import java.util.*;
import java.io.*;

public class Agenda implements Serializable{
    private ArrayList<Contacto> lista;

    public Agenda() {
        lista = new ArrayList<Contacto>();
    }

    public Contacto buscar(Contacto c){
        int indice = lista.indexOf(c);
        if(indice==-1) {
            return null;
        }
        return lista.get(indice);
    }
    public Contacto buscar(String nombre){
        for (Contacto c : lista) {
            if(c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Contactos:\n");
        for (Contacto contacto : lista) {
            sb.append(contacto.toString()).append("\n");
        }
        return String.valueOf(sb);
    }
    public String favorito(){
        StringBuilder sb = new StringBuilder();
        sb.append("Contactos:\n");
        for (Contacto c : lista){
            if (c.getFavorito().equalsIgnoreCase("si")){
                sb.append(c).append("\n");
            }
        }
        return String.valueOf(sb);
    }

    public Agenda add(Contacto c)throws ErrorContactoDuplicado{
        if(lista.contains(c)) {
            throw new ErrorContactoDuplicado(c);
        }else {
            lista.add(c);
            return this;
        }
    }

    /*public boolean borrar(Contacto c){
        int index = lista.indexOf(c);
        if (index != -1){
            lista.remove(index);
            return true;
        }
        return false;
    }
     */
    public Agenda borrar(Contacto c)throws ErrorContactoNoEncontrado{
        if(lista.contains(c)) {
            lista.remove(c);
            return this;
        }else{
            throw new ErrorContactoNoEncontrado(c);
        }
    }

    public int size() {
        return lista.size();
    }

    public void modificarContacto(Contacto contacto, String atributo, String valor) {
        Contacto encontrado = buscar(contacto);
        if (encontrado != null) {
            switch (atributo.toLowerCase()) {
                case "nombre":
                    encontrado.setNombre(valor);
                    break;
                case "apellido":
                    encontrado.setApellido(valor);
                    break;
                case "telefono":
                    encontrado.setTelefono(valor);
                    break;
                case "favorito":
                    encontrado.setFavorito(valor);
                    break;
                default:
                    System.out.println("Atributo no reconocido.");
                    break;
            }
        }
    }

    public static Agenda leer() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("contactos.dat"))) {
            return (Agenda) ois.readObject();
        } catch (Exception e) {
            System.out.println("No se encontró el archivo. Se creará una nueva agenda.");
            return new Agenda();
        }
    }
    public void guardar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("contactos.dat"))) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.out.println("Error al guardar la agenda.");
        }
    }
}