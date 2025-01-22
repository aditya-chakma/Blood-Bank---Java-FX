
package Subtasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class matchLogin implements  Runnable{
    String fileName="information.dat";
    String username;
    String password;
    boolean answer=false;
    private String message="";

    public matchLogin(String s, String p) {
        username=s;
        //username.toLowerCase();
        password=p;
    }
    
    public boolean match(){
        File f=new File(fileName);
        Scanner sc=null;
        if(!f.exists()) return false;
        
        //System.out.println(username + " " + password);
        
        try {
            sc=new Scanner(f);
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to read data");
        }
        
        while(sc.hasNext()){
            String s=sc.nextLine();
            
            System.out.println("read line: "+s);
            if(s.length()==0) continue;
            
            String[] data=s.split(":");
            
            //System.out.println("Checking " + data[1] + " " + data[2]);
            
            if(username.equalsIgnoreCase(data[1]))
                if(password.equals(data[2]))
                {
                    System.out.println("read line: "+s);
                    
                    answer=true;
                    message=s;
                    break;
                }
            //System.out.println("Anser: " +username.equalsIgnoreCase(data[1]) + " " + password.equals(data[2]) );
        }
        
        System.out.println(answer);
        return answer;
    }
    
    
    public String get(){
        return message;
    }

    @Override
    public void run() {
        match();
    }
}
