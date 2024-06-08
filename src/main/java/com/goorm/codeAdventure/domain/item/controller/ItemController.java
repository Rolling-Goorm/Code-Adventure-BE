package com.goorm.codeAdventure.domain.item.controller;

import com.goorm.codeAdventure.domain.item.dto.ItemDetailResponse;
import com.goorm.codeAdventure.domain.item.dto.ItemListResponse;
import com.goorm.codeAdventure.domain.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ItemDetailResponse findOne(@PathVariable Long itemId)
    {
        return itemService.findOne(itemId);
    }





}
