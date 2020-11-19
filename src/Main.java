import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        // default soclet settings in:6666 out:6667
        int portS = 6666;
        int portC = 6667;

        createGui();

        createInputThread(portS);
        createOutputThread(portC);

    }

    private static void createOutputThread(int portC) {
        Thread tin = new Thread(new Runnable() {
            @Override
            public void run() {
        // client

                Socket socket = new Socket("127.0.0.1",portC);

                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                dos.writeUTF("test wyslania");
                dos.flush();
                dos.close();
                socket.close();

            }
        });

        tin.start();
    }


    private static void createInputThread(int portS) {
        // serwer

        ServerSocket serverSocket = new ServerSocket(portS);
        Socket socketServ = serverSocket.accept();
        DataInputStream dis = new DataInputStream(socketServ.getInputStream());
        String str = (String) dis.readUTF();
        System.out.println("wiadomosc test" + str);
        serverSocket.close();



    }

    private static void createGui() {
        //
    }
}
