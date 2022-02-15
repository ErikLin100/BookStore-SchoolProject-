package HH.palvelinohjelmointi.BookStore.web;

import HH.palvelinohjelmointi.BookStore.domain.Book;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookStoreController {
	
	
		@RequestMapping(value = "/index", method = RequestMethod.GET)
		public String getBooks(Model model) {
			List<Book> Books = new ArrayList<Book>();
			model.addAttribute("Books", Books);
			return "booklist"; 
								
		}

	

}