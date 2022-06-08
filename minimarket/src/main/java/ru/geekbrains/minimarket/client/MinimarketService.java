package ru.geekbrains.minimarket.client;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.geekbrains.minimarket.model.Category;
import ru.geekbrains.minimarket.model.Product;

import java.util.List;

import static ru.geekbrains.minimarket.Retrofit.executeCall;

public class MinimarketService {
    private final MinimarketApi api;

    public MinimarketService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(System.out::println);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        api = new Retrofit.Builder()
                .baseUrl("https://minimarket1.herokuapp.com/market/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(MinimarketApi.class);
    }

    public Category getCategory(long id) {
        return executeCall(api.getProductById(id));
    }

    public List<Product> getProducts() {
        return executeCall(api.getProducts());
    }

    public Product postProduct(Product product) {
        return executeCall(api.postProduct(product));
    }

    public Product putProduct(long id, String title, Integer price, String categoryTitle) {
        Product product = new Product(id, title, price, categoryTitle);
        return executeCall(api.putProduct(product));
    }

    public Product getProduct(long id) {
        return executeCall(api.getProduct(id));
    }

    public void deleteProduct(long id) {
        executeCall(api.deleteProduct(id));
    }
}
