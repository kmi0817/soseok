package shop.soseok.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.soseok.domain.Item;
import shop.soseok.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item save(Item item) {
        validateDuplication(item);
        return itemRepository.save(item);
    }

    private void validateDuplication(Item item) {
        Item findItem = itemRepository.findByName(item.getName());

        if (findItem != null) {
            throw new IllegalStateException("중복된 상품명입니다.");
        }
    }
}
