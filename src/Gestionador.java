
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author yorsh
 */
public class Gestionador {

    int max = 10;
    Empleado[] dato = new Empleado[max];
    int numDato;

    public Gestionador() {
        this.numDato = -1;
    }

    public String ingresarDato(int cod, String nom, int ed, float pe, float talla, boolean isBornout, String causaBornout, int idBornout) {
        numDato++;
        if (numDato > max) {
            return "Esta lleno";
        } else {
            Empleado objDato = new Empleado(cod, nom, ed, pe, talla, isBornout, causaBornout, idBornout);
            this.dato[numDato] = objDato;
            return "Se agregó con exito";
        }
    }

    public String listarDato() {
        String msj = "";
        for (int i = 0; i <= numDato; i++) {
            msj = msj + "\n " + dato[i].toString() + " " + calcularImc(dato[i].getPeso(), dato[i].getTalla());
        }
        return msj;
    }

    public String calcularImc(float pe, float ta) {
        float imc = pe / (ta * ta);
        if (imc < 19) {
            return "Bajo Peso";
        } else {
            if (imc >= 19 && imc < 21) {
                return "Peso normal";
            } else if (imc >= 21 && imc < 23) {
                return "Sobrepeso";
            } else {
                return "Obeso";
            }
        }

    }

    public String buscar(int cod) {
        for (int i = 0; i <= numDato; i++) {
            if (dato[i].getCodigo() == cod) {
                return dato[i].toString();
            }
        }
        return "Empleado no encontrado";
    }

    public List<String[]> buscarPorNombre(String nombreBuscado) {
        List<String[]> lista = new ArrayList<>();
        for (int i = 0; i <= numDato; i++) {
            if (dato[i].getNombre().equalsIgnoreCase(nombreBuscado)) {
                String[] datos = new String[8];
                datos[0] = String.valueOf(dato[i].getCodigo());
                datos[1] = dato[i].getNombre();
                datos[2] = String.valueOf(dato[i].getEdad());
                datos[3] = String.valueOf(dato[i].getPeso());
                datos[4] = String.valueOf(dato[i].getTalla());
                datos[5] = calcularImc(dato[i].getPeso(), dato[i].getTalla());
                if (dato[i].isIsBornout()) {
                    datos[6] = "Sí";
                } else {
                    datos[6] = "No";
                }
                datos[7] = String.valueOf(dato[i].getCausaBornout());
                lista.add(datos);
            }
        }
        return lista;
    }

    public List<String[]> getEmpleados() {
        List<String[]> lista = new ArrayList<>();
        for (int i = 0; i <= numDato; i++) {
            String[] datos = new String[8];
            datos[0] = String.valueOf(dato[i].getCodigo());
            datos[1] = dato[i].getNombre();
            datos[2] = String.valueOf(dato[i].getEdad());
            datos[3] = String.valueOf(dato[i].getPeso());
            datos[4] = String.valueOf(dato[i].getTalla());
            datos[5] = calcularImc(dato[i].getPeso(), dato[i].getTalla());
            if (dato[i].isIsBornout()) {
                datos[6] = "Sí";
            } else {
                datos[6] = "No";
            }
            datos[7] = String.valueOf(dato[i].getCausaBornout());
            lista.add(datos);
        }
        return lista;
    }

    public String[] getCausaStats() {
        String[] stats = new String[4];
        List<String[]> datos = getEmpleados();
        int countBornout = 0; // Contador para los empleados con isBornout = true

        for (String[] empleado : datos) {
            if ("Sí".equals(empleado[6])) {
                countBornout++;
            }
        }

        stats[1] = String.valueOf(countBornout);
        return stats;
    }
}
