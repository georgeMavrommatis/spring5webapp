package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    @Autowired
    public BootStrapData(AuthorRepository authorRepository,
                         BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("Corfu");
        publisher.setState("Vraganiotika");

        publisherRepository.save(publisher);
        System.out.println("Number of Publishers: "+ publisherRepository.count());


        Author eric = new Author("Eric","Evans");
        Book ddd = new Book("Domain Driven Design","123123");

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod","Johnson");
        Book noEJ = new Book("J2EE Developement without EJB","324234234");
        rod.getBooks().add(noEJ);
        noEJ.getAuthors().add(rod);

        noEJ.setPublisher(publisher);
        publisher.getBooks().add(noEJ);

        /*save into h2 database*/
        authorRepository.save(rod);
        bookRepository.save(noEJ);
        publisherRepository.save(publisher);

        System.out.println("Number of Books: "+ bookRepository.count());
        System.out.println("Publisher Number of Books: "+ publisher.getBooks().size());
    }
}
