package com.akimbotheone.pg.patterns.behavioral;
import java.util.logging.Logger;

/**
 * Memento Pattern – Behavioral Design Pattern
 * Saves and restores Loan internal state.
 */
public class MementoPattern {
    private static final Logger logger = Logger.getLogger(MementoPattern.class.getName());

    /** Memento: stores state. */
    public static class LoanState {
        private final String status;
        public LoanState(String status) { this.status = status; }
        public String getStatus() { return status; }
    }

    /** Originator: Loan object. */
    public class Loan {
        private String status;
        public void setStatus(String s) { this.status = s; }
        public LoanState save() { return new LoanState(status); }
        public void restore(LoanState m) { this.status = m.getStatus(); }
        public void print() { logger.info("Status: " + status); }
    }

    public static void main(String[] args) {
        MementoPattern pattern = new MementoPattern();
        Loan loan = pattern.new Loan();
        loan.setStatus("Submitted");
        LoanState saved = loan.save();
        loan.setStatus("Approved");
        loan.restore(saved);
        loan.print();
    }
}
