package com.akimbotheone.pg.patterns.structural;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Adapter Pattern – Structural Design Pattern
 * Adapts LegacyCustomerService (SOAP/XML) to CustomerService (JSON).
 */
public class AdapterPattern {
    private static final Logger logger = Logger.getLogger(AdapterPattern.class.getName());

    /**
     * Target interface expected by clients.
     */
    public interface CustomerService {
        String getCustomerJson(String customerId);
    }

    /**
     * Adaptee: legacy SOAP-based service.
     */
    public class LegacyCustomerService {
        public String getCustomerXML(String customerId) {
            return "<customer><id>" + customerId + "</id></customer>";
        }
    }

    /**
     * Adapter: translates XML from LegacyCustomerService into JSON.
     */
    public class CustomerServiceAdapter implements CustomerService {
        private final LegacyCustomerService legacy = new LegacyCustomerService();

        @Override
        public String getCustomerJson(String customerId) {
            Objects.requireNonNull(customerId, "CustomerId must not be null");
            String xml = legacy.getCustomerXML(customerId);
            // Simplified transformation
            String json = "{\"id\":\"" + customerId + "\"}";
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Converted XML to JSON: " + json);
            }
            return json;
        }
    }

    public static void main(String[] args) {
        AdapterPattern pattern = new AdapterPattern();
        CustomerService service = pattern.new CustomerServiceAdapter();
        System.out.println(service.getCustomerJson("123"));
    }
}
