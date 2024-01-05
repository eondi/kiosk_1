package kiosk;

public class Food extends Menu {

    double price;
    String type;


    public Food(String type, String name, double price, String explan) {
        super(name, explan);
        this.price = price;
        this.type = type;
    }

    @Override
    public void ShowMenu()
    {
        System.out.println(String.format("%-15s", name)+" | W "+price +" | "+explan);
    }

    public  String getMenu(){
        return String.format("%-15s", name)+" | W "+price +" | "+explan;
    }
}
