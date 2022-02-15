package HH.palvelinohjelmointi.BookStore;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import HH.palvelinohjelmointi.BookStore.domain.Book;
import HH.palvelinohjelmointi.BookStore.domain.BookRepository;

@SpringBootApplication
public class BookStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository) 
	{return (args) -> {
	
		Book b1 = new Book("Breathe: A Life in Flow", "Rickson Gracie", 2021,"978-0-06-301895-2", 27.99);
		Book b2 = new Book("Naked economics", "Charles Wheelan", 2002,"978-0-393-35649-6", 16.95);
		
		repository.save(b1);
		repository.save(b2);
	};
	

	}
}
	


