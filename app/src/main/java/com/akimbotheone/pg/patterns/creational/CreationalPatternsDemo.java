package com.akimbotheone.pg.patterns.creational;

import java.util.logging.Logger;

/**
 * Demonstration class for all Creational Design Patterns
 */
public class CreationalPatternsDemo {
    private static final Logger logger = Logger.getLogger(CreationalPatternsDemo.class.getName());

    public static void main(String[] args) {
        logger.info("=== Creational Design Patterns Demo ===");

        logger.info("\n1. Factory Method Pattern:");
        FactoryMethodPattern.main(args);

        logger.info("\n2. Abstract Factory Pattern:");
        AbstractFactoryPattern.main(args);

        logger.info("\n3. Singleton Pattern:");
        SingletonPattern.main(args);

        logger.info("\n4. Builder Pattern:");
        BuilderPattern.main(args);

        logger.info("\n5. Prototype Pattern:");
        PrototypePattern.main(args);

        logger.info("\n6. Object Pool Pattern:");
        ObjectPoolPattern.main(args);

        logger.info("\n=== Creational Demo Complete ===");
    }
}
