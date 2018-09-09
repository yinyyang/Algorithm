import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class IntervalLowestPrice {

    //获取最终最大跨度最低价格集合
    List<Interval> getLowestPrices(List<Interval> sourceList) {
        List<Interval> resultList = new ArrayList<Interval>();
        List<Interval> newSortList = createIntervalListBySortDay(sourceList);
        //首次从最后一个结果开始 进行跨度最大价格最低的算法
        int lastIndex = newSortList.size() - 1;
        int lastEnd = newSortList.get(lastIndex).end;
        int lastPrice = newSortList.get(lastIndex).price;
        //第一个跨度时需要终止
        int firstStart = newSortList.get(0).start;

        while (true) {
            Interval resultInterval = groupInterval(lastEnd, lastPrice, newSortList);
            resultList.add(resultInterval);

            if (resultInterval.start == firstStart) {
                break;
            } else {
                lastEnd = resultInterval.start;
                lastPrice = getPrice(lastEnd, newSortList);
            }
        }

        //再次按照start升序排序
        resultList.sort((Interval x, Interval y) -> x.start > y.start ? 1 : -1);

        return resultList;

    }

    //通过end查找对应的price

    public int getPrice(int end, List<Interval> newSortList) {
        int price = 0;

        price = newSortList.stream().filter(interval -> interval.end == end).collect(Collectors.toList()).get(0).price;

        return price;


    }

    //同一价格的间隔分成一组兵取得跨度最大的一个结果
    public Interval groupInterval(int lastEnd, int lastPrice, List<Interval> newSortList) {
        List<Interval> list = new ArrayList<Interval>();
        for (Interval tempInterval : newSortList) {

            int start = tempInterval.start;
            int end = tempInterval.end;
            int price = tempInterval.price;
            if (lastEnd == end && lastPrice == price) {
                list.add(tempInterval);
            }


        }

        Interval resultInterval = getMaxInterval(list, newSortList);

        return resultInterval;

    }

    //同一价格最大间隔
    public Interval getMaxInterval(List<Interval> list, List<Interval> newSortList) {

        //按同一价格跨度升序排序所有
        list.sort((Interval x, Interval y) -> (x.end - x.start) > (y.end - y.start) ? 1 : -1);
        //其它跨度需要查找是否是上一个end
        for (Interval in1 : list) {
            for (Interval in2 : newSortList) {
                if (in1.start == in2.end && in1.price > in2.price) {
                    return in1;
                }
            }
        }
        //默认返回最大跨度
        return list.get(list.size() - 1);
    }


    //获取所有新的排序间隔集合
    public List<Interval> createIntervalListBySortDay(List<Interval> sourceList) {

        List<Integer> list = new ArrayList<Integer>();
        for (Interval interval : sourceList) {
            list.add(interval.start);
            list.add(interval.end);

        }
        List<Integer> sortList = list.stream().distinct().collect(Collectors.toList());
        //升序排序,end
        sortList.sort((Integer x, Integer y) -> x > y ? 1 : -1);

        List<Interval> newList = newList(sortList, sourceList);
        //新list按照start升序排序
        newList.sort((Interval x, Interval y) -> x.start > y.start ? 1 : -1);

        return newList;
    }

    //生成所有区间的Interval集合
    List<Interval> newList(List<Integer> list, List<Interval> sourceList) {
        List<Interval> newList = new ArrayList<Interval>();
        for (int i = 0; i < list.size() - 1; i++) {
            int start = list.get(i);
            int end = list.get(i + 1);
            int price = getPrice(start, end, sourceList);

            Interval interval = new Interval(start, end, price);
            newList.add(interval);
        }
        newList.addAll(sourceList);
        return newList;
    }

    //获取当前区间的价格
    int getPrice(int start, int end, List<Interval> sourceList) {
        List<Integer> priceList = new ArrayList<Integer>();

        for (Interval interval : sourceList) {
            if (start >= interval.start && end <= interval.end) {

                priceList.add(interval.price);

            }
        }

        //升序排列价格
        priceList.sort((Integer x, Integer y) -> x > y ? 1 : -1);

        //取价格最低返回
        return priceList.get(0);
    }

}


class Interval {

    int start;
    int end;
    int price;

    Interval(int start, int end, int price) {
        this.start = start;
        this.end = end;
        this.price = price;
    }


}

