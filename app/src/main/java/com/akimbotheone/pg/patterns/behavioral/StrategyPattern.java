package com.akimbotheone.pg.patterns.behavioral;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Strategy Pattern – Behavioral Design Pattern
 * Encapsulates tax calculation algorithms for different regions.
 */
public class StrategyPattern {
    private static final Logger logger = Logger.getLogger(StrategyPattern.class.getName());

    /** Strategy interface for tax calculation. */
    public interface TaxStrategy {
        double calculate(double income);
    }

    /** Concrete strategy for US tax rules. */
    public class USStrategy implements TaxStrategy {
        @Override
        public double calculate(double income) {
            return income * 0.30;
        }
    }

    /** Concrete strategy for UK tax rules. */
    public class UKStrategy implements TaxStrategy {
        @Override
        public double calculate(double income) {
            return income * 0.25;
        }
    }

    /** Context class using a TaxStrategy. */
    public class TaxCalculator {
        private final TaxStrategy strategy;

        public TaxCalculator(TaxStrategy strategy) {
            this.strategy = Objects.requireNonNull(strategy, "Strategy must not be null");
        }

        public double apply(double amount) {
            return strategy.calculate(amount);
        }
    }

    public static void main(String[] args) {
        StrategyPattern pattern = new StrategyPattern();
        TaxCalculator calc = pattern.new TaxCalculator(pattern.new USStrategy());
        double tax = calc.apply(100_000);
        logger.info("Calculated tax: " + tax);
    }
}
