package time;

import casa.Casa;

public class Clock {
    private int hour;
    private int minute;
    private int day;
    private final Casa casa;
    private final int simulationDuration;
    private int elapsedTime;

    public Clock(int simulationDuration) {
        this.hour = 0;
        this.minute = 0;
        this.day = 0;
        this.casa = new Casa();
        this.simulationDuration = simulationDuration;
        this.elapsedTime = 0;
    }

    public void tick() {
        increment();
        casa.aggiornaConsumi();
        elapsedTime++;
    }

    private void increment() {
        minute++;
        if (minute == 60) {
            minute = 0;
            hour++;
        }
        if (hour == 24) {
            hour = 0;
            day++;
        }
    }

    public boolean isSimulationFinished() {
        return elapsedTime >= simulationDuration;
    }

    public Casa getCasa() {
        return casa;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    @Override
    public String toString() {
        return "Giorno " + day + " - " + String.format("%02d", hour) + ":" + String.format("%02d", minute);
    }
}