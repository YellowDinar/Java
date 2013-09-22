import java.util.regex.*;
public class Main {
    public static void main(String[] args) {
        money("$45.55"); // вводим число
    }
    public static boolean money(String money){
        try{
            Pattern P = Pattern.compile("\\$|€");
            Matcher M = P.matcher(money);
            boolean b = false;
            double dol = 31.7326;
            double eur = 42.9501;
            if(check(money)){// проверяем на правильно ли ввели
                if(M.find()){
                    char c = M.group().charAt(0);
                    money = M.replaceAll("");
                    money = money.replaceAll(",", ".");
                    double res;
                    if (c == '$'){
                        res = Double.parseDouble(money) * dol;
                    }else{
                        res = Double.parseDouble(money) * eur;
                    }
                    System.out.printf("Р" + "%8.2f", res);           
                    b = true;
                }else{
                    System.out.println(0);    
                }
            }
            return b;
        }catch(PatternSyntaxException e){
            System.out.println("Wrong regexp pattern");
            return false;
        }
    }   
        public static boolean check(String money){// проверяем на правильно ли ввели
        try{
            Pattern P = Pattern.compile("(\\$|€)(\\d*|(\\d*(,|\\.)\\d\\d?))");
            Matcher M = P.matcher(money);
            boolean b = false;
            if(M.matches()){
                b = true;
            }else{
                System.out.println(0);
            }
            return b;
        }catch(PatternSyntaxException e){
            System.out.println("Wrong regexp pattern");
            return false;
        }
    }
}
