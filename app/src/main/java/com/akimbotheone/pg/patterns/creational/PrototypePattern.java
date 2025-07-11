package com.akimbotheone.pg.patterns.creational;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Prototype Pattern – Creational Design Pattern
 * Supports efficient object duplication without relying on constructors.
 */
public class PrototypePattern {
    private static final Logger logger = Logger.getLogger(PrototypePattern.class.getName());

    public static class KYCProfile implements Cloneable {
        private String name;
        private String id;

        public KYCProfile(String name, String id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public KYCProfile clone() {
            try {
                return (KYCProfile) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }

        public void display() {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("KYC: " + name + ", ID: " + id);
            }
        }
    }

    public static void main(String[] args) {
        KYCProfile original = new KYCProfile("Alice", "A1234");
        KYCProfile copy = original.clone();
        copy.display();
    }
}
