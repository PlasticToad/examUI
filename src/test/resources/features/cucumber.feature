#language: ru
  Функция: Проверка JiraUI
    Сценарий: Проверка количества задач
      Дано Проверка количества задач
    Сценарий: Проверка версии и статуса
      Дано Переход на страницу задачи TestSelenium
      Тогда Проверка статуса СДЕЛАТЬ
      Тогда Проверка версии
    Сценарий: Создание, закрытие задачи + контроль статуса ГОТОВ
      Дано Создать задачу
      Тогда Заполнить поля задачи
      Когда Перейти к созданой задаче
      Тогда Исполнить задачу
      Тогда Проверка статуса ГОТОВО

