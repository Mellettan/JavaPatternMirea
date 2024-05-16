package ru.mirea.pract_14;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;

@org.springframework.stereotype.Controller
public class Controller {
    @Value("${GITHUB_GIST}")
    private String GITHUB_GIST;

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Market> markets = new ArrayList<>();

    @GetMapping("/home")
    public @ResponseBody String home() throws IOException {
        WebClient client = WebClient.create(GITHUB_GIST);
        String responseBody = client.get().retrieve().toEntity(String.class).block().getBody();
        return responseBody;
    }

    @GetMapping("/products")
    public @ResponseBody String returnOrders() {
        StringBuilder builder = new StringBuilder();
        for (Product product : products) {
            builder.append(product.getName()).append(" ").append(product.getPrice()).append(" ").append("\n");
        }
        return builder.toString();
    }

    @GetMapping("/markets")
    public @ResponseBody String returnMarkets() {
        StringBuilder builder = new StringBuilder();
        for (Market market : markets) {
            builder.append(market.getName()).append(" ").append(market.getAddress()).append(" ").append("\n");
        }
        return builder.toString();
    }

    @PostMapping("/products")
    public @ResponseBody String addProduct(@RequestParam String name,
                                        @RequestParam int price) {
        products.add(new Product(name, price));
        return "Product successfully added";
    }

    @PostMapping("/markets")
    public @ResponseBody String addMarket(@RequestParam String name,
                                         @RequestParam String address) {
        markets.add(new Market(name, address));
        return "Market successfully added";
    }

    @DeleteMapping(value = "/products/delete/{productName}")
    public @ResponseBody String removeProduct(@PathVariable String productName) {
        int id = findProduct(productName);
        if (id == -1) {
            return "There is no product with such name";
        }
        products.remove(id);
        return "Product successfully deleted";
    }


    @DeleteMapping(value = "/markets/delete/{marketName}")
    public @ResponseBody String removeMarkets(@PathVariable String marketName) {
        int id = findMarket(marketName);
        if (id == -1) {
            return "There is no market with such name";
        }
        markets.remove(id);
        return "Market successfully deleted";
    }

    private int findProduct(String name) {
        for (int i = 0; i < products.size(); ++i) {
            if (products.get(i).getName().equals(name))
                return i;
        }
        return -1;
    }

    private int findMarket(String name) {
        for (int i = 0; i < markets.size(); ++i) {
            if (markets.get(i).getName().equals(name))
                return i;
        }
        return -1;
    }
}
