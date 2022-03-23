import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class GUI extends JFrame
{
    //Initialize common swing variables
    JTable DriverTableView;
    JTable RandomRaceTable;
    JTable RacesTable;
    JTable SearchResultsTable;
    JScrollPane ScrollPane1;
    JScrollPane ScrollPane2;
    JScrollPane ScrollPane3;
    JScrollPane ScrollPane4;
    JButton AscendingOrderButton;
    JButton DescendingByPositionButton;
    JButton CreateRandomRaceButton;
    JButton CreateRaceWithProbButton;
    JButton ViewRacesButton;
    JButton SearchButton;
    JLabel DateOfRace;
    JTextField NameInputField;

    public static void main (String args[])
    {
        new GUI();
    }

    public GUI()
    {
        Formula1ChampionshipManager driverObject = new Formula1ChampionshipManager();

        //Setting the JFrame
        setTitle("Formula One Championship Manager");
        setSize(1175,820);
        //setBackground(Color.CYAN);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Displaying drivers' statistics on the table
        String[] columnsStatTable = {"Driver Name","Driver Location","Driver Team","First Positions","Second Positions","Third Positions","No of Races Participated","Total Points"};
        String[][] rowsStatTable = new String[driverObject.drivers.size()][8]; //Holds data of the table
        TableDataPointsDescending(rowsStatTable,driverObject);
        DriverTableView = new JTable(rowsStatTable,columnsStatTable);
        DriverTableView.setDefaultEditor(Object.class, null); //get an uneditable table
        ScrollPane1=new JScrollPane(DriverTableView); //make the table scrollable
        ScrollPane1.setBounds(25,25,1110,300);

        //Button to sort statistics table in ascending order
        AscendingOrderButton = new JButton("View Ascending Order");
        AscendingOrderButton.setBounds(25,340,200,50);
        AscendingOrderButton.setBackground(Color.LIGHT_GRAY);
        AscendingOrderButton.addActionListener(new  ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                TableDataPointsAscending(rowsStatTable,driverObject);
                DriverTableView.repaint(); //refresh the table
            }
        });

        //Button to sort the statistics table in descending order according to the first positions of each driver
        DescendingByPositionButton = new JButton("Sort By First Positions");
        DescendingByPositionButton.setBounds(300,340,200,50);
        DescendingByPositionButton.setBackground(Color.LIGHT_GRAY);
        DescendingByPositionButton.addActionListener(new  ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                TableDataPositionsDescending(rowsStatTable,driverObject);
                DriverTableView.repaint(); //Refresh the table
            }
        });

        //Button to generate a random race according to probability with table
        CreateRaceWithProbButton = new JButton("Probability Random Race");
        CreateRaceWithProbButton.setBounds(575,340,190,50);
        CreateRaceWithProbButton.setBackground(Color.LIGHT_GRAY);
        CreateRaceWithProbButton.addActionListener(new  ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                String date = RandomRaceProbability(driverObject);
                TableDataPointsDescending(rowsStatTable,driverObject);
                DriverTableView.repaint(); //Refresh the table
            }
        });

        //Button to generate a random race with table
        String [] columnGenerateRandom = {"Name","Position"};
        String [][] randomRaceRow = new String[driverObject.drivers.size()][2];
        DateOfRace = new JLabel();
        CreateRandomRaceButton = new JButton("Create Random Race");
        CreateRandomRaceButton.setBounds(880,340,160,50);
        CreateRandomRaceButton.setBackground(Color.LIGHT_GRAY);
        CreateRandomRaceButton.addActionListener(new  ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                //Getting random date
                String date = RandomRace(driverObject,randomRaceRow);
                DateOfRace.setBounds(880, 415, 250, 20);
                DateOfRace.setText("Random Race Date  : " + date);

                //Displaying random race information on the table
                TableDataPointsDescending(rowsStatTable, driverObject); //update values inside the table
                DriverTableView.repaint(); //Refresh the table
                RandomRaceTable = new JTable(randomRaceRow, columnGenerateRandom);
                RandomRaceTable.setDefaultEditor(Object.class, null);
                ScrollPane2 = new JScrollPane(RandomRaceTable);
                ScrollPane2.setBounds(790, 460, 350, 310);
                add(ScrollPane2);
            }
        });

        //Button to display all the finished races
        ViewRacesButton = new JButton("View All Races");
        ViewRacesButton.setBounds(130,405,150,40);
        ViewRacesButton.setBackground(Color.LIGHT_GRAY);
        ViewRacesButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                //Displaying all finished races' information on the table
                String[] raceTableColumn = {"Date","Participant Count"};
                String[][] raceTableData = new String[driverObject.races.size()][2];
                RaceTableData(driverObject,raceTableData);

                RacesTable = new JTable(raceTableData,raceTableColumn);
                RacesTable.setDefaultEditor(Object.class, null);
                ScrollPane3 = new JScrollPane(RacesTable);
                ScrollPane3.setBounds(25,460,350,310);
                add(ScrollPane3);
            }
        });

        //Button to display search results
        NameInputField = new JTextField(); //Gets search name
        NameInputField.setBounds(575,405,192,40);
        SearchButton = new JButton("Search");
        SearchButton.setBounds(450,405,100,40);
        SearchButton.setBackground(Color.LIGHT_GRAY);
        SearchButton.addActionListener(new  ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                //Displaying search results on the table
                String [] resultTableColumn = {"Date","Finished Position"};
                String [][] resultTableData = new String[driverObject.races.size()][2];
                ResultTableData(NameInputField.getText(),driverObject,resultTableData);

                SearchResultsTable = new JTable(resultTableData,resultTableColumn);
                SearchResultsTable.setDefaultEditor(Object.class,null);
                ScrollPane4 = new JScrollPane(SearchResultsTable);
                ScrollPane4.setBounds(395,460,380,310);
                add(ScrollPane4);
            }
        });

        //Adds swing components to the interface
        add(ScrollPane1);
        add(AscendingOrderButton);
        add(CreateRandomRaceButton);
        add(CreateRaceWithProbButton);
        add(DescendingByPositionButton);
        add(DateOfRace);
        add(ViewRacesButton);
        add(NameInputField);
        add(SearchButton);
    }

    public void TableDataPointsDescending(String[][] rows,Formula1ChampionshipManager driverObject)
    {
        //Sorting drivers in the "drivers" in descending order according to their points
        Collections.sort(driverObject.drivers, new PointComparator());
        int count=0;
        for (Formula1Driver temp : driverObject.drivers){
            rows [count][0] = temp.getDriver_Name();
            rows [count][1] = temp.getDriver_Team();
            rows [count][2] = temp.getDriver_Location();
            rows [count][3] = Integer.toString(temp.getFirst_Positions());
            rows [count][4] = Integer.toString(temp.getSecond_Positions());
            rows [count][5] = Integer.toString(temp.getThird_Positions());
            rows [count][6] = Integer.toString(temp.getNo_Of_Races());
            rows [count][7] = Integer.toString(temp.getPoints());
            count++;
        }
    }

    public void TableDataPointsAscending(String[][] rows,Formula1ChampionshipManager driverObject)
    {
        //Sorting drivers in the "drivers" in ascending order according to their points
        Collections.sort(driverObject.drivers,new PointComparatorAscending());
        int count=0;
        for (Formula1Driver temp : driverObject.drivers){
            rows [count][0] = temp.getDriver_Name();
            rows [count][1] = temp.getDriver_Team();
            rows [count][2] = temp.getDriver_Location();
            rows [count][3] = Integer.toString(temp.getFirst_Positions());
            rows [count][4] = Integer.toString(temp.getSecond_Positions());
            rows [count][5] = Integer.toString(temp.getThird_Positions());
            rows [count][6] = Integer.toString(temp.getNo_Of_Races());
            rows [count][7] = Integer.toString(temp.getPoints());
            count++;
        }
    }

    public void TableDataPositionsDescending(String[][] rows,Formula1ChampionshipManager driverObject)
    {
        //Sorting drivers in the "drivers" in descending order according to their first positions
        Collections.sort(driverObject.drivers, new PointComparator());
        int count=0;
        for (Formula1Driver temp : driverObject.drivers){
            rows [count][0] = temp.getDriver_Name();
            rows [count][1] = temp.getDriver_Team();
            rows [count][2] = temp.getDriver_Location();
            rows [count][3] = Integer.toString(temp.getFirst_Positions());
            rows [count][4] = Integer.toString(temp.getSecond_Positions());
            rows [count][5] = Integer.toString(temp.getThird_Positions());
            rows [count][6] = Integer.toString(temp.getNo_Of_Races());
            rows [count][7] = Integer.toString(temp.getPoints());
            count++;
        }
    }

    //Creating a random race
    public String RandomRace(Formula1ChampionshipManager driverObject,String[][] randomRaceRow)
    {
        try
        {
            ArrayList<Integer> validPosition = new ArrayList<>(); //Store generated valid positions
            int position;
            int count = 0;
            int month = (int) (Math.random() * 12) + 1; //Generates a random month
            Calendar calender = Calendar.getInstance();
            calender.set(2021, month, 0);
            int daysOfMonth = calender.get(Calendar.DAY_OF_MONTH);  //Gets num of days in the month
            int day = (int) (Math.random() * daysOfMonth + 1); //Generates a random day
            String dateS = day + "/" + month + "/" + 2021;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
            Date date = dateFormat.parse(dateS);
            Race race = new Race(date, driverObject.drivers);
            driverObject.races.add(race);

            //Selecting drivers one by one
            for (Formula1Driver temp : driverObject.drivers)
            {
                //Generating a random position and check for validity
                while (true)
                {
                    position = (int) (Math.random() * driverObject.drivers.size()) + 1;
                    if (validPosition.isEmpty())
                    {
                        validPosition.add(position);
                        break;
                    }
                    else if (!validPosition.contains(position))
                    {
                        validPosition.add(position);
                        break;
                    }
                }
                //Add values to the table
                randomRaceRow[count][0] = temp.getDriver_Name();
                randomRaceRow[count][1] = Integer.toString(position);

                //Add points and position
                temp.calculateThePoints(position);

                if (position == 1)
                {
                    temp.setFirstPositions();
                }
                else if (position == 2)
                {
                    temp.setSecondPositions();
                }
                else if (position == 3)
                {
                    temp.setThirdPositions();
                }
                race.setParticipants();
                temp.setNo_Of_Races();

                //Store name and position of each driver
                race.setDriverNameAndDriverPosition(temp.getDriver_Name(), Integer.toString(position));
                count++;
            }
            return dateS;
        }
        catch (ParseException ignored)
        {
            return null;
        }
    }

    //Generating a random race where random driver win the race according to starting position (specific probability for each starting position)
    public String RandomRaceProbability(Formula1ChampionshipManager driverObject)
    {
        try
        {
            ArrayList<Integer> validProbabilityPositions = new ArrayList<>(); //Holds randomly created valid positions
            boolean won;
            int driverPosition;
            int randomNumber;
            boolean gotFirstPosition = false;
            //Since this programme is made for one season of the championship year was not generated as a random.

            int month = (int) (Math.random() * 12) + 1; //Generates a random month
            Calendar calender = Calendar.getInstance();
            calender.set(2021, month, 0);
            int daysOfMonth = calender.get(Calendar.DAY_OF_MONTH);  //Gets num of days in the month
            int day = (int) (Math.random() * daysOfMonth + 1); //Generates a random day
            String dates = day + "/" + month + "/" + 2021;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
            Date date = dateFormat.parse(dates);
            Race race = new Race(date, driverObject.drivers);
            driverObject.races.add(race);
            Collections.shuffle(driverObject.drivers);//Getting random starting positions by shuffling the list

            for (Formula1Driver temp : driverObject.drivers)
            {
                int starting = driverObject.drivers.indexOf(temp)+1; //starting position = index of the shuffled list
                //Generating a valid finished position
                while (true)
                {
                    //Wins the race considering starting position and its probability
                    randomNumber = (int) (Math.random() * 100 + 1);
                    won = false;
                    if (starting == 1)
                    {
                        if (randomNumber >= 1 && randomNumber <= 40)
                        {
                            won = true;
                        }
                    }
                    else if (starting == 2)
                    {
                        if (randomNumber >= 40 && randomNumber <= 70)
                        {
                            won = true;
                        }
                    }
                    else if (starting == 3 || starting == 4)
                    {
                        if (randomNumber >= 70 && randomNumber <= 80)
                        {
                            won = true;
                        }
                    }
                    else if (starting >= 5 && starting <= 8)
                    {
                        if (randomNumber == 100 || randomNumber == 99)
                        {
                            won = true;
                        }
                    }
                    else if(starting == 9)
                    {
                        won = true;
                    }
                    if (won && !gotFirstPosition || (driverObject.drivers.size() < 9 && starting == driverObject.drivers.size()-1 && !validProbabilityPositions.contains(1)))
                    {
                        driverPosition = 1;
                        gotFirstPosition = true;
                    }
                    else
                    {
                        driverPosition = (int) (Math.random() * driverObject.drivers.size() - 1) + 2;
                    }
                    if (validProbabilityPositions.isEmpty())
                    {
                        validProbabilityPositions.add(driverPosition);
                        break;
                    }
                    else if (!validProbabilityPositions.contains(driverPosition))
                    {
                        validProbabilityPositions.add(driverPosition);
                        break;
                    }
                }

                temp.calculateThePoints(driverPosition);
                //Add points and position
                if (driverPosition == 1)
                {
                    temp.setFirstPositions();
                }
                else if (driverPosition == 2)
                {
                    temp.setSecondPositions();
                }
                else if (driverPosition == 3)
                {
                    temp.setThirdPositions();
                }
                race.setParticipants();
                temp.setNo_Of_Races();
                //Store name and position of each driver
                race.setDriverNameAndDriverPosition(temp.getDriver_Name(), Integer.toString(driverPosition));
            }
            return dates;

        }
        catch (ParseException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    //Setting values in races table
    public void RaceTableData(Formula1ChampionshipManager driverObject,String [][] raceTableData)
    {
        //Sort the races according to date
        Collections.sort(driverObject.races,new DateComparator());
        int count =0;
        //Date and number of participants gets added to table for each race
        for(Race temp : driverObject.races)
        {
            raceTableData[count][0] = temp.getDate().toString();
            raceTableData[count][1] = Integer.toString(temp.getParticipants());
            count++;
        }
    }

    //Setting values in results table
    public void ResultTableData(String name,Formula1ChampionshipManager driverObject,String[][] resultTableData)
    {
        int count = 0;
        //checks for driver name in races
        for(Race temp : driverObject.races)
        {
            for(String[] driverTemp : temp.getDriverNameAndDriverPosition())
            {
                //Checks whether selected array element is null
                if(driverTemp[0] != null)
                {
                    //date and position is of the race gets added to the table if a participated race is found
                    if (driverTemp[0].equals(name))
                    {
                        resultTableData[count][0] = temp.getDate().toString();
                        resultTableData[count][1] = driverTemp[1];
                        break;
                    }
                }
            }
            count++;
        }
    }
}
