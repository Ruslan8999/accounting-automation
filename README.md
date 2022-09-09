## My third project "Accounting automation"
#### This is a task from the real world of development

My application provides **the following functionality**:
1. Have a console interface for managing the program..
2. Read monthly and annual accounting reports from CSV files and bring them into application objects.
3. Perform reconciliation of monthly and annual reports.
4. Display information about monthly and annual reports.
____
The application was written in Java. Code example:

```java
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<MonthlyReport>[] monthlyReports = new ArrayList[12];
        ArrayList<YearlyReport> yearlyReports = new ArrayList<>();
        HashMap<Integer,String> month= initialMonth();
        ...
    }
}
``` 
