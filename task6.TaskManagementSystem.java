import java.util.ArrayList;
import java.util.Scanner;

// Task class to represent a single task
class Task {
    private String title;
    private String description;
    private String deadline;

    // Constructor
    public Task(String title, String description, String deadline) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    // Override toString() to display task details
    @Override
    public String toString() {
        return "Title: " + title + "\nDescription: " + description + "\nDeadline: " + deadline + "\n";
    }
}

// TaskManager class to manage tasks
class TaskManager {
    private ArrayList<Task> tasks;

    // Constructor
    public TaskManager() {
        tasks = new ArrayList<>();
    }

    // Add a new task
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Task added successfully!");
    }

    // Update an existing task
    public void updateTask(int index, String title, String description, String deadline) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setTitle(title);
            task.setDescription(description);
            task.setDeadline(deadline);
            System.out.println("Task updated successfully!");
        } else {
            System.out.println("Invalid task index.");
        }
    }

    // Delete a task
    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("Task deleted successfully!");
        } else {
            System.out.println("Invalid task index.");
        }
    }

    // View all tasks
    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("Task " + (i + 1) + ":\n" + tasks.get(i));
            }
        }
    }
}

// Main class to run the Task Management System
public class TaskManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        while (true) {
            System.out.println("\nTask Management System");
            System.out.println("1. Add Task");
            System.out.println("2. Update Task");
            System.out.println("3. Delete Task");
            System.out.println("4. View Tasks");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add Task
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter task deadline: ");
                    String deadline = scanner.nextLine();
                    Task task = new Task(title, description, deadline);
                    taskManager.addTask(task);
                    break;

                case 2: // Update Task
                    taskManager.viewTasks();
                    System.out.print("Enter the task number to update: ");
                    int updateIndex = scanner.nextInt() - 1;
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new description: ");
                    String newDescription = scanner.nextLine();
                    System.out.print("Enter new deadline: ");
                    String newDeadline = scanner.nextLine();
                    taskManager.updateTask(updateIndex, newTitle, newDescription, newDeadline);
                    break;

                case 3: // Delete Task
                    taskManager.viewTasks();
                    System.out.print("Enter the task number to delete: ");
                    int deleteIndex = scanner.nextInt() - 1;
                    scanner.nextLine(); // Consume newline
                    taskManager.deleteTask(deleteIndex);
                    break;

                case 4: // View Tasks
                    taskManager.viewTasks();
                    break;

                case 5: // Exit
                    System.out.println("Exiting Task Management System. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}