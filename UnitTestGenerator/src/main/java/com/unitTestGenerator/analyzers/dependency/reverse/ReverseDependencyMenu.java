package com.unitTestGenerator.analyzers.dependency.reverse;

import com.unitTestGenerator.ioc.ContextIOC;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReverseDependencyMenu {



    public static void main(String[] args) throws IOException {
        Scanner scannerInput = new Scanner(System.in);

        System.out.println("=== Reverse Dependency Scanner ===");
        System.out.println("1. Enter project path manually");
        System.out.println("2. Use predefined path");
        System.out.println("3. Use command-line argument");
        System.out.print("Choice (1/2/3): ");

        String option = scannerInput.nextLine().trim();
        Path projectRoot;

        switch (option) {
            case "1":
                System.out.print("Enter project path: ");
                projectRoot = Paths.get(scannerInput.nextLine().trim());
                break;
            case "2":
                projectRoot = Paths.get("/Users/user/Documents/REPOSITORY/Unit_Test_/UnitTestGenerator");
                break;
            case "3":
                if (args.length == 0) {
                    System.out.println("❌ No argument provided.");
                    return;
                }
                projectRoot = Paths.get(args[0]);
                break;
            default:
                System.out.println("❌ Invalid option.");
                return;
        }


//        ReverseDependencyScanner scanner = ContextIOC.getInstance().getClassInstance(ReverseDependencyScanner.class);
        ReverseDependencyScanner scanner = new ReverseDependencyScanner();
        scanner.scanProject(projectRoot);

        System.out.println("\n=== Reverse Dependency Map ===");
        List<ReverseDependencyNode> nodes = scanner.buildDependencyTree();

        for (ReverseDependencyNode node : nodes) {
            System.out.println(node);
        }
    }


}
