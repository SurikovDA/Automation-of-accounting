# java-sprint2-hw
Second sprint homework

# Техническое задание проекта

## Постановка задачи

Отдел бухгалтерии парка развлечений пользуется простыми excel-таблицами для ведения бюджета. 
В их работе постоянно возникают ошибки при вводе данных и подсчёте балансов. 
Руководство компании поставило перед вами сложную задачу — создать новое приложение для бухгалтерии.
Оно должно предоставлять следующий функционал:
1) Иметь консольный интерфейс для управления программой.
2) Считывать месячные и годовые отчёты бухгалтерии из файлов и приводить их в объекты приложения.
3) Проводить сверку данных по месячным и годовым отчётам.
4) Выводить информацию о месячных и годовом отчёте.

В настоящий момент бухгалтерия готова предоставить три месячных отчёта: 
за январь, февраль и март 2021 года, а также частичный годовой отчёт за эти же три месяца. 
Пока приложение должно обрабатывать только эти данные.

## Детали реализации:

### Формат входных файлов:

Бухгалтерия готова предоставить данные о своей деятельности в виде файлов в формате CSV — Comma-Separated Values
(англ. «значения, разделённые запятыми»). Нужно разбить входящий файл на составляющие и преобразовать к объектам 
приложения. Приложение должно работать с двумя видами отчётов:
- Месячный отчёт, содержащий данные о доходах и расходах в рамках одного календарного месяца. В программе они
представляются классом MonthlyReport.
- Годовой отчёт, содержащий ровно по 2 записи на каждый из 12 месяцев — общий доход и расход за этот месяц.
Представлен классом YearlyReport.

Каждый файл в формате CSV состоит из набора строк. В самой первой строке идут заголовки полей.
Далее каждая строка состоит из значений, разделённых символов-разделителем — запятой.

### Именование файлов

Файлы с отчётами именуются определённым образом, чтобы упростить их считывание и обработку.

### Именование месячных отчётов

Имена файлов с месячными отчётами имеют формат m.YYYYMM.csv, где:
- m — буква m в начале файла, чтобы отделить отчёты за месяц и отчёты за год;
- YYYY — год. Например, 2021;
- MM — месяц строго двумя цифрами. Счёт начинается с единицы, то есть 01 — «январь», а 11 — «ноябрь».
Пример именований: m.202001.csv — месячный отчёт за январь 2020 года, m.201912.csv — месячный отчёт
за декабрь 2019 года.

### Именование годовых отчётов

Имена файлов с годовым отчётом имеют формат y.YYYY.csv, где:
- y — буква y в начале файла, чтобы отделить отчёты за месяц и отчёты за год;
- YYYY — год. Например, 2021.
Пример именований: y.2020.csv — годовой отчёт за 2020 год, y.2018.csv — годовой отчёт за 2018 год.

### Формат месячного отчёта

Месячный отчёт содержит информацию о всех тратах, произведённых в течение календарного месяца. 
Сюда попадает информация как о доходах, так и о расходах парка развлечений.
Пример CSV файла с месячным отчётом:

item_name,is_expense,quantity,sum_of_one
Воздушные шарики,TRUE,5000,5
Автоматы с мороженным,TRUE,12,15000
Продажа мороженного,FALSE,1000,120

Месячные отчёты состоят из четырёх полей:
- item_name — название товара;
- is_expense — одно из двух значений: TRUE или FALSE. Обозначает, является ли запись тратой (TRUE) или доходом (FALSE);
- quantity — количество закупленного или проданного товара;
- sum_of_one — стоимость одной единицы товара. Целое число.

### Формат годового отчёта

Годовой отчёт содержит информацию о всех тратах, произведённых в течение года. Он содержит по две записи на каждый
месяц. Месяц обозначается строго двумя цифрами, начиная с единицы, то есть 01 — «январь», а 11 — «ноябрь».

Пример CSV файла с годовым отчётом:

month,amount,is_expense
01,100000,false
01,30000,true
02,321690,false
02,130000,true
03,999999,true
03,999999,false

Строка годового отчёта состоит из трёх полей:

- month — месяц. Целое число;
- amount — сумма;
- is_expense — одно из двух значений: true или false. Обозначает, является ли запись тратой (true) или доходом (false).

###  Консольный интерфейс

Консольный интерфейс по работе с программной должен позволять оператору произвести одно из пяти действий по выбору:
1) Считать все месячные отчёты
2) Считать годовой отчёт
3) Сверить отчёты
4) Вывести информацию о всех месячных отчётах
5) Вывести информацию о годовом отчёте

После выбора и исполнения действия, программа должна позволять оператору ввести следующее действие. 
Программа должна завершаться только при вводе оператором специальной последовательности символов. 
Придумать такую последовательность вы можете сами.

### Считывание файлов

Программа позволяет считывать месячные и годовые отчёты. При выборе оператором действия «считать месячный отчёт» 
должно происходить считывание трёх файлов:
- m.202101.csv
- m.202102.csv
- m.202103.csv 
  При выборе действия «считать годовой отчёт» должно происходить считывание из одного файла:
- y.2021.csv
  Содержимое файлов должно приводиться к объектам приложения для дальнейшей обработки.
  Метод принимает в качестве параметра имя файла, который нужно считать, и возвращает либо список строк
содержимое файла, либо пустой список, если файл не будет найден. Аргументом метода является полный или относительный
путь до файла.

### Сверка данных

Сверка данных — это проверка, что данные в двух и более разных источниках не противоречат друг другу. В данном случае
при сверке данных вам нужно проверить, что информация по месяцу в годовом отчёте не противоречит информации в месячном 
отчёте.
При вызове сверки данных программа должна:
1) Подсчитывать две суммы: общие доходы и общие расходы по каждому из месяцев.
2) Сверять полученные суммы с суммой доходов и расходов в отчёте по году.
Если обнаружена ошибка, программа должна выводить месяц, в котором обнаружено несоответствие.
Если ошибок не обнаружено, должна выводиться только информация об успешном завершении операции.

### Вывод информации

Программа должна поддерживать вывод двух коротких текстовых отчётов:
1) Информацию о всех месячных отчётах.
2) Информация о годовом отчёте.

#### Информация о всех месячных отчётах

При вызове этой функции программа должна выводить следующие данные:
- Название месяца;
- Самый прибыльный товар, то есть товар для которого is_expense == false, а произведение количества (quantity) на сумму
(sum_of_one) максимально. Вывести название товара и сумму;
- Самую большую трату. Вывести название товара и сумму.

Эта информация должна выводиться по каждому из месяцев.

#### Информация о годовом отчёте

При вызове этой функции программа должна выводить следующие данные:
- Рассматриваемый год;
- Прибыль по каждому месяцу. Прибыль — это разность доходов и расходов;
- Средний расход за все месяцы в году;
- Средний доход за все месяцы в году.