package com.akimbotheone.pg.patterns.behavioral;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Interpreter Pattern – Behavioral Design Pattern
 * Interprets boolean financial rules against a context.
 */
public class InterpreterPattern {
    private static final Logger logger = Logger.getLogger(InterpreterPattern.class.getName());

    /** Context with data to interpret. */
    public static class Context {
        public final int amount;
        public final String country;
        public Context(int amount, String country) {
            this.amount = amount;
            this.country = country;
        }
    }

    /** Expression interface. */
    public interface Expression {
        boolean interpret(Context context);
    }

    /** Concrete expression for amount comparison. */
    public class AmountExpression implements Expression {
        @Override
        public boolean interpret(Context ctx) {
            return ctx.amount > 1000;
        }
    }

    /** Concrete expression for country comparison. */
    public class CountryExpression implements Expression {
        @Override
        public boolean interpret(Context ctx) {
            return "US".equals(ctx.country);
        }
    }

    /** Composite expression for logical AND. */
    public class AndExpression implements Expression {
        private final Expression exp1, exp2;
        public AndExpression(Expression exp1, Expression exp2) {
            this.exp1 = Objects.requireNonNull(exp1);
            this.exp2 = Objects.requireNonNull(exp2);
        }
        @Override
        public boolean interpret(Context ctx) {
            return exp1.interpret(ctx) && exp2.interpret(ctx);
        }
    }

    public static void main(String[] args) {
        InterpreterPattern pattern = new InterpreterPattern();
        Expression rule = pattern.new AndExpression(pattern.new AmountExpression(), pattern.new CountryExpression());
        boolean result = rule.interpret(new Context(1200, "US"));
        logger.info("Interpretation result: " + result);
    }
}
