package com.akimbotheone.pg.patterns.behavioral;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Observer Pattern – Behavioral Design Pattern
 * Notifies registered observers upon price updates.
 */
public class ObserverPattern {
    private static final Logger logger = Logger.getLogger(ObserverPattern.class.getName());

    public interface Observer {
        void update(String asset, double price);
    }

    public static class PriceFeed {
        private final List<Observer> observers = new ArrayList<>();

        public void add(Observer o) {
            observers.add(o);
        }

        public void setPrice(String asset, double price) {
            for (Observer o : observers) {
                o.update(asset, price);
            }
        }
    }

    public static class Dashboard implements Observer {
        @Override
        public void update(String asset, double price) {
            logger.info("Dashboard: " + asset + " -> $" + price);
        }
    }

    public static void main(String[] args) {
        PriceFeed feed = new PriceFeed();
        feed.add(new Dashboard());
        feed.setPrice("BTC", 30500.0);
    }
}
