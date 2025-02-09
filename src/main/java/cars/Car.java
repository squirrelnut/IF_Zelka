package cars;

public abstract class Car {
    private int year;
    private int horsePower;
    private double engineCapacity;
    private String model;
    private String color;
    private String transmission;

    public Car(int year, int horsePower, double engineCapacity, String model, String color, String transmission) {
        this.year = year;
        this.horsePower = horsePower;
        this.engineCapacity = engineCapacity;
        this.model = model;
        this.color = color;
        this.transmission = transmission;
    }

    @Override
    public String toString() {
        return "model='" + model + '\'' +
                ", year=" + year +
                ", horsePower=" + horsePower +
                ", engineCapacity=" + engineCapacity +
                ", color='" + color + '\'' +
                ", transmission='" + transmission + '\'';
    }

    public static void printNewCars(Car car) {
        if (car.getYear() > 2006) {
            System.out.println(car);
        } else {
            System.out.println("устаревший автомобиль " + car.getModel() + " " + car.getYear());
        }
    }

    public static void changeFromGreenToRed(Car car) {
        if (car.getColor().equals("зеленый")) {
            car.setColor("красный");
            System.out.println("Для авто " + car.getModel() + " заменили цвет на " + car.getColor());
        }
    }

    public static void printPowerfulCars(Car car) {
        if (car.getHorsePower() >= 200) {
            System.out.println("мощный автомобиль: " + car);
        } else {
            System.out.println("слабый автомобиль " + car.getModel() + " " + car.getHorsePower());
        }
    }

    public String getColor() {
        return color;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public void setColor(String color) {
        this.color = color;
    }

}

