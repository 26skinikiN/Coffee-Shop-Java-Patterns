//Фабричный метод - порождающий паттерн - ConcreteCoffeeFactory
public interface CoffeeFactory {
     Coffee createCoffee(String type, double price, int weight);
}
