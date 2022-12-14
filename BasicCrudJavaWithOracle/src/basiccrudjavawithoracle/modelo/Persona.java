
package basiccrudjavawithoracle.modelo;

public class Persona {
    //atrubutos de la clase
    
    private int idPersona;
    private String nombre;
    private String apellido;
    private String email;

    public Persona() {
    }

    public Persona(int idPersona, String nombre, String apellido, String email) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }
    
    public Persona(String nombre, String apellido, String email) {
        this.idPersona = 0;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Persona{" + "idPersona=" + idPersona + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + '}';
    }
    
    
}
