package ar.fiuba.tdd.tp.red;

import ar.fiuba.tdd.tp.games.Action;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;

public class Client {

    private Socket serverSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String hostName;
    //private Command command;
    private Response response;
    private boolean connected;

    public Client(String hostName, int portNumber) {
        this.hostName = hostName;
        connected = true;
        try {
            serverSocket = new Socket(this.hostName, portNumber);
            out = new ObjectOutputStream(serverSocket.getOutputStream());
            in = new ObjectInputStream(serverSocket.getInputStream());
        } catch (IOException e) {
            System.err.println("IOException!");
        } catch (NumberFormatException e) {
            System.err.println("Invalid Format!");
        }
    }

    public void run() {
        try {
            receive();
            while (connected) {

                //Command command = this.readCommand();
                //sendCommand(command);

                String rawCommand = this.readRawCommand();
                sendRawCommand(rawCommand);

                receive();
            }
            serverSocket.close();

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
        } catch (Exception e) {
            System.err.println("Couldn't transfer data");
        }
    }

    /* (Nico) No puedo usar CommandInterpreter del lado del cliente porque usa los juegos. Esa logica
     * debe estar del lado del servidor.
    private void sendCommand(Command command) throws Exception {
        this.out.writeObject(command);
    }

    private Command readCommand() throws Exception {
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));
        System.out.print("Type command: ");
        CommandInterpreter interpreter = new CommandInterpreter();
        return interpreter.getCommand(stdIn.readLine());
    }
    */

    private String readRawCommand() throws Exception {
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));
        System.out.print("Type command: ");
        return stdIn.readLine();
    }


    private void sendRawCommand(String string) throws Exception {
        this.out.writeObject(string);
    }

    private void receive() throws Exception {
        response = (Response) in.readObject();
        System.out.println(response.getResponse());
        if (response.isGameFinalized()) {
            connected = false;
        }
    }
}
