//реализация фабричного метода
class ConcreteCoffeeFactory implements CoffeeFactory {
    @Override
    public Coffee createCoffee(String type, double price, int weight) {

        return new Coffee(type, price, weight);
    }

}