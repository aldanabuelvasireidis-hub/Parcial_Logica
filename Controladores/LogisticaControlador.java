package Controladores;

import Modelos.TipoEnvio;
import Servicios.EnvioServicio;
import Vistas.LogisticaVista;

public class LogisticaControlador {

    private LogisticaVista vista;

    public LogisticaControlador(LogisticaVista vista) {
        this.vista = vista;
        EnvioServicio.cargarDatosIniciales();
        mostrarEnvios();
        this.vista.setGuardarEnvioClick(evento -> agregarEnvio());
        this.vista.setQuitarEnvioClick(evento -> quitarEnvio());
    }

    private void mostrarEnvios() {
        vista.mostrarEnvios(EnvioServicio.getDatos(), EnvioServicio.getEncabezados());
    }

    private void agregarEnvio() {
        TipoEnvio tipo = vista.getTipoSeleccionado();
        String codigo = vista.getNumero();
        String cliente = vista.getCliente();
        double peso = vista.getPeso();
        double distancia = vista.getDistancia();

        if (codigo.isEmpty() || cliente.isEmpty()) {
            vista.mostrarMensaje("El número y el cliente son obligatorios.");
            return;
        }
        if (peso <= 0 || distancia <= 0) {
            vista.mostrarMensaje("Peso y distancia deben ser números mayores que cero.");
            return;
        }
        if (EnvioServicio.existe(codigo)) {
            vista.mostrarMensaje("Ya existe un envío con el número " + codigo + ".");
            return;
        }

        EnvioServicio.agregar(tipo, codigo, cliente, peso, distancia);

        mostrarEnvios();

        vista.limpiarFormulario();
        vista.ocultarEdicionEnvio();
    }

    private void quitarEnvio() {
        int fila = vista.getFilaSeleccionada();
        if (fila < 0) {
            vista.mostrarMensaje("Seleccione un envío de la tabla para retirarlo.");
            return;
        }
        if (vista.confirmar("¿Desea retirar el envío seleccionado?")) {
            EnvioServicio.quitar(fila);
            mostrarEnvios();
        }
    }
}