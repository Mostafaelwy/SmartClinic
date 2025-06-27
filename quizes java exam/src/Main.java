import trials.Seen;
//import trials.No

import javax.xml.catalog.Catalog;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//oop
//cop class oriented ?



//Factory?
public class Main {
    static int counter = 0;

    Seen seen = new Seen();

    public static void main(String[]args) {
        Car car = new Car();
        car.drive();
        Car car2 = new Car(120);
        System.out.println(car2.getSpeed());
//        car2.getSpeed();
        try{
            Car.drive();

        }
        catch (Exception e){
            System.out.println(e);
        }
        catch (ArithmeticException || InputMismatchException e){

        }
    }
}
abstract class Vehicle{
    public static int x;
//    public abstract Ex();
    public void drive() throws InputMismatchException {
        System.out.println("Vehicle driving");
    }
}
class Car extends Vehicle{
    private int speed = 12;
//    public final int x, y;
    String driver;
//    Driver driver;

    public Car() {
//        super("lol");
        System.out.println("Car Constructor");
    }

    public Car(int speed) {
//        this();
        this.speed = speed;

    }
    public Car(Car car) {
        this.speed = car.speed;
//        model = "dasd";
    }
    @Override
    public void drive() throws ArithmeticException, ArrayIndexOutOfBoundsException, NullPointerException {
//        this();
        System.out.println("Car driving");
//        System.out.println(speed);
    }

    public int getSpeed(){
        return speed;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
}

