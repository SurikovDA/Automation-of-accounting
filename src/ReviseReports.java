import java.util.ArrayList;
import java.util.HashMap;
/**
 * Класс для Сверки отчётов
 */
public class ReviseReports {
    /**
     * monthlyReportsMaps - Итоговая таблица месячных отчетов;
     * yearlyReportMap - Итоговая таблица годового отчета;
     * monthlyReportMap - Форма таблицы месячных отчетов для сверки.
     */
    static void reviseSumReports(HashMap<Integer, HashMap<String, ArrayList<Double>>> monthlyReportsMaps, HashMap<Integer, ArrayList<Double>> yearlyReportMap) {
        /**
         * Метод для Сверки отчётов
         */
        //Таблица для хранения суммы доход/расход по месяцам
        HashMap<Integer, ArrayList<Double>> reviseSumMonthlyReport = new HashMap<>();
        int monthError;

        if ((monthlyReportsMaps.size() != 0) && (yearlyReportMap.size() != 0)) {
            //Цикл вычисления суммы доход/расход по месяцам
            for (Integer keyMonthlyReportsMaps : monthlyReportsMaps.keySet()) {

                HashMap<String, ArrayList<Double>> monthlyReportMap = monthlyReportsMaps.get(keyMonthlyReportsMaps);
                //Переменные для хранения доходов и расходов за месяц
                double monthlySumsProfit = 0;
                double monthlySumsExpenses = 0;
                //Цикл суммирования доход/расход категорий внутри месяца
                for (String keyMonthlyReportMap : monthlyReportMap.keySet()) {

                    ArrayList<Double> arrayProfitAndExpense = monthlyReportMap.get(keyMonthlyReportMap);
                    monthlySumsProfit += arrayProfitAndExpense.get(0);
                    monthlySumsExpenses += arrayProfitAndExpense.get(1);
                }
                //Запись общей суммы доход/расход за месяц
                ArrayList<Double> arrayMonthlySums = new ArrayList<>(2);
                arrayMonthlySums.add(0.0);
                arrayMonthlySums.add(0.0);
                arrayMonthlySums.set(0, monthlySumsProfit);
                arrayMonthlySums.set(1, monthlySumsExpenses);
                //Сохранение общей суммы доход/расход по месяцам
                reviseSumMonthlyReport.put(keyMonthlyReportsMaps, arrayMonthlySums);
            }
            int allSuccessfulReviseReports = 0;
            //Цикл вызова общей суммы месячных отчетов по месяцам
            for (Integer keyMonthlyReportsMaps : reviseSumMonthlyReport.keySet()) {

                boolean successfulRevise = false;
                //Запись в список суммы доход/расход по месяцам
                ArrayList<Double> arrayMonthlySums = reviseSumMonthlyReport.get(keyMonthlyReportsMaps);
                //Вывод помесячных доход/расход из годового отчета
                ArrayList<Double> arrayYearlySums = yearlyReportMap.get(keyMonthlyReportsMaps);
                //Сверка помесячного доход/расход с годовым отчетом по месяцам
                if ((arrayMonthlySums.get(0).equals(arrayYearlySums.get(0))) &&
                        (arrayMonthlySums.get(1).equals(arrayYearlySums.get(1)))) {

                    successfulRevise = true;
                    allSuccessfulReviseReports += 1;
                }

                monthError = keyMonthlyReportsMaps;

                 if (!successfulRevise) {

                    System.out.println("Отчет за месяц: " + calendar(monthError) + " не соответствует годовому отчету.");

                } else if (allSuccessfulReviseReports == reviseSumMonthlyReport.size()) {

                    System.out.println("Сверка отчётов успешно завершена, ошибок не обнаружено.");
                }
            }
        } else {
            System.out.println("Перед Сверкой отчётов необходимо:\n" +
                    "1 - Считать все месячные отчёты;\n" +
                    "2 - Считать годовой отчёт.");
        }
    }

    public static String calendar(Integer month) {
        /**
         * Метод для замены номера месяца на его название
         */
        String nameMonth = null;
        switch (month) {
            case 1: nameMonth = "\"Январь\"";
                break;
            case 2: nameMonth = "\"Февраль\"";
                break;
            case 3: nameMonth = "\"Март\"";
                break;
            case 4: nameMonth = "\"Апрель\"";
                break;
            case 5: nameMonth = "\"Май\"";
                break;
            case 6: nameMonth = "\"Июнь\"";
                break;
            case 7: nameMonth = "\"Июль\"";
                break;
            case 8: nameMonth = "\"Август\"";
                break;
            case 9: nameMonth = "\"Сентябрь\"";
                break;
            case 10: nameMonth = "\"Октябрь\"";
                break;
            case 11: nameMonth = "\"Ноябрь\"";
                break;
            case 12: nameMonth = "\"Декабрь\"";
                break;
        }
        return nameMonth;
    }
}
