public class MonthlyReport {
    private final String itemName;
    private final boolean expense;
    private final int quantity;
    private final int sumOfOne;

    MonthlyReport(String itemName,boolean expense,int quantity,int sumOfOne) {
        this.itemName=itemName;
        this.expense=expense;
        this.quantity=quantity;
        this.sumOfOne=sumOfOne;
    }

    public String getItemName() {
        return itemName;
    }
    public boolean isExpense() {
        return expense;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getSumOfOne() {
        return sumOfOne;
    }
}
