package com.goorm.codeAdventure.domain.item.service;

import com.goorm.codeAdventure.domain.item.dto.ItemDetailResponse;
import com.goorm.codeAdventure.domain.item.dto.ItemListResponse;
import com.goorm.codeAdventure.domain.item.entity.Item;
import com.goorm.codeAdventure.domain.item.repository.ItemRepository;
import com.goorm.codeAdventure.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public List<ItemListResponse> items() {
        List<Item> itemList = itemRepository.findAll();//엔티티를 서비스 밖으로 내보내면 안됨.
        ArrayList<ItemListResponse> list = new ArrayList<>();
        for (Item item : itemList) {
            list.add(toListDto(item));
        }
//        List<ItemListResponse> list = itemList.stream().map(this::toDto).toList();
        return list;
    }

    private ItemListResponse toListDto(Item item) {
        return ItemListResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .stockQuantity(item.getStockQuantity())
                .price(item.getPrice())
                .build();
    }

    public ItemDetailResponse findOne(Long itemId) {
        //itemRepository.findOne(itemId); findOne은 아무거나 하나 가져옴
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        //옵셔널의 경우에는 예외처리가 필요함
        Item item = optionalItem.orElseThrow(IllegalArgumentException::new);
        return toDto(item);

    }

    private ItemDetailResponse toDto(Item item) {
        return ItemDetailResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .stockQuantity(item.getStockQuantity())
                .price(item.getPrice())
                .build();
    }
}
