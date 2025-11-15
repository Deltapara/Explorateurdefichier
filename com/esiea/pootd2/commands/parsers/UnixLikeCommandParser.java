package com.esiea.pootd2.commands.parsers;

import com.esiea.pootd2.commands.*;

public class UnixLikeCommandParser implements ICommandParser {

    @Override
    public Command parse(String commandStr) {
        if (commandStr == null || commandStr.trim().isEmpty()) {
            return new ErrorCommand("Commande vide");
        }

        String[] args = splitArguments(commandStr.trim());
        return mapCommand(args);
    }

    private String[] splitArguments(String commandStr) {
        return commandStr.split("\\s+");
    }

    private Command mapCommand(String[] args) {
        if (args.length == 0) {
            return new ErrorCommand("Commande vide");
        }

        String commandName = args[0];

        switch (commandName) {
            case "ls":
                return new ListCommand();

            case "cd":
                if (args.length < 2) {
                    return new ErrorCommand("cd: argument manquant");
                }
                return new ChangeDirectoryCommand(args[1]);

            case "mkdir":
                if (args.length < 2) {
                    return new ErrorCommand("mkdir: argument manquant");
                }
                return new MakeDirectoryCommand(args[1]);

            case "touch":
                if (args.length < 2) {
                    return new ErrorCommand("touch: argument manquant");
                }
                return new TouchCommand(args[1]);

            default:
                return new ErrorCommand("Commande inconnue: " + commandName);
        }
    }
}