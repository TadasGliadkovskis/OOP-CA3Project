package com.dkit.sd2a.tadasgliadkovskis;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ComputerDB implements IDBMethods
{
    ArrayList<Computer> computers;
    final static String FILE_NAME = "computers.txt";
    static int dataIndexCounter = 0;

    public ComputerDB()
    {
        computers = new ArrayList<>();
    }

    protected void loadComputersFromFile()
    { //todo identify in the file with the amount of variables or just put in a word at the start as the identifier?
        try (Scanner computersFile = new Scanner(new BufferedReader(new FileReader(FILE_NAME))))
        {
            String input;
            while (computersFile.hasNextLine())
            {
                input = computersFile.nextLine();
                String[] data = input.split(",");
                switch (data[dataIndexCounter++]) //the first element is the type of Computer
                {
                case ("Laptop"): //10
                    constructLaptop(data);
                    break;
                case ("Desktop"): //11
                    constructDesktop(data);
                    break;
                case ("RaspberryPi"): //9
                    constructRaspberryPi(data);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + data[0]);
                }
            }
        } catch (FileNotFoundException fne)
        {
            System.out.println(Colours.RED + "File " + FILE_NAME + " not found" + Colours.RESET);
        }
    }

    private void constructRaspberryPi(String[] data)
    {
        int counter = 1;
        String manufacturer = data[dataIndexCounter++].trim();
        String processor = data[dataIndexCounter++].trim();
        int ramCapacity = Integer.parseInt(data[dataIndexCounter++].trim());
        String weight = data[dataIndexCounter++].trim();
        String assetTag = data[dataIndexCounter++].trim();
        String purchaseDate = data[dataIndexCounter++].trim();
        int noOfGPIOPins = Integer.parseInt(data[dataIndexCounter++].trim());
        String sdCardCapacity = data[dataIndexCounter++].trim();

        Computer readInRaspberryPi = new RaspberryPi
                (manufacturer, processor, ramCapacity, weight, assetTag, purchaseDate, noOfGPIOPins, sdCardCapacity);
        computers.add(readInRaspberryPi);
        dataIndexCounter = 0;
    }

    private void constructDesktop(String[] data)
    {
        int counter = 1;
        String manufacturer = data[dataIndexCounter++].trim();
        String processor = data[dataIndexCounter++].trim();
        int ramCapacity = Integer.parseInt(data[dataIndexCounter++].trim());
        String hardDiskCapacity = data[dataIndexCounter++].trim();
        String weight = data[dataIndexCounter++].trim();
        String assetTag = data[dataIndexCounter++].trim();
        String purchaseDate = data[dataIndexCounter++].trim();
        Monitor monitor = createMonitor(data);
        Computer readInDesktop = new Desktop
                (manufacturer, processor, ramCapacity, hardDiskCapacity, weight, assetTag, purchaseDate, monitor);
        computers.add(readInDesktop);
        dataIndexCounter = 0;
    }

    private Monitor createMonitor(String[] data)
    {
        String manufacturer = data[dataIndexCounter++].trim();
        double screenSize = Double.parseDouble(data[dataIndexCounter++].trim());
        int resolutionWidth = Integer.parseInt(data[dataIndexCounter++].trim());
        int resolutionHeight = Integer.parseInt(data[dataIndexCounter++].trim());
        Resolution monitorResolution = new Resolution(resolutionWidth, resolutionHeight);
        return new Monitor(manufacturer, screenSize, monitorResolution);
    }

    private void constructLaptop(String[] data)
    {
        int counter = 1;
        String manufacturer = data[dataIndexCounter++].trim();
        String processor = data[dataIndexCounter++].trim();
        int ramCapacity = Integer.parseInt(data[dataIndexCounter++].trim());
        String hardDiskSize = data[dataIndexCounter++].trim();
        String weight = data[dataIndexCounter++].trim();
        String assetTag = data[dataIndexCounter++].trim();
        String purchaseDate = data[dataIndexCounter++].trim();
        double screenSize = Double.parseDouble(data[dataIndexCounter++].trim());
        double batteryLife = Double.parseDouble(data[dataIndexCounter++].trim());
        Computer readInLaptop = new Laptop
                (manufacturer, processor, ramCapacity,
                        hardDiskSize, weight, assetTag, purchaseDate, screenSize, batteryLife);
        computers.add(readInLaptop);
        dataIndexCounter = 0;
    }

    public void saveComputersToFile()
    {
        if (!computers.isEmpty())
        {
            try (BufferedWriter computersFile = new BufferedWriter(new FileWriter(FILE_NAME)))
            {
                for (Computer computer : computers)
                {
                    computersFile.write(getInfoAsString(computer) + "\n");
                }
            } catch (IOException fne)
            {
                System.out.println(Colours.RED + "File " + FILE_NAME + " not found" + Colours.RESET);
            }
        }
    }

    private String getInfoAsString(Computer computer)
    {
        return computer.getClass().getSimpleName() + ", " + computer.toString();
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

    }

    @Override
    public String toString()
    {
        return "ComputerDB{" +
                "computers=" + computers +
                '}';
    }
}
