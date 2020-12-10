package com.dkit.sd2a.tadasgliadkovskis;
public class Monitor
{
    private String manufacturer;
    private double screenSize;
    private Resolution resolution;

    public Monitor(String manufacturer, double screenSize, Resolution resolution)
    {
        this.manufacturer = manufacturer;
        this.screenSize = screenSize;
        this.resolution = resolution;
    }

    public String getManufacturer()
    {
        return manufacturer;
    }

    public double getScreenSize()
    {
        return screenSize;
    }

    public Resolution getResolution()
    {
        return resolution;
    }
}
