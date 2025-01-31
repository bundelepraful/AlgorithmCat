import java.util.*;

public class CalendarApp {

    static class Event {
        String title;
        String date; // Format: YYYY-MM-DD
        String time; // Format: HH:MM
        String description;

        public Event(String title, String date, String time, String description) {
            this.title = title;
            this.date = date;
            this.time = time;
            this.description = description;
        }

        @Override
        public String toString() {
            return "Title: " + title + "\nDate: " + date + "\nTime: " + time + "\nDescription: " + description;
        }
    }

    static class Calendar {
        Map<String, List<Event>> events = new HashMap<>();

        public void createEvent(String date, Event event) {
            events.putIfAbsent(date, new ArrayList<>());
            events.get(date).add(event);
            System.out.println("Event added successfully!");
        }

        public void updateEvent(String date, String oldTitle, Event updatedEvent) {
            if (events.containsKey(date)) {
                List<Event> dayEvents = events.get(date);
                for (int i = 0; i < dayEvents.size(); i++) {
                    if (dayEvents.get(i).title.equals(oldTitle)) {
                        dayEvents.set(i, updatedEvent);
                        System.out.println("Event updated successfully!");
                        return;
                    }
                }
            }
            System.out.println("Event not found.");
        }

        public void dropEvent(String date, String title) {
            if (events.containsKey(date)) {
                List<Event> dayEvents = events.get(date);
                dayEvents.removeIf(event -> event.title.equals(title));
                if (dayEvents.isEmpty()) {
                    events.remove(date);
                }
                System.out.println("Event deleted successfully!");
            } else {
                System.out.println("Event not found.");
            }
        }

        public void viewEvents(String date) {
            if (events.containsKey(date)) {
                System.out.println("Events on " + date + ":");
                for (Event event : events.get(date)) {
                    System.out.println(event);
                    System.out.println("-------------------");
                }
            } else {
                System.out.println("No events found on this date.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calendar calendar = new Calendar();

        while (true) {
            System.out.println("\nCalendar Application");
            System.out.println("1. Create Event");
            System.out.println("2. Update Event");
            System.out.println("3. Drop Event");
            System.out.println("4. View Events");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter time (HH:MM): ");
                    String time = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();

                    Event event = new Event(title, date, time, description);
                    calendar.createEvent(date, event);
                    break;

                case 2:
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    date = scanner.nextLine();
                    System.out.print("Enter the title of the event to update: ");
                    String oldTitle = scanner.nextLine();
                    System.out.print("Enter new title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new time (HH:MM): ");
                    String newTime = scanner.nextLine();
                    System.out.print("Enter new description: ");
                    String newDescription = scanner.nextLine();

                    Event updatedEvent = new Event(newTitle, date, newTime, newDescription);
                    calendar.updateEvent(date, oldTitle, updatedEvent);
                    break;

                case 3:
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    date = scanner.nextLine();
                    System.out.print("Enter the title of the event to delete: ");
                    title = scanner.nextLine();

                    calendar.dropEvent(date, title);
                    break;

                case 4:
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    date = scanner.nextLine();
                    calendar.viewEvents(date);
                    break;

                case 5:
                    System.out.println("Exiting application. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
