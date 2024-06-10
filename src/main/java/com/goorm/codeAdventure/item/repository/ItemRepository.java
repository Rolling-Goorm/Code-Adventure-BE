package com.goorm.codeAdventure.item.repository;

import com.goorm.codeAdventure.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item,Long> {


}
