import java.util.Comparator;

public class PointComparator implements Comparator<Formula1Driver>
{
    public int compare(Formula1Driver points1, Formula1Driver points2)
    {
        if (points1.getPoints() == points2.getPoints())
        {
            if (points1.getFirst_Positions() < points2.getFirst_Positions())
            {
                return 1;
            }
            else
                return -1;
        }
        else if (points1.getPoints() < points2.getPoints())
        {
            return 1;
        }
        else
            return -1;
    }
}
