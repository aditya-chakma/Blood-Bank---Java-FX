
package Subtasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class sendDonor implements Runnable{
    
    String data;

    public sendDonor(String data) {
        this.data=data;
    }
    
    

    @Override
    public void run() {
        String[] splited=data.split(":");
        //submit:name,bloodGroup,amount,time,city,email
        File file=new File("information.dat");
        Scanner sc;
        try {
            sc=new Scanner(file);
            while(sc.hasNext()){
                String s=sc.nextLine();
                //signUp:aditya29:12345:adirya_chakma199@yahoo.com:A+:Dhaka:palashi:01521429165

                if(s.length()==0) continue;
                
                String[] don=s.split(":");
                if(splited[2].equals(don[4]))
                {
                    if(splited[5].equals(don[5]))
                    {
                        Thread t=new Thread(new sendMail(don[3], don[1], splited[7], 1, splited[4]));
                        t.start();
                    }
                }
            }
            
            System.out.println("Sending data to everyone :D");
            
        } catch (FileNotFoundException ex) {
            
        }
        
        
    }
    
}
