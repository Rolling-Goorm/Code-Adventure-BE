package com.goorm.codeAdventure.domain.item.service;

import com.goorm.codeAdventure.domain.item.entity.Item;
import com.goorm.codeAdventure.domain.item.repository.ItemRepository;
import com.goorm.codeAdventure.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

}
