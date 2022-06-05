package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance= new SingletonService();

    //오직 이 함수만으로 instance에 접근가능
    public static SingletonService getInstance(){
        return  instance;
    }

    //생성자를 private으로 선언해서 new 키워드를 사용한 객체 생성을 못하게 막는다(싱글톤 구현)
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
