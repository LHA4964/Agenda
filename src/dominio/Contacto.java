package dominio;
import java.io.Serializable;
public class Contacto implements Serializable{
    private String nombre;
    private String apellido;
    private String telefono;
    private String favorito;
    public Contacto(String nombre_, String apellido_, String telefono_, String favorito_) {
        nombre = nombre_;
        apellido = apellido_;
        telefono = telefono_;
        favorito = favorito_;
    }
    public Contacto(String nombre_, String apellido_) {
        nombre = nombre_;
        apellido = apellido_;
    }

    public String getNombre() {
        return nombre;
    }
    public Contacto setNombre(String nombre_){
        nombre = nombre_;
        return this;
    }
    public String getApellido() {
        return apellido;
    }
    public Contacto setApellido(String apellido_) {
        apellido = apellido_;
        return this;
    }
    public String getTelefono() {
        return telefono;
    }
    public Contacto setTelefono(String telefono_) {
        telefono = telefono_;
        return this;
    }
    public String getFavorito() {
        return favorito;
    }
    public Contacto setFavorito(String favorito_) {
        favorito = favorito_;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(nombre).append(" ").append(apellido).append(" Telefono: ").append(telefono)
                .append(" Favorito: ").append(favorito);
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (o==null){
            return false;
        }
        if (this.getClass()!=o.getClass()) {
            return false;
        }
        Contacto c = (Contacto)o;
        return nombre.equals(c.nombre) && apellido.equals(c.apellido);
    }
    public int hashCode(Object o) {
        return (nombre.hashCode()-1)*33+apellido.hashCode();
    }
}