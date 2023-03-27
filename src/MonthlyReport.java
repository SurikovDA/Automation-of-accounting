import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс для работы с Месячными отчетами
 */
public class MonthlyReport {
    /**
     * monthlyReportsMaps - Форма итоговой таблицы месячных отчетов;
     * pathCatalogBy_Months - Каталог с месячными отчетами;
     * formatMonthlyReport - Формат месячных отчетов.
     */
    //Сюда будем складывать, по номеру месяца, общую сумму доходов и расходов:
    HashMap<Integer, HashMap<String, ArrayList<Double>>> monthlyReportsMaps = new HashMap<>();
    static String pathCatalogBy_Months = ("resources");
    static String formatMonthlyReport = "m.\\d{6}.csv";
    static String patternFormatMonthlyReport = "m.YYYYMM.csv";
    File catalogByMonths;


    void readingMonthlyReports() {
        /**
         * Метод для Считывания всех месячных отчётов
         */
        String itemName;
        String isExpense;
        double quantity;
        double sum;
        catalogByMonths = new File(pathCatalogBy_Months);

        try {
            File[] pathsMonthlyReports = catalogByMonths.listFiles((dir, name) -> name.matches(formatMonthlyReport));

            if (pathsMonthlyReports != null && pathsMonthlyReports.length != 0 && pathsMonthlyReports.length < 13) {

                for (File pathFileReport : pathsMonthlyReports) {

                    Integer monthNumber = Integer.parseInt(pathFileReport.getName().replaceFirst("m.\\d{4}", "")
                            .replaceFirst(".csv", ""));
                    //Сюда будем складывать списком общую сумму доходов и расходов по наименованию товара:
                    HashMap<String, ArrayList<Double>> monthlyReportMap = new HashMap<>();
                    BufferedReader readerMonthlyReport = new BufferedReader(new FileReader(pathFileReport));
                    String lineMonthlyReport;

                    while ((lineMonthlyReport = readerMonthlyReport.readLine()) != null) {
                        //Кладем строку в массив, разбивая по запятой
                        String[] arrayLinesMonthlyReport = lineMonthlyReport.split(",");
                        //Записываем стринговые значения в переменные
                        itemName = arrayLinesMonthlyReport[0];
                        isExpense = arrayLinesMonthlyReport[1];
                        //Создаем список с 2мя значениями: для расходов и доходов
                        ArrayList<Double> arrayProfitAndExpense = new ArrayList<>(2);
                        //Доходы
                        arrayProfitAndExpense.add(0.0);
                        //Расходы
                        arrayProfitAndExpense.add(0.0);
                        //Проверяем значение isExpense на доход или расход, парсим строки и записываем в переменные.
                        //Добавляем общую сумму в соответствующее значение списка arrayProfitAndExpense: 0 - доход, 1 - расход.
                        if (isExpense.equalsIgnoreCase("false"))  {

                            quantity = Double.parseDouble(arrayLinesMonthlyReport[2]);
                            sum = Double.parseDouble(arrayLinesMonthlyReport[3]);
                            arrayProfitAndExpense.set(0, quantity * sum);
                            //Добавляем значения общей суммы доходов и расходов по наименованию товара
                            monthlyReportMap.put(itemName, arrayProfitAndExpense);
                            //Добавляем этот тримап, в тримап по номеру месяца
                            monthlyReportsMaps.put(monthNumber, monthlyReportMap);

                        } else if (isExpense.equalsIgnoreCase("true")) {

                            quantity = Double.parseDouble(arrayLinesMonthlyReport[2]);
                            sum = Double.parseDouble(arrayLinesMonthlyReport[3]);
                            arrayProfitAndExpense.set(1, quantity * sum);
                            //Добавляем значения общей суммы доходов и расходов по наименованию товара
                            monthlyReportMap.put(itemName, arrayProfitAndExpense);
                            //Добавляем этот тримап, в тримап по номеру месяца
                            monthlyReportsMaps.put(monthNumber, monthlyReportMap);
                        }

                    }
                    readerMonthlyReport.close();
                }
                System.out.println("Считывание всех месячных отчётов успешно завершено.");
            } else {

                //Метод вывода ошибок при считывании месячных отчетов
                windowsErrorMonthlyReports();
            }
        } catch (IOException e) {
            windowsErrorMonthlyReports();
        }

    }

    void windowsErrorMonthlyReports() {
        /**
         * Красный круг с крестиком windows error
         */
        System.out.println(
                "Ошибка: файл не найден.\n" +
                        "⣿⠀⠀⢀⣴⣾⣿⣿⣿⣿⣦⡀⠀⠀⣿ Возможно файл в формате " + patternFormatMonthlyReport + " не находится в папке:\n" +
                        "⣿⠀⢠⣿⣿⡉⠙⠿⠋⢉⣻⣿⡄⠀⣿ \"" + catalogByMonths.getAbsolutePath() + "\".\n" +
                        "⣿⠀⢸⣿⣿⣿⠆⠀⠰⣿⣿⣿⡇⠀⣿   m — буква в начале файла отделяет отчёты за месяц(m) и отчёты за год(y);\n" +
                        "⣿⠀⠸⣿⣿⣄⣴⣿⣦⣀⣽⣿⠏⠀⣿YYYY — год. Например, 2022;\n" +
                        "⣿⠀⠀⠙⢿⣿⣿⣿⣿⣿⡿⠋⠀⠀⣿  MM — месяц строго 2мя цифрами." +
                        "Количество файлов в папке не должно превышать 12 месяцев в году.");
    }

    void outputInfoMonthlyReports() {
        /**
         * Метод Вывода информации о всех месячных отчётах
         */
        if (monthlyReportsMaps.size() != 0) {

            for (Integer keyMonthlyReportsMaps : monthlyReportsMaps.keySet()) {

                String maxProfitItemName = null;
                String maxExpanseItemName = null;
                double maxProfitMonthlyReport = 0.0;
                double maxExpanseMonthlyReport = 0.0;

                HashMap<String, ArrayList<Double>> monthlyReportMap = monthlyReportsMaps.get(keyMonthlyReportsMaps);

                System.out.println("Месяц: " + ReviseReports.calendar(keyMonthlyReportsMaps));

                for (String itemName : monthlyReportMap.keySet()) {

                    ArrayList<Double> arrayProfitAndExpense = monthlyReportMap.get(itemName);

                    if (arrayProfitAndExpense.get(0) > maxProfitMonthlyReport) {

                        maxProfitMonthlyReport = arrayProfitAndExpense.get(0);
                        maxProfitItemName = itemName;

                    } else if (arrayProfitAndExpense.get(1) > maxExpanseMonthlyReport) {

                        maxExpanseMonthlyReport = arrayProfitAndExpense.get(1);
                        maxExpanseItemName = itemName;
                    }
                }

                System.out.println(" Самый прибыльный товар: \"" + maxProfitItemName + "\" на сумму: \"" + maxProfitMonthlyReport + "\"");
                System.out.println(" Самая большая трата: \"" + maxExpanseItemName + "\" на сумму: \"" + -maxExpanseMonthlyReport + "\"");
            }
        } else {
            System.out.println("Перед Выводом информации о всех месячных отчётах необходимо:\n" +
                    "1 - Считать все месячные отчёты.");
        }
    }
}



