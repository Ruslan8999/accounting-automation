import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReportUtils {
    public static ArrayList<MonthlyReport> readMonthReports(String path) {
        ArrayList<MonthlyReport> month = null;
        try {
            String fileContent = Files.readString(Path.of(path));
            if (fileContent != null && !fileContent.isBlank()) {
                month = new ArrayList<>();
                String[] lines = fileContent.split("\n");
                for (int i = 1; i < lines.length; i++) {
                    try {
                        String[] lineContent = lines[i].split(",");
                        String itemName = lineContent[0];
                        boolean expense = Boolean.parseBoolean(lineContent[1]);
                        int quantity = Integer.parseInt(lineContent[2]);
                        int sumOfOne = Integer.parseInt(lineContent[3]);
                        MonthlyReport monthlyReport = new MonthlyReport(itemName, expense, quantity, sumOfOne);
                        month.add(monthlyReport);
                    } catch (Exception e) {
                        System.out.println("Некорректные данные в строке " + lines[i]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл " + path + " с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
        return month;
    }

    public static void printReport(ArrayList<MonthlyReport> month) {

        String maxIncomeItem = "";
        int maxIncome = 0;
        String maxCostItem = "";
        int maxCost = 0;

        for (MonthlyReport monthlyReport : month) {
            int currentCost = monthlyReport.getQuantity() * monthlyReport.getSumOfOne();
            if (monthlyReport.isExpense()) {
                if (currentCost > maxCost) {
                    maxCost = currentCost;
                    maxCostItem = monthlyReport.getItemName();
                }
            } else {
                if (currentCost > maxIncome) {
                    maxIncome = currentCost;
                    maxIncomeItem = monthlyReport.getItemName();
                }
            }
        }
        System.out.println("Самый прибыльный товар - " + maxIncomeItem + ", общая стоимость - " + maxIncome + " руб.");
        System.out.println("Самая большая трата - " + maxCostItem + ", общая стоимость - " + maxCost + " руб.");
    }

    public static HashMap<String,Integer> findGeneralTotalMonth(ArrayList<MonthlyReport> month) {
        int sumIncome = 0;
        int sumExpense=0;
        for (MonthlyReport monthlyReport : month) {
            int currentCost = monthlyReport.getQuantity() * monthlyReport.getSumOfOne();
            if (!monthlyReport.isExpense()) {
                sumIncome += currentCost;
            } else {
                sumExpense+=currentCost;
            }
        }
        HashMap<String,Integer> total = new HashMap<>();
        total.put("expense",sumExpense);
        total.put("income",sumIncome);
        return total;
    }
}