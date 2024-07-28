package shop.soseok.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shop.soseok.domain.Item;
import shop.soseok.dto.PostPutItemRequest;
import shop.soseok.service.ItemService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/items")
@RestController
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public List<Item> findAllItem() {
        return itemService.findAll();
    }

    @PostMapping()
    public Object saveItem(@RequestBody @Valid PostPutItemRequest postPutItemRequest, BindingResult bindingResult) {
        log.info(String.valueOf(postPutItemRequest));

        if (bindingResult.hasErrors()) {
            log.info("postItemRequest Errors = {}", bindingResult);
            // TODO: 에러 반환 메시지 포맷팅
            return bindingResult.getAllErrors();
        }

        return itemService.save(postPutItemRequest.toEntity());
    }

    @GetMapping("/{id}")
    public Item findItemById(@PathVariable("id") Long itemId) {
        return itemService.findById(itemId);
    }

    @PutMapping("/{id}")
    public Item updateItemById(
            @PathVariable("id") Long itemId,
            @RequestBody @Valid PostPutItemRequest postPutItemRequest
    ) {
        return itemService.updateById(itemId, postPutItemRequest.toEntity());
    }

    @DeleteMapping("/{id}")
    public Item removeItemById(@PathVariable("id") Long itemId) {
        return itemService.removeById(itemId);
    }
}
