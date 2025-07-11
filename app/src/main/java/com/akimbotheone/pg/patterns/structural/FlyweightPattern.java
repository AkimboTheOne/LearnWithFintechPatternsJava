package com.akimbotheone.pg.patterns.structural;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Flyweight Pattern – Structural Design Pattern
 * Shares account icon instances to minimize memory usage.
 */
public class FlyweightPattern {
    private static final Logger logger = Logger.getLogger(FlyweightPattern.class.getName());

    public interface Icon {
        void render(String accountId);
    }

    public class AccountIcon implements Icon {
        private final String type;

        public AccountIcon(String type) {
            this.type = type;
        }

        @Override
        public void render(String accountId) {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Rendering " + type + " icon for account " + accountId);
            }
        }
    }

    public class IconFactory {
        private final Map<String, Icon> cache = new HashMap<>();

        public Icon getIcon(String type) {
            return cache.computeIfAbsent(type, AccountIcon::new);
        }
    }

    public static void main(String[] args) {
        FlyweightPattern pattern = new FlyweightPattern();
        IconFactory factory = pattern.new IconFactory();
        Icon icon1 = factory.getIcon("savings");
        icon1.render("AC123");
        Icon icon2 = factory.getIcon("savings");
        icon2.render("AC456");
    }
}
