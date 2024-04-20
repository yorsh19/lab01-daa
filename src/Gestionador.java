
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        } else if (imc >= 19 && imc < 21) {
            return "Peso normal";
        } else if (imc >= 21 && imc < 23) {
            return "Sobrepeso";
        } else {
            return "Obeso";
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

    public String[] calcularYObtenerEstadisticas() {
        Map<Integer, Integer> estadisticas = new HashMap<>();
        // Suponiendo que numDato y dato están correctamente definidos en la clase.
        System.out.println("numDato en calcularYObtenerEstadisticas: " + numDato);

        // Recopilar estadísticas de burnout por ID.
        for (int i = 0; i <= numDato; i++) {
            Empleado empleado = dato[i];
            System.out.println("Empleado: " + empleado);
            if (empleado.isIsBornout()) {
                int idBurnout = empleado.getIdBornout();
                estadisticas.put(idBurnout, estadisticas.getOrDefault(idBurnout, 0) + 1);
            }
        }

        // Obtener las causas específicas y calcular porcentajes.
        int causa1 = estadisticas.getOrDefault(1, 0);
        int causa2 = estadisticas.getOrDefault(2, 0);
        int causa3 = estadisticas.getOrDefault(3, 0);
        int causa4 = estadisticas.getOrDefault(4, 0);
        int total = causa1 + causa2 + causa3 + causa4;

        String[] resultados = new String[4]; // Para almacenar los porcentajes formatados.
        if (total > 0) {
            resultados[0] = String.format("%.1f%%", (float) causa1 / total * 100);
            resultados[1] = String.format("%.1f%%", (float) causa2 / total * 100);
            resultados[2] = String.format("%.1f%%", (float) causa3 / total * 100);
            resultados[3] = String.format("%.1f%%", (float) causa4 / total * 100);
        } else {
            resultados[0] = "0%";
            resultados[1] = "0%";
            resultados[2] = "0%";
            resultados[3] = "0%";
        }

        return resultados;
    }

    public Map<Integer, Map<String, Integer>> estadisticasImcPorIdBurnout() {
        Map<Integer, Map<String, Integer>> resultados = new HashMap<>();

        for (int i = 0; i <= numDato; i++) {
            Empleado empleado = dato[i];
            if (empleado.isIsBornout()) { // Solo considerar empleados con burnout
                int idBurnout = empleado.getIdBornout();
                String categoriaImc = calcularImc(empleado.getPeso(), empleado.getTalla());

                resultados.putIfAbsent(idBurnout, new HashMap<String, Integer>() {
                    {
                        put("Bajo Peso", 0);
                        put("Peso normal", 0);
                        put("Sobrepeso", 0);
                        put("Obeso", 0);
                    }
                });

                Map<String, Integer> categorias = resultados.get(idBurnout);
                categorias.put(categoriaImc, categorias.getOrDefault(categoriaImc, 0) + 1);
            }
        }

        return resultados;
    }

    public String[] obtenerEstadisticasComoString() {
        Map<Integer, Map<String, Integer>> estadisticas = estadisticasImcPorIdBurnout();
        List<String> reporte = new ArrayList<>();

        for (Map.Entry<Integer, Map<String, Integer>> entry : estadisticas.entrySet()) {
            int idBurnout = entry.getKey();
            Map<String, Integer> categorias = entry.getValue();
            String estadisticasString = "ID Burnout " + idBurnout + ": ";
            estadisticasString += "Bajo Peso: " + categorias.get("Bajo Peso") + ", ";
            estadisticasString += "Peso Normal: " + categorias.get("Peso normal") + ", ";
            estadisticasString += "Sobrepeso: " + categorias.get("Sobrepeso") + ", ";
            estadisticasString += "Obeso: " + categorias.get("Obeso");
            System.out.println("estadisticasString: " + estadisticasString);
            reporte.add(estadisticasString);
        }

        return reporte.toArray(new String[0]); // Convertir la lista a un array de strings
    }

}
