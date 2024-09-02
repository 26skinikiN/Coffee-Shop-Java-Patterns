import java.io.Serializable;
import java.util.Arrays;

public class Coffee implements Serializable
{

    private String type;
    private int weight;
    private double price;
    public void setPrice(double price) {
        this.price = price;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public Coffee(String type, double price, int weight){
        this.type =type;
        this.price=price;
        this.weight= weight;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    public int getWeight(){
        return weight;
    }
    public double getPrice(){
        return price;
    }
    public String toString(){
        return
                "Тип кофе: " + type + "."+
                        "\nВес кофе: " + weight + " г."+
                        "\nЦена: " + price+ " р/"+weight+" г.";
    }
}
