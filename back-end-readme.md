Инструкция по установке локального сервера проэкта компей.

Скачать и установить JDK 8 
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

Скачать и установить Apache Tomcat 8.5 (zip)
https://tomcat.apache.org/download-80.cgi

Разахривировать его в любое доступное место(вы потому туда еще попадете, так что запомните его :) )

Открыть свойтства вашего компьютера -> Дополнительные параметры системы -> Переменные среды:
Переменные среды пользователя. Создать и использовать ваши пути

	JRE_HOME  	      	C:\Program Files\Java\jre1.8.0_151
	CATALINA_HOME		D:\Development\Tomcat\apache-tomcat-8.5.23  

  Системные переменные 

	Path (нажать на изменить) и добавить туда путь аналогичный Catalina_home

Из корня репозитория скопировать .war файл и вставить его в \Tomcat\apache-tomcat-8.5.23\bin

В папке \apache-tomcat-8.5.23\conf найти файл tomcat-users.xml и заменить его тем, что находится в репозетории

В папке \bin нажать на startup.bat и ждать, пока серв не запуститься. При возникновении ошибок - гуглить))


При завершении откроется окно в браузере по адресу localhost:8080 (если не открылось, то открыть его в браузере самостоятельно)

Далее - дело за клиентом.




