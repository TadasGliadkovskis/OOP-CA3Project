package com.dkit.sd2a.tadasgliadkovskis;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App
{
    final private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args)
    {
        App app = new App();
        app.start();
    }

    private void start()
    {
        StudentDB students = new StudentDB();
        students.loadStudentsFromFile();
        BookingDB bookings = new BookingDB();
        bookings.loadBookingsFromFile();
        ComputerDB computers = new ComputerDB();
        computers.loadComputersFromFile();

        doMainMenuLoop(students, bookings, computers);
    }

    private void doMainMenuLoop(StudentDB students, BookingDB bookings, ComputerDB computers)
    {
        boolean loop = true;
        MainMenu menuOption;
        while (loop)
        {
            printMainMenu();
            try
            {
                menuOption = MainMenu.values()[Integer.parseInt(keyboard.nextLine().trim())];
                switch (menuOption)
                {
                case QUIT_APPLICATION:
                    loop = false;
                    break;
                case DISPLAY_BOOKING_MENU:
                    doBookingMainMenuLoop(students, bookings, computers);
                    break;
                case DISPLAY_STUDENT_MENU:
                    doStudentMainMenuLoop(students);
                    break;
                }
            } catch (InputMismatchException | ArrayIndexOutOfBoundsException | NumberFormatException ioe)
            {
                System.out.println(Colours.RED + " please enter a valid option " + Colours.RESET);
            }
        }
        System.out.println(Colours.PURPLE + "End of app");
        //TODO save arrayList status here? Yes
    }

    private void doStudentMainMenuLoop(StudentDB students)
    {
        boolean loop = true;
        StudentMainMenu menuOption;
        while (loop)
        {
            printStudentMainMenu();
            try
            {
                menuOption = StudentMainMenu.values()[Integer.parseInt(keyboard.nextLine().trim())];
                switch (menuOption)
                {
                case QUIT_STUDENT_MENU:
                    loop = false;
                    break;
                case ADD_STUDENT:
                    students.addStudent();
                    break;
                case UPDATE_STUDENT:
                    students.updateStudent();
                    break;
                case REMOVE_STUDENT:
                    students.deleteStudent();
                    break;
                case PRINT_ALL_STUDENTS:
                    students.printAllStudents();
                    break;
                case SEARCH_FOR_STUDENT:
                    students.printSelectedStudent();
                }
            } catch (InputMismatchException | ArrayIndexOutOfBoundsException | NumberFormatException ioe)
            {
                System.out.println(Colours.RED + " please enter a valid option " + Colours.RESET);
            }
        }

    }

    private void doBookingMainMenuLoop(StudentDB students, BookingDB bookings, ComputerDB computers)
    {
        boolean loop = true;
        BookingMainMenu menuOption;
        while (loop)
        {
            printBookingMainMenu();
            try
            {
                menuOption = BookingMainMenu.values()[Integer.parseInt(keyboard.nextLine().trim())];
                switch (menuOption)
                {
                case QUIT_BOOKING_MENU:
                    loop = false;
                    break;
                case BOOK_A_DEVICE:
                    bookings.addBooking(students, computers);
                    break;
                case RETURN_COMPUTER:
                    bookings.returnBookedComputer();
                    break;
                case REMOVE_BOOKING:
                    bookings.deleteBooking(students, computers);
                    break;
                case PRINT_ALL_BOOKINGS:
                    bookings.printAllBookings();
                    break;
                case SEARCH_FOR_BOOKING:
                    bookings.findBooking();
                }
            } catch (InputMismatchException | ArrayIndexOutOfBoundsException | NumberFormatException ioe)
            {
                System.out.println(Colours.RED + " please enter a valid option " + Colours.RESET);
            }
        }
    }

    private void printMainMenu()
    {
        System.out.println("\n Options to select: ");
        for (int i = 0; i < MainMenu.values().length; i++)
        {
            System.out.println("\t" + Colours.BLUE + i + ". " + MainMenu.values()[i].toString() + Colours.RESET);
        }
        System.out.print("Enter a number to select the option -> ");
    }

    private void printStudentMainMenu()
    {
        System.out.println("\n Options to select: ");
        for (int i = 0; i < StudentMainMenu.values().length; i++)
        {
            System.out.println("\t" + Colours.BLUE + i + ". " + StudentMainMenu.values()[i].toString() + Colours.RESET);
        }
        System.out.print("Enter a number to select the option -> ");
    }

    private void printBookingMainMenu()
    {
        System.out.println("\n Options to select: ");
        for (int i = 0; i < BookingMainMenu.values().length; i++)
        {
            System.out.println("\t" + Colours.BLUE + i + ". " + BookingMainMenu.values()[i].toString() + Colours.RESET);
        }
        System.out.print("Enter a number to select the option -> ");
    }
}
