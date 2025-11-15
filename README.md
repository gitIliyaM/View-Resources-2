# Views & Resources

**Цели работы**: 
1. Научиться верстать экраны в XML используя библиотеку компонентов, 
2. Научиться правильно организовывать ресурсы/цвета для легкого переключения темы
3. Научиться пользоватся Figma для верстки

**Предварительное условие**:
1. Аккаунт в [Figma](https://www.figma.com/)
2. Откройте макеты в Figma по [ссылке](https://www.figma.com/community/file/1433109187156322315/views-and-resources) в своем аккаунте

## Задание 1

В этом задании вам необходимо собрать layout по макетам в Figma

1. Используйте гайдлайны и имплементацию классов из библиотеки [Material Components](https://m3.material.io/components)
2. Иконки можно взять из готовых material ассетов в студии или с сайта [https://materialdesignicons.com](https://materialdesignicons.com/)
3. Используйте директорию `res` для строк, картинок и прочих ресурсов
4. Картинки для корзины - по аашему выбору

Задание содержит 3 страницы:
1. `Contacts` - страница контакта записной книжки
2. `Cart` - страница корзины
3. `Login` - [MaterialAlertDialog](https://m2.material.io/components/dialogs/android) формы логина. создай  
   Simple dialog
   Simple dialogs display a list of items that take immediate effect when selected. При логине вводим имейл и попадаем в личную кабинет
, который выглядит как картинка с названием файла Contacts Day.png. 

Для первого и второго задания в проекте сделаны две пустых `Activity`:
1. `ContactsActivity`
2. `CartActivity`

Для третьего задания, в `MainActivity` добавлено отображение `MaterialAlertDialog` при нажатии на кнопку `Show Sign In Dialog`.

### Дополнительно

1. Не забывайте про ландшафтный режим. Верстка должна оставаться рабочей и в этом режиме.
2. Будет плюсом, если список пунктов в корзине будет реализован через `RecyclerView` или собственный `ViewGroup` с переиспользованием элементов.

## Задание 2

На каждой странице макетов в Figma есть темная тема. 
Воспользуйтесь `res/values` и `res/values-night` для реализации светлой и темной темы.
Обязательно используйте аттрибуты темы в верстке для реализации этой функциональности.

## Полезные ссылки

Настоятельно рекомендуется ознакомиться с следующими ресурсами:

1. [Material Design](https://material.io/design) - официальный сайт Material Design
2. [Material Components](https://m3.material.io/components) - библиотека компонентов Material Design
3. [Material Colors](https://m2.material.io/design/color/the-color-system.html#color-usage-and-palettes) - цветовая палитра Material Design и их использование
4. [Material theme builder](https://m3.material.io/blog/material-theme-builder) - генератор тем Material Design
