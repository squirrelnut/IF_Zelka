import cars.Car;
import cars.models.*;

import java.util.ArrayList;
import java.util.List;

import static cars.Car.*;

public class Main {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        cars.add(new Lada(2006, 150, 1.4, "Vesta", "белый", "механика"));
        cars.add(new Renault(2016, 200, 2.0, "Duster", "серый", "робот"));
        cars.add(new Toyota(2024, 180, 1.5, "Corolla", "черный", "вариатор"));
        cars.add(new Suzuki(2015, 190, 1.8, "Swift", "зеленый", "механика"));
        cars.add(new Nissan(2003, 250, 1.9, "Teana", "коричневый", "автомат"));
        cars.add(new Lada(2000, 155, 1.2, "Priora", "синий", "робот"));
        cars.add(new Renault(2008, 140, 1.3, "Logan", "розовый", "механика"));
        cars.add(new Suzuki(2005, 120, 2.4, "Vitara", "желтый", "вариатор"));
        cars.add(new Nissan(2011, 220, 1.8, "Qashqai", "голубой", "механика"));
        cars.add(new Toyota(2001, 270, 2.2, "Camry", "зеленый", "автомат"));

        for (Car car : cars) {
            printNewCars(car); // печать авто старше 2006
            changeFromGreenToRed(car); // замена цвета
            printPowerfulCars(car); // печать авто мощностью 200 л.с. и более
        }
    }
}




