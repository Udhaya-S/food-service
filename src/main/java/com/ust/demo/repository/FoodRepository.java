package com.ust.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.demo.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

}
