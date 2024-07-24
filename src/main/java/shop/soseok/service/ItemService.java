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

    public Item findById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
    }

    public Item removeById(Long itemId) {
        Item findItem = findById(itemId);
        itemRepository.delete(findItem);
        return findItem;
    }
}
