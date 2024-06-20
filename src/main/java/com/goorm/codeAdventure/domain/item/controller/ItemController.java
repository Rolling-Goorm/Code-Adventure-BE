package com.goorm.codeAdventure.domain.item.controller;

import com.goorm.codeAdventure.domain.item.dto.ItemPurchaseRequest;
import com.goorm.codeAdventure.domain.item.dto.ItemPurchaseResponse;
import com.goorm.codeAdventure.domain.item.service.ItemService;
import com.goorm.codeAdventure.domain.item.dto.ItemDetailResponse;
import com.goorm.codeAdventure.domain.item.dto.ItemListResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;


    @GetMapping
    @Operation(summary = "아이템 전체 조회 API", description = "사용 가능한 모든 아이템의 리스트를 반환합니다.")
    public List<ItemListResponse> items()//전체 조회
    {
        return itemService.items();
    }

    @GetMapping("/{itemId}")
    @Operation(summary = "아이템 상세 조회 API", description = "특정 아이템의 상세 정보를 반환합니다.")
    public ItemDetailResponse findOne(@PathVariable Long itemId)
    {
        return itemService.findOne(itemId);
    }

    @PostMapping("/{itemId}")
    @Operation(summary = "아이템 구매 API", description = "특정 아이템을 구매합니다.")
    public ItemPurchaseResponse purchase(
            @PathVariable Long itemId,
            @RequestParam Long userId,
            @RequestBody ItemPurchaseRequest request
            ) {
        return itemService.purchase(itemId,userId,request);
    }







}
