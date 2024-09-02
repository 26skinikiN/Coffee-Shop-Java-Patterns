import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayList;

//Operation sum = (a) -> {
//        return a;
//        };
//
//        double a = sum.cost(3.0);
//        System.out.println(a);

public class Main {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        Scanner scn = new Scanner(System.in);
        korzinaprice prc = (totalprice) -> {
            double sum = 0;
            return sum + totalprice;
        };
        // Метки выполнения фильтров и сортировок
        boolean filtprice = true;
        boolean filtlet = true;
        boolean sortup = true;
        boolean sortdown =  true;

        Scanner scanner = new Scanner(System.in);
        ArrayList<Coffee> coffeelist = new ArrayList<>();

        ArrayList<Coffee> Catalog = new ArrayList<>();

        CoffeeFactory coffeeFactory = new ConcreteCoffeeFactory();
        coffeelist.add(coffeeFactory.createCoffee("Итальянский", 50, 100));
        coffeelist.add(coffeeFactory.createCoffee("Колумбийский", 300, 100));
        coffeelist.add(coffeeFactory.createCoffee("Испанский", 100, 100));
        coffeelist.add(coffeeFactory.createCoffee("Бразильский", 350, 100));
        coffeelist.add(coffeeFactory.createCoffee("Французский", 280, 100));
        coffeelist.add(coffeeFactory.createCoffee("Китайский", 210, 100));
        coffeelist.add(coffeeFactory.createCoffee("Белорусский", 140, 100));
        coffeelist.add(coffeeFactory.createCoffee("Ирландский", 320, 100));


        ArrayList<Coffee> filtercoffee = new ArrayList<>();
        filtercoffee.addAll(coffeelist); // Копия кофелиста
        ArrayList<Coffee> filtmaxprc = new ArrayList<>();
        ArrayList<Coffee> filtletter = new ArrayList<>();

