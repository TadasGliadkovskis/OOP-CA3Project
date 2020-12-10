package com.dkit.sd2a.tadasgliadkovskis;
public class Student
{
    private String name;
    private String id;
    private String email;
    private String telephone;
    private String computersOnLoan;

    public Student(String name, String id, String email, String telephone)
    {
        this.name = name;
        this.id = id;
        this.email = email;
        this.telephone = telephone;
        this.computersOnLoan = "";
    }

    public Student(String name, String id, String email, String telephone, String computersOnLoan)
    {
        this.name = name;
        this.id = id;
        this.email = email;
        this.telephone = telephone;
        this.computersOnLoan = computersOnLoan;
    }

    public String getName()
    {
        return name;
    }

    public String getId()
    {
        return id;
    }

    public String getEmail()
    {
        return email;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public String getComputersOnLoan()
    {
        return computersOnLoan;
    }
}
