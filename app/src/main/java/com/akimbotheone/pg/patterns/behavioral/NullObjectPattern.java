package com.akimbotheone.pg.patterns.behavioral;
import java.util.logging.Logger;

/**
 * Null Object Pattern – Behavioral Design Pattern
 * Uses a placeholder for missing Customer instances.
 */
public class NullObjectPattern {
    private static final Logger logger = Logger.getLogger(NullObjectPattern.class.getName());

    public interface Customer {
        String getName();
    }

    public class RealCustomer implements Customer {
        private final String name;
        public RealCustomer(String name) { this.name = name; }
        @Override public String getName() { return name; }
    }

    public class NullCustomer implements Customer {
        @Override public String getName() { return "Unknown"; }
    }

    public static void main(String[] args) {
        NullObjectPattern pattern = new NullObjectPattern();
        Customer cust = pattern.new NullCustomer();
        logger.info("Customer name: " + cust.getName());
    }
}
