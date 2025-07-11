package com.akimbotheone.pg.patterns.behavioral;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Iterator Pattern – Behavioral Design Pattern
 * Enables traversal of AccountCollection without exposing its internal list.
 */
public class IteratorPattern {
    private static final Logger logger = Logger.getLogger(IteratorPattern.class.getName());

    public static class Account {
        private final String type;
        public Account(String type) { this.type = type; }
        public String getType() { return type; }
    }

    public static class AccountCollection implements Iterable<Account> {
        private final List<Account> accounts = new ArrayList<>();
        public void add(Account acc) {
            accounts.add(acc);
        }
        @Override
        public Iterator<Account> iterator() {
            return accounts.iterator();
        }
    }

    public static void main(String[] args) {
        AccountCollection coll = new AccountCollection();
        coll.add(new Account("Savings"));
        coll.add(new Account("Loan"));
        for (Account acc : coll) {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Account type: " + acc.getType());
            }
        }
    }
}
