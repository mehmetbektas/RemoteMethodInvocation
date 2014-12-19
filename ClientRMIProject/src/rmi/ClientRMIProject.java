package rmi;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ClientRMIProject extends JFrame implements ActionListener {
    
    JButton buton;
    Webcam webcam;
    JRadioButton rb1,rb2,rb3,rb4,rb5,rb6,rb7,rb8;
    
    IGoruntuIsleme iGoruntuIsleme;
    
    int port;
    String ip;
    
    public ClientRMIProject() {
        
        ip=getIp();
        port=getPort();
        
        try {
            Registry registry = LocateRegistry.getRegistry(ip,port);
            iGoruntuIsleme = (IGoruntuIsleme) registry.lookup("IGoruntuIsleme");
            
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ClientRMIProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Swing elemanları yükleniyor
        setLayout(new FlowLayout());
        
        
        rb1 = new JRadioButton("Siyah Beyaz");
        rb2 = new JRadioButton("Kırmızı");
        rb3 = new JRadioButton("Yeşil");
        rb4 = new JRadioButton("Mavi");
        rb5 = new JRadioButton("Sarı");
        rb6 = new JRadioButton("Cyan");
        rb7 = new JRadioButton("Eflatun");
        rb8 = new JRadioButton("Negatif");
        
        ButtonGroup rbGrup = new ButtonGroup();
        rbGrup.add(rb1);
        rbGrup.add(rb2);
        rbGrup.add(rb3);
        rbGrup.add(rb4);
        rbGrup.add(rb5);
        rbGrup.add(rb6);
        rbGrup.add(rb7);
        rbGrup.add(rb8);
        rb1.setSelected(true);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(rb1);
        panel.add(rb2);
        panel.add(rb3);
        panel.add(rb4);
        panel.add(rb5);
        panel.add(rb6);
        panel.add(rb7);
        panel.add(rb8);
        
        setTitle("Webcam Resim Çekme Ekranı");
        setSize(450, 320);
        setResizable(false);
        buton = new JButton("Fotoğraf Çek");
        buton.addActionListener(this);
        webcam = Webcam.getDefault();
        webcam.setViewSize(new Dimension(320, 240));
        webcam.open();
        
        add(new WebcamPanel(webcam));
        add(panel);
        add(buton);
        setVisible(true);
    }
    
    private BufferedImage resimGonder(BufferedImage resim, int secim) throws IOException{
        byte[] donenArray = iGoruntuIsleme.isle(resimdenByteArraye(resim), secim);
        BufferedImage donenResim = byteArraydenResime(donenArray);
        return donenResim;
    }
    
    public static void main(String[] args) {
        new ClientRMIProject();
    
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        BufferedImage donenResim = null;
        BufferedImage resim = webcam.getImage();
        if (rb1.isSelected()) {
            try {
                donenResim = resimGonder(resim, 1);
            } catch (IOException ex) {
                Logger.getLogger(ClientRMIProject.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (rb2.isSelected()) {
            try {
                donenResim = resimGonder(resim, 2);
            } catch (IOException ex) {
                Logger.getLogger(ClientRMIProject.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (rb3.isSelected()) {
            try {
                donenResim = resimGonder(resim, 3);
            } catch (IOException ex) {
                Logger.getLogger(ClientRMIProject.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (rb4.isSelected()) {
            try {
                donenResim = resimGonder(resim, 4);
            } catch (IOException ex) {
                Logger.getLogger(ClientRMIProject.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (rb5.isSelected()) {
            try {
                donenResim = resimGonder(resim, 5);
            } catch (IOException ex) {
                Logger.getLogger(ClientRMIProject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (rb6.isSelected()) {
            try {
                donenResim = resimGonder(resim, 6);
            } catch (IOException ex) {
                Logger.getLogger(ClientRMIProject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (rb7.isSelected()) {
            try {
                donenResim = resimGonder(resim, 7);
            } catch (IOException ex) {
                Logger.getLogger(ClientRMIProject.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                donenResim = resimGonder(resim, 8);
            } catch (IOException ex) {
                Logger.getLogger(ClientRMIProject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //işlenen resim yeni pencerede gösteriliyor
        JFrame yeniResimEkrani = new JFrame("İşlenen Resim");
        yeniResimEkrani.setSize(320, 270);
        JLabel label = new JLabel(new ImageIcon(donenResim));
        yeniResimEkrani.add(label);
        yeniResimEkrani.setResizable(false);
        yeniResimEkrani.setVisible(true);
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
    
    private String getIp() {
        return JOptionPane.showInputDialog(this,"Bir IP adresi giriniz:","IP",JOptionPane.PLAIN_MESSAGE);
    }
    private int getPort() {
        return Integer.parseInt(JOptionPane.showInputDialog(this,"Bir port giriniz:","Port",JOptionPane.PLAIN_MESSAGE));
    }
}
