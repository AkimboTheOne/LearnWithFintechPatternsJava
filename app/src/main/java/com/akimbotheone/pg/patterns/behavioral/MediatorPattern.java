package com.akimbotheone.pg.patterns.behavioral;
import java.util.logging.Logger;

/**
 * Mediator Pattern – Behavioral Design Pattern
 * Coordinates interaction between Form and Summary components.
 */
public class MediatorPattern {
    private static final Logger logger = Logger.getLogger(MediatorPattern.class.getName());

    /** Mediator interface. */
    public interface Mediator {
        void notify(Component sender, String event);
    }

    /** Base component. */
    public abstract class Component {
        protected Mediator mediator;
        protected Component(Mediator m) { this.mediator = m; }
    }

    /** Concrete component: Summary. */
    public class Summary extends Component {
        public Summary(Mediator m) { super(m); }
        public void update() {
            logger.info("Summary updated");
        }
    }

    /** Concrete component: Form. */
    public class Form extends Component {
        public Form(Mediator m) { super(m); }
        public void input() {
            logger.info("Form input received");
            mediator.notify(this, "input");
        }
    }

    /** Concrete mediator. */
    public class UIManager implements Mediator {
        private final Summary summary;
        public UIManager(Summary summary) {
            this.summary = summary;
        }
        @Override
        public void notify(Component sender, String event) {
            if ("input".equals(event)) {
                summary.update();
            }
        }
    }

    public static void main(String[] args) {
        MediatorPattern pattern = new MediatorPattern();
        Summary summary = pattern.new Summary(null);
        UIManager manager = pattern.new UIManager(summary);
        Form form = pattern.new Form(manager);
        summary.mediator = manager;
        form.input();
    }
}
