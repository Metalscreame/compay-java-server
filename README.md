## Back-End: памятка

Клонируем ветку DEV (обращаю внимание - НЕ Master) из репозитория:
```
git clone https://gitlab.com/daminort/compay-server.git . -b dev
```
> Точка после адреса репозитория означает, что ветка будет склонирована в текущий каталог.
> Без точки в текущем каталоге будет создана папка "compay-web-client" 
> и ветка будет склонирована в нее.

Для каждой задачи создаем локально отдельную ветку. Название ветки: номер задачи из Канбан + краткое название (буду стараться писать в тексте задачи). Например: `task512-createDataBase-demo`:
```
git checkout -b task512-createDataBase-demo
```

По мере выполнения задачи коммитим в нее изменения. Далее, когда задача готова, сначала "спуливаем" из репозитория все, что могло быть добавлено за это время:
```
git checkout dev
git pull origin dev
```
и, при необходимости, разрешаем возможные конфликты (постараюсь не допускать).

Далее сливаем все вместе
```
git checkout dev
git merge task512-createDataBase-demo
```
и "пушим" в репозиторий:
```
git push origin dev
```
В ветку `Master` "пушить" не имеет смысла, т.к. доступа все равно нет.

После этого свою локальную ветку можно "прибить":
```
git branch -d task512-createDataBase-demo
```
### Шпаргалки
 * [H2 DataBase (en)](http://h2database.com/html/main.html)
 * [Git (ru)](https://github.com/nicothin/web-development/tree/master/git)
 * [Markdown Syntax (en)](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)
 * [Markdown Editor](https://jbt.github.io/markdown-editor)