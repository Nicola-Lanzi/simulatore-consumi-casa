package casa;
import home_appliances.*;

public class Casa {
    private final Elettrodomestico[] elettrodomestici;

    public Casa() {
        elettrodomestici = new Elettrodomestico[4];
        elettrodomestici[0] = new Lavastoviglie();
        elettrodomestici[1] = new Lavatrice();
        elettrodomestici[2] = new Asciugatrice();
        elettrodomestici[3] = new Frigo();
    }

    public void aggiornaConsumi() {
        for (Elettrodomestico e : elettrodomestici) {
            e.aggiornaConsumo();
        }
    }

    public double getConsumoTotale() {
        double totale = 0;
        for (Elettrodomestico e : elettrodomestici) {
            totale += e.getConsumoAccumulato();
        }
        return totale;
    }

    public void stampaStatoElettrodomestici() {
        System.out.println("\nSTATO ELETTRODOMESTICI");
        for (Elettrodomestico e : elettrodomestici) {
            System.out.println(e);
        }
        System.out.println("Consumo totale casa: " + String.format("%.2f", getConsumoTotale()) + " kW");
        System.out.println();
    }

    public Elettrodomestico[] getElettrodomestici() {
        return elettrodomestici;
    }

}