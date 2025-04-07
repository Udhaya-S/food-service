package com.ust.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.demo.entity.Food;
import com.ust.demo.exception.FoodNotFoundException;
import com.ust.demo.exceptions.FoodAlreadyFoundException;
import com.ust.demo.service.FoodService;

@RestController
@RequestMapping("/food")
public class FoodController {
	@Autowired
	private FoodService fs;
	
	@PostMapping
	public ResponseEntity<Object> addFood(@RequestBody Food food) {
		ResponseEntity<Object> re=null;
		Food f=null;
		try {
			f=fs.create(food);
			re=new ResponseEntity<>(f,HttpStatus.OK);
		}catch(FoodAlreadyFoundException ex) {
			re=new ResponseEntity<>(f,HttpStatus.NOT_FOUND);
		}
		return re;
	}
	
	@GetMapping
	public ResponseEntity<List<Food>> retrieveAllFoods() {
		return new ResponseEntity<>(fs.read(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> findFoodById(@PathVariable Long id) {
		Food f=null;
		ResponseEntity<Object> re=null;
		try {
			f=fs.read(id);
			re=new ResponseEntity<>(f,HttpStatus.OK);
		}catch(FoodNotFoundException ex) {
			re=new ResponseEntity<>(f,HttpStatus.NOT_FOUND);
		}
		return re;
	}
	
	@PutMapping
	public ResponseEntity<Object> updateFood(@RequestBody Food food) {
		Food f=null;
		ResponseEntity<Object> re=null;
		try {
			f=fs.update(food);
			re=new ResponseEntity<>(f,HttpStatus.OK);
		}catch(FoodNotFoundException ex) {
			re=new ResponseEntity<>(f,HttpStatus.NOT_FOUND);			
		}
		return re;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteFood(@PathVariable Long id) {
		Long i=null;
		ResponseEntity<Object> re=null;
		try {
			i=fs.delete(id);
			re=new ResponseEntity<>(i,HttpStatus.OK);
		}catch(FoodNotFoundException ex) {
			re=new ResponseEntity<>(i,HttpStatus.NOT_FOUND);			
		}
		return re;
	}
	
}
