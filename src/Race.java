import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Race implements Serializable
{
    private Date date;
    private int Participants;
    private String [][] driverNameAndDriverPosition;
    private int index = 0;

    public Race(Date date, ArrayList<Formula1Driver> arrayList)
    {
        this.date = date;
        driverNameAndDriverPosition = new String[arrayList.size()][2];
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getParticipants() {
        return Participants;
    }

    public void setParticipants() {
        this.Participants++;
    }

    public String[][] getDriverNameAndDriverPosition()
    {
        return driverNameAndDriverPosition;
    }

    public void setDriverNameAndDriverPosition(String indexOne, String indexTwo)
    {
        this.driverNameAndDriverPosition[index][0] = indexOne;
        this.driverNameAndDriverPosition[index][1] = indexTwo;
        index = index + 1;
    }
}
