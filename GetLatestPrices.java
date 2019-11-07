/* Only works with Java 9 */

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.BinaryOperator;

class Price{
    private int id;
    private double price;
    private long timestamp;

    public Price(int id, double price, long timestamp){
        this.id = id;
        this.price = price;
        this.timestamp = timestamp;
    }

    public int getId(){
        return id;
    }

    public double getPrice(){
        return price;
    }

    public long getTimestamp(){
        return timestamp;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setTimestamp(long timestamp){
        this.timestamp = timestamp;
    }

    public String toString(){
        return "Price[" + id + "," + price + "," + timestamp + "]";
    }

}

public class GetLatestPrices{

    public static void main(String []args){
        List<Price> prices = Arrays.asList(
                new Price(1,3.0,31111),
                new Price(1,20.0,44444),
                new Price(1,5.0,22222),
                new Price(2,2.0,11111),
                new Price(2,1.0,33333),
                new Price(3,10.0,99989)
        );

        System.out.println(getLatestPrice(prices));
    }

    private static List<Price> getLatestPrice(List<Price> prices) {
        Comparator<Price> byTimestamp = Comparator.comparing(Price::getTimestamp);
        List<Price> latestPrices = prices.stream()
                .collect(
                        Collectors.groupingBy(x -> x.getId(),Collectors.reducing(BinaryOperator.maxBy(byTimestamp))))
                .values()
                .stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        return latestPrices;
    }
}
