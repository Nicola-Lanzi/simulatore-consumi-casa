package home_appliances;

public class Asciugatrice extends Elettrodomestico {
    private int temperaturaAsciugatura;
    private final int durataCiclo; // minuti

    public Asciugatrice() {
        super("Asciugatrice", 3.0); // consumo alto in kW/h
        this.temperaturaAsciugatura = 60;
        this.durataCiclo = 120;
    }

    public void setTemperaturaAsciugatura(int temperatura) {
        this.temperaturaAsciugatura = temperatura;

        // Temperatura più alta = consumo più alto
        double consumoBase = 2.5;
        double nuovoConsumo = consumoBase * (temperatura / 60.0);
        setConsumoOrario(nuovoConsumo);
        System.out.println(getNome() + ": temperatura asciugatura impostata a " + temperatura + "°C");
    }

    public int getTemperaturaAsciugatura() {
        return temperaturaAsciugatura;
    }

    public int getDurataCiclo() {
        return durataCiclo;
    }
}