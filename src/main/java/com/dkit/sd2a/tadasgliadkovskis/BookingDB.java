package com.dkit.sd2a.tadasgliadkovskis;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class BookingDB
{
    private ArrayList<Booking> bookings;
    private final static String FILE_NAME = "bookings.txt";
    private static int dataIndexCounter = 0;
    private int[] bookedDevicesCount; //Desktop, Laptop, PI
    private Scanner userInput = new Scanner(System.in);

    public BookingDB()
    {
        bookings = new ArrayList<>();
        bookedDevicesCount = new int[3];
    }

    protected void loadBookingsFromFile()
    {
        try (Scanner bookingsFile = new Scanner(new BufferedReader(new FileReader(FILE_NAME))))
        {
            String input;
            while (bookingsFile.hasNextLine())
            {
                input = bookingsFile.nextLine();
                String[] data = input.split(",");
                if (data.length > 5)
                {
                    constructBooking(data);
                } else
                {
                    throw new ArrayIndexOutOfBoundsException();
                }
            }
        } catch (FileNotFoundException fne)
        {
            System.out.println(Colours.RED + "File " + FILE_NAME + " not found" + Colours.RESET);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException formatError)
        {
            System.out.println(Colours.RED + "File " + FILE_NAME + " has incorrect formatting" + Colours.RESET);
        }
    }

    private void constructBooking(String[] data)
    {
        String bookingID = data[dataIndexCounter++];
        String dayBorrowed = data[dataIndexCounter++].trim();
        String dayReturned = data[dataIndexCounter++].trim();
        String computerType = data[dataIndexCounter++].trim();
        countAvailableDevices(computerType);
        String computerAssetTag = data[dataIndexCounter++].trim();
        String studentID = data[dataIndexCounter++].trim();

        Booking readInBooking = new Booking(bookingID, dayBorrowed, dayReturned, computerType,
                computerAssetTag, studentID);
        bookings.add(readInBooking);
        dataIndexCounter = 0;
    }

    private void countAvailableDevices(String computerType)
    {
        switch (computerType.toUpperCase())
        {
        case ("DESKTOP"):
            bookedDevicesCount[0]++;
            break;
        case ("LAPTOP"):
            bookedDevicesCount[1]++;
            break;
        case ("RASPBERRYPI"):
            bookedDevicesCount[2]++;
            break;
        }
    }

    public void saveBookingsToFile()
    {
        if (!bookings.isEmpty())
        {
            try (BufferedWriter bookingsFile = new BufferedWriter(new FileWriter(FILE_NAME)))
            {
                for (Booking booking : bookings)
                {
                    bookingsFile.write(getInfoAsString(booking) + "\n");
                }
            } catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        } else
        {
            System.out.println(Colours.RED + "There is no data to be saved for computers " + Colours.RESET);
        }
    }

    public String getInfoAsString(Booking booking)
    {
        return booking.getBookingID() + ", " + booking.getDayBorrowed() + ", " + booking.getReturnDate() + ", " +
                booking.getComputerType() + ", " + booking.getComputerAssetTag() + ", " + booking.getStudentID();
    }

    public void addBooking(StudentDB students, ComputerDB computers)
    {
        ArrayList<String> studentIDs = students.getAllStudentIDs();
        ArrayList<ArrayList<String>> computerAssetTags = computers.getAllAssetTags();
        ArrayList<String> bookingIDs = getAllBookingIDs();
        ArrayList<String> bookedComputersAssetTags = getAllBookedComputersAssetTags();

        try
        {
            String computerType = getComputerTypeFromUser(computers);
            String studentID = getStudentIDFromUser(studentIDs);
            String bookingID = createRandomBookingID(bookingIDs);
            String dayBorrowed = createBookingDate();
            String computerAssetTag = getComputerAssetTag(computerAssetTags, computerType,
                    bookedComputersAssetTags);

            Booking addedBooking = new Booking(bookingID, dayBorrowed, computerType, computerAssetTag, studentID);
            bookings.add(addedBooking);
            System.out.println(Colours.GREEN + "Thank you for booking a " + computerType
                    + ". Use your booking ID to return the device \nBooking ID = " + bookingID + Colours.RESET);
        } catch (InputMismatchException ie)
        {
            System.out.println(Colours.PURPLE + "Process Canceled" + Colours.RESET);
        }

        //TODO tell back student id and give them their booking id
    }

    private String getComputerAssetTag(ArrayList<ArrayList<String>> computerAssetTags, String computerType, ArrayList<String> bookedComputersAssetTags)
    {

        final int DESKTOP_IDS = 0;
        final int LAPTOP_IDS = 1;
        final int RASPBERRYPI_IDS = 2;
        String bookedComputerAssetTag = "";
        if (computerType.equals("Desktop"))
        {
            for (String assetTag : computerAssetTags.get(DESKTOP_IDS))
            {
                bookedComputerAssetTag = assetTag;
                if (!bookedComputersAssetTags.contains(bookedComputerAssetTag))
                {
                    break;
                }
            }
        } else if (computerType.equals("Laptop"))
        {
            for (String assetTag : computerAssetTags.get(LAPTOP_IDS))
            {
                bookedComputerAssetTag = assetTag;
                if (!bookedComputersAssetTags.contains(bookedComputerAssetTag))
                {
                    break;
                }
            }
        } else
        {
            for (String assetTag : computerAssetTags.get(RASPBERRYPI_IDS))
            {
                bookedComputerAssetTag = assetTag;
                if (!bookedComputersAssetTags.contains(bookedComputerAssetTag))
                {
                    break;
                }
            }
        }

        return bookedComputerAssetTag;
    }

    private String createBookingDate()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy HH:mm");
        return formatter.format(new Date());
    }

    private String createRandomBookingID(ArrayList<String> bookingIDs)
    {
        String bookingIDStart = "1000";
        String bookingID = "";
        boolean loop = true;
        Random rand = new Random();
        while (loop)
        {
            bookingID = bookingIDStart + rand.nextInt(999);
            loop = bookingIDs.contains(bookingID);
        }
        return bookingID;
    }

    private String getStudentIDFromUser(ArrayList<String> studentIDs)
    {
        String studentID = "";
        boolean loop = true;
        while (loop)
        {
            System.out.println("\n To quit this process enter 0");
            System.out.print("Please enter in your student ID -> ");
            studentID = userInput.nextLine().trim();

            boolean studentIDExists = studentIDs.contains(studentID.toUpperCase());
            if (studentID.equals("0"))
            {
                throw new InputMismatchException();
            } else if (!studentIDExists)
            {
                System.out.println(Colours.RED + "No such student ID exists" + Colours.RESET);
            } else
            {
                loop = false;
            }
        }
        return studentID.toUpperCase();
    }

    private String getComputerTypeFromUser(ComputerDB computers)
    {
        final int DESKTOP = 0;
        final int LAPTOP = 1;
        final int RASPBERRY_PI = 2;
        String deviceType = null;
        boolean loop = true;
        while (loop)
        {
            System.out.println("\nTo quit this process enter 0");
            printDevicesForBooking(computers);
            deviceType = userInput.nextLine();
            if (deviceType.equals("0"))
            {
                throw new InputMismatchException();
            } else if (deviceType.equalsIgnoreCase("DESKTOP") &&
                    bookedDevicesCount[DESKTOP] != computers.getTotalDevicesCount()[DESKTOP])
            {
                deviceType = "Desktop";
                loop = false;
            } else if (deviceType.equalsIgnoreCase("LAPTOP") &&
                    bookedDevicesCount[LAPTOP] != computers.getTotalDevicesCount()[LAPTOP])
            {
                deviceType = "Laptop";
                loop = false;
            } else if (deviceType.equalsIgnoreCase("RASPBERRYPI") &&
                    bookedDevicesCount[RASPBERRY_PI] != computers.getTotalDevicesCount()[RASPBERRY_PI])
            {
                deviceType = "RaspberryPi";
                loop = false;
            } else
            {
                System.out.println(Colours.RED + "This device is not available" + Colours.RESET);
            }
        }
        return deviceType;
    }

    private void printDevicesForBooking(ComputerDB computers)
    {
        System.out.println("Devices Available: ");
        for (int i = 0; i < bookedDevicesCount.length; i++)
        {
            System.out.println("\t" + Colours.BLUE + DevicesForBooking.values()[i].toString()
                    + ": " + Colours.RESET + (computers.getTotalDevicesCount()[i] - bookedDevicesCount[i]));
        }
        System.out.print("Enter the name of the device you would like to book -> ");
    }

    public void returnBookedComputer(StudentDB students, ComputerDB computers)
    {

    }

    public void deleteBooking(StudentDB students, ComputerDB computers)
    {

    }

    public void printAllBookings()
    {
        for (Booking booking : bookings)
        {
            System.out.println("\nBooking ID: " + booking.getBookingID());
            System.out.println("Day Borrowed: " + booking.getDayBorrowed());
            System.out.println(
                    "Return Date: " + booking.getReturnDate());
            System.out.println("Computer on Loan: " + booking.getComputerType());
            System.out.println("Computer Asset Tag: " + booking.getComputerAssetTag());
            System.out.println("Student ID: " + booking.getStudentID());
        }
    }

    public void findBooking()
    {

    }

    public ArrayList<String> getAllBookingIDs()
    {
        ArrayList<String> bookingIDs = new ArrayList<>();
        for (Booking booking : bookings)
        {
            bookingIDs.add(booking.getBookingID());
        }
        return bookingIDs;
    }

    public ArrayList<String> getAllBookedComputersAssetTags()
    {
        ArrayList<String> bookedComputersAssetTags = new ArrayList<>();
        for (Booking booking : bookings)
        {
            bookedComputersAssetTags.add(booking.getComputerAssetTag());
        }
        return bookedComputersAssetTags;
    }



}
