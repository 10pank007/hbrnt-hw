package hw2;

import hw2.entity.Car;
import hw2.entity.DriveLicense;
import hw2.entity.Owner;
import hw2.entity.TYPE;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (StandardServiceRegistry serviceRegistry =
                     new StandardServiceRegistryBuilder().configure("hibernateThree.cfg.xml").build();) {
            Metadata metadata = new MetadataSources(serviceRegistry)
                    .addAnnotatedClass(Car.class)
                    .addAnnotatedClass(Owner.class)
                    .addAnnotatedClass(DriveLicense.class)
                    .getMetadataBuilder()
                    .build();
            try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
                 Session session = sessionFactory.openSession()
            ) {
                Transaction transaction = session.beginTransaction();
                Owner pavlo = new Owner("pavlo",
                        List.of(new Car("BMW", TYPE.Cabriolet, 1.6, 2000, 2020),
                                new Car("Skoda", TYPE.Cabriolet, 1.2, 8500, 2017))
                        , new DriveLicense("54884454"));
                Owner alina = new Owner("alina",
                        List.of(new Car("Opel", TYPE.Pickup, 2.2, 8000, 2015))
                        , new DriveLicense("6458428"));
                Owner bohdan = new Owner("bohdan",
                        List.of(new Car("VW", TYPE.Crossover, 1.9, 9000, 2018),
                                new Car("Honda", TYPE.Compartment, 1.3, 5000, 2019))
                        , new DriveLicense("86656623"));
                Owner sofia = new Owner("sofia",
                        List.of(new Car("Renault", TYPE.Compartment, 1.8, 11000, 2020))
                        , new DriveLicense("556232346"));
                session.persist(pavlo);
                session.persist(alina);
                session.persist(bohdan);
                session.persist(sofia);

                transaction.commit();

            }
        }

    }
}
