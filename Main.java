package kiosk;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 메뉴일람 표시
        Setting set = new Setting();
        set.loadMenu();
        set.display();
    }
}
