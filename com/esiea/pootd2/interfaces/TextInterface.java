package com.esiea.pootd2.interfaces;

import java.util.Scanner;
import com.esiea.pootd2.controllers.IExplorerController;

public class TextInterface extends AbstractInterface {

    public TextInterface(IExplorerController controller) {
        super(controller);
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Explorateur de fichiers (mode texte) ===");
        
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Fermeture de l'application...");
                break;
            }

            // Exécuter la commande via le contrôleur
            String result = controller.executeCommand(input);

            if (result != null && !result.isEmpty()) {
                System.out.println(result);
            }
        }

        scanner.close();
    }
}
