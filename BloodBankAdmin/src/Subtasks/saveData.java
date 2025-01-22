
package Subtasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class saveData {
    String data="";

    public saveData(String s) {
        data=s;
        data+='\n';
        save();
    }
    
    public saveData(){
        
    }
    
    public void putData(String s){
        data=s;
        data+='\n';
        save();
    }
    
    public void save(){
        String name="information.dat";
        File f=new File(name);
        FileOutputStream file=null;
        if(!f.exists()){
            try {
                file=new FileOutputStream(name);
            } catch (FileNotFoundException ex) {
                System.out.println("File not found\n"+ex);
            }
        }else{
            try {
                file=new FileOutputStream(name,true);
            } catch (FileNotFoundException ex) {
                System.out.println("Unable to open file in append mode\n" +ex);
            }
        }
        
        byte b[]=data.getBytes();
        try {
            file.write(b);
            file.write(System.getProperty("line.separator").getBytes());
        } catch (IOException ex) {
            System.out.println("Unable to write data\n"+ex);
        }
        
        try {
            file.close();
        } catch (IOException ex) {
            System.out.println("Unable to clode file");
        }
        
        System.out.println("Data saved successfully...");
    }
}
