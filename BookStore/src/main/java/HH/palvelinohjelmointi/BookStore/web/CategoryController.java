package HH.palvelinohjelmointi.BookStore.web;


import HH.palvelinohjelmointi.BookStore.domain.BookRepository;
import HH.palvelinohjelmointi.BookStore.domain.Category;
import HH.palvelinohjelmointi.BookStore.domain.CategoryRepository;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

public class CategoryController {
	
	@Autowired
	BookRepository repository;
	
	@Autowired
	CategoryRepository crepository; 
	
	
	@RequestMapping(value = "/categorylist", method = RequestMethod.GET)
	public String getCategories(Model model) {
			List<Category> category =  (List<Category>) crepository.findAll(); 
			model.addAttribute("category", category); 
			return "categorylist"; 
								
		}
	@RequestMapping(value = "/newcategory", method = RequestMethod.GET)
	public String getaddNewCategory(Model model) {
		model.addAttribute("category", new Category());
		return "newcategoryform";
	}

	
	@RequestMapping(value = "/savecategory", method = RequestMethod.POST)
	public String saveCategory(@ModelAttribute Category category) {
		
		crepository.save(category);
		return "redirect:/categorylist";
	}
	@RequestMapping(value = "/deletecategory/{id}", method = RequestMethod.GET)
	public String deleteCategory(@PathVariable("id") Long categoryid) {
		crepository.deleteById(categoryid);
		return "redirect:../categorylist";
	}
	
    @RequestMapping(value="/categories", method = RequestMethod.GET)
    public @ResponseBody List<Category> getCategoriesRest() {	
        return (List<Category>) crepository.findAll();
    }    

	
    @RequestMapping(value="/categories/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Category> findCategoryRest(@PathVariable("id") Long cId) {	
    	return crepository.findById(cId);
    } 
    
    
    @RequestMapping(value="/categories", method = RequestMethod.POST)
    public @ResponseBody Category saveBookRest(@RequestBody Category category) {	
    	return crepository.save(category);
    }
}