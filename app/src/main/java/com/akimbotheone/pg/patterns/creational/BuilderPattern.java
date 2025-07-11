package com.akimbotheone.pg.patterns.creational;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Builder Pattern – Creational Design Pattern
 * Separates complex object construction from its representation.
 */
public class BuilderPattern {
    private static final Logger logger = Logger.getLogger(BuilderPattern.class.getName());

    public static class MortgageApplication {
        private String applicant;
        private double income;
        private boolean hasCreditHistory;

        private MortgageApplication() {}

        public void display() {
            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("Applicant: %s, Income: %.2f, CreditHistory: %s",
                    applicant, income, hasCreditHistory));
            }
        }

        public static class Builder {
            private final MortgageApplication app = new MortgageApplication();

            public Builder setApplicant(String name) {
                Objects.requireNonNull(name, "Applicant name must not be null");
                app.applicant = name;
                return this;
            }
            public Builder setIncome(double income) {
                app.income = income;
                return this;
            }
            public Builder setCreditHistory(boolean hasHistory) {
                app.hasCreditHistory = hasHistory;
                return this;
            }
            public MortgageApplication build() {
                return app;
            }
        }
    }

    public static void main(String[] args) {
        MortgageApplication app = new MortgageApplication.Builder()
            .setApplicant("John Doe")
            .setIncome(95000)
            .setCreditHistory(true)
            .build();
        app.display();
    }
}
