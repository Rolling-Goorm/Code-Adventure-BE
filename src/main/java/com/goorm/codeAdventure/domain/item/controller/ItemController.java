package com.goorm.codeAdventure.domain.item.controller;

import com.goorm.codeAdventure.domain.item.dto.ItemListResponse;
import com.goorm.codeAdventure.domain.item.repository.ItemRepository;
import com.goorm.codeAdventure.domain.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;


    @GetMapping
    public List<ItemListResponse> items()//전체 조회
    {
        return itemService.items();
    }

    @GetMapping("/{itemId}")
    public void findOne(@PathVariable Long itemId)
    {

    }




}
