package com.dkit.sd2a.tadasgliadkovskis;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingDB implements IDBMethods
{
    ArrayList<Booking> bookings;
    final static String FILE_NAME = "bookings.txt";
    //TODO implement a static counter to use for the data[x] number?

    public BookingDB()
    {
        bookings = new ArrayList<>();
    }

    protected void loadBookingsFromFile() //Todo reduce the middle part ? by seperating into two methods
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
                    int bookingID = Integer.parseInt(data[0]);
                    String dayBorrowed = data[1].trim();
                    String dayReturned = data[2].trim();
                    String computerType = data[3].trim();
                    String computerAssetTag = data[4].trim();
                    String studentID = data[5].trim();

                    Booking readInBooking = new Booking(bookingID, dayBorrowed, dayReturned, computerType,
                            computerAssetTag, studentID);
                    bookings.add(readInBooking);
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

    @Override
    public void addData()
    {

    }

    @Override
    public void editData()
    {

    }

    @Override
    public void deleteData()
    {

    }

    @Override
    public void readData()
    {
        for (Booking booking : bookings)
        {
            System.out.println("\nBooking ID: " + booking.getBookingID());
            System.out.println("Day Borrowed: " + booking.getDayBorrowed());
            System.out.println(
                    "Return Date: " + booking.getReturnDate()); //Todo should this be an if statment to not print if empty?
            System.out.println("Computer on Loan: " + booking.getComputerType());
            System.out.println("Computer Asset Tag: " + booking.getComputerAssetTag());
            System.out.println("Student ID: " + booking.getStudentID());
        }
    }
}
