
import java.util.Scanner;



public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printMenu();                                // Метод печати меню
        int command = scanner.nextInt();            // Ввод команды пользователем

        // Объявление новых объектов

        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();


        // Цикл программы
        while (command != 0) {
            // Обработка команд
            if (command == 1) {

                //Считывание месячных отчетов
                monthlyReport.readingMonthlyReports();

            } else if (command == 2) {
                //Считывание годовых отчетов
                yearlyReport.readingYearlyReport();


            } else if (command == 3) {
                //Сверить отчеты
                ReviseReports.reviseSumReports(monthlyReport.monthlyReportsMaps, yearlyReport.yearlyReportMap);


            } else if (command == 4) {
                //Вывод информации о месячных отчетах
                monthlyReport.outputInfoMonthlyReports();


            } else if (command == 5) {
                //Вывод информации о годовом отчете
                yearlyReport.outputInfoYearlyReport();


            } else {
                System.out.println("Извините, такой команды пока не существует!");
                System.out.println("Пожалуйста выберите другую команду");
            }
            // Повторная печать меню, перед завершением предыдущего действия
            printMenu();
            // Повторное считывание данных от пользователя
            command = scanner.nextInt();
        }
        System.out.println("Программа завершена");
    }

    // Метод вывода меню
    private static void printMenu() {
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
    }
}

