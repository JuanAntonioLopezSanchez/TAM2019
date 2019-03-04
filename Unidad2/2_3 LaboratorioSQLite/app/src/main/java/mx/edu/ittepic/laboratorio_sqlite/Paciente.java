package mx.edu.ittepic.laboratorio_sqlite;



public class Paciente {

    private int idPaciente;
    private String nombre;

    public Paciente(int idPaciente, String nombre) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public String getNombre() {
        return nombre;
    }
}

