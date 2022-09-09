import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class YearlyReportUtils {
    public static ArrayList<YearlyReport> readYearlyReport (String path) {
        ArrayList<YearlyReport> year = null;
        try {
            String fileContent =  Files.readString(Path.of(path));
            if (fileContent!=null&&!fileContent.isBlank()) {
                year = new ArrayList<>();
                String[] lines = fileContent.split("\n");
                for (int i = 1; i < lines.length; i++) {
                    try {
                        String[] lineContent = lines[i].split(",");
                        int month = Integer.parseInt(lineContent[0]);
                        int amount = Integer.parseInt(lineContent[1]);
                        boolean isExpense = Boolean.valueOf(lineContent[2]);
                        YearlyReport yearlyReport = new YearlyReport(month, amount, isExpense);
                        year.add(yearlyReport);
                    } catch (Exception e) {
                        System.out.println("Некорректные данные в строке " + lines[i]);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
        return year;
    }

    public static int findTotalAmountYearForMonth (ArrayList<YearlyReport> year, int month, boolean isExpense) {

        for(YearlyReport yearlyReport: year){
            if (yearlyReport.getMonth()==month && yearlyReport.isExpense()==isExpense){
                return yearlyReport.getAmount();
            }
        }
        return 0;
       }
    public static void averageConsumption (ArrayList<YearlyReport> year){
        int averageConsumption=0;
        for (YearlyReport yearlyReport: year){
            if (yearlyReport.isExpense()){
                averageConsumption += yearlyReport.getAmount();
            }
        }
        averageConsumption = averageConsumption / year.size()*2;
        System.out.println("Средний расход за все месяцы в году: "+averageConsumption);
    }

    public static void averageIncome (ArrayList<YearlyReport> year){
        int averageIncome=0;
        for (YearlyReport yearlyReport: year){
            if (!yearlyReport.isExpense()){
                averageIncome += yearlyReport.getAmount();
            }
        }
        averageIncome = averageIncome / year.size()*2;
        System.out.println("Средний доход за все месяцы в году: "+averageIncome);
    }

}

