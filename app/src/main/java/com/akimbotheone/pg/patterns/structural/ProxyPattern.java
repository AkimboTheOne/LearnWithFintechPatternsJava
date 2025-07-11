package com.akimbotheone.pg.patterns.structural;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Proxy Pattern – Structural Design Pattern
 * Controls access to credit service with role-based authorization.
 */
public class ProxyPattern {
    private static final Logger logger = Logger.getLogger(ProxyPattern.class.getName());

    public interface CreditService {
        int getScore(String userId);
    }

    public class RealCreditService implements CreditService {
        @Override
        public int getScore(String userId) {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Accessing real credit data for " + userId);
            }
            return 750;
        }
    }

    public class CreditServiceProxy implements CreditService {
        private final RealCreditService real = new RealCreditService();
        private final Set<String> roles = Set.of("admin","audit");
        private final String role;

        public CreditServiceProxy(String role) {
            this.role = role;
        }

        @Override
        public int getScore(String userId) {
            if (!roles.contains(role)) {
                throw new SecurityException("Access denied for role: " + role);
            }
            return real.getScore(userId);
        }
    }

    public static void main(String[] args) {
        ProxyPattern pattern = new ProxyPattern();
        CreditService service = pattern.new CreditServiceProxy("admin");
        System.out.println("Score: " + service.getScore("U123"));
    }
}
