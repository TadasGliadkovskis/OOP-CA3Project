package com.dkit.sd2a.tadasgliadkovskis;
public class Monitor
{
    private String manufacturer;
    private String model;
    private double screenSize;
    private Resolution resolution;

    public Monitor(String manufacturer, String model, double screenSize, Resolution resolution)
    {
        this.manufacturer = manufacturer;
        this.model = model;
        this.screenSize = screenSize;
        this.resolution = resolution;
    }

    public String getManufacturer()
    {
        return manufacturer;
    }

    public String getModel()
    {
        return model;
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
