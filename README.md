## Тестовое задание в компанию KTE Labs
### Описание проекта:
Веб приложение для записи пациентов на приемы к врачам

### Стек технологий:
Java, Spring (Boot, Data, Validation), Maven, PostgreSQL, Lombok, Swagger

### Инструкция по запуску:
1) Склонировать репозиторий
2) Создать базу данных с названием "ktelabs" под пользователем с именем "postgres" и паролем "password"
3) Сгенерировать вспомогательные классы нажав правой кнопкой мыши на 'pom.xml' -> 'Maven' -> 'Generate Sources And Update Folders'
4) Запустить приложение через main в классе "KtelabsApplication"
5) Перейти на сайт http://localhost:8080/swagger-ui/
6) Чтобы получить XML-конфигурацию SOAP для создания расписания перейти на сайт http://localhost:8080/ws/schedule.wsdl
