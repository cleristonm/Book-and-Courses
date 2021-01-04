package com.keko.code.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.keko.code.Ingredient;
import com.keko.code.Ingredient.Type;
import com.keko.code.Order;
import com.keko.code.Taco;
import com.keko.code.User;
import com.keko.code.data.IngredientRepository;
import com.keko.code.data.TacoRepository;
import com.keko.code.data.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
	
	private final IngredientRepository ingredientRepository;
	
	private TacoRepository designRepo;
	
	private UserRepository userRepo;
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepository,
			TacoRepository designRepo, UserRepository userRepo) {
		this.ingredientRepository = ingredientRepository;
		this.designRepo = designRepo;
		this.userRepo = userRepo;
	}
	
	
	@GetMapping 
	public String showDesignForm(Model model, Principal principal) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepository.findAll().forEach( i -> ingredients.add(i) );
				
		Type[] types = Ingredient.Type.values();
		for (Type type: types) {
			model.addAttribute(type.toString().toLowerCase(),
					filterByType(ingredients, type)
					);
			System.out.println(type.toString().toLowerCase()+filterByType(ingredients, type));
		}
		
		String username  = principal.getName();
		User user = userRepo.findByUsername(username);
		System.out.println(user);
		
		model.addAttribute("user", user);
		
		model.addAttribute("design", new Taco());
		
		return "design";
	}
	
	@PostMapping
	public String processDesign(
			@Valid Taco design, Errors errors,
			@ModelAttribute Order order) {
		
		if (errors.hasErrors()) {
			return "design";
		}
		
		Taco saved = designRepo.save(design);
		order.addDesign(saved);
		
		log.info("Processing design: " + design);
		return "redirect:/orders/current";
	}
	
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	
	private List<Ingredient> filterByType(
			List<Ingredient> ingredients, Type type){
		return ingredients
				.stream()
				.filter( x -> x.getType().equals(type))
				.collect(Collectors.toList());
	}

}
