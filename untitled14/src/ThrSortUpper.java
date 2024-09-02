import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ThrSortUpper extends Thread{
    private ArrayList<Coffee> cflist;

    public ThrSortUpper(ArrayList<Coffee>list)
    {
        this.cflist = list;
    }

    @Override
    public void run(){
        Collections.sort(cflist, new Comparator<Coffee>() {
            @Override
            public int compare(Coffee o1, Coffee o2) {
                return o1.getType().compareTo(o2.getType());
//                return Double.compare(o2.getPrice(),(o1.getPrice()));

            }
        });
    }
}
