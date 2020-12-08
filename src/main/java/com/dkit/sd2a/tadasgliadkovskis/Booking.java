package com.dkit.sd2a.tadasgliadkovskis;
import java.util.Date;

public class Booking
{
    private int bookingID;
    private Date dayBorrowed;
    private Date returnDate;
    private Computer computerType;
    private String computerAssetTag;
    private String studentID;

    public Booking(int bookingID, Date dayBorrowed, Computer computerType, String computerAssetTag, String studentID)
    {
        this.bookingID = bookingID;
        this.dayBorrowed = dayBorrowed;
        this.computerType = computerType;
        this.returnDate = null;
        this.computerAssetTag = computerAssetTag;
        this.studentID = studentID;
    }

    public int getBookingID()
    {
        return bookingID;
    }

    public Date getDayBorrowed()
    {
        return dayBorrowed;
    }

    public Date getReturnDate()
    {
        return returnDate;
    }

    public Computer getComputerType()
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
