package com.akimbotheone.pg.patterns.behavioral;
import java.util.logging.Logger;

/**
 * State Pattern – Behavioral Design Pattern
 * Changes LoanContext behavior based on its current state.
 */
public class StatePattern {
    private static final Logger logger = Logger.getLogger(StatePattern.class.getName());

    public interface LoanState {
        void handle();
    }

    public class DraftState implements LoanState {
        @Override public void handle() { logger.info("Loan is in draft state"); }
    }

    public class ApprovedState implements LoanState {
        @Override public void handle() { logger.info("Loan has been approved"); }
    }

    public class LoanContext {
        private LoanState state;
        public void setState(LoanState s) { this.state = s; }
        public void process() { state.handle(); }
    }

    public static void main(String[] args) {
        StatePattern pattern = new StatePattern();
        LoanContext ctx = pattern.new LoanContext();
        ctx.setState(pattern.new DraftState());
        ctx.process();
        ctx.setState(pattern.new ApprovedState());
        ctx.process();
    }
}
