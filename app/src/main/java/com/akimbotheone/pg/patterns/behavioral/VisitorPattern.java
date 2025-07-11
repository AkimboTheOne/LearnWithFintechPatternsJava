package com.akimbotheone.pg.patterns.behavioral;
import java.util.logging.Logger;

/**
 * Visitor Pattern – Behavioral Design Pattern
 * Applies operations to Transaction objects without modifying them.
 */
public class VisitorPattern {
    private static final Logger logger = Logger.getLogger(VisitorPattern.class.getName());

    public interface Visitable {
        void accept(Visitor v);
    }

    public interface Visitor {
        void visit(Transaction tx);
    }

    public class Transaction implements Visitable {
        @Override
        public void accept(Visitor v) {
            v.visit(this);
        }
    }

    public class SummaryReport implements Visitor {
        @Override public void visit(Transaction tx) {
            logger.info("Generating summary report for transaction");
        }
    }

    public static void main(String[] args) {
        VisitorPattern pattern = new VisitorPattern();
        Transaction tx = pattern.new Transaction();
        tx.accept(pattern.new SummaryReport());
    }
}
