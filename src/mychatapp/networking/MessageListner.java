/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatapp.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Reem Tarek
 */
public class MessageListner extends Thread {
 ServerSocket server;
 int port=8877;
 WritableGUI gui; 
 public MessageListner(WritableGUI gui ,int port) {
 this.port=port;
 this.gui=gui;
     try {
         server=new ServerSocket(port);
     } catch (IOException ex) {
         Logger.getLogger(MessageListner.class.getName()).log(Level.SEVERE, null, ex);
     }
}
 public MessageListner(){
     try {
         server=new ServerSocket(port);
     } catch (IOException ex) {
         Logger.getLogger(MessageListner.class.getName()).log(Level.SEVERE, null, ex);
     }
}
 @Override
 public void run()
 {
     try {
         Socket ClientSocket;
         if((ClientSocket = server.accept()) != null)
         { int foundpeer = Notify();
          System.out.println("found peer port number "+foundpeer+" to communicate");
          DataStore d=new DataStore(String.valueOf(foundpeer));
         }
         
         while((ClientSocket = server.accept()) != null)
         {
         
          InputStream is = ClientSocket.getInputStream();
          BufferedReader br =new BufferedReader(new InputStreamReader(is));
          String Line = br.readLine();
          if(Line != null)
          {
              gui.write(Line);
          }
            
         }} catch (IOException ex) {
            // System.out.println("done connection");
         Logger.getLogger(MessageListner.class.getName()).log(Level.SEVERE, null, ex);
     }
    
 }
 private int Notify()
 {
    return server.getLocalPort();
 }
 
}
