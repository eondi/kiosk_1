package kiosk;


import java.util.ArrayList;
import java.util.HashMap;

public class Menu {
    //필드
    String name;
    String explan;

    // 생성자
    public Menu(String name, String explan){
        this.name = name;
        this.explan = explan;
    }

    void ShowMenu(){
        System.out.println(String.format("%-15s", name)+" | "+explan);
    }

}
