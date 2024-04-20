/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yorsh
 */
public class Empleado {

    private int codigo;
    private String nombre;
    private int edad;
    private float peso;
    private float talla;

    private boolean isBornout;
    private String causaBornout;
    private int idBornout;

    public Empleado() {
    }

    public Empleado(int codigo, String nombre, int edad, float peso, float talla, boolean isBornout, String causaBornout, int idBornout) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.talla = talla;
        this.isBornout = isBornout;
        this.causaBornout = causaBornout;
        this.idBornout = idBornout;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getTalla() {
        return talla;
    }

    public void setTalla(float talla) {
        this.talla = talla;
    }

    public boolean isIsBornout() {
        return isBornout;
    }

    public void setIsBornout(boolean isBornout) {
        this.isBornout = isBornout;
    }

    public String getCausaBornout() {
        return causaBornout;
    }

    public void setCausaBornout(String causaBornout) {
        this.causaBornout = causaBornout;
    }

    public int getIdBornout() {
        return idBornout;
    }

    public void setIdBornout(int idBornout) {
        this.idBornout = idBornout;
    }


    @Override
    public String toString() {
        return codigo + " " + nombre + " " + edad + " " + peso + " " + talla;
    }

}
