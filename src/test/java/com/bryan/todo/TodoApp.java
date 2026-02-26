package com.bryan.todo;

import java.util.Scanner;

public class TodoApp {
    public static void main(String[] args) {

        TodoDao dao = new TodoDao();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== TO-DO MENU ===");
            System.out.println("1. Add task");
            System.out.println("2. Remove task");
            System.out.println("3. Show tasks");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter task: ");
                    String task = scanner.nextLine().trim();
                    if (task.isEmpty()) {
                        System.out.println("Task cannot be blank.");
                        break;
                    }
                    int id = dao.addTask(task);
                    System.out.println(id > 0 ? "Task added (ID: " + id + ")." : "Task not added.");
                }

                case 2 -> {
                    System.out.print("Enter task ID to remove: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println(dao.removeTask(id) ? "Task removed." : "Task not found.");
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                    }
                }

                case 3 -> {
                    var tasks = dao.getAllTasks();
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks to show.");
                    } else {
                        System.out.println("Your Tasks:");
                        tasks.forEach(System.out::println);
                    }
                }

                case 4 -> running = false;

                default -> System.out.println("Invalid choice.");
            }
        }

        scanner.close();
        HibernateUtil.shutdown();
    }
}
