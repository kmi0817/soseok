package shop.soseok.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.soseok.domain.Item;
import shop.soseok.repository.ItemRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }
}
