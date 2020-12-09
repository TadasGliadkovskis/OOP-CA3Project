package com.dkit.sd2a.tadasgliadkovskis;
import java.util.Date;

public class Booking
{
    private int bookingID;
    private String dayBorrowed;
    private String returnDate;
    private String computerType;
    private String computerAssetTag;
    private String studentID;

    public Booking(int bookingID, String dayBorrowed, String computerType, String computerAssetTag, String studentID)
    {
        this.bookingID = bookingID;
        this.dayBorrowed = dayBorrowed;
        this.computerType = computerType;
        this.returnDate = null;
        this.computerAssetTag = computerAssetTag;
        this.studentID = studentID;
    }

    public Booking(int bookingID, String dayBorrowed, String returnDate, String computerType, String computerAssetTag, String studentID)
    {
        this.bookingID = bookingID;
        this.dayBorrowed = dayBorrowed;
        this.returnDate = returnDate;
        this.computerType = computerType;
        this.computerAssetTag = computerAssetTag;
        this.studentID = studentID;
    }

    public int getBookingID()
    {
        return bookingID;
    }

    public String getDayBorrowed()
    {
        return dayBorrowed;
    }

    public String getReturnDate()
    {
        return returnDate;
    }

    public String getComputerType()
    {
        return computerType;
    }

    public String getComputerAssetTag()
    {
        return computerAssetTag;
    }

    public String getStudentID()
    {
        return studentID;
    }

    @Override
    public String toString()
    {
        return "Booking{" +
                "bookingID=" + bookingID +
                ", dayBorrowed=" + dayBorrowed +
                ", returnDate=" + returnDate +
                ", computerType=" + computerType +
                ", computerAssetTag='" + computerAssetTag + '\'' +
                ", studentID='" + studentID + '\'' +
                '}';
    }
}
