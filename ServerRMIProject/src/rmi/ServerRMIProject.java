/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 *
 * @author cyberuser
 */
public class ServerRMIProject extends JFrame implements IGoruntuIsleme {

    int port;
    
    
    
    public ServerRMIProject() {

        JLabel labelIP = new JLabel("IP Adresiniz: "+getIPNo());
        JLabel labelPort = new JLabel("Girdiğiniz Port: "+getPort());
        setLayout(new FlowLayout());
        add(labelIP);
        add(labelPort);
        setSize(200, 150);
        setVisible(true);
        
        
        
    }
    private String getIPNo() {
        InetAddress inet = null;
        String ip = null;
        try {
            inet = InetAddress.getLocalHost();
            ip = inet.getHostAddress();
        } catch (java.net.UnknownHostException ex) {
            Logger.getLogger(ServerRMIProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ip;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerRMIProject srp = new ServerRMIProject();
        try {
            IGoruntuIsleme iGoruntuIsleme = (IGoruntuIsleme) UnicastRemoteObject.exportObject(srp,2500);
            Registry registry = LocateRegistry.createRegistry(srp.port);
            System.out.println("RMI registry hazır");
            registry.bind("IGoruntuIsleme", iGoruntuIsleme);
            LocateRegistry.getRegistry();
            
        } catch (RemoteException | AlreadyBoundException ex) {
            Logger.getLogger(ServerRMIProject.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    private byte[] resimdenByteArraye(BufferedImage resim) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ImageIO.write(resim, "jpg", baos);
	baos.flush();
	byte[] array = baos.toByteArray();
	baos.close();
        return array;
    }
    private BufferedImage byteArraydenResime(byte[] array) throws IOException{
        InputStream in = new ByteArrayInputStream(array);
	BufferedImage resim = ImageIO.read(in);
        return resim;
    }

    @Override
    public byte[] isle(byte[] gelenArray, int secim) throws RemoteException {
        byte[] donenArray = null;
        try {
            BufferedImage islenecekResim = byteArraydenResime(gelenArray);
            BufferedImage islenenResim = new ResimIsleme(islenecekResim, secim).getResim();
            donenArray = resimdenByteArraye(islenenResim);
            
        } catch (IOException ex) {
            Logger.getLogger(ServerRMIProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return donenArray;
    }
    
    private int getPort() {
        int portAl = Integer.parseInt(JOptionPane.showInputDialog(this,"Bir port giriniz:","Port",JOptionPane.PLAIN_MESSAGE));
        this.port=portAl;
        return portAl;
    }
}
