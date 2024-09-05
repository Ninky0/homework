package practice.day9;

public interface accountBook {
    void putRightDate();
    void printMenu();
    void addItem();
    void addHistory(String name, int price);
    void displayAll();
    void displayDetail();
    void deleteItem();
    void deleteAll();
}
