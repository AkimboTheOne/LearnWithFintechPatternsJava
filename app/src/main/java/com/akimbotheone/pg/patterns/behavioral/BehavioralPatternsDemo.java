package com.akimbotheone.pg.patterns.behavioral;

import java.util.logging.Logger;

/**
 * Demonstration class for all Behavioral Design Patterns
 */
public class BehavioralPatternsDemo {
    private static final Logger logger = Logger.getLogger(BehavioralPatternsDemo.class.getName());

    public static void main(String[] args) {
        logger.info("=== Behavioral Design Patterns Demo ===");

        logger.info("\n1. Iterator Pattern:");
        IteratorPattern.main(args);

        logger.info("\n2. Command Pattern:");
        CommandPattern.main(args);

        logger.info("\n3. Observer Pattern:");
        ObserverPattern.main(args);

        logger.info("\n4. Template Method Pattern:");
        TemplateMethodPattern.main(args);

        logger.info("\n5. Strategy Pattern:");
        StrategyPattern.main(args);

        logger.info("\n6. Chain of Responsibility Pattern:");
        ChainOfResponsibilityPattern.main(args);

        logger.info("\n7. Interpreter Pattern:");
        InterpreterPattern.main(args);

        logger.info("\n8. Mediator Pattern:");
        MediatorPattern.main(args);

        logger.info("\n9. Memento Pattern:");
        MementoPattern.main(args);

        logger.info("\n10. Null Object Pattern:");
        NullObjectPattern.main(args);

        logger.info("\n11. State Pattern:");
        StatePattern.main(args);

        logger.info("\n12. Visitor Pattern:");
        VisitorPattern.main(args);

        logger.info("\n=== Behavioral Demo Complete ===");
    }
}
