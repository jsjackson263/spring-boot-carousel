/**
 * 
 */
package info.jsjackson.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import info.jsjackson.services.FileService;

/**
 * @author jsjackson
 *
 */
@Controller
public class HomeController {

	private FileService fileService;
	
	public HomeController(FileService fileService) {
		this.fileService = fileService;
	}
	
	
	@GetMapping(value="/")
	public String homePage( Model model) {
		String[] urlArray = fileService.getUrlArray();
		model.addAttribute("imageUrls", urlArray);
		return "index";
	}
	
	
}
