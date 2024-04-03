package pract_6;

/*
 Строитель — это порождающий паттерн проектирования, который позволяет создавать сложные объекты пошагово.
 */

/**
 * Продукт
 */
class Product1 {
    private String part1;
    private String part2;

    public void setPart1(String part1) {
        this.part1 = part1;
    }

    public void setPart2(String part2) {
        this.part2 = part2;
    }

    public void showProduct() {
        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);
    }
}

/**
 * Интерфейс строителя
 */
interface Builder {
    void buildPart1();
    void buildPart2();
    Product1 getProduct();
}

/**
 * Конкретный строитель
 */
class ConcreteBuilder implements Builder {
    private Product1 product = new Product1();

    @Override
    public void buildPart1() {
        product.setPart1("Part 1 built");
    }

    @Override
    public void buildPart2() {
        product.setPart2("Part 2 built");
    }

    @Override
    public Product1 getProduct() {
        return product;
    }
}

/**
 * Директор
 */
class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void construct() {
        builder.buildPart1();
        builder.buildPart2();
    }
}

/**
 * Тестирование
 */
public class builderMethod {
    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        director.construct();

        Product1 product = builder.getProduct();
        product.showProduct();
    }
}
