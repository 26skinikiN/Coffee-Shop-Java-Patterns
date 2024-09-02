import java.util.ArrayList;
import java.util.Scanner;

public class AdminManager extends ComponentManager {
    private String filename;
    private String filecomputer;
    private ArrayList<User> accountList;
    Scanner scanner = new Scanner(System.in);
    Scanner scan = new Scanner(System.in);

    //соединила свою коллекцию с main
    public AdminManager(ArrayList<Coffee> coffeeArrayList, ArrayList<User> accountList, String filename, String filecomputer) {
        super(coffeeArrayList);
        this.accountList = accountList;
        this.filename = filename;
        this.filecomputer = filecomputer;
    }

    @Override
    protected void displayMenu() {
        System.out.println("\nМеню Администратора:");
        System.out.println("Выберите действие:");
        System.out.println("1 - Все кофе");
        System.out.println("2 - Добавить кофе");
        System.out.println("3 - Удалить кофе");
        System.out.println("4 - Редактировать кофе");
        System.out.println("5 - Пользователи");
        System.out.println("6 - Выход");
    }

    @Override
    protected void performAction(int choice) {
        switch (choice) {
            case 1:
                if (coffeeArrayList.isEmpty()) {
                    System.out.println("Кофе нет");
                } else {
                    FileManager.readFromFile(filecomputer);
                }
                break;
            case 2:
                addComponent(coffeeArrayList, scan);
                break;
            case 3:
                System.out.println("Удаление кофе:");
                deleteComponent(coffeeArrayList, scan);
                break;
            case 4:
                editComponent(coffeeArrayList, scan);
                break;
            default:
                break;
        }
    }



    // Метод для удаления компонента
    public void deleteComponent(ArrayList<Coffee> coffeeArrayList, Scanner scan) {
        System.out.println("Выберите название кофе, которое хотите удалить:");
        int a = 1;
        for (Coffee i : coffeeArrayList) {
            System.out.println(a++ + ")");
            System.out.println(i);
        }
        String componentDelete = scan.nextLine();
        // Находим комплектующее по названию
        Coffee component = null;
        for (Coffee i : coffeeArrayList) {
            if (i.getType().equals(componentDelete)) {
                component = i;
                break;
            }
        }
        if (component != null) {
            //if (componentIndex > 0 && componentIndex <= FilterCatalog.size()) {
            //ComputerComponent removedComponent = FilterCatalog.remove(componentIndex - 1);
            //Computerlist.remove(removedComponent); // Удаляем из общего списка компонентов
            coffeeArrayList.remove(component);
            System.out.println("Компонент '" + componentDelete + "' удален.");
        } else {
            System.out.println("Название такого компонента не найдено.");
        }
    }

    public void addComponent(ArrayList<Coffee> coffeeArrayList, Scanner scan) {
        try {
            System.out.print("Введите тип кофе: ");
            String coffeeType = scan.nextLine();
            System.out.print("Введите вес кофе: ");
            int coffeeWeight = scanner.nextInt();
            System.out.print("Введите цену кофе: ");
            double coffeePrice = scanner.nextDouble();
            if (coffeePrice < 0) {
                throw new InvalidPriceException("Цена не может быть отрицательной!");
            }
            Coffee newComponent = new Coffee(coffeeType, coffeePrice, coffeeWeight);
            //Computerlist.add(newComponent);
            coffeeArrayList.add(newComponent);
            System.out.println("Кофе '" + coffeeType + "' добавлен.");
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }

    }

    public void editComponent(ArrayList<Coffee> coffeeArrayList, Scanner scan) {
        try {
            System.out.println("Введите название кофе, которое хотите отредактировать:");
            String editCoffee = scan.nextLine();
            Coffee component = null;
            for (Coffee i : coffeeArrayList) {
                if (i.getType().equals(editCoffee)) {
                    component = i;
                    break;
                }
            }
            if (component != null) {
                System.out.println("Введите новый тип кофе: ");
                String newType = scan.nextLine();
                System.out.println("Введите новый вес кофе: ");
                int newWeight = scan.nextInt();
                System.out.println("Введите новую цену кофе: ");
                double newPrice = scan.nextDouble();
                if (newPrice < 0) {
                    throw new InvalidPriceException("Цена не может быть отрицательной!");
                }

                component.setType(newType); //устанавливает новое значение для имени компонента
                component.setWeight(newWeight);
                component.setPrice(newPrice);

                System.out.println("Кофе '" + editCoffee + "' отредактирован.");
            } else {
                System.out.println("Название такого компонента не найдено.");
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }

    }


}