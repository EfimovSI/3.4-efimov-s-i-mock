# Домашнее задание к занятию «Композиция и зависимость объектов. Mockito при создании автотестов»

## Задача №1 - "Менеджер Афиши"

### Легенда

На основе проекта с лекции необходимо реализовать менеджер Афиши (все фильмы хранятся внутри самого менеджера в массиве, без всякого репозитория):

![](pic/afisha.png)

Какие методы должны быть у менеджера?
1. Добавить фильм в ленту (класс фильма напишите сами по аналогии с лекции).
1. Выдать последние 10 добавленных фильмов* (фильмы выдаются в обратном порядке, т.е. первым в массиве результатов будет тот, который был добавлен последним).

Примечание*: если фильмов меньше 10, то выдаёте столько, сколько есть.

Сделайте так, чтобы по умолчанию выводилось последние 10 добавленных фильмов, но при создании менеджера можно было указать другое число, чтобы, например, выдавать 5 (а не 10). Т.е. у вас у менеджера должно быть два конструктора: один без параметров, выставляющий лимит менеджера в 10, а другой с параметром, берущий значение лимита для менеджера из параметра конструктора.

Метод получения последних фильмов будет очень похож на тот что был в лекции. Основное отличие будет в том, что вам в его начале надо будет вычислить правильный ожидаемый размер результирующего массива-ответа, а не просто брать длину массива что лежит в поле; сделать это можно заведя целочисленную переменную в которую вы сохраните желаемый размер создаваемого массива, вычислите с помощью условных операторов для неё значение, а затем только создадите массив указав эту переменную как требуемый для него размер, например:

```java
...
  ??? resultLength;
  if ??? {
    resultLength = ???
  } else {
    resultLength = ???
  }
  ??? result = new ???[resultLength];
  for (???) {
    // заполняем result из массива что лежит в поле
    // в обратном порядке
  }
...
```

Не забывайте про использование отладчика для поиска проблем вашей реализации если тесты проходить не будут.

Напишите необходимые, с вашей точки зрения, автотесты на различные состояния менеджера (можно их делать не в одном файле).

Требования к проекту:
1. Подключите плагин Surefire так, чтобы сборка падала в случае отсутствия тестов.
1. Подключите плагин JaCoCo в режиме генерации отчётов (обрушать сборку по покрытию не нужно).
1. Реализуйте нужные классы и методы.
1. Напишите автотесты на методы, содержащие логику, добившись 100% покрытия по branch'ам.
1. Подключите CI на базе Github Actions и выложите всё на Github.

Итого: у вас должен быть репозиторий на GitHub, в котором расположен ваш Java-код.

## Задача №2 - "Менеджер Афиши" (divide and conquer)*

**Важно**: это необязательная задача. Её (не)выполнение не влияет на получение зачёта по ДЗ.

### Легенда

В первой задаче создайте новую ветку `layers`, в которой разделите менеджера на менеджера и репозиторий.

В репозитории должны быть следующие методы:
1. `findAll` - возвращает массив всех хранящихся в массиве объектов
1. `save` - добавляет объект в массив
1. `findById` - возвращает объект по идентификатору (либо `null`, если такого объекта нет)
1. `removeById` - удаляет объект по идентификатору (если объекта нет, то пусть будет исключение, как на лекции)
1. `removeAll`* - полностью вычищает репозиторий

<details>
  <summary>Подсказка</summary>
  
  Для удаления всех элементов достаточно в `items` положить пустой массив. 
  
  В Java встроен механизм, который называется Garbage Collection (сборка мусора), он сам удаляет из памяти машины неиспользуемые объекты.
</details>

<details>
  <summary>Как должен работать менеджер</summary>
  
  Если брать пример из лекции и реализовывать репозиторий, работающий в памяти, то репозиторий будет выглядеть вот так:
```java
package ru.netology.repository;

import ru.netology.domain.PurchaseItem;

public class CartRepository {
  private PurchaseItem[] items = new PurchaseItem[0];

  public void save(PurchaseItem item) {
    int length = items.length + 1;
    PurchaseItem[] tmp = new PurchaseItem[length];
    System.arraycopy(items, 0, tmp, 0, items.length);
    int lastIndex = tmp.length - 1;
    tmp[lastIndex] = item;
    items = tmp;
  }

  public PurchaseItem[] findAll() {
    return items;
  }

  public void removeById(int id) {
    int length = items.length - 1;
    PurchaseItem[] tmp = new PurchaseItem[length];
    int index = 0;
    for (PurchaseItem item : items) {
      if (item.getId() != id) {
        tmp[index] = item;
        index++;
      }
    }
    items = tmp;
  }
}
```

А сервис вот так:
```java
package ru.netology.manager;

import ru.netology.domain.PurchaseItem;
import ru.netology.repository.CartRepository;

public class CartManager {
  private CartRepository repository;

  public CartManager(CartRepository repository) {
    this.repository = repository;
  }

  public void add(PurchaseItem item) {
    repository.save(item);
  }

  public PurchaseItem[] getAll() {
    PurchaseItem[] items = repository.findAll();
    PurchaseItem[] result = new PurchaseItem[items.length];
    for (int i = 0; i < result.length; i++) {
      int index = items.length - i - 1;
      result[i] = items[index];
    }
    return result;
  }

  public void removeById(int id) {
    repository.removeById(id);
  }
}
```
</details>

Напишите автотесты на репозиторий (допускается, что как и в лекции на `removeById` может возникать исключение при удалении по несуществующему id).

Обеспечьте использование менеджером созданного вами репозитория (новых функций в менеджер по сравнению с первым заданием добавлять не нужно). Репозиторий должен быть зависимостью для менеджера (т.е. задаваться через конструктор и храниться в приватном поле).

Покройте менеджера автотестами, используя Mockito для организации моков репозитория.

Итого у вас должно быть:
1. Ветка `layers`, в которой должны быть классы `AfishaManager` и `AfishaRepository`
1. Pull Request на Github (удостоверьтесь, что CI успешно проводит сборку*)

Примечание*: если вы реализовали тест на удаление по несуществующему id, то допускается, что сборка в CI будет failed.

Итого: у вас должен быть репозиторий на GitHub, в котором расположен ваш Java-код и Pull Request.
