/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaocasamento;

/**
 *
 * @author SOUSA
 */
public class UsuarioDAO {

    GUI gui = new GUI();
    Usuario[] usuario = new Usuario[40];

    public UsuarioDAO(PessoaDAO pessoaDAO) {

        Usuario usuario01 = new Usuario();
        usuario01.setLogin("silvio");
        usuario01.setSenha("123");
        usuario01.setTipo("Noivo");
        usuario01.setDataCriacao(Datas.pegaDataAgora());
        usuario01.setDataModificacao(Datas.pegaDataAgora());
        adicionaUsuario(usuario01, pessoaDAO.pegaPessoa(usuario01.getLogin()));

        Usuario usuario02 = new Usuario();
        usuario02.setLogin("bruna");
        usuario02.setSenha("789");
        usuario02.setTipo("Noiva");
        usuario02.setDataCriacao(Datas.pegaDataAgora());
        usuario02.setDataModificacao(Datas.pegaDataAgora());
        adicionaUsuario(usuario02, pessoaDAO.pegaPessoa(usuario02.getLogin()));
    }

    public boolean adicionaUsuario(Usuario novoUsuario, Pessoa pessoa) {

        if (pessoa == null) {
            Usuario.setCount();
            return false;
        } else {
            for (int i = 0; i < 40; i++) {

                if (usuario[i] != null) {

                    if (usuario[i].getLogin().equals(novoUsuario.getLogin())) {
                        gui.exibirMensagemUsuarioExistente();
                        Usuario.setCount();
                        return false;

                    } else if (validaTipo(this.usuario[i].getTipo(), novoUsuario.getTipo()) == false) {
                        Usuario.setCount();
                        return false;
                    }
                }
                if (this.usuario[i] == null) {

                    novoUsuario.setPessoa(pessoa);
                    usuario[i] = novoUsuario;
                    return true;

                }
            }
        }

        gui.exibirMensagemListaUsuarioLotada();
        return false;
    }

    public boolean validaTipo(String tipo, String novoTipo) {

        if (tipo.equals("Noivo") & tipo.equals(novoTipo)) {
            gui.exibirNoivoJaCadastrado();
            return false;
        } else if (tipo.equals("Noiva") & tipo.equals(novoTipo)) {
            gui.exibirNoivaJaCadastrada();
            return false;
        }
        return true;
    }

    public String mostraUsuario() {

        boolean vazio = true;

        String texto = "USUARIOS CADASTRADOS";

        for (int i = 0; i < 40; i++) {

            if (this.usuario[i] != null) {
                texto += "\n" + usuario[i].toString();
                vazio = false;
            }
        }
        if (vazio == true) {
            return null;

        } else {
            return texto;
        }
    }

    public Usuario consultaUsuario(String nomeUsuario) {

        for (int i = 0; i < 40; i++) {

            if (this.usuario[i] != null && this.usuario[i].getLogin().equals(nomeUsuario)) {
                return usuario[i];
            }
        }
        return null;
    }

    public boolean alteraSenhaUsuario(Usuario usuario, String novaSenha) {

        if (usuario == null) {
            return false;
        } else {

            for (int i = 0; i < 40; i++) {

                if (this.usuario[i] != null) {
                    if (this.usuario[i].getSenha().equals(usuario.getSenha()) && this.usuario[i].getLogin().equals(usuario.getLogin())) {
                        this.usuario[i].setSenha(novaSenha);
                        this.usuario[i].setDataModificacao(Datas.pegaDataAgora());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean excluirUsuario(Usuario usuario) {

        if (usuario == null) {
            return false;
        }
        for (int i = 0; i < 40; i++) {

            if (this.usuario[i] == usuario) {
                this.usuario[i] = null;
                return true;
            }
        }
        return true;
    }

    public boolean buscaUsuario(String nomeLogin, String senha) {

        for (int i = 0; i < 40; i++) {

            if (this.usuario[i] != null) {
                if (this.usuario[i].getLogin().equals(nomeLogin) && this.usuario[i].getSenha().equals(senha)) {
                    return true;
                }
            }
        }
        return false;
    }
}
