
package Subtasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class districts {
    
    public static void main(String[] args) {
        districts d=new districts("Dhaka.txt");
        d.getValue();
    }

    String name="Dhaka.txt";
    
    public districts(String str){
        name=str;
        
    }
    
    public String[] getValue(){
       File f=new File(name);
       
       if(f.exists())
       {
           try {
               Scanner sc=new Scanner(f);
               
               String s="";
               while(sc.hasNext()) /*s+=sc.nextLine()+":";*/
               {
                   s+=sc.nextLine()+":";
                   
               }
               System.out.println(s);
               String[] ans=s.split(":");
               return ans;
           } catch (FileNotFoundException ex) {
               System.out.println("File not found");
            }
       
       
       }
       return null;
    }
}
