package shop.soseok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.soseok.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByName(String name);
}
