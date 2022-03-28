# Maven homework 28.03.2022

##To execute the program, run:

```
./mvnw clean install
cd service
java -jar target/service-1.0-SNAPSHOT.jar
```

##Homework
Необходимо:
1. Создать Multimodule проект с двумя модулями:
- common (вспомогательные/общие классы, jar)
- service (основной функционал, jar, зависит от модуля common)
2. Переопределить основные плагины, чтобы везде использовались актуальные версии

3. Должен быть настроен запуск Unit и Integration тестов
4. Должен быть настроен jacoco plugin, который генерирует отчет после Integration тестов

5. Для сборки проектов нужно использовать maven wrapper

