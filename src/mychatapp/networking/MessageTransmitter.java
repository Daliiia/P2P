/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatapp.networking;

import java.io.IOException;
import java.net.Socket;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Reem Tarek
 */
public class MessageTransmitter extends Thread{
   
    String message="hi", hostname;
    int port;
    
    public MessageTransmitter(){}
    
    public MessageTransmitter( String hostname, int port)
    {
        
        this.hostname=hostname;
        this.port=port;
    }
    @Override
    public void run()
    {
        try {
            senddata();
        } catch (IOException ex) {
            Logger.getLogger(MessageTransmitter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(MessageTransmitter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public synchronized void senddata() throws IOException, InterruptedException
    {
    CircuitBreaker cb = new CircuitBreaker();
    cb.transaction(hostname, port);

    }
    
}
