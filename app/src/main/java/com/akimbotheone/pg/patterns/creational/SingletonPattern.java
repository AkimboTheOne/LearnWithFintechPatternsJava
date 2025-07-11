package com.akimbotheone.pg.patterns.creational;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton Pattern – Creational Design Pattern
 * Ensures a single instance of a shared configuration.
 */
public class SingletonPattern {
    private static final Logger logger = Logger.getLogger(SingletonPattern.class.getName());
    private static SingletonPattern instance;
    private double rate;

    private SingletonPattern() { this.rate = 0.05; }

    public static synchronized SingletonPattern getInstance() {
        if (instance == null) {
            instance = new SingletonPattern();
        }
        return instance;
    }

    public double getRate() { return rate; }
    public void setRate(double rate) { this.rate = rate; }

    public static void main(String[] args) {
        SingletonPattern config = SingletonPattern.getInstance();
        if (logger.isLoggable(Level.INFO)) {
            logger.info("Current rate: " + config.getRate());
        }
    }
}
