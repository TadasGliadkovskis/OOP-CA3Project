package com.dkit.sd2a.tadasgliadkovskis;
import java.util.Date;

public class RaspberryPi extends Computer
{
    private int noOfGPIOPins;
    private String sdCardCapacity;

    public RaspberryPi(String manufacturer, String processor, int ramCapacity,
                       String weight, String assetTag, String purchaseDate, int noOfGPIOPins, String sdCardCapacity)
    {
        super(manufacturer, processor, ramCapacity, sdCardCapacity, weight, assetTag, purchaseDate);
        this.noOfGPIOPins = noOfGPIOPins;
        this.sdCardCapacity = sdCardCapacity;
    }

    public int getNoOfGPIOPins()
    {
        return noOfGPIOPins;
    }

    public String getSdCardCapacity()
    {
        return sdCardCapacity;
    }

    @Override
    public String toString()
    {
        return getManufacturer() + ", " + getProcessor() + ", " + getRamCapacity() + ", "
                + getWeight() + ", " + getAssetTag() + ", "
                + getPurchaseDate() + ", " + getNoOfGPIOPins() + ", " + getSdCardCapacity();
    }
}
