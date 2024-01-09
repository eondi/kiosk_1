package kiosk;

import java.util.*;

public class Setting {
    Scanner sc = new Scanner(System.in);
    Map<Integer, String> menu_num = new HashMap<>();
    ArrayList<Menu> menu_list = new ArrayList<Menu>();
    ArrayList<Food> food_list = new ArrayList<Food>();
    HashMap<String, Double> food_price_map = new HashMap<String, Double>();


    ArrayList<String> list = new ArrayList<String>();

    HashMap<String, Integer> food_count_map = new HashMap<String, Integer>();

    boolean option_flg = false;

    public void loadMenu(){
    Menu  buger = new Menu("Burgers", "앵거스 비프 통살을 다져만든 버거");
    Menu  SIDE = new Menu("Sides", "매장에서 직접 튀기는 감자튀김");
    Menu  Drinks = new Menu("Drinks", "매장에서 직접 만드는 음료");
    Menu  Beer = new Menu("Beer", "뉴욕 브루클린 브루어리에서 양조한 맥주");
    menu_list.add(buger);
    menu_list.add(SIDE);
    menu_list.add(Drinks);
    menu_list.add(Beer);

    Food shack_burger = new Food("Burgers", "shack_burger", 6.9,"토마토, 양상추, 쉑소스가 토핑된 치즈버거");
    Food SmokeShack = new Food("Burgers", "SmokeShack", 8.9,"베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거");
    Food ShroomBurger = new Food("Burgers", "Shroom Burger", 9.4,"몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거");
    Food CheeseBurger = new Food("Burgers", "Cheese Burger", 6.9,"포테이토 번과 비프패티, 치즈가 토핑된 치즈버거");
    Food Hamburger = new Food("Burgers", "Hamburger", 5.4,"비프패티를 기반으로 야채가 들어간 기본버거");
    food_list.add(shack_burger);
    food_list.add(SmokeShack);
    food_list.add(ShroomBurger);
    food_list.add(CheeseBurger);
    food_list.add(Hamburger);
    Food cherry_papper_cheese = new Food("Sides", "Cherry Papper Cheese", 5.4,"매콤한 체리 페퍼와 고소한 치즈 소스가 어우러진 프라이");
    food_list.add(cherry_papper_cheese);
    }

    public void   display() throws InterruptedException {
        //메인 메뉴판 화면
        int cnt = showMenu();
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        // 총 판매상품 목록
        if (num == 0) {
            AllfoodScreen();

        }
        if (num <= menu_list.size()){
            //상품 메뉴판 화면
            String menu_name = menu_num.get(num);
            int cnt2 = showDetail(menu_name);
        } else if (num == menu_list.size() + 1) {
            //주문 화면
            ReceiptScreen();
        } else if (num == menu_list.size() + 2) {
            calcelScreen();
        }
    }

    // 초기메뉴 표시
    public int showMenu(){

        int cnt = 1;
        System.out.println("SHAKESHACK BURGER 에 오신걸 환영합니다.");
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
        System.out.println("[ SHAKESHACK MENU ]");
        for (Menu i : menu_list){
            menu_num.put(cnt, i.name);
            System.out.print(cnt + ". ");
            i.ShowMenu();
            cnt++;
        }
        System.out.println();
        System.out.println("[ ORDER MENU ]");
        System.out.println(cnt + ". "+ String.format("%-15s", "Order")+" | "+"장바구니를 확인 후 주문합니다.");
        System.out.println((cnt +1) + ". "+ String.format("%-15s", "Cancel")+" | "+"진행중인 주문을 취소합니다.");
        return  cnt;
    }

    public int showDetail(String menu_name) throws InterruptedException {

        HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();
        Map<Integer, Double> PriceMap = new HashMap<>();

        int cnt = 1;
        System.out.println("SHAKESHACK BURGER 에 오신걸 환영합니다.");
        System.out.println("아래 상품메뉴판을 보시고 상품을 골라 입력해주세요.");
        System.out.printf("[ %s MENU ]\n",menu_name);

        if (Objects.equals(menu_name, "Burgers") || Objects.equals(menu_name, "Drinks")){
            option_flg = true;
        }

        for (Food i : food_list){
            if(Objects.equals(i.type,menu_name)) {
                if (i.option1_name != null){
//                    food_list.remove(i);
                    break;
                }
                ArrayList<String> list = new ArrayList<String>();
                list.add(i.name);
                list.add(i.getMenu());
                map.put(cnt,list);
                PriceMap.put(cnt,i.price);
                System.out.print(cnt + ". ");
                i.ShowMenu();
                cnt++;
            }
        }
        int FoodNum = sc.nextInt();
        double food_price = PriceMap.get(FoodNum);

        // 구매 화면
        OrderScreen(FoodNum, map, food_price, menu_name);


        return  FoodNum;
    }

