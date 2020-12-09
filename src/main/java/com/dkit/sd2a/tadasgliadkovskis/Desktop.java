package com.dkit.sd2a.tadasgliadkovskis;
import java.util.Date;

public class Desktop extends Computer
{
    private Monitor monitor;

    public Desktop(String manufacturer, String processor, int ramCapacity, String hardDiskCapacity, String weight, String assetTag, String purchaseDate, Monitor monitor)
    {
        super(manufacturer, processor, ramCapacity, hardDiskCapacity, weight, assetTag, purchaseDate);
        this.monitor = monitor;
    }

    public Monitor getMonitor()
    {
        return monitor;
    }
}
