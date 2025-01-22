package Admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AdminControl {
    
    String data;
    String filename="Admin.dat";
    
    public static void main(String[] args) {
        
    }
    
    public boolean AdminLogin(String username,String password)
    {
        File f=new File(filename);
        
        try {
            Scanner sc=new Scanner(f);
            while(sc.hasNext())
            {
                String s=sc.nextLine();
                if(s.length()==0) continue;
                String[] splited=s.split(":");
                
                if(splited[0].equalsIgnoreCase(username) && splited[1].equals(password)) return true;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found (AdminControl.java)\n"+ex);
            return false;
        }
        
        return false;
    }
}
