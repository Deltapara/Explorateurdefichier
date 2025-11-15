package com.esiea.pootd2;

import com.esiea.pootd2.controllers.ExplorerController;
import com.esiea.pootd2.controllers.IExplorerController;
import com.esiea.pootd2.interfaces.HttpInterface;
import com.esiea.pootd2.interfaces.IUserInterface;
import com.esiea.pootd2.interfaces.TextInterface;

public class ExplorerApp {
    public static void main(String[] args) {
        // Créer le contrôleur
        IExplorerController controller = new ExplorerController();
        
        // Créer l'interface appropriée selon l'argument
        IUserInterface userInterface;
        
        if (args.length > 0 && "http".equalsIgnoreCase(args[0])) {
            // Mode HTTP
            userInterface = new HttpInterface(controller);
        } else {
            // Mode texte par défaut
            userInterface = new TextInterface(controller);
        }
        
        // Lancer l'interface
        userInterface.run();
    }
}