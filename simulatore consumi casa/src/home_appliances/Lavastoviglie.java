package home_appliances;

public class Lavastoviglie extends Elettrodomestico {
    private int programma;
    private double temperatura;

    public Lavastoviglie() {
        super("Lavastoviglie", 1.2);
        this.programma = 1;
        this.temperatura = 55;
    }

    public void setProgramma(int programma) {
        this.programma = programma;
        switch(programma) {
            case 1: // Eco
                setConsumoOrario(1.0);
                temperatura = 45;
                break;
            case 2: // Intensivo
                setConsumoOrario(1.5);
                temperatura = 65;
                break;
            case 3: // Rapido
                setConsumoOrario(0.8);
                temperatura = 40;
                break;
        }
        System.out.println(getNome() + ": programma " + programma + " impostato (Temperatura: " + temperatura + "°C)");
    }

    public int getProgramma() {
        return programma;
    }

    public double getTemperatura() {
        return temperatura;
    }
}