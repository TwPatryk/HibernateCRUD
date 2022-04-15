package pl.tworek.patryk.hibernate.crud.repository.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.tworek.patryk.hibernate.crud.App;
import pl.tworek.patryk.hibernate.crud.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRepository {

    static Scanner scanner = new Scanner(System.in);


    public static void addToDataBase() {

        Car car = new Car();

        System.out.println("Enter model");
        car.setModel(scanner.nextLine());

        System.out.println("Enter brand");
        car.setBrand(scanner.nextLine());

        System.out.println("Enter color");
        car.setColor(scanner.nextLine());

        System.out.println("Enter pieces");
        car.setPieces(scanner.nextInt());

        System.out.println("Enter owner id");
        car.setOwner_id(scanner.nextInt());

        Session session = App.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(car);
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
        } finally  {
            session.close();
        }

    }


    public static void getFromDataBase() {
        Session session = App.sessionFactory.openSession();
        Transaction tx = null;
        List<Car> carList = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            Query<Car> query = session.createQuery("FROM pl.tworek.patryk.hibernate.crud.model.Car");
            carList = query.getResultList();
            System.out.println(carList);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
        } finally {
            session.close();
        }
    }


    public static void updateCar() {

        Car car = new Car();

        System.out.println("Enter id: ");
        car.setId(scanner.nextInt());

        System.out.println("Edit model");
        car.setModel(scanner.nextLine());

        System.out.println("Edit brand");
        car.setBrand(scanner.nextLine());

        System.out.println("Edit color");
        car.setColor(scanner.nextLine());

        System.out.println("Edit pieces");
        car.setPieces(scanner.nextInt());

        System.out.println("Edit owner id");
        car.setOwner_id(scanner.nextInt());

        Session session = App.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(car);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
        } finally {
            session.close();
        }
    }


    public static void deleteCarFromDataBase(Car car) {
        Session session = App.sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(car);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
        } finally {
            session.close();
        }

    }
    public static Car getCarById() {

        System.out.println("Enter id: ");
        int id = scanner.nextInt();

        Session session = App.sessionFactory.openSession();
        Transaction tx = null;
        Car car = null;

        try {
            tx = session.beginTransaction();
            Query<Car> query = session.createQuery("FROM pl.tworek.patryk.hibernate.crud.model.Car WHERE id= " + id);
            car = query.getSingleResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
        } finally {
            session.close();
        }
        return car;
    }
}
