package com.goorm.codeAdventure.item.controller;

import com.goorm.codeAdventure.item.dto.ItemDetailResponse;
import com.goorm.codeAdventure.item.dto.ItemListResponse;
import com.goorm.codeAdventure.item.dto.ItemPurchaseRequest;
import com.goorm.codeAdventure.item.dto.ItemPurchaseResponse;
import com.goorm.codeAdventure.item.service.ItemService;
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
    public ItemDetailResponse findOne(@PathVariable Long itemId)
    {
        return itemService.findOne(itemId);
    }

    @PostMapping("/{itemId}")
    public ItemPurchaseResponse purchase(
            @PathVariable Long itemId,
            @RequestParam Long userId,
            @RequestBody ItemPurchaseRequest request
            ) {
        return itemService.purchase(itemId,userId,request);
    }







}
