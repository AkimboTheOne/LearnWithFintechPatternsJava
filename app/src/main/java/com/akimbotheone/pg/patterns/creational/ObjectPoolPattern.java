package com.akimbotheone.pg.patterns.creational;
import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Object Pool Pattern – Creational Design Pattern
 * Optimizes performance by reusing expensive objects.
 */
public class ObjectPoolPattern {
    private static final Logger logger = Logger.getLogger(ObjectPoolPattern.class.getName());

    public static class MarketConnection {
        private final UUID id = UUID.randomUUID();
        public void connect() {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Using connection: " + id);
            }
        }
    }

    public static class ConnectionPool {
        private final Queue<MarketConnection> pool = new LinkedList<>();

        public ConnectionPool(int size) {
            for (int i = 0; i < size; i++) {
                pool.add(new MarketConnection());
            }
        }
        public MarketConnection acquire() {
            return pool.poll();
        }
        public void release(MarketConnection conn) {
            pool.offer(conn);
        }
    }

    public static void main(String[] args) {
        ConnectionPool pool = new ConnectionPool(2);
        MarketConnection conn = pool.acquire();
        conn.connect();
        pool.release(conn);
    }
}
