package app;

import controller.RBACSystem;
import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        RBACSystem sistema = new RBACSystem();
        sistema.initialize();

        ConsoleView vista = new ConsoleView(sistema);
        vista.run();
    }
}