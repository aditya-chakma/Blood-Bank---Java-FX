/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subtasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class setNotice {
    
    private String notice;
    private final String fileName="notice.dat";

    public setNotice(String notice) {
        this.notice=notice;
    }
    
    public boolean set(){
        
        File f=new File(fileName);
        
        if(f.exists()){
            try {
                FileOutputStream os=new FileOutputStream(f);
                byte[] b=notice.getBytes();
                
                try {
                    os.write(b);
                    os.flush();
                    os.close();
                } catch (IOException ex) {
                    System.out.println("Can't write file(setNotice.java)\n" + ex);
                    return false;
                }
                
            } catch (FileNotFoundException ex) {
                System.out.println("File not found!(setNotice.java)\n" + ex);
                return false;
            }
            return true;
        }
        return false;
    }
    
}
