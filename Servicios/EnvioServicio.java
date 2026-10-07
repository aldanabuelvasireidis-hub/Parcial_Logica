package Servicios;

import java.util.ArrayList;
import java.util.List;

import Modelos.Aereo;
import Modelos.Envio;
import Modelos.Maritimo;
import Modelos.Terrestre;
import Modelos.TipoEnvio;

public class EnvioServicio {

    private static String[] encabezados = { "Tipo", "Código", "Cliente", "Peso", "Distancia", "Costo" };

    private static List<Envio> envios = new ArrayList<>();

    public static String[] getEncabezados() {
        return encabezados;
    }

    public static Envio agregar(TipoEnvio tipo,
            String codigo,
            String cliente,
            double peso,
            double distancia) {
        Envio envio = null;
        switch (tipo) {
            case TERRESTRE:
                envio = new Terrestre(codigo, cliente, peso, distancia);
                break;
            case AEREO:
                envio = new Aereo(codigo, cliente, peso, distancia);
                break;
            case MARITIMO:
                envio = new Maritimo(codigo, cliente, peso, distancia);
                break;
        }
        if (envio != null) {
            envios.add(envio);
        }
        return envio;
    }

    public static boolean existe(String codigo) {
        for (Envio envio : envios) {
            if (envio.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    public static boolean quitar(int indice) {
        if (indice >= 0 && indice < envios.size()) {
            envios.remove(indice);
            return true;
        }
        return false;
    }

    public static void cargarDatosIniciales() {
        agregar(TipoEnvio.TERRESTRE, "10001", "Polimeros Colombia", 1200, 400);
        agregar(TipoEnvio.TERRESTRE, "10002", "Textiles Pepalfa", 500, 600);
        agregar(TipoEnvio.AEREO, "10003", "Flores Colombia", 1500, 2000);
    }

    public static String[][] getDatos() {
        String[][] datos = new String[envios.size()][encabezados.length];
        for (int i = 0; i < envios.size(); i++) {
            Envio envio = envios.get(i);
            datos[i][0] = envio.getTipo();
            datos[i][1] = envio.getCodigo();
            datos[i][2] = envio.getCliente();
            datos[i][3] = String.valueOf(envio.getPeso());
            datos[i][4] = String.valueOf(envio.getDistancia());
            datos[i][5] = String.valueOf(envio.calcularTarifa());
        }
        return datos;
    }
}