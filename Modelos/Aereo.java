package Modelos;

public class Aereo extends Envio {

    private static final double TARIFA_BASE_KM = 5000;
    private static final double RECARGO_KG = 4000;

    public Aereo(String codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }

    @Override
    public String getTipo() {
        return "Aéreo";
    }

    @Override
    public double calcularTarifa() {
        return TARIFA_BASE_KM * getDistancia() + RECARGO_KG * getPeso();
    }
}