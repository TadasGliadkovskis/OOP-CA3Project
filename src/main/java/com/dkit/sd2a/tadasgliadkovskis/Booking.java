package com.dkit.sd2a.tadasgliadkovskis;
import java.util.Date;

public class Booking
{
    private String bookingID;
    private String dayBorrowed;
    private String returnDate;
    private String computerType;
    private String computerAssetTag;
    private String studentID;

    public Booking(String bookingID, String dayBorrowed, String computerType, String computerAssetTag, String studentID)
    {
        this.bookingID = bookingID;
        this.dayBorrowed = dayBorrowed;
        this.computerType = computerType;
        this.returnDate = "";
        this.computerAssetTag = computerAssetTag;
        this.studentID = studentID;
    }

    public Booking(String bookingID, String dayBorrowed, String returnDate, String computerType, String computerAssetTag, String studentID)
    {
        this.bookingID = bookingID;
        this.dayBorrowed = dayBorrowed;
        this.returnDate = returnDate;
        this.computerType = computerType;
        this.computerAssetTag = computerAssetTag;
        this.studentID = studentID;
    }

    public String getBookingID()
    {
        return bookingID;
    }

    public String getDayBorrowed()
    {
        return dayBorrowed;
    }

    public String getReturnDate()
    {
        if (returnDate.isEmpty())
        {
            return "";
        }
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

    public void setReturnDate(String returnDate)
    {
        this.returnDate = returnDate;
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
