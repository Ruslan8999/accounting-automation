import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<MonthlyReport>[] monthlyReports = new ArrayList[12];
        ArrayList<YearlyReport> yearlyReports = new ArrayList<>();
        HashMap<Integer,String> month= initialMonth();

        while (true) {
            System.out.println("Что выберете?");
            System.out.println("1 - Считать все месячные отчёты");
            System.out.println("2 - Считать годовой отчет");
            System.out.println("3 - Сверить отчёты");
            System.out.println("4 - Вывести информацию о всех месячных отчётах");
            System.out.println("5 - Вывести информацию о годовом отчёте");
            System.out.println("Для выхода из приложения введите 13");

            int userInput = scanner.nextInt();

            if (userInput == 1) {
                for (int i = 1; i < 13; i++) {
                    String monthNom;
                    if (i < 10) {
                        monthNom = "0" + i;
                    } else {
                        monthNom = String.valueOf(i);
                    }
                    monthlyReports[i - 1] = MonthlyReportUtils.readMonthReports("resources" + File.separator + "m.2021" + monthNom + ".csv");
                }
            } else if (userInput == 2) {
                yearlyReports = YearlyReportUtils.readYearlyReport("resources" + File.separator + "y.2021.csv");
            } else if (userInput == 3) {
                boolean failFlag = false;
                if (yearlyReports.isEmpty()){
                    System.out.println("Годовой отчет не загружен или пустой");
                } else {
                    for (int i = 1; i < 13; i++) {
                        if (monthlyReports[i - 1] != null) {
                            HashMap<String, Integer> total = MonthlyReportUtils.findGeneralTotalMonth(monthlyReports[i - 1]);
                            if (total.get("expense") != YearlyReportUtils.findTotalAmountYearForMonth(yearlyReports, i, true)) {
                                System.out.println("Данные расхода в месячном отчете за " + month.get(i) + " не соответствуют данным годового отчета");
                                failFlag = true;
                            }
                            if (total.get("income") != YearlyReportUtils.findTotalAmountYearForMonth(yearlyReports, i, false)) {
                                System.out.println("Данные дохода в месячном отчете за " + month.get(i) + " не соответствуют данным годового отчета");
                                failFlag = true;
                            }
                        } else {
                            if (YearlyReportUtils.findTotalAmountYearForMonth(yearlyReports, i, false) != 0 || YearlyReportUtils.findTotalAmountYearForMonth(yearlyReports, i, true) != 0) {
                                System.out.println("Данные в месячном отчете за " + month.get(i) + " не соответствуют данным годового отчета");
                                failFlag = true;
                            }
                        }
                    }
                    if (!failFlag) {
                        System.out.println("Сверка прошла успешно");
                    }
                }
            } else if (userInput == 4) {
                for (int i = 1; i < 13; i++) {
                    System.out.println("Месяц " +month.get(i));
                    if (monthlyReports[i-1]!=null) {
                        MonthlyReportUtils.printReport(monthlyReports[i-1]);
                    } else {
                        System.out.println("Данные за месяц "+month.get(i)+ " не найдены");
                    }
                }
            } else if (userInput == 5) {
                if (yearlyReports.isEmpty()){
                    System.out.println("Годовой отчет не загружен или пустой");
                } else {
                    System.out.println("Отчет за 2021 год:");
                    for (int i = 1; i < 13; i++) {
                        int income = YearlyReportUtils.findTotalAmountYearForMonth(yearlyReports, i, false);
                        int expense = YearlyReportUtils.findTotalAmountYearForMonth(yearlyReports, i, true);
                        if (income != 0 && expense != 0) {
                            System.out.println("Прибыль за " + month.get(i) + " " + (income - expense) + " руб.");
                        }
                    }
                    YearlyReportUtils.averageConsumption(yearlyReports);
                    YearlyReportUtils.averageIncome(yearlyReports);
                }
            } else if (userInput == 13) {
                System.out.println("Выход");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }
    }

    private static HashMap<Integer,String> initialMonth() {
            HashMap<Integer,String> month = new HashMap<>();
            month.put(1,"Январь");
            month.put(2,"Февраль");
            month.put(3,"Март");
            month.put(4,"Апрель");
            month.put(5,"Май");
            month.put(6,"Июнь");
            month.put(7,"Июль");
            month.put(8,"Август");
            month.put(9,"Сентябрь");
            month.put(10,"Октябрь");
            month.put(11,"Ноябрь");
            month.put(12,"Декабрь");
        return month;
    }
}

