package ej7_mvc;

import javax.swing.JOptionPane;


public class ControladorPrincipal {
    private ModeloPrincipal modelo;
    private VistaPrincipal vista;

    public ControladorPrincipal(ModeloPrincipal modelo, VistaPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.getBtnEjecutar().addActionListener(e -> ejecutarAccion());
        vista.getBtnLimpiar().addActionListener(e -> vista.limpiarCampos());

        actualizarVista();
    }


    private void ejecutarAccion() {
        String accion = vista.getAccionSeleccionada();

        try {
            switch (accion) {
                case "Incrementar contador":
                    modelo.incrementar();
                    break;

                case "Decrementar contador":
                    modelo.decrementar();
                    break;

                case "Reiniciar contador":
                    modelo.reiniciarContador();
                    break;

                case "Guardar nombre":
                    guardarNombre();
                    break;

                case "Sumar nÃºmeros":
                    double suma = modelo.sumar(leerNumero(vista.getNumero1()), leerNumero(vista.getNumero2()));
                    vista.mostrarResultado("Resultado de la suma: " + suma);
                    actualizarVista();
                    return;

                case "Restar nÃºmeros":
                    double resta = modelo.restar(leerNumero(vista.getNumero1()), leerNumero(vista.getNumero2()));
                    vista.mostrarResultado("Resultado de la resta: " + resta);
                    actualizarVista();
                    return;

                case "Mostrar resumen del modelo":
                    vista.mostrarResultado(modelo.generarResumen());
                    actualizarVista();
                    return;
            }

            actualizarVista();
            vista.mostrarResultado(modelo.getUltimaAccion());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Debe ingresar nÃºmeros vÃ¡lidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "ValidaciÃ³n", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void guardarNombre() {
        String nombre = vista.getNombre();

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar un nombre.");
        }

        modelo.guardarNombre(nombre);
    }

    private double leerNumero(String texto) {
        return Double.parseDouble(texto.trim().replace(",", "."));
    }

    private void actualizarVista() {
        vista.mostrarContador(modelo.getContador());
    }
}
