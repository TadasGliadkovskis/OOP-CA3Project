package com.dkit.sd2a.tadasgliadkovskis;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentDB
{
    private ArrayList<Student> students;
    final static String FILE_NAME = "students.txt";
    private static int dataIndexCounter = 0;

    public StudentDB()
    {
        students = new ArrayList<>();
    }

    protected void loadStudentsFromFile()
    {
        try (Scanner studentsFile = new Scanner(new BufferedReader(new FileReader(FILE_NAME))))
        {
            String input;
            int STUDENT_WITH_NO_COMPUTERS_ON_LOAN = 4;
            int STUDENT_WITH_COMPUTERS_ON_LOAN = 5;
            while (studentsFile.hasNextLine())
            {
                input = studentsFile.nextLine();
                String[] data = input.split(",");

                if (data.length == STUDENT_WITH_NO_COMPUTERS_ON_LOAN)
                {
                    studentWithNoComputer(data);
                } else if (data.length == STUDENT_WITH_COMPUTERS_ON_LOAN)
                {
                    studentWithComputer(data);
                } else
                {
                    throw new ArrayIndexOutOfBoundsException();
                }
            }
        } catch (FileNotFoundException fne)
        {
            System.out.println(Colours.RED + "File " + FILE_NAME + " not found" + Colours.RESET);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException formatError)
        {
            System.out.println(Colours.RED + "File " + FILE_NAME + " has incorrect formatting" + Colours.RESET);
        }
    }

    private void studentWithNoComputer(String[] data)
    {
        String name = data[dataIndexCounter++].trim();
        String id = data[dataIndexCounter++].trim();
        String email = data[dataIndexCounter++].trim();
        String telephone = data[dataIndexCounter++].trim();
        Student readInStudent = new Student(name, id, email, telephone);
        students.add(readInStudent);
        dataIndexCounter = 0;
    }

    private void studentWithComputer(String[] data)
    {
        String name = data[dataIndexCounter++].trim();
        String id = data[dataIndexCounter++].trim();
        String email = data[dataIndexCounter++].trim();
        String telephone = data[dataIndexCounter++].trim();
        String computersOnLoan = data[dataIndexCounter++].trim();
        Student readInStudent = new Student(name, id, email, telephone, computersOnLoan);
        students.add(readInStudent);
        dataIndexCounter = 0;
    }

    public void saveStudentsToFile()
    {
        if (!students.isEmpty())
        {
            try (BufferedWriter studentsFile = new BufferedWriter(new FileWriter(FILE_NAME)))
            {
                for (Student student : students)
                {
                    studentsFile.write(getInfoAsString(student) + "\n");
                }
            } catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        } else
        {
            System.out.println(Colours.RED + "There is no data to be saved for students " + Colours.RESET);
        }
    }

    public String getInfoAsString(Student student)
    {
        return student.getName() + ", " + student.getId() + ", " + student.getEmail()
                + ", " + student.getTelephone() + ", " + student.getComputersOnLoan();
    }

    protected ArrayList<String> getAllStudentIDs()
    {
        ArrayList<String> studentIDs = new ArrayList<>();
        for (Student student : students)
        {
            studentIDs.add(student.getId());
        }
        return studentIDs;
    }

    public void addStudent()
    {

    }

    public void updateStudent()
    {

    }

    public void deleteStudent()
    {

    }

    public void printAllStudents()
    {

    }

    public void printSelectedStudent()
    {

    }
}
