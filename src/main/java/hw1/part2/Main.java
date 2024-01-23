package hw1.part2;

import hw1.part2.entity.Car;
import hw1.part2.entity.TYPE;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernateTwo.cfg.xml")
                .build()) {
            Metadata metadata = new MetadataSources(serviceRegistry)
                    .addAnnotatedClass(Car.class)
                    .getMetadataBuilder()
                    .build();

            try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
                 Session session = sessionFactory.openSession()) {

                Car bmw = new Car("BMW", TYPE.Hatchback, 200.0, 2020);
                Car renault = new Car("RENAULT", TYPE.Pickup, 180.5, 2021);
                Car opel = new Car("OPEL", TYPE.Hatchback, 190.5, 2019);
                Car skoda = new Car("SKODA", TYPE.Sedan, 170.5, 2015);
                Car honda = new Car("HONDA", TYPE.Compartment, 210.0, 2021);

                session.save(bmw);
                session.save(renault);
                session.save(opel);
                session.save(skoda);
                session.save(honda);


                List<Car> cars = session.createNativeQuery("select * from cars", Car.class).list();
                System.out.println(cars);
            }
        }
    }
}
