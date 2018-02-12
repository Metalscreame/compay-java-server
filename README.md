## Compay. Сервер: порядок установки и запуска

1. Скачать и установить JDK 8: [http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

2. Скачать и установить Apache Tomcat 8.5: [https://tomcat.apache.org/download-80.cgi](https://tomcat.apache.org/download-80.cgi)

3. Разахривировать его в любое доступное место (запомните путь, поскольку он еще понадобится).

4. Открыть свойства вашего компьютера -> Дополнительные параметры системы -> Переменные среды:
Переменные среды пользователя. Создать и использовать ваши пути
```
JRE_HOME        C:\Program Files\Java\jre1.8.0_151
CATALINA_HOME   D:\Development\Tomcat\apache-tomcat-8.5.23  
```

5. Системные переменные 
```
Path (нажать на изменить) и добавить туда путь аналогичный CATALINA_HOME
```

6. Из корня репозитория скопировать `.war` файл и вставить его в `\Tomcat\apache-tomcat-8.5.23\bin`

7. В папке `\apache-tomcat-8.5.23\conf` найти файл `tomcat-users.xml` и заменить его тем, что находится в репозитории.

8. В папке `\bin` запустить на исполнение `startup.bat` и ожидать запуска сервера.

9. При завершении откроется окно в браузере по адресу `localhost:8080` (если не открылось, то открыть его в браузере самостоятельно).