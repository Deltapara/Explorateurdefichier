package com.esiea.pootd2.controllers;

import com.esiea.pootd2.commands.*;
import com.esiea.pootd2.commands.parsers.ICommandParser;
import com.esiea.pootd2.commands.parsers.UnixLikeCommandParser;
import com.esiea.pootd2.models.*;

public class ExplorerController implements IExplorerController {
    private FolderInode root;
    private FolderInode currentDirectory;
    private ICommandParser parser;

    public ExplorerController() {
        this.root = new FolderInode("/");
        this.currentDirectory = root;
        this.parser = new UnixLikeCommandParser();
    }

    @Override
    public String executeCommand(String commandStr) {
        Command command = parser.parse(commandStr);
        return doCommand(command);
    }

    private String doCommand(Command command) {
        if (command instanceof ListCommand) {
            return doCommand((ListCommand) command);
        } else if (command instanceof ChangeDirectoryCommand) {
            return doCommand((ChangeDirectoryCommand) command);
        } else if (command instanceof MakeDirectoryCommand) {
            return doCommand((MakeDirectoryCommand) command);
        } else if (command instanceof TouchCommand) {
            return doCommand((TouchCommand) command);
        } else if (command instanceof ErrorCommand) {
            return doCommand((ErrorCommand) command);
        }
        return "Commande non reconnue";
    }

    private String doCommand(ListCommand command) {
        StringBuilder result = new StringBuilder();
        for (Inode child : currentDirectory.getChildren()) {
            result.append(child.getName()).append(" ").append(child.getSize()).append("\n");
        }
        return result.toString().trim();
    }

    private String doCommand(ChangeDirectoryCommand command) {
        String path = command.getPath();
        
        if ("..".equals(path)) {
            // Remonter au parent
            if (currentDirectory == root) {
                return "cd: déjà à la racine";
            }
            // Trouver le parent
            FolderInode parent = findParent(root, currentDirectory);
            if (parent != null) {
                currentDirectory = parent;
                return "";
            } else {
                return "cd: impossible de trouver le parent";
            }
        } else if ("/".equals(path)) {
            currentDirectory = root;
            return "";
        } else {
            // Chercher le dossier dans le répertoire courant
            for (Inode child : currentDirectory.getChildren()) {
                if (child instanceof FolderInode && child.getName().equals(path)) {
                    currentDirectory = (FolderInode) child;
                    return "";
                }
            }
            return "cd: dossier introuvable: " + path;
        }
    }

    private FolderInode findParent(FolderInode potentialParent, FolderInode target) {
        for (Inode child : potentialParent.getChildren()) {
            if (child == target) {
                return potentialParent;
            }
            if (child instanceof FolderInode) {
                FolderInode result = findParent((FolderInode) child, target);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    private String doCommand(MakeDirectoryCommand command) {
        String name = command.getName();
        
        // Vérifier si le dossier existe déjà
        for (Inode child : currentDirectory.getChildren()) {
            if (child.getName().equals(name)) {
                return "mkdir: le dossier existe déjà: " + name;
            }
        }
        
        FolderInode newFolder = new FolderInode(name);
        currentDirectory.addInode(newFolder);
        return "";
    }

    private String doCommand(TouchCommand command) {
        String name = command.getName();
        
        // Vérifier si le fichier existe déjà
        for (Inode child : currentDirectory.getChildren()) {
            if (child.getName().equals(name)) {
                return "touch: le fichier existe déjà: " + name;
            }
        }
        
        FileInode newFile = new FileInode(name);
        currentDirectory.addInode(newFile);
        return "";
    }

    private String doCommand(ErrorCommand command) {
        return "Erreur: " + command.getMessage();
    }
}