/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatapp.networking;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Queue;

/**
 *
 * @author dell
 */
public class DataStore {
    String fileName;
    File file = new File("data.txt");
    
    DataStore(String fileName){this.fileName=fileName;
        
    }
    DataStore(){}
    void addToFile( Queue <Double> q) throws IOException{
            
            if(!file.exists())file.createNewFile();
            try(PrintWriter pw =new PrintWriter(file)){
            pw.append(q.toString());
           
            }catch(IOException e){e.printStackTrace();}
  
            System.out.println("Your Cache data consumed !!,Your Data Moved to the File");
            
}
}