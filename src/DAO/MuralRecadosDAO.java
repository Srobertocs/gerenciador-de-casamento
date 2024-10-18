/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import beans.Usuario;
import beans.Recados;

/**
 *
 * @author SOUSA
 */
public class MuralRecadosDAO {

    Recados[] recados = new Recados[20];

    public boolean postarRecado(Recados recado, Usuario usuario) {

        for (int i = 0; i < 20; i++) {

            if (this.recados[i] == null) {

                recado.setUsuario(usuario);
                this.recados[i] = recado;

                return true;
            }
        }
        return false;
    }

    public String mostrarMuralRecados() {
        
        boolean vazio = true;

        String texto = "MURAL DE RECADOS";

        for (int i = 0; i < 20; i++) {

            if (this.recados[i] != null) {
                texto += "\n\n" + recados[i].toString();
                vazio = false;
            }
        }
        if (vazio == true) {
            return null;

        } else {
            return texto;
        }
    }
}
