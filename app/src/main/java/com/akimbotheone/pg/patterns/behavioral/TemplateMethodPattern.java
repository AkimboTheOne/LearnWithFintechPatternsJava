package com.akimbotheone.pg.patterns.behavioral;

import java.util.logging.Logger;

/**
 * Template Method Pattern – Behavioral Design Pattern
 * Defines a template for fund transfers, with variant transfer logic.
 */
public class TemplateMethodPattern {
    private static final Logger logger = Logger.getLogger(TemplateMethodPattern.class.getName());

    public abstract static class FundTransfer {
        public final void executeTransfer() {
            validate();
            authenticate();
            transfer();
            notifyUser();
        }
        protected void validate() {
            logger.info("Validating account");
        }
        protected void authenticate() {
            logger.info("Authenticating user");
        }
        protected abstract void transfer();
        protected void notifyUser() {
            logger.info("Notifying user");
        }
    }

    public static class DomesticTransfer extends FundTransfer {
        @Override
        protected void transfer() {
            logger.info("Domestic transfer executed");
        }
    }

    public static void main(String[] args) {
        FundTransfer ft = new DomesticTransfer();
        ft.executeTransfer();
    }
}
