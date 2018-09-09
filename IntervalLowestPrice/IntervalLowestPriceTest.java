import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class IntervalLowestPriceTest {


    @Test
    public void getLowestPrices() {

        //[1, 5, 5], [1, 5, 10]
        Interval interval_1 = new Interval(1, 5, 5);
        Interval interval_2 = new Interval(1, 5, 10);
        List<Interval> list_1 = new ArrayList<Interval>();
        list_1.add(interval_1);
        list_1.add(interval_2);

        //[1, 5, 10], [3, 6, 5]
        Interval intervalt_3 = new Interval(1, 5, 10);
        Interval interval_4 = new Interval(3, 6, 5);
        List<Interval> list_2 = new ArrayList<Interval>();
        list_2.add(intervalt_3);
        list_2.add(interval_4);

        //[1, 6, 20], [3, 5, 10], [5, 9, 25], [9, 12, 20], [10, 15, 8]
        Interval interval_5 = new Interval(1, 6, 20);
        Interval interval_6 = new Interval(3, 5, 10);
        Interval interval_7 = new Interval(5, 9, 25);
        Interval interval_8 = new Interval(9, 12, 20);
        Interval interval_9 = new Interval(10, 15, 8);
        List<Interval> list_3 = new ArrayList<Interval>();
        list_3.add(interval_5);
        list_3.add(interval_6);
        list_3.add(interval_7);
        list_3.add(interval_8);
        list_3.add(interval_9);

        IntervalLowestPrice ilp = new IntervalLowestPrice();
        List<Interval> sortList = ilp.getLowestPrices(list_2);


        for (Interval interval : sortList) {
            System.out.println(interval.start + "," + interval.end + "," + interval.price);
        }
    }
}
