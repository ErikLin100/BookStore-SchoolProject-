package HH.palvelinohjelmointi.BookStore.web;

import HH.palvelinohjelmointi.BookStore.domain.Book;


import HH.palvelinohjelmointi.BookStore.domain.BookRepository;
import HH.palvelinohjelmointi.BookStore.domain.CategoryRepository;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin

public class BookStoreController {
	
	@Autowired
	BookRepository repository; 
	
	@Autowired
	CategoryRepository crepository; 
	
	
	
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
	
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String bookList(Model model) {
			 
			model.addAttribute("books", repository.findAll());  
			return "booklist"; 
								
		}
	
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) repository.findAll();
    }    


    @RequestMapping(value="/books/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return repository.findById(bookId);
    }      
    
    
    @RequestMapping(value="/books", method = RequestMethod.POST)
    public @ResponseBody Book saveBookRest(@RequestBody Book book) {	
    	return repository.save(book);
    }
	
	
	@RequestMapping(value = "/newbook", method = RequestMethod.GET)
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}

	
	@RequestMapping(value = "/savebook", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute Book book) {
		
		repository.save(book);
		return "redirect:/booklist";
	}

	
	@PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("id") Long studentId, Model model) {
    	repository.deleteById(studentId);
        return "redirect:../studentlist";
    }  
	

}