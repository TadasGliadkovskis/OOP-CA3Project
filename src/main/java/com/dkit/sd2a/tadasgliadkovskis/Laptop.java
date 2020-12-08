package com.dkit.sd2a.tadasgliadkovskis;
import java.util.Date;

public class Laptop extends Computer
{
    private double screenSize;
    private double batteryLifeHours;

    public Laptop(String manufacturer, String processor, int ramCapacity, String hardDiskCapacity,
                  String weight, String assetTag, Date purchaseDate, double screenSize, double batteryLifeHours)
    {
        super(manufacturer, processor, ramCapacity, hardDiskCapacity, weight, assetTag, purchaseDate);
        this.screenSize = screenSize;
        this.batteryLifeHours = batteryLifeHours;
    }

    public double getScreenSize()
    {
        return screenSize;
    }

    public double getBatteryLifeHours()
    {
        return batteryLifeHours;
    }
}
