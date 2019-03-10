/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatapp.networking;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author dell
 */
public class QueueModel {
    Queue <Double> queue;
    int MaxSize=10;
    int halfSize=5;
    QueueModel(){
        queue=new PriorityQueue <Double>(); 
       
    }
    void add(double dbl) throws IOException{
        if(queue.size()<MaxSize) 
            queue.add(dbl);
        else {
            MoveAndClear();
        }
        if(queue.size()==halfSize)
            System.out.println("you consumed half of your memory size !! ");
        
    }
    void MoveAndClear() throws IOException{
       
         DataStore data=new DataStore();
         data.addToFile(queue);
         queue.clear();
         
    }

}
