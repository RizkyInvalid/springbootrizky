package com.example.orderingsystem.repository;

import com.example.orderingsystem.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, String> {
}
