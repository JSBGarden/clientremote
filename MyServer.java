
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import sun.awt.X11.XConstants;

public class MyServer {
    
    public static void direction(int a){
  try {
            
            Robot robot = new Robot();
            robot.keyPress(a);
            robot.keyRelease(a);            
            
        } catch (AWTException e) {
            e.printStackTrace();
        }
 
}

public static void main(String[] args) throws IOException {
Socket clientSocket = null;
ServerSocket serverSocket = null;
try{
    serverSocket = new ServerSocket(8081);
    System.out.println("server started....");
    while (true){ 
    System.out.println("WATING FOR CLIENT ");
    clientSocket = serverSocket.accept();
    System.out.println("CLIENT FOUND");

//BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputS­tream()));
Scanner in1 = new Scanner(clientSocket.getInputStream());
String mes; 

 while(in1.hasNext()){

        mes=in1.nextLine();
        if (mes.contentEquals("v")){
            MyServer.direction(KeyEvent.VK_DOWN);
        }
        else if (mes.contentEquals("^")){
            MyServer.direction(KeyEvent.VK_UP);
        }
        else if (mes.contentEquals("<")){
            MyServer.direction(KeyEvent.VK_LEFT);
        }
        else if (mes.contentEquals(">")){
            MyServer.direction(KeyEvent.VK_RIGHT);
        }
        System.out.println("Client message :"+mes);

  }
    }
}catch(Exception e){} //read & display the message
}

}


