import casa.Casa;
import time.Clock;

void main() {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    IO.println("SIMULAZIONE CONSUMI DOMESTICI");
    IO.println("Inserisci la durata della simulazione in ore: ");
    int oreSimulazione = scanner.nextInt();
    int durataMinuti = oreSimulazione * 60;

    IO.println("CONFIGURAZIONE ELETTRODOMESTICI");

    Clock clock = new Clock(durataMinuti);
    Casa casa = clock.getCasa();

    IO.println("\n1. Configurazione Lavastoviglie:");
    IO.println("   Programmi disponibili:");
    IO.println("   1 - Eco (45°C, 1.0 kW)");
    IO.println("   2 - Intensivo (65°C, 1.5 kW)");
    IO.println("   3 - Rapido (40°C, 0.8 kW)");
    IO.println("   Seleziona programma (1-3): ");
    int programmaLavastoviglie = scanner.nextInt();
    ((home_appliances.Lavastoviglie) casa.getElettrodomestici()[0]).setProgramma(programmaLavastoviglie);

    IO.println("\n2. Configurazione Lavatrice:");
    IO.println("   Velocità centrifuga (400-1600 giri/min): ");
    int velocitaCentrifuga = scanner.nextInt();
    ((home_appliances.Lavatrice) casa.getElettrodomestici()[1]).setVelocitaCentrifuga(velocitaCentrifuga);

    IO.println("\n3. Configurazione Asciugatrice:");
    IO.println("   Temperatura asciugatura (40-80°C): ");
    int tempAsciugatrice = scanner.nextInt();
    ((home_appliances.Asciugatrice) casa.getElettrodomestici()[2]).setTemperaturaAsciugatura(tempAsciugatrice);

    IO.println("\n4. Configurazione Frigo:");
    IO.println("   Temperatura frigo (2-8°C): ");
    double tempFrigo = scanner.nextDouble();
    ((home_appliances.Frigo) casa.getElettrodomestici()[3]).setTemperatura(tempFrigo);

    int[] tempoRimanenteAccensione = new int[4];
    boolean[] accensioneProgrammata = new boolean[4];

    IO.println("\nINIZIO SIMULAZIONE");
    IO.println("Inizio simulazione: " + clock);
    IO.println("Durata simulazione: " + oreSimulazione + " ore (" + durataMinuti + " minuti)");
    casa.stampaStatoElettrodomestici();

    tempoRimanenteAccensione[3] = Integer.MAX_VALUE;
    casa.getElettrodomestici()[3].accendi();

    int oraInizioLavaggio = -1;
    boolean lavaggioAvviato = false;

    while (!clock.isSimulationFinished()) {
        clock.tick();

        if (clock.getMinute() == 0) {
            for (int i = 0; i < 3; i++) {
                if (!casa.getElettrodomestici()[i].isAcceso() && tempoRimanenteAccensione[i] <= 0) {
                    if (random.nextDouble() < 0.6) {
                        casa.getElettrodomestici()[i].accendi();
                        tempoRimanenteAccensione[i] = 180; // 3 ore
                        accensioneProgrammata[i] = false;

                        switch (i) {
                            case 0: // Lavastoviglie
                                int prog = ((home_appliances.Lavastoviglie) casa.getElettrodomestici()[0]).getProgramma();
                                String[] nomiProgrammi = {"Eco", "Intensivo", "Rapido"};
                                IO.println("Ora " + clock.getHour() + ":00 - Lavastoviglie acceso per 3 ore (Programma: " +
                                        nomiProgrammi[prog - 1] + ", " +
                                        ((home_appliances.Lavastoviglie) casa.getElettrodomestici()[0]).getTemperatura() + "°C)");
                                break;
                            case 1: // Lavatrice
                                IO.println("Ora " + clock.getHour() + ":00 - Lavatrice acceso per 3 ore (" +
                                        ((home_appliances.Lavatrice) casa.getElettrodomestici()[1]).getVelocitaCentrifuga() + " giri/min)");
                                break;
                            case 2: // Asciugatrice
                                IO.println("Ora " + clock.getHour() + ":00 - Asciugatrice acceso per 3 ore (" +
                                        ((home_appliances.Asciugatrice) casa.getElettrodomestici()[2]).getTemperaturaAsciugatura() + "°C)");
                                break;
                        }

                        if (i == 1 && !lavaggioAvviato) {
                            oraInizioLavaggio = clock.getHour();
                            lavaggioAvviato = true;
                            IO.println("   >> Ciclo di lavaggio iniziato. Asciugatura tra 2 ore.");
                        }
                    }
                }
            }

            if (lavaggioAvviato && clock.getHour() == (oraInizioLavaggio + 2) % 24) {
                if (!casa.getElettrodomestici()[2].isAcceso() && tempoRimanenteAccensione[2] <= 0) {
                    casa.getElettrodomestici()[2].accendi();
                    tempoRimanenteAccensione[2] = 120;
                    IO.println("Ora " + clock.getHour() + ":00 - Asciugatrice acceso automaticamente dopo lavaggio (" +
                            ((home_appliances.Asciugatrice) casa.getElettrodomestici()[2]).getDurataCiclo() + " min di ciclo)");
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            if (tempoRimanenteAccensione[i] > 0) {
                tempoRimanenteAccensione[i]--;
                if (tempoRimanenteAccensione[i] == 0 && casa.getElettrodomestici()[i].isAcceso()) {
                    casa.getElettrodomestici()[i].spegni();
                    String nomeElettrodomestico = casa.getElettrodomestici()[i].getNome();

                    if (i == 2) {
                        lavaggioAvviato = false;
                        IO.println(nomeElettrodomestico + " spento dopo ciclo completo di asciugatura");
                    } else {
                        IO.println(nomeElettrodomestico + " spento dopo 3 ore di utilizzo");
                    }
                }
            }
        }

        if (clock.getElapsedTime() % 60 == 0 && clock.getElapsedTime() > 0) {
            int oraSimulazione = clock.getElapsedTime() / 60;
            IO.println("\nOra di simulazione: " + oraSimulazione + " - Tempo: " + clock);

            if (oraSimulazione % 6 == 0) {
                IO.println("DETTAGLI CONFIGURAZIONE");
                IO.println("Frigo: " + ((home_appliances.Frigo) casa.getElettrodomestici()[3]).getTemperatura() + "°C" +
                        (((home_appliances.Frigo) casa.getElettrodomestici()[3]).hasCongelatore() ? " (con congelatore)" : ""));
                IO.println("Lavatrice: " + ((home_appliances.Lavatrice) casa.getElettrodomestici()[1]).getCapacita() + " kg, " +
                        ((home_appliances.Lavatrice) casa.getElettrodomestici()[1]).getVelocitaCentrifuga() + " giri/min");
            }

            casa.stampaStatoElettrodomestici();
        }
    }

    IO.println("\nFINE SIMULAZIONE");
    IO.println("Tempo finale: " + clock);
    casa.stampaStatoElettrodomestici();

    double consumoKWh = casa.getConsumoTotale() / 60.0;
    double costo = consumoKWh * 0.20;

    IO.println("RIEPILOGO FINALE");
    IO.println("Durata totale simulazione: " + oreSimulazione + " ore");
    IO.println("Consumo totale: " + String.format("%.2f", consumoKWh) + " kWh");
    IO.println("Costo stimato (" + String.format("%.2f", 0.20) + " €/kWh): " + String.format("%.2f", costo) + " €");

    for (int i = 0; i < 4; i++) {
        casa.getElettrodomestici()[i].resetConsumo();
    }

    scanner.close();
}