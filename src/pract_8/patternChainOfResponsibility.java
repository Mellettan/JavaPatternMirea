package pract_8;

/*
 Паттерн Цепочка обязанностей позволяет передавать запросы последовательно по цепочке обработчиков,
 пока один из них не обработает запрос.
 Каждый обработчик решает, может ли он обработать запрос, и передает его дальше по цепочке, если не может.
 */

/**
 * Интерфейс обработчика запроса.
 */
interface Handler {
    /**
     * Метод для обработки запроса.
     *
     * @param request запрос, который необходимо обработать
     */
    void handleRequest(Request request);

    /**
     * Метод для установки следующего обработчика в цепочке.
     *
     * @param nextHandler следующий обработчик в цепочке
     */
    void setNextHandler(Handler nextHandler);
}

/**
 * Класс, представляющий запрос.
 */
class Request {
    private String content;

    public Request(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

/**
 * Конкретный обработчик запроса.
 */
class ConcreteHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void handleRequest(Request request) {
        // В этом примере обработчик просто выводит содержимое запроса
        System.out.println("Handling request: " + request.getContent());

        // Передает запрос следующему обработчику в цепочке, если таковой существует
        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

/**
 * Класс для демонстрации использования паттерна "Цепочка обязанностей".
 */
public class patternChainOfResponsibility {
    public static void main(String[] args) {
        // Создаем обработчики
        Handler handler1 = new ConcreteHandler();
        Handler handler2 = new ConcreteHandler();
        Handler handler3 = new ConcreteHandler();

        // Устанавливаем цепочку обработчиков
        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);

        // Создаем запросы
        Request request1 = new Request("Request 1");
        Request request2 = new Request("Request 2");

        // Передаем запрос на обработку первому обработчику
        // При этом после обработчика handler1 будут вызваны обработчики handler2 и handler3
        handler1.handleRequest(request1);
        // Если мы передаем запрос на обработку сразу второму обработчику,
        // то после него будет вызван обработчик handler3
        // Обработчик handler1 не будет вызван
        handler2.handleRequest(request2);
    }
}

