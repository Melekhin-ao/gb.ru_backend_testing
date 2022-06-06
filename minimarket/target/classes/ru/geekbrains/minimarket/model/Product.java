package ru.geekbrains.minimarket.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;
    private String title;
    private Integer price;
    private String categoryTitle;

}
