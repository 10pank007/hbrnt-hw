package hw1.part1;

import hw1.part1.entity.Word;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try (StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernateOne.cfg.xml")
                .build()) {
            Metadata metadata = new MetadataSources(serviceRegistry)
                    .addAnnotatedClass(Word.class)
                    .getMetadataBuilder()
                    .build();

            try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
                 Session session = sessionFactory.openSession()) {


                Word word = new Word("word1");
                Word word2 = new Word("word2");
                Word word3 = new Word("word3");
                Word word4 = new Word("word4");
                Word word5 = new Word("word5");
                Transaction transaction = session.beginTransaction();
                session.persist(word);
                session.persist(word2);
                session.persist(word3);
                session.persist(word4);
                session.persist(word5);
                System.out.println("Product created");

                List<Word> wordList = session.createNativeQuery("select * from word", Word.class).list();
                System.out.println(wordList);

                List<String> values = wordList
                        .stream()
                        .map(Word::getValue)
                        .collect(Collectors.toList());
                System.out.println(values);
                transaction.commit();

            }
        }
    }
}
