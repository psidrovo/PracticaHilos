/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicahilos;

import java.awt.Color;
import java.util.List;
import java.util.Random;
import javax.swing.JLabel;

/**
 *
 * @author Paul Idrovo
 */
public class FilosofoHilo extends Thread {

    private final int numero;
    private int segundoCubierto;
    private boolean estado;
    private boolean pensando;
    private List<Cubierto> cubiertos;
    private List<JLabel> imagenesCubiertos;
    private List<JLabel> imagenesFilosofos;

    public FilosofoHilo(int numero, List<Cubierto> cubiertos, List<JLabel> imagenesCubiertos, List<JLabel> imagenesFilosofos, int segundoCubierto) {
        this.numero = numero;
        this.segundoCubierto = segundoCubierto;
        this.estado = true;
        this.pensando = true;
        this.cubiertos = cubiertos;
        this.imagenesCubiertos = imagenesCubiertos;
        this.imagenesFilosofos = imagenesFilosofos;
    }

    @Override
    public void run() {
        esperarXsegundos();
        while (true) {
            //System.out.println(numero + " - " + (cubiertos.size() - 1));.
            if (pensando) {
                if (estado) {
                    if (numero == 0) {
                        estadosCubiertosFilosofos(numero, cubiertos.size() - 1);
                        if (!estado && !cubiertos.get(numero).isEstado() && !cubiertos.get(cubiertos.size() - 1).isEstado()) {
                            esperarXsegundos();
                        }
                    } else {
                        estadosCubiertosFilosofos(numero, numero - 1);
                        if (!estado && !cubiertos.get(numero).isEstado() && !cubiertos.get(numero - 1).isEstado()) {
                            esperarXsegundos();
                        }
                    }
                } else {
                    if (numero == 0) {
                        pensar(numero, cubiertos.size() - 1);
                    } else {
                        pensar(numero, numero - 1);
                    }
                }
            }
        }
    }

    public synchronized void estadosCubiertosFilosofos(int i, int posicion) {
        if (estado && cubiertos.get(i).isEstado() && cubiertos.get(posicion).isEstado()) {
            estado = false;
            cubiertos.get(i).setEstado(false);
            cubiertos.get(posicion).setEstado(false);
            imagenesFilosofos.get(i).setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(255, 0, 51)));
            imagenesCubiertos.get(i).setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(255, 0, 51)));
            imagenesCubiertos.get(posicion).setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(255, 0, 51)));
            //System.out.println(imagenesCubiertos.get(i).getText() + " --- " + imagenesCubiertos.get(posicion).getText() + " --- " + imagenesFilosofos.get(i).getText());            
            System.out.println("FILOSOFO " + (i + 1) + " Estado = Comiendo ");
            //esperarXsegundos();
        }
    }

    public void pensar(int i, int posicion) {
        pensando = false;
        imagenesFilosofos.get(numero).setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(255, 204, 0)));
        imagenesCubiertos.get(numero).setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(51, 255, 0)));
        imagenesCubiertos.get(posicion).setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(51, 255, 0)));
        cubiertos.get(i).setEstado(true);
        cubiertos.get(posicion).setEstado(true);
        System.out.println("FILOSOFO " + (i + 1) + " Estado = PENSANDO ");
        esperarXsegundos();
        pensando =true;
        estado = true;        
        imagenesFilosofos.get(numero).setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(51, 255, 0)));
    }

    private void esperarXsegundos() {
        try {
            int tiempo = (int) (Math.random() * 4000) + 1000;
            Thread.sleep(tiempo);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