        String filecoffee = "Coffeelist.txt";
        // Загрузка существующих данных из файла, если файл существует
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filecoffee))) {
            filtercoffee = (ArrayList<Coffee>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Файл не найден или возникла ошибка при чтении: " + e.getMessage());
        }

        FileManager.writeToFile(filecoffee, filtercoffee);

        boolean maxFilter = true; // - для фильтрации и сортировки
        boolean minFilter = true;

        boolean sortIncrease = false; //для сортировки по возрастанию при фильтрации отмены
        boolean sortDecrease = false;


        String filename = "users.txt";
        ArrayList<User> accountList = new ArrayList<>();
        accountList.add(new User("admin", "1111", 0));


        // Загрузка существующих данных из файла, если файл существует
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            accountList = (ArrayList<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Файл не найден или возникла ошибка при чтении: " + e.getMessage());
        }

        SystemFacade systemFacade = new SystemFacade(accountList, filtercoffee, filename, filecoffee);
        AdminManager adminManager = new AdminManager(filtercoffee, accountList, filename, filecoffee);

        boolean check_welcome = true, check_admin = true, check_user = true;


        int choice, num, choiceAdmin, choice_about_user;
        //0 - админ, 1 - пользователь
        //////////////////////////////////////////
        User currentUser = null;
        do {
            while (check_welcome) {
                System.out.println("Добро пожаловать.");
                System.out.println("1 - Зарегистрироваться.");
                System.out.println("2 - Войти.");
                int system = scan.nextInt();
                switch (system) {
                    case 1:
                        systemFacade.registerUser();
                        //запись данных с регистрацией в файл
                        FileManager.writeToFile(filename, accountList);
                        break;
                    case 2:
                        currentUser = systemFacade.loginUser();
                        check_welcome = false; // выход из цикла
                        break;
                    default:
                        System.out.println("Выбрана неверная цифра.");
                }
            }
            ///////////////////////////////////////////

            do {
                if (currentUser != null) {
                    try {
                        if (currentUser.getRole() == 0) {
                            adminManager.displayMenu();
                            choiceAdmin = scanner.nextInt();
                            adminManager.performAction(choiceAdmin);
                            if (choiceAdmin == 5) {
                                systemFacade.performAdminOperations(accountList, filename);
                            }
                            if(choiceAdmin == 6) {
                                check_admin = false;
                                System.out.println("Успешно вышли");
                                check_welcome = true;
                                break; // выход из внутреннего цикла
                            }
                            FileManager.writeToFile(filecoffee, filtercoffee);
                            FileManager.writeToFile(filename, accountList);
                        } else if (currentUser.getRole() == 1) {

                            System.out.println("\nМеню Покупателя:");
                            System.out.println("Выберите действие:" +
                                    "\n1 - Показать все виды кофе." +
                                    "\n2 - Просмотр корзины." +
                                    "\n3 - Добавить в корзину." +
                                    "\n4 - Фильрация." +
                                    "\n5 - Сортировать по убыванию (А-Я)." +
                                    "\n6 - Сортировать по возрастанию (Я-А)." +
                                    "\n7 - Отменить сортировку." +
                                    "\n8 - Выйти из магазина.");

                            int number = scan.nextInt();
                            switch (number) {
                                case 1:
                                    int a = 1;
                                    for (Coffee i : filtercoffee) {
                                        System.out.println(a++ + ")");
                                        System.out.println(i);
                                    }
                                    break;
                                case 2:
                                    double total = 0;
                                    System.out.println("Ваша корзина:");
                                    int c = 1;
                                    for (Coffee i : currentUser.getKorzina()) {
                                        System.out.println(c++ + ")");
                                        System.out.println(i);
                                        total += prc.summa(i.getPrice());
                                    }
                                    System.out.println("\nСумма корзины: " + total);
                                    FileManager.writeToFile("users.txt",accountList);
                                    break;
                                case 3:
                                    System.out.println("Выберите кофе из списка:");
                                    int b = 1;
                                    for (Coffee i : filtercoffee) {
                                        System.out.println(b++ + ")");
                                        System.out.println(i);
                                    }
                                    System.out.println("\nВведите номер выбранного кофе из списка:");
                                    b = scan.nextInt();
                                    Coffee coffee = new Coffee(filtercoffee.get(b - 1).getType(), filtercoffee.get(b - 1).getPrice(), filtercoffee.get(b - 1).getWeight());
                                    currentUser.getKorzina().add(coffee);
                                    System.out.println("Введите требуемый вес кофе:");
                                    int weight = scan.nextInt();
                                    currentUser.getKorzina().get(currentUser.getKorzina().size() - 1).setWeight(weight);
                                    System.out.println("Выберите форму кофе из списка:\n1 - Зерновой.\n2 - Молотый.\n3 - Растворимый.");
                                    System.out.println("\nВведите номер выбранной формы кофе из списка");
                                    int formnum;
                                    formnum = scan.nextInt();
                                    double coffeeprice = 0;
                                    double coeff = 0;
                                    switch (formnum) {
                                        case 1:
                                            coeff = 2.5;
                                            coffeeprice = currentUser.getKorzina().get(currentUser.getKorzina().size() - 1).getPrice() / 100.0 * currentUser.getKorzina().get(currentUser.getKorzina().size() - 1).getWeight() * coeff;
                                            currentUser.getKorzina().get(currentUser.getKorzina().size() - 1).setPrice(coffeeprice);
                                            System.out.println("Добавлено в корзину.");
                                            break;
                                        case 2:
                                            coeff = 1.8;
                                            coffeeprice = currentUser.getKorzina().get(currentUser.getKorzina().size() - 1).getPrice() / 100.0 * currentUser.getKorzina().get(currentUser.getKorzina().size() - 1).getWeight() * coeff;
                                            currentUser.getKorzina().get(currentUser.getKorzina().size() - 1).setPrice(coffeeprice);
                                            System.out.println("Добавлено в корзину.");
                                            break;
                                        case 3:
                                            coeff = 1.2;
                                            coffeeprice = currentUser.getKorzina().get(currentUser.getKorzina().size() - 1).getPrice() / 100.0 * currentUser.getKorzina().get(currentUser.getKorzina().size() - 1).getWeight() * coeff;
                                            currentUser.getKorzina().get(currentUser.getKorzina().size() - 1).setPrice(coffeeprice);
                                            System.out.println("Добавлено в корзину.");
                                            break;
                                    }
                                    break;
                                case 4: // Фильтрация
                                    System.out.println("\n1 - Фильтр по цене, до введенной." +
                                            "\n2 - Фильтр по букве в названии." +
                                            "\n3 - Очистить фильтры.");
                                    choice = scan.nextInt();
                                    switch (choice) {
                                        case 1:
                                            filtprice = false;
                                            if (coffeelist.isEmpty()) {
                                                System.out.println("Нет кофе.");
                                            } else {
                                                filtmaxprc.addAll(coffeelist);
                                                System.out.println("Введите максимальную цену: ");
                                                double max_price = scan.nextDouble();
                                                ArrayList<Coffee> maxPriceFilter = FilterByMax(coffeelist, max_price);
                                                filtercoffee = combineFilters(filtercoffee, maxPriceFilter);
                                                filtmaxprc = combineFilters(filtmaxprc, maxPriceFilter);
                                            }
                                            break;
                                        case 2:
                                            filtlet = false;
                                            if (coffeelist.isEmpty()) {
                                                System.out.println("Нет кофе.");
                                            } else {
                                                filtletter.addAll(coffeelist);
                                                System.out.println("Введите первую букву названия: ");
                                                String letter = scn.nextLine();
                                                ArrayList<Coffee> LetterFilter = FilterByLetter(coffeelist, letter);
                                                filtercoffee = combineFilters(filtercoffee, LetterFilter);
                                                filtletter = combineFilters(filtletter, LetterFilter);
                                            }
                                            break;
                                        case 3:
                                            System.out.println("\n1 - Очистить фильтр по цене, до введенной." +
                                                    "\n2 - Очистить фильтр по букве в названии." +
                                                    "\n3 - Очистить все фильтры.");
                                            int clearF = scan.nextInt();
                                            switch (clearF) {
                                                case 1:
                                                    filtmaxprc.clear();
                                                    filtercoffee.clear();
                                                    if (filtletter.isEmpty()) {
                                                        filtercoffee.addAll(coffeelist);
                                                    } else {
                                                        filtercoffee.addAll(filtletter);
                                                    }
                                                    break;
                                                case 2:
                                                    filtletter.clear();
                                                    filtercoffee.clear();
                                                    if (filtmaxprc.isEmpty()) {
                                                        filtercoffee.addAll(coffeelist);
                                                    } else {
                                                        filtercoffee.addAll(filtmaxprc);
                                                    }
                                                    break;
                                                case 3:
                                                    filtletter.clear();
                                                    filtmaxprc.clear();
                                                    filtercoffee.clear();
                                                    filtercoffee.addAll(coffeelist);
                                                    if(!sortdown){
                                                        RnblSortLower rnblSortLower = new RnblSortLower(filtercoffee);
                                                        rnblSortLower.run();
                                                    } else if(!sortup){
                                                        ThrSortUpper thrSortUpper = new ThrSortUpper(filtercoffee);
                                                        Thread th = new Thread(thrSortUpper);
                                                        th.start();
                                                    }
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                                case 5:
                                    ThrSortUpper thrSortUpper = new ThrSortUpper(filtercoffee);
                                    Thread th = new Thread(thrSortUpper);
                                    th.start();
                                    sortup = false;
                                    break;
                                case 6:
                                    RnblSortLower rnblSortLower = new RnblSortLower(filtercoffee);
                                    rnblSortLower.run();
                                    sortdown = false;
                                    break;
                                case 7:
                                    if(!filtprice){ // проверка использовался ли
                                        filtercoffee.clear();
                                        filtercoffee.addAll(filtmaxprc);
                                    } else if(!filtlet){
                                        filtercoffee.clear();
                                        filtercoffee.addAll(filtletter);
                                    } else {
                                        filtercoffee.clear();
                                        filtercoffee.addAll(coffeelist);
                                    }
                                    break;
                                case 8:
                                    check_user = false;
                                    System.out.println("Успешно вышли");
                                    check_welcome = true;
                                    break; // выход из внутреннего цикла
                            }
                        }


                    } catch (InputMismatchException c) {
                        System.out.println("Ошибка! Введите число!");
                        scanner.next();
                    } catch (NumberFormatException ex) {
                        System.out.println("Ошибка! Неправильный ввод");
                    } catch (Exception e) {
                        System.out.println("Произошла ошибка: " + e.getMessage());
                    }

                }

            } while (check_admin && check_user);
        } while (true);

    }

    public static ArrayList<Coffee> FilterByMax(ArrayList<Coffee> FilterComponent, double max_price) {
        return (ArrayList<Coffee>) FilterComponent.stream().filter(amr -> amr.getPrice() <= max_price)
                .collect(Collectors.toList());
    }
    public static ArrayList<Coffee> FilterByLetter(ArrayList<Coffee> FilterComponent, String letter) {
        return (ArrayList<Coffee>) FilterComponent.stream().filter(amr -> amr.getType().startsWith(letter))
                .collect(Collectors.toList());
    }
    // Функция для объединения двух списков
    public static ArrayList<Coffee> combineFilters(ArrayList<Coffee> list1, ArrayList<Coffee> list2) {
        ArrayList<Coffee> combinedList = new ArrayList<>(list1);
        combinedList.retainAll(list2);
        return combinedList;
    }

}


class InvalidPriceException extends Exception
{
    public InvalidPriceException (String message)
    {
        super(message);
    }
}

@FunctionalInterface // аннотация
interface korzinaprice { // функциональный интерфейс
    double summa(double totalprice); // только один! абстрактный! метод
}