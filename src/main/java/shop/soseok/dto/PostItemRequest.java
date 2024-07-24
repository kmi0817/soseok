package shop.soseok.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import shop.soseok.constant.ItemCategory;
import shop.soseok.constant.ItemStatus;
import shop.soseok.domain.Item;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class PostItemRequest {
    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    @Length(max = 255, message = "상품명이 너무 깁니다.")
    private String name;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private int price;

    @NotBlank(message = "상품 카테고리는 필수 입력 값입니다.")
    private String category;

    // TODO: ItemStatus 값만 허용하도록 validation 설정
    @NotBlank(message = "상품 상태는 필수 입력 값입니다.")
    private String status;

    private String description;

    public Item toEntity() {
        return Item.builder()
                .name(name)
                .price(price)
                .category(ItemCategory.valueOf(category))
                .status(ItemStatus.valueOf(status))
                .description(description)
                .build();
    }
}
