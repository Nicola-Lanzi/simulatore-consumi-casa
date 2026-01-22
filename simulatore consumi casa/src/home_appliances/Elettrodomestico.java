package home_appliances;

public abstract class Elettrodomestico {
    private final String nome;
    private boolean acceso;
    private double consumoOrario;
    private double consumoAccumulato;

    public Elettrodomestico(String nome, double consumoOrario) {
        this.nome = nome;
        this.consumoOrario = consumoOrario;
        this.acceso = false;
        this.consumoAccumulato = 0;
    }

    public void accendi() {
        this.acceso = true;
        System.out.println(nome + " acceso");
    }

    public void spegni() {
        this.acceso = false;
        System.out.println(nome + " spento");
    }

    public void aggiornaConsumo() {
        if (acceso) {
            consumoAccumulato += consumoOrario / 60.0;
        }
    }

    public boolean isAcceso() {
        return acceso;
    }

    public double getConsumoOrario() {
        return consumoOrario;
    }

    public double getConsumoAccumulato() {
        return consumoAccumulato;
    }

    public String getNome() {
        return nome;
    }

    public void resetConsumo() {
        consumoAccumulato = 0;
    }

    public void setConsumoOrario(double consumoOrario) {
        this.consumoOrario = consumoOrario;
    }

    @Override
    public String toString() {
        return String.format("%s [%s] - Consumo orario: %.2f kW - Consumo accumulato: %.2f kW",
                nome, acceso ? "ACCESO" : "SPENTO", consumoOrario, consumoAccumulato);
    }
}