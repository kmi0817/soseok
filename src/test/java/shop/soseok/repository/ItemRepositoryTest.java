package shop.soseok.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import shop.soseok.constant.ItemStatus;
import shop.soseok.domain.Item;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @DisplayName("정상 상품 등록")
    void saveTest() {
        // given
        Item item = Item.builder()
                .name("test")
                .price(1000)
                .status(ItemStatus.SELL)
                .description("This is Test")
                .build();

        // when
        itemRepository.save(item);
        Long itemId = item.getId();
        Item findItem = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException("상품 저장 안 됨"));

        // then
        assertThat(item.getName()).isEqualTo(findItem.getName());
        assertThat(item.getPrice()).isEqualTo(findItem.getPrice());
        assertThat(item.getStatus()).isEqualTo(findItem.getStatus());
        assertThat(item.getDescription()).isEqualTo(findItem.getDescription());
    }

    @Test
    @DisplayName("상품 전체 조회")
    void findAllTest() {
        // given
        int itemCount = 10;
        for (int i = 0; i < itemCount; i++) {
            Item temp = Item.builder()
                    .name("test" + i)
                    .price(1000 + i)
                    .status(ItemStatus.SELL)
                    .description("This is Test" + i)
                    .build();
            itemRepository.save(temp);
        }

        // when
        List<Item> itemList = itemRepository.findAll();

        // then
        assertThat(itemList.size()).isEqualTo(itemCount);
    }
}