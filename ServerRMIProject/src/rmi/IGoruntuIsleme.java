/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author mehmet
 */
public interface IGoruntuIsleme extends Remote {
    byte[] isle(byte[] gelenArray, int secim) throws RemoteException;
}
