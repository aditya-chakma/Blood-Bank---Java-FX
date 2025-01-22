
package Subtasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class matchLogin {
    String fileName="information.dat";
    String username;
    String password;
    boolean answer=false;

    public matchLogin(String s, String p) {
        username=s;
        username.toLowerCase();
        password=p;
    }
    
    public boolean match(){
        File f=new File(fileName);
        Scanner sc=null;
        if(!f.exists()) return false;
        
        try {
            sc=new Scanner(f);
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to read data");
        }
        
        while(sc.hasNext()){
            String s=sc.nextLine();
            String[] data=s.split(":");
            if(username.equals(data[0].toLowerCase()))
                if(password.equals(data[1])) answer=true;
        }
        
        return answer;
    }
}
