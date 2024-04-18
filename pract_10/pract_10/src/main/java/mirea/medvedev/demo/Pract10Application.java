package mirea.medvedev.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@SpringBootApplication
//public class Pract10Application {
//
//	public static void main(String[] args) {
//		SpringApplication.run(Pract10Application.class, args);
//	}
//
//}

// Интерфейс Programmer с методом doCoding()
interface Programmer {
	void doCoding();
}

// Реализации интерфейса Programmer
class Junior implements Programmer {
	@Override
	public void doCoding() {
		System.out.println("Junior programmer is coding...");
	}
}

class Middle implements Programmer {
	@Override
	public void doCoding() {
		System.out.println("Middle programmer is coding...");
	}
}

class Senior implements Programmer {
	@Override
	public void doCoding() {
		System.out.println("Senior programmer is coding...");
	}
}

// Конфигурация Spring контейнера
@Configuration
class AppConfig {
	@Bean(name = "juniorBean")
	public Junior junior() {
		return new Junior();
	}

	@Bean(name = "middleBean")
	public Middle middle() {
		return new Middle();
	}

	@Bean(name = "seniorBean")
	public Senior senior() {
		return new Senior();
	}
}

// Основной класс приложения
public class Pract10Application {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java Pract10Application <beanName>");
			return;
		}

		// Создание контекста приложения на основе аннотаций
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		// Получение бина по его имени из аргументов командной строки
		String beanName = args[0];
		Programmer programmer = (Programmer) context.getBean(beanName);

		// Вызов метода doCoding() у полученного бина
		programmer.doCoding();
	}
}
