package com.akimbotheone.pg.patterns.creational;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Factory Method Pattern – Creational Design Pattern
 * Creates TransactionProcessor instances without exposing their concrete classes.
 */
public class FactoryMethodPattern {
    private static final Logger logger = Logger.getLogger(FactoryMethodPattern.class.getName());

    public enum ProcessorType { CREDIT, WIRE }

    public interface TransactionProcessor {
        void processTransaction(double amount);
    }

    public class CreditCardProcessor implements TransactionProcessor {
        @Override
        public void processTransaction(double amount) {
            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("Processing credit-card transaction: $%.2f", amount));
            }
        }
    }

    public class WireTransferProcessor implements TransactionProcessor {
        @Override
        public void processTransaction(double amount) {
            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("Processing wire transfer transaction: $%.2f", amount));
            }
        }
    }

    public class ProcessorFactory {
        public TransactionProcessor getProcessor(ProcessorType type) {
            Objects.requireNonNull(type, "ProcessorType must not be null");
            return switch (type) {
                case CREDIT -> new CreditCardProcessor();
                case WIRE   -> new WireTransferProcessor();
            };
        }
    }

    public static void main(String[] args) {
        FactoryMethodPattern pattern = new FactoryMethodPattern();
        ProcessorFactory factory = pattern.new ProcessorFactory();
        TransactionProcessor processor = factory.getProcessor(ProcessorType.CREDIT);
        processor.processTransaction(250.00);
    }
}
