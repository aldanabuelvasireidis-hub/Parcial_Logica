package Modelos;

public class Terrestre extends Envio {

    private static final double TARIFA_BASE_KM = 1500;
    private static final double RECARGO_KG = 2000;

    public Terrestre(String codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }

    @Override
    public String getTipo() {
        return "Terrestre";
    }

    @Override
    public double calcularTarifa() {
        return TARIFA_BASE_KM * getDistancia() + RECARGO_KG * getPeso();
    }
}