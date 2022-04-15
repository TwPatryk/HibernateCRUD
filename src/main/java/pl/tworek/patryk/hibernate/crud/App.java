package pl.tworek.patryk.hibernate.crud;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.tworek.patryk.hibernate.crud.model.Car;
import pl.tworek.patryk.hibernate.crud.repository.impl.CarRepository;

import java.util.Scanner;

public class App {
    public static SessionFactory sessionFactory = null;
    public static void main(String[] args) {
         sessionFactory = new Configuration().configure().buildSessionFactory();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Select: ");
        System.out.println("1. Add car to database");
        System.out.println("2. View cars");
        System.out.println("3. Edit car");
        System.out.println("4. Delete car from database");
        System.out.println("5. Exit");

        switch (scanner.nextInt()) {
            case 1:
                CarRepository.addToDataBase();
                break;
            case 2:
                CarRepository.getFromDataBase();
                break;
            case 3:
                CarRepository.updateCar();
                break;
            case 4:
                Car car = CarRepository.getCarById();
                CarRepository.deleteCarFromDataBase(car);
                break;
            case 5:
                System.exit(0);
        }
    }
}
