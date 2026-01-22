package home_appliances;

public class Lavatrice extends Elettrodomestico {
    private int velocitaCentrifuga;
    private final double capacita;

    public Lavatrice() {
        super("Lavatrice", 2.0);
        this.velocitaCentrifuga = 1200;
        this.capacita = 7;
    }

    public void setVelocitaCentrifuga(int velocita) {
        this.velocitaCentrifuga = velocita;
        // Maggiore velocità = maggiore consumo
        double consumoBase = getConsumoOrario();
        double nuovoConsumo = consumoBase * (velocita / 1200.0);
        setConsumoOrario(nuovoConsumo);
        System.out.println(getNome() + ": velocità centrifuga impostata a " + velocita + " giri/min");
    }

    public int getVelocitaCentrifuga() {
        return velocitaCentrifuga;
    }

    public double getCapacita() {
        return capacita;
    }
}