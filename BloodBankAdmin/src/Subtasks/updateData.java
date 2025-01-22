package Subtasks;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class updateData {
    
    private String oldData="";
    private String newData="";
    
//    public static void main(String[] args) throws IOException {
//        //File f=new File("information.dat");
//        Path path = FileSystems.getDefault().getPath("", "information.dat");
//        List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
//
//        for (int i = 0; i < fileContent.size(); i++) {
//            if (fileContent.get(i).equals("old line")) {
//                fileContent.set(i, "new line");
//                break;
//            }
//        }
//
//        Files.write(path, fileContent, StandardCharsets.UTF_8);
//    }

    public updateData(String oldData,String newData) {
        this.oldData=oldData;
        this.newData=newData;
    }
    
    public void update(){
        
        Path path = FileSystems.getDefault().getPath("", "information.dat");
        List<String> fileContent = null;
        try {
            fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
        } catch (IOException ex) {
            System.out.println("Unable to open file\n" +ex);
        }

        for (int i = 0; i < fileContent.size(); i++) {
            if (fileContent.get(i).equals(oldData)) {
                fileContent.set(i, newData);
                break;
            }
        }

        try {
            Files.write(path, fileContent, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.out.println("Unable to update data\n"+ex);
        }
    }
    
}
