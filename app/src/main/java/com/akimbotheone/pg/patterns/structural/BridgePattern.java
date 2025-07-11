package com.akimbotheone.pg.patterns.structural;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Bridge Pattern – Structural Design Pattern
 * Separates Alert hierarchy from NotificationSender implementations.
 */
public class BridgePattern {
    private static final Logger logger = Logger.getLogger(BridgePattern.class.getName());

    /**
     * Implementor interface.
     */
    public interface NotificationSender {
        void send(String message);
    }

    /**
     * Concrete Implementor for email.
     */
    public class EmailSender implements NotificationSender {
        @Override
        public void send(String message) {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Email sent: " + message);
            }
        }
    }

    /**
     * Concrete Implementor for SMS.
     */
    public class SmsSender implements NotificationSender {
        @Override
        public void send(String message) {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("SMS sent: " + message);
            }
        }
    }

    /**
     * Abstraction for alerts.
     */
    public abstract class Alert {
        protected final NotificationSender sender;

        protected Alert(NotificationSender sender) {
            this.sender = sender;
        }

        public abstract void notifyUser(String msg);
    }

    /**
     * Refined Abstraction for low-balance alerts.
     */
    public class LowBalanceAlert extends Alert {
        public LowBalanceAlert(NotificationSender sender) {
            super(sender);
        }

        @Override
        public void notifyUser(String msg) {
            sender.send("Low Balance Alert: " + msg);
        }
    }

    public static void main(String[] args) {
        BridgePattern pattern = new BridgePattern();
        Alert alert = pattern.new LowBalanceAlert(pattern.new SmsSender());
        alert.notifyUser("Balance below $100");
    }
}
