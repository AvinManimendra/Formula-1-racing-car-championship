import java.io.Serializable;

public abstract class Driver implements Serializable
{
    private String Driver_Name;
    private String Driver_Location;
    private String Driver_Team;
    private int First_Positions = 0;
    private int Second_Positions = 0;
    private int Third_Positions = 0;
    private int points = 0;
    private int No_Of_Races = 0;

    public Driver(String Driver_Name, String Driver_Location, String Driver_Team)
    {
        this.Driver_Name=Driver_Name;
        this.Driver_Location=Driver_Location;
        this.Driver_Team=Driver_Team;
    }

    public String getDriver_Name() {
        return Driver_Name;
    }

    public void setDriver_Name(String driver_Name) {
        Driver_Name = driver_Name;
    }

    public String getDriver_Location() {
        return Driver_Location;
    }

    public void setDriver_Location(String driver_Location) {
        Driver_Location = driver_Location;
    }

    public String getDriver_Team() {
        return Driver_Team;
    }

    public void setDriver_Team(String driver_Team) {
        Driver_Team = driver_Team;
    }

    public int getFirst_Positions() {
        return First_Positions;
    }

    public int getSecond_Positions() {
        return Second_Positions;
    }

    public int getThird_Positions() {
        return Third_Positions;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getNo_Of_Races() {
        return No_Of_Races;
    }

    public void setNo_Of_Races()
    {
        this.No_Of_Races++;
    }

    public void setFirstPositions() {
    }

    public void setSecondPositions() {
    }

    public void setThirdPositions() {
    }
}