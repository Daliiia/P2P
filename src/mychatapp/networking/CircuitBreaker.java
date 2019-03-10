/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatapp.networking;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Reem Tarek
 */
public class CircuitBreaker {
    private static int count=0;
    private static boolean open=false;
    private static boolean closed = true;
    private static boolean halfopen = false;
   
    private int maxcount = 20;
    public synchronized void transaction(String hostname, int port) throws IOException, InterruptedException
    {    QueueModel q=new QueueModel();
        FakeData fd = new FakeData();
        while (true) {try (Socket s = new Socket(hostname, port)) {
            if(count <=( maxcount/2))
            {double random = fd.FakeDataGenerator();
            
            String   message="sending fake data " + random+"";
            q.add(random);
            s.getOutputStream().write(message.getBytes());
            
            System.out.println("closed");
			try {
				this.wait(2000);
                        
			} catch (InterruptedException e) {
 
				e.printStackTrace();
			}
                        count++;
                       
            }
        else if((count > (maxcount/2)) &&( count < maxcount))
                {System.out.println("half open");
                double random = fd.FakeDataGenerator();
                String   message="sending fake data " + random+"";
                s.getOutputStream().write(message.getBytes());
                q.add(random);
                try{ this.wait(4000);}
                catch (InterruptedException e) {
                    
                    e.printStackTrace();
                }
                    count++;
                }
        else if(count == maxcount)
        {System.out.println("reset, open");
            reset();
        }
            
                       
                //         System.out.println(msg);
		}
        }
        
    }
    
    private synchronized void reset() throws InterruptedException{
        count=0;
        try
        {
            this.wait(7000);
        }
        catch (InterruptedException e) {
                        
                        e.printStackTrace();
        }
    }
  
}
