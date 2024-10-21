/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import beans.Usuario;
import beans.Recados;
import util.Datas;

/**
 *
 * @author SOUSA
 */
public class MuralRecadosDAO {

    Recados[] recados = new Recados[20];

    public MuralRecadosDAO(UsuarioDAO usuario) {

        Recados recado01 = new Recados();
        recado01.setRecado("Fiquem livres para comentarem o que quiser aqui.");
        recado01.setDataCriacao(Datas.pegaDataAgora());
        recado01.setDataModificacao(Datas.pegaDataAgora());
        this.postarMuralRecados(recado01, usuario.consultaUsuario("silvio"));
        
        Recados recado02 = new Recados();
        recado02.setRecado("Esperamos que gostem da cerimonia");
        recado02.setDataCriacao(Datas.pegaDataAgora());
        recado02.setDataModificacao(Datas.pegaDataAgora());
        this.postarMuralRecados(recado02, usuario.consultaUsuario("bruna"));
    }

    public boolean postarMuralRecados(Recados recado, Usuario usuario) {

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

    public String consultarMuralRecados(Usuario usuario) {
        int aux = 0;
        String recados = "RECADOS ENVIADOS POR: " + usuario.getLogin();

        for (int i = 0; i < 20; i++) {
            if (this.recados[i] != null && this.recados[i].getUsuario().equals(usuario)) {

                recados += "\n\n" + this.recados[i].toString();
                aux++;
            }
        }
        if (aux == 0) {
            return null;
        } else {
            return recados;
        }
    }

    public long validaCodigoRecado(long codigoRecado) {

        for (int i = 0; i < 20; i++) {
            if (this.recados[i] != null && this.recados[i].getId() == codigoRecado) {
                return codigoRecado;
            }
        }
        return 0;
    }

    public boolean validaUsuarioRecado(Usuario usuarioLogado, long codigo) {

        for (int i = 0; i < 20; i++) {
            if (this.recados[i] != null ) {
                if(this.recados[i].getUsuario().getLogin().equals(usuarioLogado.getLogin()) && this.recados[i].getId() == codigo){
                      return true;
                }
            }
        }
        return false;
    }

    public boolean editarComentarioMuralRecados(long codigoRecado, String novoRecado) {

        for (int i = 0; i < 20; i++) {
            if (this.recados[i] != null && this.recados[i].getId() == codigoRecado) {
                this.recados[i].setRecado(novoRecado);
                this.recados[i].setDataModificacao(Datas.pegaDataAgora());
                return true;
            }
        }
        return false;
    }
}
