package pract_7;

/*
 Предоставляет объект-заместитель, который контролирует доступ к другому объекту.
 Заместитель обеспечивает тот же интерфейс, что и оригинальный объект, и позволяет выполнять
 дополнительные действия до или после выполнения запроса к оригинальному объекту.
 */

/**
 * Интерфейс Subject представляет основные операции, которые реализует какой-либо объект.
 */
interface Subject {
    void operation();
}

/**
 * Класс исходного объекта, реализующий интерфейс Subject.
 */
class RealSubject implements Subject {
    public void operation() {
        System.out.println("Выполнение операции исходного объекта.");
    }
}

/**
 * Заместитель, представляет объект класса ConcreteSource.
 */
public class patternProxy implements Subject {
    private Subject subject;

    public patternProxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void operation() {
        System.out.println("Дополнительные действия перед вызовом операции.");

        subject.operation();

        System.out.println("Дополнительные действия после вызова операции.");
    }
    public static void main(String[] args) {
        Subject source = new RealSubject();
        patternProxy proxy = new patternProxy(source);

        proxy.operation();
    }
}
