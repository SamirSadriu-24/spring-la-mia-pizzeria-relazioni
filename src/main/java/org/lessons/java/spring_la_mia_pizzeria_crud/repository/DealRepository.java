package org.lessons.java.spring_la_mia_pizzeria_crud.repository;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, Integer>{

}
