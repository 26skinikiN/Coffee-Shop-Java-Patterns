import java.util.ArrayList;
        import java.util.Collections;
        import java.util.Comparator;
        import java.util.stream.Collectors;

public class  RnblSortLower implements Runnable{
    private ArrayList<Coffee> cflist;
    public RnblSortLower(ArrayList<Coffee>list)
    {
        this.cflist = list;
    }
    public void run(){
        Collections.sort(cflist, new Comparator<Coffee>() {
            @Override
            public int compare(Coffee o1, Coffee o2) {
                return o2.getType().compareTo(o1.getType());
//                return Double.compare(o1.getPrice(),(o2.getPrice())); // На всякий случай, если сортить по цене
            }
        });
    }
}
