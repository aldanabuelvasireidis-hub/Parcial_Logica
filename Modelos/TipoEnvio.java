package Modelos;

public enum TipoEnvio {
    TERRESTRE("Terrestre"),
    AEREO("Aéreo"),
    MARITIMO("Marítimo");

    private final String nombre;

    TipoEnvio(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}