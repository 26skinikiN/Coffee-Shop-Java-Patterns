import java.util.ArrayList;
import java.util.Scanner;

//Абстрактный класс с шаблонным методом - поведенческий паттерн - потом в AdminManager
public abstract class ComponentManager {
    protected ArrayList<Coffee> coffeeArrayList;
    Scanner scan = new Scanner(System.in);

    public ComponentManager(ArrayList<Coffee> coffeeArrayList) {

        this.coffeeArrayList = coffeeArrayList;
    }

    protected abstract void displayMenu();

    protected abstract void performAction(int choice);

}