package dominio;

public class ErrorContactoDuplicado extends Exception {
    private Contacto cerror;
    public ErrorContactoDuplicado(Contacto c) {
        cerror = c;
    }
    public Contacto getContacto() {
        return cerror;
    }
}