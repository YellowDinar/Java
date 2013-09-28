//  Писал на макбуке, по этому такие пути

import java.io.*;

public class Search {
    public static void showInfo(String dir, String r){
        File f = new File(dir);
        if(f.exists()){
            if(f.isFile()){
                if(f.getPath().contains("." + r)){
                    System.out.println(f.length() + " " + f.getPath());
                }
            }else if(f.isDirectory()){
                String[] s = f.list();
                for(int i = 0;i < f.list().length;i++){
                    String s2 = f.getPath()+ "/" + s[i];
                    showInfo(s2, r);
                }
            }
        }else{
            System.out.println("Directory not found");
        }
    }
    public static void main(String[] args) {
        showInfo("/w", "php");
    }
}
