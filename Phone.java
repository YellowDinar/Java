// 1 и 2 задания
import java.util.regex.*;

public class Phone {
    public static boolean check(String number){
        try{
            Pattern P = Pattern.compile("\\+\\d{1,3}\\s?\\(\\d{2,8}\\)\\s?((\\d{1,6}\\-){2})\\d{1,6}");
            Matcher M = P.matcher(number);
            boolean b = false;
            while(M.find()){
                b = true;
                System.out.println(M.group());
            }
            return b;
        }
        catch (PatternSyntaxException e) {
            System.out.println("Wrong regexp pattern");
            return false;
        }
        
    }
    
    public static String change(String number){
        try{
            Pattern P = Pattern.compile("\\s|\\(|\\)|\\-");
            Matcher M = P.matcher(number);
            boolean b = false;
            if (check(number)){
                while(M.find()){
                number = M.replaceAll("");
                b = true;
                }
                
            }
            else{
              return "wrong number";  
            }
            
            return number;
        }
        catch (PatternSyntaxException e) {
            System.out.println("Wrong regexp pattern");
            return "";
        }
    }
    
    public static void main(String[] args) {
        System.out.println(change("+7(843)521-21-21"));
    }
}

