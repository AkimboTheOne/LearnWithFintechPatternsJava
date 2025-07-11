package com.akimbotheone.pg.patterns.structural;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Composite Pattern – Structural Design Pattern
 * Represents portfolio and accounts uniformly.
 */
public class CompositePattern {
    private static final Logger logger = Logger.getLogger(CompositePattern.class.getName());

    /**
     * Component interface.
     */
    public interface AccountComponent {
        void display();
    }

    /**
     * Leaf class representing an account.
     */
    public class Account implements AccountComponent {
        private final String name;

        public Account(String name) {
            this.name = name;
        }

        @Override
        public void display() {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Account: " + name);
            }
        }
    }

    /**
     * Composite class representing a portfolio.
     */
    public class Portfolio implements AccountComponent {
        private final String name;
        private final List<AccountComponent> components = new ArrayList<>();

        public Portfolio(String name) {
            this.name = name;
        }

        public void add(AccountComponent component) {
            components.add(component);
        }

        @Override
        public void display() {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Portfolio: " + name);
            }
            for (AccountComponent comp : components) {
                comp.display();
            }
        }
    }

    public static void main(String[] args) {
        CompositePattern pattern = new CompositePattern();
        Portfolio main = pattern.new Portfolio("Main");
        main.add(pattern.new Account("Savings"));
        Portfolio inv = pattern.new Portfolio("Investments");
        inv.add(pattern.new Account("Stocks"));
        inv.add(pattern.new Account("Bonds"));
        main.add(inv);
        main.display();
    }
}
