import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.*;

public class Serialize {
      
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String login;
        
        boolean bb = false;
        while(!bb){
            System.out.println("Input your Login");
            login = sc.nextLine();
            if(valid(login)){
                bb = true;
                if(showInfo("/S/"+login+".txt")){
                    boolean b = false;
                    while(!b){
                        System.out.println("Input your Password");
                        String password = sc.nextLine();
                        User user = (User) Deserialize("/S/"+login+".txt");
                        if(password.equals(user.password)){
                            b = true;
                            SimpleDateFormat sdf = new SimpleDateFormat();
                            System.out.println( sdf.format(user.lastlogintime) );// old date
                            user.lastlogintime = new Date();
                            System.out.println();
                            System.out.println( sdf.format(user.lastlogintime) );// now
                            Serialize(user, "/S/"+login+".txt");
                        }else{
                            System.out.println();
                            System.out.println("You input wrong password!");
                        }
                    }
                }else{
                    boolean b = false;
                    while(!b){
                        System.out.println("Input your Password");
                        String password = sc.nextLine();
                        if(password.length() > 3 && password.length() < 40){
                            b = true;
                            Date date = new Date();
                            SimpleDateFormat sdf = new SimpleDateFormat();
                            System.out.println(sdf.format(date));
                            User user = new User(login, password, date);
                            Serialize(user, "/S/"+login+".txt");
                        }else{
                            System.out.println("Error Password. Password must contain 3 - 40 numbers!");
                        }
                    }
                }
            }
        }
    }
    
    public static void Serialize(Object object, String fileName){
        try{
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream output = new ObjectOutputStream(file);

            output.writeObject(object);
            output.flush();
            output.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static Object Deserialize(String fileName){
        try{
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream object = new ObjectInputStream(file);

            return object.readObject();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public static boolean showInfo(String dir){
        File f = new File(dir);
        boolean ff = false;
        if(f.exists() && f.isFile()){
            ff = true;
        }
        return ff;
    }
    
    public static boolean valid(String login){
        boolean f = false;
        
        Pattern pattern = Pattern.compile("[a-zA-Z\\d]{2,10}");
        Matcher matcher = pattern.matcher(login);

        if(matcher.matches()){
            f = true;
        }else{
            System.out.println("Error Login");
        }
        return f;
    }
}

class User implements Serializable{
    String login;
    String password;
    Date lastlogintime;
    public User(String login, String password, Date lastlogintime){
        this.login = login;
        this.password = password;
        this.lastlogintime = lastlogintime;        
    }
}
