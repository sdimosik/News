# News

![](https://media.giphy.com/media/N5mFxuN2pys17aJmDf/giphy.gif) ![](https://media.giphy.com/media/QIchSROjmQpszDVOZg/giphy.gif) ![](https://media.giphy.com/media/fSf035hmdnqn8fLTUd/giphy.gif)


## Done tasks
- Новости сгруппированы по категориям и пролистывались горизонтально
- Категории отображались в алфавитном порядке
- Новости отображались от новых к старым
- При нажатии на новость она открывалась в браузере
- На изображение был наложен градиент, для лучшего отображения текста
- Изображения при переиспользовании ячеек не мелькали и были корректными (а не
от предыдущих новостей). Например, при быстром пролистывании новостей и
медленном интернете
- Загруженные новости кэшировались в базу данных
на ваш выбор
- Загруженные изображения для новостей
кэшировались на диск
- При следующем запуске, даже при отсутствии
интернета, список новостей оставался доступным,
как и ранее загруженные изображения
- Пагинация

## Technology stack

- Kotlin
- Clean Arch
- MVVM
- Corutines
- Retrofit 2
- Room
- Hilt

## About

Приложение для просмотра новостей

При иницилизации загружает 20 новостей каждой категории, если категория оказывается пуста, то не показывает её
После прокрутки до конца подгружается ещё 20 страница и т.д.
Если страницы закончились, бдует alert

Есть базовое кэширование от okHttp, но так же есть кэширование с помощью Room

Если нет network connecetion, то просто берём информацию из базы данных
Если сеть есть, сначала грузим новые, сохраняем их и чистим бд от старых
(Кэшировать захотел только первые 20 новостей, последующие не кэшируются с помощью Room)

Загрузка и кэширование картинок происходит с помощью Glide