package hello.core.singleton;

public class StatefulService {
    //private int price;//상태를 유지하는 필드

    public int order(String name, int price){
        int Price=price;//이렇게 지역변수로 처리해서 특정 client에 의해 값이 변해도 괜찮게 만들자!!
        System.out.println("name=" + name + "price=" + Price);
        return Price;
        //this.price=price;//여기가 문제!
    }

    /*public int getPrice(){
        return price;
    }*/
}
