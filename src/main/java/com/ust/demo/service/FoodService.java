package com.ust.demo.service;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.demo.entity.Food;
import com.ust.demo.exception.FoodNotFoundException;
import com.ust.demo.exceptions.FoodAlreadyFoundException;
import com.ust.demo.repository.FoodRepository;

@Service
@Transactional
public class FoodService {
	@Autowired
	private FoodRepository fr;

	public Food create(Food food) {
		Food f=null;
		if(food.getId()!=null) {
			Optional<Food> temp = fr.findById(food.getId());
			if(temp.isPresent()) {
				//already found
				throw new FoodAlreadyFoundException("Food already found. Cannot add duplicate id.");
			}
		}
		f=fr.save(food);
		return f;
	}
	
	public List<Food> read() {
		return fr.findAll();
	}
	
	public Food read(Long id) {
		Optional<Food> temp = fr.findById(id);
		Food f=null;
		if(temp.isPresent()) {
			f=temp.get();
		}else
		{
			throw new FoodNotFoundException("No food found for given id : "+id);
		}
		return f;
	}
	
	public Food update(Food food) {
		Food f = read(food.getId());
		if(f!=null) {
			f=fr.save(food);
		}
		return f;
	}
	public Long delete(Long id) {
		fr.deleteById(id);
		return id;
	}
	
}
