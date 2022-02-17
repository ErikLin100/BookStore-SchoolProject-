package HH.palvelinohjelmointi.BookStore.web;

import HH.palvelinohjelmointi.BookStore.domain.Book;
import HH.palvelinohjelmointi.BookStore.domain.BookRepository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookStoreController {
	
	@Autowired
	BookRepository bookRepository; 
	
	
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String getBooks(Model model) {
			List<Book> book =  (List<Book>) bookRepository.findAll(); 
			model.addAttribute("book", book); 
			return "booklist"; 
								
		}
	@RequestMapping(value = "/newbook", method = RequestMethod.GET)
	public String getaddNewBook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}

	
	@RequestMapping(value = "/savebook", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute Book book) {
		
		bookRepository.save(book);
		return "redirect:/booklist";
	}

	
	@RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId) {
		bookRepository.deleteById(bookId);
		return "redirect:../booklist";
	}
	

}