    public void  OrderScreen(int FoodNum, HashMap<Integer, ArrayList<String>> map, double food_price, String menu_name) throws InterruptedException {


        String OrderFood = map.get(FoodNum).get(1);
        String order_name =  map.get(FoodNum).get(0);
        System.out.println(OrderFood);
        //옵션화면
        if (option_flg){
            Food option_food = OpstionScreen(OrderFood, order_name);
            order_name = option_food.name;
            food_price = option_food.price;
        }

        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");

        int order_num = sc.nextInt();
        if (order_num == 1) {
            System.out.printf(" %s 가 장바구니에 추가되었습니다.\n", order_name);

            food_price_map.put(order_name, food_price);

            if( food_count_map.containsKey(order_name)){
                food_count_map.put(order_name,food_count_map.get(order_name) + 1);
            }
            else {
                food_count_map.put(order_name,1);
            }

        } else{
            System.out.println("진행하던 주문이 취소되었습니다.");

        }
        System.out.println();
        display();
    }

    public void  ReceiptScreen() throws InterruptedException {
        int wait = 1;
        double total = 0;

        System.out.println("아래와 같이 주문 하시겠습니까?");
        System.out.println("[ Orders ]");

        for ( Food i : food_list){
            if (food_count_map.containsKey(i.name)){
                System.out.println(i.getMenu(food_count_map.get(i.name)));
                total +=  i.price * food_count_map.get(i.name);
            }
        }




        System.out.println("[ Total ]");
        System.out.println("W " + total);


        System.out.println("1. 주문      2. 메뉴판");

        int order_num = sc.nextInt();
        if (order_num == 1) {
            System.out.println("주문이 완료되었습니다! ");
            System.out.println( String.format("대기번호는 [%d] 번 입니다.", wait));
            System.out.println("(3초후 메뉴판으로 돌아갑니다.) ");
            // 장바구니 초기화
            food_count_map.clear();
            Thread.sleep(3000);

        }
        System.out.println();
        display();
    }
    public void  calcelScreen() throws InterruptedException {

        System.out.println("진행하던 주문을 취소하시겠습니까?");
        System.out.println("1. 확인        2. 취소");

        int order_num = sc.nextInt();
        if (order_num == 1) {
            System.out.println("진행하던 주문이 취소되었습니다.");
            // 장바구니 초기화
            food_count_map.clear();

        }
        System.out.println();
        display();
    }

    public void  AllfoodScreen() throws InterruptedException {

        System.out.println("[ 총 판매금액 현황 ]");
        System.out.println("현재까지 총 판매된 상품 목록은 아래와 같습니다.");
        // 판매 상품 목록 표시
        for (String key : food_price_map.keySet()){
            System.out.printf("- %-20s | W %f\n",key,food_price_map.get(key));
        }

        // 총 판맥 상품액 표시
        double sum = 0;
        for ( String i : food_count_map.keySet()){
            sum += food_price_map.get(i) * food_count_map.get(i);
        }
        System.out.printf("현재까지 총 판매된 금액은 [ W  %f ] 입니다.\n", sum);
        System.out.println();

        System.out.println("1. 돌아가기");
        int num = sc.nextInt();

        if (num == 1){
            display();
        }


    }
    public Food  OpstionScreen(String OrderFood, String order_name)  {
        option_flg = false;
        HashMap<Integer, ArrayList<String>> option_map = new HashMap<Integer, ArrayList<String>>();

        ArrayList<String> list = new ArrayList<String>();


        System.out.println("위 메뉴의 어떤 옵션으로 추가하시겠습니까?");
        Food option_food = null;
        for (Food i : food_list){
            if (Objects.equals(i.name, order_name)){
                option_food = i.copyFood(i);
            }
        }
        switch (option_food.type){
            case "Burgers":
                option_food.option1_name = "Single";
                option_food.option1_price = 5.4;

                option_food.option2_name = "Double";
                option_food.option2_price = 9.0;
                break;
            case "Drinks":
                option_food.option1_name = "Double Shot";
                option_food.option1_price = 3.0;

                option_food.option2_name = "Cool";
                option_food.option2_price = 2.0;
                break;
            default: break;
        }

        System.out.printf("1. %s(W %f)        2. %s(W %f)\n",option_food.option1_name,option_food.option1_price,option_food.option2_name,option_food.option2_price);

        int option_num = sc.nextInt();

        if (option_num == 1){
            option_food.name = option_food.name + String.format("(%s)", option_food.option1_name);
            option_food.price = option_food.price + option_food.option1_price;
        } else if (option_num == 2) {
            option_food.name = option_food.name + String.format("(%s)", option_food.option2_name);
            option_food.price = option_food.price + option_food.option2_price;
        }

        option_food.ShowMenu();

        // 리스트 컴플리헨션.. 없낭 나중에 찾기
        for (Food i :food_list){
            list.add(i.name);
        }
        if (!(list.contains(option_food.name))){
            food_list.add(option_food);
        }


        return  option_food;




    }
}
