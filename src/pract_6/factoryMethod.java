package pract_6;

/*
 Фабричный метод — это порождающий паттерн проектирования, который определяет общий интерфейс для создания объектов
 в суперклассе, позволяя подклассам изменять тип создаваемых объектов.
 */

/**
 * Интерфейс продукта
 */
interface Product {
    void operation();
}

/**
 * Конкретный продукт
 */
class ConcreteProduct implements Product {
    @Override
    public void operation() {
        System.out.println("ConcreteProduct operation");
    }
}

/**
 * Интерфейс создателя
 */
interface Creator {
    Product factoryMethod();
}

/**
 * Конкретный создатель, реализующий интерфейс
 */
class ConcreteCreator implements Creator {
    @Override
    public Product factoryMethod() {
        return new ConcreteProduct();
    }
}

/**
 * Тестирование
 */
public class factoryMethod {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        Product product = creator.factoryMethod();
        product.operation();
    }
}
