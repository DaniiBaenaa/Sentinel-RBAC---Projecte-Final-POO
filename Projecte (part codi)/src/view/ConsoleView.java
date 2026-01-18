package view;

import controller.RBACSystem;
import java.util.List;
import java.util.Scanner;
import model.AccessRequest;
import model.Resource;
import model.enums.RequestStatus;
import model.enums.RoleType;

public class ConsoleView {

    private final RBACSystem sistema;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleView(RBACSystem sistema) {
        this.sistema = sistema;
    }

    public void run() {
        System.out.println("=== Sentinel RBAC ===");

        while (true) {

            if (!sistema.sessioIniciada()) {
                pantallaLogin();
                continue;
            }

            System.out.println("\n1) Llistar recursos ");
            System.out.println("2) Demanar accés ");
            System.out.println("3) Veure log ");
            System.out.println("4) Tancar sessió ");
            System.out.println("0) Sortir ");
            System.out.print("Opció: ");

            int opcio = llegirEnter();

            if (opcio == 0) {
                System.out.println("Adeu!");
                return;
            }

            if (opcio == 4) {
                sistema.tancarSessio();
                System.out.println("Sessió tancada.");
                continue;
            }

            if (opcio == 1) {
                llistarRecursos();
            } else if (opcio == 2) {
                fluxDemanarAcces();
            } else if (opcio == 3) {

                if (sistema.getUsuariActual().getRole() != RoleType.ADMIN) {
                    System.out.println(" Per veure els logs has de ser admin ");
                    continue;
                }

                System.out.println("\n--- LOG ---");
                System.out.println(sistema.llegirLogFinal(25));

            } else {
                System.out.println("Opció no vàlida");
            }
        }
    }

    private void pantallaLogin() {
        System.out.print("Nom d'usuari: ");
        String usuari = scanner.nextLine();

        System.out.print("Contrasenya: ");
        String contrasenya = scanner.nextLine();

        boolean ok = sistema.iniciarSessio(usuari, contrasenya);

        if (!ok) {
            System.out.println("Nom d'usuari o contrasenya incorrectes.");
        } else {
            System.out.println("Sessió iniciada. ");
        }
    }

    private void llistarRecursos() {
        List<Resource> recursos = sistema.getRecursos();

        System.out.println("\n--- Recursos disponibles ---");
        for (int i = 0; i < recursos.size(); i++) {
            System.out.println((i + 1) + ") " + recursos.get(i));
        }
    }

    private void fluxDemanarAcces() {
        llistarRecursos();

        System.out.print("Selecciona recurs: ");
        int index = llegirEnter() - 1;

        List<Resource> recursos = sistema.getRecursos();

        if (index < 0 || index >= recursos.size()) {
            System.out.println("Selecció incorrecta.");
            return;
        }

        System.out.print("Introdueix IP (ex: 127.0.0.1): ");
        String ip = scanner.nextLine();

        AccessRequest peticio = sistema.demanarAcces(recursos.get(index), ip);

        if (peticio.getStatus() == RequestStatus.APPROVED) {
            System.out.println("ACCÉS AUTORITZAT");
            System.out.println("Recurs obert (simulat): " + peticio.getTarget().getFilepath());
        } else {
            System.out.println("ACCÉS DENEGAT");
        }
    }

    private int llegirEnter() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Introdueix un número: ");
            }
        }
    }
}
