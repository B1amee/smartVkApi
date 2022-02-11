# d.vereshchagin
## 2 уровень. SMART VK API
Изучение и применение на практике Selenide, работа с http-запросами
## Стэк технологий
java 11/maven/TestNG/selenide/rest-assured
## Установка
Перед запуском проекта необходимо:
- Залогиниться в Вк;
- Получить токен используя [ссылку](https://oauth.vk.com/authorize?client_id=7693362&display=page&scope=wall,photos&response_type=token&v=5.92&state=123456)
- Заполнить в [creds.json](src/main/resources/creds.json):
  * login
  * password
  * token
  * owner_id
  
## Запуск
Запускать можно как в IDE указав путь к testng.xml, как src/test/resources/testng.xml, 

или используя консоль выполнить команду
```
    mvn clean test
```