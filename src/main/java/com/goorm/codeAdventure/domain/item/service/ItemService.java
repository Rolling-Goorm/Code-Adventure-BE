package com.goorm.codeAdventure.domain.item.service;

import com.goorm.codeAdventure.domain.item.dto.ItemListResponse;
import com.goorm.codeAdventure.domain.item.entity.Item;
import com.goorm.codeAdventure.domain.item.repository.ItemRepository;
import com.goorm.codeAdventure.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public List<ItemListResponse> items() {
        List<Item> itemList = itemRepository.findAll();//엔티티를 서비스 밖으로 내보내면 안됨.
        ArrayList<ItemListResponse> list = new ArrayList<>();
        for (Item item : itemList) {
            list.add(toDto(item));
        }
//        List<ItemListResponse> list = itemList.stream().map(this::toDto).toList();
        return list;
    }

    private ItemListResponse toDto(Item item) {
        return ItemListResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .stockQuantity(item.getStockQuantity())
                .price(item.getPrice())
                .build();
    }
}
