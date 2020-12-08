package com.dkit.sd2a.tadasgliadkovskis;
import java.util.Date;

public class Computer
{
    private String manufacturer;
    private String processor;
    private int ramCapacity;
    private String hardDiskCapacity;
    private String weight; //TODO is this even needed?
    private String assetTag;
    private Date purchaseDate;

    public Computer(String manufacturer, String processor, int ramCapacity, String hardDiskCapacity, String weight, String assetTag, Date purchaseDate)
    {
        this.manufacturer = manufacturer;
        this.processor = processor;
        this.ramCapacity = ramCapacity;
        this.hardDiskCapacity = hardDiskCapacity;
        this.weight = weight;
        this.assetTag = assetTag;
        this.purchaseDate = purchaseDate;
    }

    public String getManufacturer()
    {
        return manufacturer;
    }

    public String getProcessor()
    {
        return processor;
    }

    public int getRamCapacity()
    {
        return ramCapacity;
    }

    public String getHardDiskCapacity()
    {
        return hardDiskCapacity;
    }

    public String getWeight()
    {
        return weight;
    }

    public String getAssetTag()
    {
        return assetTag;
    }

    public Date getPurchaseDate()
    {
        return purchaseDate;
    }

}
