/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicahilos;

/**
 *
 * @author Paul Idrovo
 */
public class Cubierto {
    private boolean estado;

    public Cubierto() {
    }

    public Cubierto(boolean estado) {
        this.estado = estado;
    }
    
    
    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
