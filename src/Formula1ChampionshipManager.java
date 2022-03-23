import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Formula1ChampionshipManager implements Serializable {

    static ArrayList<Formula1Driver> drivers = new ArrayList<Formula1Driver>();
    static ArrayList<Race> races = new ArrayList<>();

    public static void main(String[] args)
    {
        // Initialize Common Variables
        int stop_count =99;

        Scanner input = new Scanner(System.in);
        System.out.println();

        //Loading previous data to the program
        LoadDate();

        // Start Of The Main Loop
        while (stop_count != 0)
        {
            // Displaying The Menu
            System.out.println();
            System.out.println("--------------------Read The Menu---------------------");
            System.out.println("Enter 1 or CND for Create a New Driver");
            System.out.println("Enter 2 or DAD for Delete A Driver");
            System.out.println("Enter 3 or CTD for Change The Driver");
            System.out.println("Enter 4 or DVS for Display the Various Statistics ");
            System.out.println("Enter 5 or DFT for Display the Formula 1 Driver Table");
            System.out.println("Enter 6 or AAR for Add A Race");
            System.out.println("Enter 7 or SIF for Saving In a File");
            System.out.println("Enter 8 or GUI for Open The Graphical User Interface");
            System.out.println("Enter 0 or EXT for Exit the Program");
            System.out.println("------------------------------------------------------");

            System.out.print("Enter Task Number or ID : ");
            String func_id = input.next();
            System.out.println();

            // Method Selection
            if (func_id.equals("1") || func_id.equals("CND"))
                CreateANewDriver();

            else if (func_id.equals("2") || func_id.equals("DAD"))
                DeleteADriver();

            else if (func_id.equals("3") || func_id.equals("CTD"))
                ChangeTheDriver();

            else if (func_id.equals("4") || func_id.equals("DVS"))
                DisplayTheVariousStatistics();

            else if (func_id.equals("5") || func_id.equals("DFT"))
                DisplayTheFormula1DriverTable();

            else if (func_id.equals("6") || func_id.equals("AAR"))
                AddARace();

            else if (func_id.equals("7") || func_id.equals("SIF"))
                SavingInAFile();

            else if (func_id.equals("8") || func_id.equals("GUI"))
                OpenTheGraphicalUserInterface();

            else if (func_id.equals("0") || func_id.equals("EXT"))
            {
                stop_count = 0;
                System.out.println("Program Ended Safely");
                System.out.println("------------------------------------------------------");
                System.out.println("------------------------------------------------------");
            }
            else
            {
                System.out.println("Invalid Task Number Or ID !");
                System.out.println("------------------------------------------------------");
            }
        }
    }

    // To create a new driver
    public static void CreateANewDriver()
    {
        Scanner input = new Scanner(System.in);

        //Getting user inputs
        System.out.print("Enter Driver's Name : ");
        String Driver_Name = input.next();
        System.out.print("Enter Driver's Location : ");
        String Driver_Location = input.next();
        System.out.print("Enter Driver's Team : ");
        String Driver_Team = input.next();
        System.out.print("Enter No Of First Positions : ");
        int First_Positions = input.nextInt();
        System.out.print("Enter No Of Second Positions : ");
        int Second_Positions = input.nextInt();
        System.out.print("Enter No Of Third Positions : ");
        int Third_Positions = input.nextInt();
        System.out.print("Enter No of Points : ");
        int points = input.nextInt();
        System.out.print("Enter No Of Races : ");
        int No_Of_Races = input.nextInt();

        String ifUnique = "True";
        for (Formula1Driver temp : drivers)
        {
            //Checking the Driver_Team uniqueness
            if (temp.getDriver_Team().equals(Driver_Team))
            {
                ifUnique = "False";
                break;
            }
        }

        if (ifUnique.equals("False"))
        {
            System.out.println("Driver Team Already Exit !");
            System.out.println("The Driver Should Be Associated With a Unique Team");
        }
        else
        {
            drivers.add(new Formula1Driver(Driver_Name, Driver_Location, Driver_Team, First_Positions, Second_Positions, Third_Positions, points, No_Of_Races));
        }
        System.out.println("------------------------------------------------------");
    }

    //Deleting an existing driver
    public static void DeleteADriver()
    {
        Scanner input = new Scanner(System.in);

        //Getting user inputs
        System.out.print("Enter Driver Name You Want to Delete : ");
        String Delete_Driver_Name = input.next();
        System.out.print("Enter Driver Team You Want to Delete : ");
        String Delete_Driver_Team = input.next();

        Driver temp_out = null;
        String ifUnique = "True";
        for (Formula1Driver temp : drivers)
        {
            //Checking driver name and driver team
            if (temp.getDriver_Name().equals(Delete_Driver_Name) && temp.getDriver_Team().equals(Delete_Driver_Team))
            {
                temp_out = temp;
                ifUnique = "False";
            }
        }

        if(ifUnique.equals("False"))
        {
            drivers.remove(temp_out);
            System.out.println();
            System.out.println("Driver Deleted Successfully !");
            System.out.println();
        }
        else
        {
            System.out.println("Driver Name Does Not Exit !");
            System.out.println("Please Enter a Valid Driver Information");
        }
        System.out.println("------------------------------------------------------");
    }

    //Changing an existing driver
    public static void ChangeTheDriver()
    {
        Scanner input = new Scanner(System.in);

        //Getting user inputs
        System.out.print("Enter The Team Name : ");
        String Change_Team_Name = input.next();
        System.out.print("Enter New Driver Name You Want : ");
        String New_Driver_Name = input.next();
        System.out.println();

        Driver temp_out = null;
        String ifUnique = "True";
        for (Formula1Driver temp : drivers)
        {
            //Checking the availability of the driver team
            if (temp.getDriver_Team().equals(Change_Team_Name))
            {
                temp_out = temp;
                ifUnique = "False";
            }
        }

        if(ifUnique.equals("False"))
        {
            temp_out.setDriver_Name(New_Driver_Name);
            System.out.println("Driver Changed Successfully !");
        }
        else
        {
            System.out.println("Driver Team Does Not Exit !");
            System.out.println("Please Enter a Valid Driver Team");
        }
        System.out.println("------------------------------------------------------");
    }

    //Displaying statistics of a particular driver
    public static void DisplayTheVariousStatistics()
    {
        Scanner input = new Scanner(System.in);

        //Getting user inputs
        System.out.print("Enter Driver Name You Want Display : ");
        String Display_Driver_Name = input.next();
        System.out.println();

        Driver temp_out = null;
        String ifUnique = "False";
        for (Formula1Driver temp : drivers)
        {
            //Checking the availability of the driver name
            if (temp.getDriver_Name().equals(Display_Driver_Name))
            {
                temp_out = temp;
                ifUnique = "True";
            }
        }
        if (ifUnique.equals("True"))
        {
            //Displaying selected driver's information
            System.out.println("Driver Name : "+temp_out.getDriver_Name());
            System.out.println("Driver Location : "+temp_out.getDriver_Location());
            System.out.println("Driver Team : "+temp_out.getDriver_Team());
            System.out.println("Number Of First Positions : "+temp_out.getFirst_Positions());
            System.out.println("Number Of Second Position : "+temp_out.getSecond_Positions());
            System.out.println("Number Of Third Position : "+temp_out.getThird_Positions());
            System.out.println("Driver's Total Points : "+temp_out.getPoints());
            System.out.println("Driver's Number Of Races : "+temp_out.getNo_Of_Races());
        }
        else
        {
            System.out.println("Driver Name Does Not Exit !");
            System.out.println("Please Enter a Valid Driver Name");
        }

        System.out.println("------------------------------------------------------");
    }

    //Displaying statistics of all the drivers
    public static void DisplayTheFormula1DriverTable()
    {
        //Sorting all the drivers using PointComparator class
        Collections.sort(drivers, new PointComparator());
        System.out.println("------------------------------------------Driver Statistics Table-------------------------------------------");
        System.out.println();
        System.out.println("+-------------+-----------------+-------------+--------------+---------------+--------------+--------------+");
        System.out.println("| Driver Name | Driver Location | Driver Team | First Places | Second Places | Third Places | Total Points |");
        System.out.println("+-------------+-----------------+-------------+--------------+---------------+--------------+--------------+");

        for (Formula1Driver temp : drivers)
        {
            //Taking data from the arraylist into variables
            String tempDriverName = temp.getDriver_Name();
            String tempDriverLocation = temp.getDriver_Location();
            String tempDriverTeam = temp.getDriver_Team();
            int tempDriverFirstPlaces = temp.getFirst_Positions();
            int tempDriverSecondPlaces = temp.getSecond_Positions();
            int tempDriverThirdPlaces = temp.getThird_Positions();
            int tempDriverPoints = temp.getPoints();

            //Displaying information on the table
            System.out.printf("| %12s"," "+tempDriverName);
            System.out.printf("| %16s"," "+tempDriverLocation);
            System.out.printf("| %12s"," "+tempDriverTeam);
            System.out.printf("| %13s"," "+tempDriverFirstPlaces);
            System.out.printf("| %14s"," "+tempDriverSecondPlaces);
            System.out.printf("| %13s"," "+tempDriverThirdPlaces);
            System.out.printf("| %14s %n"," "+tempDriverPoints+"|");
            System.out.println("+-------------+-----------------+-------------+--------------+---------------+--------------+--------------+");
        }
        System.out.println();
    }

    //Adding a New race
    public static void AddARace()
    {
        try
        {
            Scanner input = new Scanner(System.in);

            //User inputs (Date)
            System.out.print("Enter Date (DD/MM/YYYY) : ");
            String Dates = input.next();
            SimpleDateFormat Format_Date= new SimpleDateFormat("dd/MM/yyyy");
            Date date = Format_Date.parse(Dates);
            Race race = new Race(date, drivers);
            races.add(race);

            for (Formula1Driver temp_drivers : drivers)
            {
                //Checking which drivers participated in the race
                System.out.println("Is "+ temp_drivers.getDriver_Name() +" participated in the race?");
                System.out.println();
                System.out.print("If yes enter 'Y' if not enter 'N' : ");
                String answer = input.next();
                System.out.println();

                Driver temp_out = null;
                String ifUnique = "False";
                if (answer.equals("Y"))
                {
                    temp_out = temp_drivers;
                    ifUnique = "True";
                }

                if (ifUnique.equals("True"))
                {
                    //Getting each driver's position of the current race
                    System.out.print("Enter Position of "+ temp_out.getDriver_Name() +" : ");
                    int position = input.nextInt();
                    ((Formula1Driver) temp_out).calculateThePoints(position);
                    race.setDriverNameAndDriverPosition(temp_out.getDriver_Name(), Integer.toString(position));
                    switch(position)
                    {
                        case 1 :
                            temp_out.setFirstPositions();
                            break;
                        case 2 :
                            temp_out.setSecondPositions();
                            break;
                        case 3 :
                            temp_out.setThirdPositions();
                            break;
                    }
                }
            }
            System.out.println("New Race Added Successfully !");
        }
        catch (ParseException| InputMismatchException exception)
        {
            System.out.println("Error Occurred !");
            System.out.println("Race Didn't Added");
            exception.printStackTrace();
        }
        System.out.println("------------------------------------------------------");
    }

    //Saving data into a File
    public static void SavingInAFile()
    {
        try
        {
            FileOutputStream writeData = new FileOutputStream("Data_File.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(drivers);
            writeStream.flush();
            writeStream.close();

            System.out.println("Data File Updated Successfully !");
        }
        catch (IOException e)
        {
            System.out.println("Error Occurred!");
            System.out.println("File Not Found");
            e.printStackTrace();
        }
    }

    //Opening the graphical user interface
    public static void OpenTheGraphicalUserInterface()
    {
        String[] args = new String[0];
        GUI.main(args);
    }

    //Loading previous data into the program
    //This method is automatically calling when program starting
    public static void LoadDate()
    {
        try
        {
            FileInputStream readData = new FileInputStream("Data_File.ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);
            drivers = (ArrayList<Formula1Driver>) readStream.readObject();
            readStream.close();

            System.out.println("Previous driver data restored Successfully !");
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Previous driver data didn't restored !");
            e.printStackTrace();
        }
    }
}

// References
/*
w3 schools : https://www.w3schools.com/java/default.asp
JAVA Point : https://www.javatpoint.com/java-tutorial
BAELDUBG : https://www.baeldung.com/java-printstream-printf
 */
