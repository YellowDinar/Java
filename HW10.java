import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class HW10 {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        String hash = sc.nextLine();
        Numbers n = new Numbers(hash);
        Symbols s = new Symbols(hash);
        NumberAndSymbols nas = new NumberAndSymbols(hash);
        n.start();
        s.start();
        nas.start();
        n.join();
        s.join();
        nas.join();
        if(!n.b && !s.b && !nas.b){
            System.out.println("Error");
        }
    }
    
}

class Numbers extends Thread {
    
    public String hash;
    public boolean b;
    
    public Numbers(String hash){
        this.hash = hash;
        this.b = false;
    }
    
    public void run() {
        
        MD5 md5 = new MD5();
        String[] values={"0","1","2","3","4","5","6","7","8","9"};   
        b = md5.check(values, hash);
    }   
}

class Symbols extends Thread {
    
    public String hash;
    
    public boolean b;
    public Symbols(String hash){
        this.hash = hash;
        this.b = false;
    }
    
    public void run() {
        MD5 md5 = new MD5();     
        String[] values = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        b = md5.check(values, hash);
    }
}

class NumberAndSymbols extends Thread {
    
    public String hash;
    public boolean b;
    public NumberAndSymbols(String hash){
        this.hash = hash;
        this.b = false;
    }
    
    public void run() {
        MD5 md5 = new MD5();
        String[] values = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","0","1","2","3","4","5","6","7","8","9"};
        int len = values.length;
        
        for(int j = 0;j<len && !b;j++){
                for(int k = 0;k<len && !b;k++){
                    for(int n = 0;n<len && !b;n++){
                        String value = values[j]+values[k]+values[n];
                        if(md5.getMD5(value).equals(hash)){
                            System.out.println(value);
                            b = true;
                        }
                    }
                }      
         }
    }
}
class MD5{
    public static String getMD5(String inputString) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(inputString.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean check(String[] values, String hash){
        int len = values.length;
        boolean f = false;
        for(int i = 0;i<len && !f;i++){
            for(int j = 0;j<len && !f;j++){
                for(int k = 0;k<len && !f;k++){
                    for(int n = 0;n<len && !f;n++){
                        String value = values[i]+values[j]+values[k]+values[n];
                        if(getMD5(value).equals(hash)){
                            System.out.println(value);
                            f = true;
                        }
                    }
                }
            }
        }
        return f;
    }
}
