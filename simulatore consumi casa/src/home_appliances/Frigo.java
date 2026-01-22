package home_appliances;

public class Frigo extends Elettrodomestico {
    private double temperatura;
    private final boolean congelatore;

    public Frigo() {
        super("Frigo", 0.1);
        this.temperatura = 4;
        this.congelatore = true;
        accendi();
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;

        // Temperatura più bassa = consumo più alto
        double consumoBase = 0.1;
        double nuovoConsumo = consumoBase * (8 / temperatura);
        setConsumoOrario(nuovoConsumo);
        System.out.println(getNome() + ": temperatura impostata a " + temperatura + "°C");
    }

    public double getTemperatura() {
        return temperatura;
    }

    public boolean hasCongelatore() {
        return congelatore;
    }
}