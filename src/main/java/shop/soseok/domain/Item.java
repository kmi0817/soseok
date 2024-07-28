package shop.soseok.domain;

import jakarta.persistence.*;
import lombok.*;
import shop.soseok.constant.ItemCategory;
import shop.soseok.constant.ItemStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", updatable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private ItemCategory category;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private ItemStatus status;

    @Column(length = 1000)
    private String description;

    @Column(name = "create_at")
    @Setter(AccessLevel.NONE)
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Builder
    public Item(String name, int price, ItemCategory category, ItemStatus status, String description) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.status = status;
        this.description = description;
    }
}
