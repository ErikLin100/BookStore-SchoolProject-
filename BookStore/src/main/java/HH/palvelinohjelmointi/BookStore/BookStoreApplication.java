package HH.palvelinohjelmointi.BookStore;

import org.slf4j.Logger;


import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import HH.palvelinohjelmointi.BookStore.domain.Book;
import HH.palvelinohjelmointi.BookStore.domain.BookRepository;
import HH.palvelinohjelmointi.BookStore.domain.Category;
import HH.palvelinohjelmointi.BookStore.domain.CategoryRepository;
import HH.palvelinohjelmointi.BookStore.domain.User;
import HH.palvelinohjelmointi.BookStore.domain.UserRepository;





@SpringBootApplication
public class BookStoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookStoreApplication.class); 

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	
	
	@Bean
	public CommandLineRunner demo(BookRepository brepository, CategoryRepository crepository, UserRepository urepository) 
	{return (args) -> {
		log.info("tallennetaan muutama kategoria ja kirjoja");
		Category category1 = new Category("Scifi");
		crepository.save(category1);
		Category category2 = new Category("Elämänkerta");
		crepository.save(category2);
		Category category3 = new Category("Taloustiede");
		crepository.save(category3);
		
		
		brepository.save(new Book ("Breathe: A Life in Flow", "Rickson Gracie", 2021,"978-0-06-301895-2", 27.99, category2));
		brepository.save(new Book ("Naked economics", "Charles Wheelan", 2002,"978-0-393-35649-6", 16.95, category3));
		
		User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
		User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
		urepository.save(user1);
		urepository.save(user2);
		
		
		log.info("fetch all books");
		for (Book book : brepository.findAll()) {
			log.info(book.toString());
		}
	
		
	};
	
	

	}
	}
	


