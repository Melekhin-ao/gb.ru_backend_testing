package ru.geekbrains.minimarket;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.geekbrains.minimarket.client.MinimarketService;
import ru.geekbrains.minimarket.model.Category;
import ru.geekbrains.minimarket.model.Product;

import java.util.List;

public class MinimarketTest extends AbstractTest {
    private static MinimarketService service;

    @BeforeAll
    static void beforeAll() {
        service = new MinimarketService();
    }

    @Test
    @Disabled
    @DisplayName("Получение списка продуктов из категории Food")
    void testGetFoodCategory() throws Exception {
        Category category = service.getCategory(1);
        assertJson(getResource("FoodCategory.json"), category);
    }

    @Test
    @Disabled
    @DisplayName("Получение списка продуктов из категории Electronic")
    void testGetElectronicCategory() throws Exception {
        Category category = service.getCategory(2);
        assertJson(getResource("ElectronicCategory.json"), category);
    }

    @Test
    @Disabled
    @DisplayName("Пролучение списка всех продуктов")
    void testGetProducts() throws Exception {
        List<Product> products = service.getProducts();
        assertJson(getResource("Products.json"), products);
    }

    @Test
    @Disabled
    @DisplayName("Создание продукта")
    void testPostProduct() throws Exception {
        Product product = new Product();
        product.setTitle("Carrot");
        product.setPrice(40);
        product.setCategoryTitle("Food");
        service.postProduct(product);
        System.out.println(SerializeJson.getJson(product));
        assertJson(getResource("PostProduct.json"), product);
    }

    @Test
    @Disabled
    @DisplayName("Обновление информации о продукте")
    void testPutProduct() throws Exception {
        Product products = service.putProduct(350, "Cherry Juice", 30, "Food");
        System.out.println(SerializeJson.getJson(products));

        assertJson(getResource("PutProduct.json"), products);
    }

    @Test
    @Disabled
    @DisplayName("Получение информации о конкретном продукте")
    void testGetProduct() throws Exception {
        Product products = service.getProduct(7);

        assertJson(getResource("ProductId7.json"), products);
    }

    @Test
    @Disabled
    @DisplayName("Удаление продукта")
    void testDeleteProduct() {
        Product productDelete = new Product();
        service.deleteProduct(179);
    }
}
