package dominio;

public class ErrorContactoNoEncontrado extends Exception {
    private Contacto cerror;
    public ErrorContactoNoEncontrado(Contacto c) {
        cerror = c;
    }
    public Contacto getContacto() {
        return cerror;
    }
}