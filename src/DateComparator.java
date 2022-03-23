import java.util.Comparator;

public class DateComparator implements Comparator <Race>
{
    @Override
    public int compare(Race one, Race two)
    {
        if( one.getDate().compareTo(two.getDate()) == 0 )
        {
            return 0;
        }
        else if (one.getDate().compareTo(two.getDate()) > 0)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
}
