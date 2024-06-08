package com.goorm.codeAdventure.domain.item.repository;

import com.goorm.codeAdventure.domain.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {

}
