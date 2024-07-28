package shop.soseok.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import shop.soseok.constant.ItemCategory;
import shop.soseok.constant.ItemStatus;
import shop.soseok.domain.Item;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    private List<Item> createItems(int count) {
        List<Item> itemList = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Item item = Item.builder()
                    .name("test" + i)
                    .price(1000 + i)
                    .category(i % 2 == 0 ? ItemCategory.RING : ItemCategory.KEY_RING)
                    .status(i % 2 == 0 ? ItemStatus.SELL : ItemStatus.SOLD_OUT)
                    .description("this is test" + i)
                    .build();
            itemList.add(item);
        }

        return itemList;
    }

    @Test
    @DisplayName("저장된 상품을 전체 조회한다.")
    void findAllTest() {
        // given
        int count = 10;
        List<Item> itemList = createItems(count);

        // when
        for (Item item : itemList) {
            itemService.save(item);
        }

        // then
        List<Item> findItemList = itemService.findAll();
        assertThat(findItemList.size()).isEqualTo(itemList.size());
    }

    @Test
    @DisplayName("저장된 상품이 없을 때 전체를 조회한다.")
    void findAllEmptyTest() {
        // given
        // when

        // then
        List<Item> findItemList = itemService.findAll();
        assertThat(findItemList.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("상품을 저장한다.")
    void saveTest() {
        // given
        int count = 1;
        Item item = createItems(count).get(0);

        // when
        Item saveItem = itemService.save(item);

        // then
        assertThat(saveItem.getName()).isEqualTo(item.getName());
        assertThat(saveItem.getPrice()).isEqualTo(item.getPrice());
        assertThat(saveItem.getCategory()).isEqualTo(item.getCategory());
        assertThat(saveItem.getStatus()).isEqualTo(item.getStatus());
        assertThat(saveItem.getDescription()).isEqualTo(item.getDescription());
    }

    @Test
    @DisplayName("ID로 상품을 찾는다.")
    void findByIdTest() {
        // given
        int count = 10;
        List<Item> itemList = createItems(count);
        List<Item> saveItemList = new ArrayList<>();
        for (Item item : itemList) {
            saveItemList.add(itemService.save(item));
        }

        // when
        Item item0 = saveItemList.get(0);
        Item item9 = saveItemList.get(9);

        // then
        Item findItem0 = itemService.findById(item0.getId());
        Item findItem9 = itemService.findById(item9.getId());
        assertThat(findItem0).isSameAs(item0);
        assertThat(findItem9).isSameAs(item9);
        assertThat(findItem0).isNotSameAs(item9);
    }

    @Test
    @DisplayName("ID에 해당하는 상품 내용을 수정한다.")
    void updateByIdTest() {
        // given
        int count = 1;
        Item item = createItems(count).get(0);
        Item saveItem = itemService.save(item);
        Long saveItemId = saveItem.getId();

        // when
        Item newItem = Item.builder()
                .name("수정된 이름")
                .price(5000)
                .status(ItemStatus.SOLD_OUT)
                .category(ItemCategory.BOOKMARK)
                .description("수정된 내용")
                .build();

        // then
        Item updateItem = itemService.updateById(saveItemId, newItem);
        assertThat(updateItem.getId()).isEqualTo(saveItemId);
        assertThat(updateItem.getName()).isEqualTo(newItem.getName());
        assertThat(updateItem.getPrice()).isEqualTo(newItem.getPrice());
        assertThat(updateItem.getStatus()).isEqualTo(newItem.getStatus());
        assertThat(updateItem.getCategory()).isEqualTo(newItem.getCategory());
        assertThat(updateItem.getDescription()).isEqualTo(newItem.getDescription());
    }

    @Test
    @DisplayName("존재하지 않는 상품을 수정하려고 한다.")
    void updateByIdEmptyTest() {
        // given
        int count = 1;
        Item item = createItems(count).get(0);
        // when
        // then
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class,
                () -> itemService.updateById(1L, item));
        assertThat(e.getMessage()).isEqualTo("상품이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("ID에 해당하는 상품을 삭제한다.")
    void removeById() {
        // given
        int count = 5;
        List<Item> itemList = createItems(5);
        for (Item item : itemList) {
            itemService.save(item);
        }

        // when
        Item item = itemList.get(0);

        // given
        Item removeItem = itemService.removeById(item.getId());
        assertThat(itemService.findAll().size()).isEqualTo(count - 1);
        assertThat(removeItem).isEqualTo(item);
    }

    @Test
    @DisplayName("존재하지 않는 상품을 삭제하려고 한다.")
    void removeByIdEmptyTest() {
        // given
        // when
        // then
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class,
                () -> itemService.removeById(1L));
        assertThat(e.getMessage()).isEqualTo("상품이 존재하지 않습니다.");
    }
}