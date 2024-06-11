package com.goorm.codeAdventure.domain.item.service;

import com.goorm.codeAdventure.domain.item.repository.ItemRepository;
import com.goorm.codeAdventure.domain.item.entity.Item;
import com.goorm.codeAdventure.domain.user.entity.User;
import com.goorm.codeAdventure.domain.user.repository.UserRepository;
import com.goorm.codeAdventure.domain.item.dto.ItemDetailResponse;
import com.goorm.codeAdventure.domain.item.dto.ItemListResponse;
import com.goorm.codeAdventure.domain.item.dto.ItemPurchaseRequest;
import com.goorm.codeAdventure.domain.item.dto.ItemPurchaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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

    @Transactional
    public ItemPurchaseResponse purchase(Long itemId, Long userId, ItemPurchaseRequest request) {
        //item id ( 상품 의 아이디 )  ,user Id (구매자 아이디 )
        // = > 상품의 수량을 확인해서 수량이 1개이상이면 구매 완료되었다고 메세지 뿌리고
        // = > 0개면 에러 보내주고 (상태코드 같은거? )
        // message가 완료 여부에 따라 들어가야함 .
        Item item = itemRepository.findById(itemId).orElseThrow(IllegalArgumentException::new);
        if(!item.isBuyAble()||item.isnonremain(request.getQuantity()))//물건을 살수없는 경우면
        {
            throw new RuntimeException("재고가 없습니다");
        }
        User user = userRepository.findById(userId);
//                .orElseThrow(IllegalArgumentException::new);
        //구매 로직
        Integer totalprice = item.minusStock(request.getQuantity());//남은 재고 저장 했음
        int usedCoin = user.getCoin() - totalprice;
        user.setCoin(usedCoin);
        return new ItemPurchaseResponse(usedCoin,"구매가 완료되었습니다");


    }
}
