package com.akimbotheone.pg.patterns.structural;

import java.util.logging.Logger;

/**
 * Demonstration class for all Structural Design Patterns
 */
public class StructuralPatternsDemo {
    private static final Logger logger = Logger.getLogger(StructuralPatternsDemo.class.getName());

    public static void main(String[] args) {
        logger.info("=== Structural Design Patterns Demo ===");

        logger.info("\n1. Adapter Pattern:");
        AdapterPattern.main(args);

        logger.info("\n2. Bridge Pattern:");
        BridgePattern.main(args);

        logger.info("\n3. Composite Pattern:");
        CompositePattern.main(args);

        logger.info("\n4. Decorator Pattern:");
        DecoratorPattern.main(args);

        logger.info("\n5. Facade Pattern:");
        FacadePattern.main(args);

        logger.info("\n6. Flyweight Pattern:");
        FlyweightPattern.main(args);

        logger.info("\n7. Proxy Pattern:");
        ProxyPattern.main(args);

        logger.info("\n=== Demo Complete ===");
    }
}
