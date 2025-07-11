package com.akimbotheone.pg.patterns.behavioral;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Command Pattern – Behavioral Design Pattern
 * Encapsulates financial operations as commands.
 */
public class CommandPattern {
    private static final Logger logger = Logger.getLogger(CommandPattern.class.getName());

    public interface Command {
        void execute();
    }

    public static class TransferCommand implements Command {
        @Override
        public void execute() {
            logger.info("Executing transfer command");
        }
    }

    public static class Invoker {
        private final List<Command> history = new ArrayList<>();

        public void submit(Command cmd) {
            history.add(cmd);
            cmd.execute();
        }
    }

    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        invoker.submit(new TransferCommand());
    }
}
