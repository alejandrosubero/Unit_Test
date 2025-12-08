package com.unitTestGenerator.app;

import com.unitTestGenerator.exception.LogService;

public class ErrorManagerApp {

    public ErrorManagerApp(){}

    public static void processCliArguments(String[] args) {
        String mainCommand = args[0];

        switch (mainCommand.toLowerCase()) {
            case "log":
                handleLogCommands(args);
                break;
            default:
                System.out.println("Unknown command: " + mainCommand);
                System.out.println("Available commands:");
                System.out.println("  unit log list");
                System.out.println("  unit log view <file>");
        }
    }

    private static void handleLogCommands(String[] args) {

        LogService service = new LogService();

        if (args.length == 2 && args[1].equalsIgnoreCase("list")) {
            service.listLogFiles().forEach(System.out::println);
            return;
        }

        if (args.length == 3 && args[1].equalsIgnoreCase("view")) {
            service.printLogFile(args[2]);
            return;
        }

        System.out.println("====================================================");
        System.out.println("Log commands:");
        System.out.println("  unit log list");
        System.out.println("  unit log view <file>");
    }

}
