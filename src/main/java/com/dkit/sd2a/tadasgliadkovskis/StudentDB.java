package com.dkit.sd2a.tadasgliadkovskis;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentDB
{
    private ArrayList<Student> students;
    final static String FILE_NAME = "students.txt";
    private static int dataIndexCounter = 0;
    private final static Scanner userInput = new Scanner(System.in);

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
                    studentWithNoComputerBooked(data);
                } else if (data.length == STUDENT_WITH_COMPUTERS_ON_LOAN)
                {
                    studentWithComputerBooked(data);
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

    private void studentWithNoComputerBooked(String[] data)
    {
        String name = data[dataIndexCounter++].trim();
        String id = data[dataIndexCounter++].trim();
        String email = data[dataIndexCounter++].trim();
        String telephone = data[dataIndexCounter++].trim();
        Student readInStudent = new Student(name, id, email, telephone);
        students.add(readInStudent);
        dataIndexCounter = 0;
    }

    private void studentWithComputerBooked(String[] data)
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
        ArrayList<String> studentIDs = getAllStudentIDs();
        try
        {
            String studentName = getStudentNameFromUser();
            String studentTelephoneContact = getStudentTelephoneFromUser();
            String studentID = createStudentID(studentIDs);
            String studentEmail = studentName.split(" ")[0] + "." + studentName.split(" ")[1] + "@dkit.ie";
            Student newStudent = new Student(studentName, studentID, studentEmail, studentTelephoneContact);
            students.add(newStudent);
        } catch (InputMismatchException ie)
        {
            System.out.println(Colours.PURPLE + "Process Canceled" + Colours.RESET);
        }
    }

    private String createStudentID(ArrayList<String> studentIDs)
    {
        String IDStart = "D00";
        String studentID = "";
        boolean loop = true;
        Random rand = new Random();
        while (loop)
        {
            studentID = IDStart + (rand.nextInt(899999) + 100000);
            loop = studentIDs.contains(studentID);
        }
        return studentID;
    }

    private String getStudentTelephoneFromUser()
    {
        String patterns
                = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
        Pattern pattern = Pattern.compile(patterns);
        String telephone = "";
        boolean validPhone = false;
        while (!validPhone)
        {
            System.out.println("\nTo exit this process press 0");
            System.out.print("Please enter in students telephoneNumber ->");
            telephone = userInput.nextLine();
            Matcher matcher = pattern.matcher(telephone);
            validPhone = matcher.matches();
            if (telephone.equals("0"))
            {
                throw new InputMismatchException();
            }
            if (telephone.isEmpty())
            {
                System.out.println(Colours.RED + "Telephone can't be empty" + Colours.RESET);
            } else if (!validPhone)
            {
                System.out.println(Colours.RED + "Invalid telephone format" + Colours.RESET);
            }
        }
        return telephone;
    }

    private String getStudentNameFromUser()
    {
        String firstName = "";
        String secondName = "";
        boolean loop = true;
        while (loop)
        {
            System.out.println("\nTo exit this process press 0");
            System.out.print("Please enter in students first name ->");
            firstName = userInput.nextLine();
            System.out.print("Please enter in students second name ->");
            secondName = userInput.nextLine();
            if (firstName.equals("0") || secondName.equals("0"))
            {
                throw new InputMismatchException();
            }
            if (firstName.isEmpty() || secondName.isEmpty())
            {
                System.out.println(Colours.RED + "Name can't be empty" + Colours.RESET);
            } else
            {
                loop = false;
            }
        }
        return firstName.trim().concat(" " + secondName.trim());
    }

    public void updateStudent()
    {

    }

    public void deleteStudent()
    {

    }

    public void printAllStudents()
    {
        for (Student student : students)
        {
            System.out.println("\nID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Email: " + student.getEmail());
            System.out.println("telephone: " + student.getTelephone());
            System.out.println("Computers On loan: " + student.getComputersOnLoan());
        }
    }

    public void printSelectedStudent()
    {
        ArrayList<String> studentIDs = getAllStudentIDs();
        boolean studentIDExists = false;
        String studentIDToLookFor = "";
        while (!studentIDExists)
        {
            System.out.println("\nTo cancel this process enter 0");
            System.out.print("Please enter the student ID that you would like to see ->");
            studentIDToLookFor = userInput.nextLine().toUpperCase().trim();
            studentIDExists = studentIDs.contains(studentIDToLookFor);
            if (studentIDToLookFor.equals("0"))
            {
                throw new InputMismatchException();
            }
            if (studentIDToLookFor.isEmpty())
            {
                System.out.println(Colours.RED + "student ID cant be empty" + Colours.RESET);
            } else if (!studentIDExists)
            {
                System.out.println(
                        Colours.RED + "This student ID does not exist. please try again" + Colours.RESET);
            }
        }

        for (Student student : students)
        {
            if (student.getId().equals(studentIDToLookFor))
            {
                System.out.println("\nID: " + student.getId());
                System.out.println("Name: " + student.getName());
                System.out.println("Email: " + student.getEmail());
                System.out.println("telephone: " + student.getTelephone());
                System.out.println("Computers On loan: " + student.getComputersOnLoan());
            }
        }
    }
}
