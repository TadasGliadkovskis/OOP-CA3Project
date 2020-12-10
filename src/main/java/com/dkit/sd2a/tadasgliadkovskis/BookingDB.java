package com.dkit.sd2a.tadasgliadkovskis;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingDB
{
    private ArrayList<Booking> bookings;
    final static String FILE_NAME = "bookings.txt";
    static int dataIndexCounter = 0;

    public BookingDB()
    {
        bookings = new ArrayList<>();
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
                if (data.length > 5) //TODO separate this into another methood?
                {
                    int bookingID = Integer.parseInt(data[dataIndexCounter++]);
                    String dayBorrowed = data[dataIndexCounter++].trim();
                    String dayReturned = data[dataIndexCounter++].trim();
                    String computerType = data[dataIndexCounter++].trim();
                    String computerAssetTag = data[dataIndexCounter++].trim();
                    String studentID = data[dataIndexCounter++].trim();

                    Booking readInBooking = new Booking(bookingID, dayBorrowed, dayReturned, computerType,
                            computerAssetTag, studentID);
                    bookings.add(readInBooking);
                    dataIndexCounter = 0;
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

    }


    public void editBooking(StudentDB students, ComputerDB computers)
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
                    "Return Date: " + booking.getReturnDate()); //Todo should this be an if statment to not print if empty
            System.out.println("Computer on Loan: " + booking.getComputerType());
            System.out.println("Computer Asset Tag: " + booking.getComputerAssetTag());
            System.out.println("Student ID: " + booking.getStudentID());
        }
    }

    public void findBooking()
    {

    }
}
