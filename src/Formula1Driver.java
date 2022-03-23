import java.io.Serializable;

public class Formula1Driver extends Driver implements Serializable
{
    private int First_Positions;
    private int Second_Positions;
    private int Third_Positions;
    private int points;
    private int No_Of_Races;

    public Formula1Driver(String Driver_Name, String Driver_Location, String Driver_Team, int First_Positions, int Second_Positions, int Third_Positions, int points, int No_Of_Races)

    {
        super (Driver_Name,Driver_Location,Driver_Team);
        this.First_Positions=First_Positions;
        this.Second_Positions=Second_Positions;
        this.Third_Positions=Third_Positions;
        this.points=points;
        this.No_Of_Races=No_Of_Races;
    }

    public int getFirst_Positions() {
        return First_Positions;
    }

    public void setFirst_Positions(int first_Positions) {
        First_Positions = first_Positions;
    }

    public int getSecond_Positions() {
        return Second_Positions;
    }

    public void setSecond_Positions(int second_Positions) {
        Second_Positions = second_Positions;
    }

    public int getThird_Positions() {
        return Third_Positions;
    }

    public void setThird_Positions(int third_Positions) {
        Third_Positions = third_Positions;
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

    public void setNo_Of_Races(int no_Of_Races) {
        No_Of_Races = no_Of_Races;
    }

    //Calculating the points according to the position
    public void calculateThePoints(int position)
    {
        switch (position) {
            case 1:
                this.setPoints(this.getPoints()+25);
                break;
            case 2:
                this.setPoints(this.getPoints()+18);
                break;
            case 3:
                this.setPoints(this.getPoints()+15);
                break;
            case 4:
                this.setPoints(this.getPoints()+12);
                break;
            case 5:
                this.setPoints(this.getPoints()+10);
                break;
            case 6:
                this.setPoints(this.getPoints()+8);
                break;
            case 7:
                this.setPoints(this.getPoints()+6);
                break;
            case 8:
                this.setPoints(this.getPoints()+4);
                break;
            case 9:
                this.setPoints(this.getPoints()+2);
                break;
            case 10:
                this.setPoints(this.getPoints()+1);
                break;
            default:
                break;
        }
    }
}
