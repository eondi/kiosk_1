package kiosk;

public class Food extends Menu {

    double price;
    String type;

    int number;

    String option1_name;
    Double option1_price;

    String option2_name;
    Double option2_price;




    public Food(String type, String name, double price, String explan) {
        super(name, explan);
        this.price = price;
        this.type = type;
        this.number = 1;
        this.option1_name = null;
        this.option2_name = null;
    }


    @Override
    public void ShowMenu()
    {
        System.out.println(String.format("%-15s", name)+" | W "+price +" | "+explan);
    }

    public String getMenu( ){
        //int set_plus = number+plus;
        return String.format("%-15s", name)+" | W "+price +" | "+explan;

    }

    public String getMenu(int  number){
        //int set_plus = number+plus;
        return String.format("%-15s", name)+" | W "+price +" | "+number+" 개| "+explan;

    }

    public static  Food copyFood(Food copy){
        Food copy_food = new Food(copy.type,copy.name, copy.price, copy.explan);
        return  copy_food;
    }
}
