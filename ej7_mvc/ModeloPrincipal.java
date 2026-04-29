package ej7_mvc;


public class ModeloPrincipal {
    
     private int contador = 0;
    private String nombre = "";
    private double numero1 = 0;
    private double numero2 = 0;
    private String ultimaAccion = "TodavÃ­a no se realizÃ³ ninguna acciÃ³n.";

    public int getContador() {
        return contador;
    }

    public String getUltimaAccion() {
        return ultimaAccion;
    }

    public void incrementar() {
        contador++;
        ultimaAccion = "Contador incrementado. Valor actual: " + contador;
    }

    public void decrementar() {
        contador--;
        ultimaAccion = "Contador decrementado. Valor actual: " + contador;
    }

    public void reiniciarContador() {
        contador = 0;
        ultimaAccion = "Contador reiniciado.";
    }

    public void guardarNombre(String nombre) {
        this.nombre = nombre.trim();
        ultimaAccion = "Nombre guardado desde el modelo: " + this.nombre;
    }

    public double sumar(double numero1, double numero2) {
        this.numero1 = numero1;
        this.numero2 = numero2;
        double resultado = this.numero1 + this.numero2;
        ultimaAccion = "Suma realizada: " + this.numero1 + " + " + this.numero2 + " = " + resultado;
        return resultado;
    }

    public double restar(double numero1, double numero2) {
        this.numero1 = numero1;
        this.numero2 = numero2;
        double resultado = this.numero1 - this.numero2;
        ultimaAccion = "Resta realizada: " + this.numero1 + " - " + this.numero2 + " = " + resultado;
        return resultado;
    }

    public String generarResumen() {
        return "Resumen del modelo:\n"
                + "Nombre: " + (nombre.isEmpty() ? "No cargado" : nombre) + "\n"
                + "Contador: " + contador + "\n"
                + "Ãšltima acciÃ³n: " + ultimaAccion;
    }
}
