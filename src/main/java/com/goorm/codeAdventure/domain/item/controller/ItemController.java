package com.goorm.codeAdventure.domain.item.controller;

import com.goorm.codeAdventure.domain.item.repository.ItemRepository;
import com.goorm.codeAdventure.domain.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;



}
