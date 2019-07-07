import com.devpail.reflect.ClassUtil;

public class Main {

    public static void main(String[] args) {
        String s = "Hello World!";
        System.out.println(s);
        ClassUtil.printClassMessage(s);

        Integer n1 = 1 ;
        ClassUtil.printClassMessage(n1);

    }
}
