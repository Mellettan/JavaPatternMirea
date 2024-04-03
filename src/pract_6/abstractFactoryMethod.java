package pract_6;

/*
 Абстрактная фабрика — это порождающий паттерн проектирования, который позволяет создавать семейства связанных объектов,
 не привязываясь к конкретным классам создаваемых объектов.
 */

/**
 * Интерфейс продукта А
 */
interface ProductA {
    void operationA();
}

/**
 * Конкретный продукт А1, реализующий интерфейс ProductA
 */
class ConcreteProductA1 implements ProductA {
    @Override
    public void operationA() {
        System.out.println("ConcreteProductA1 operationA");
    }
}

/**
 * Конкретный продукт А2, реализующий интерфейс ProductA
 */
class ConcreteProductA2 implements ProductA {
    @Override
    public void operationA() {
        System.out.println("ConcreteProductA2 operationA");
    }
}

/**
 * Интерфейс продукта B
 */
interface ProductB {
    void operationB();
}

/**
 * Конкретный продукт B1, реализующий интерфейс ProductB
 */
class ConcreteProductB1 implements ProductB {
    @Override
    public void operationB() {
        System.out.println("ConcreteProductB1 operationB");
    }
}

/**
 * Конкретный продукт B2, реализующий интерфейс ProductB
 */
class ConcreteProductB2 implements ProductB {
    @Override
    public void operationB() {
        System.out.println("ConcreteProductB2 operationB");
    }
}

/**
 * Интерфейс абстрактной фабрики
 */
interface AbstractFactory {
    ProductA createProductA();
    ProductB createProductB();
}

/**
 * Конкретная фабрика 1, реализующая интерфейс AbstractFactory
 */
class ConcreteFactory1 implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ConcreteProductA1();
    }

    @Override
    public ProductB createProductB() {
        return new ConcreteProductB1();
    }
}

/**
 * Конкретная фабрика 2, реализующая интерфейс AbstractFactory
 */
class ConcreteFactory2 implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ConcreteProductA2();
    }

    @Override
    public ProductB createProductB() {
        return new ConcreteProductB2();
    }
}

/**
 * Тестирование
 */
public class abstractFactoryMethod {
    public static void main(String[] args) {
        AbstractFactory factory1 = new ConcreteFactory1();
        ProductA productA1 = factory1.createProductA();
        ProductB productB1 = factory1.createProductB();

        productA1.operationA();
        productB1.operationB();

        AbstractFactory factory2 = new ConcreteFactory2();
        ProductA productA2 = factory2.createProductA();
        ProductB productB2 = factory2.createProductB();

        productA2.operationA();
        productB2.operationB();
    }
}
