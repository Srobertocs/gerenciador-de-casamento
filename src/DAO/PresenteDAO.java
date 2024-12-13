/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import view.GUI;
import beans.Presente;
import beans.Pessoa;
import beans.Usuario;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Datas;
import javax.swing.JOptionPane;

/**
 *
 * @author SOUSA
 */
public class PresenteDAO {
    
    public boolean reservaCompradorPresentes(Pessoa pessoa, long id) {
        String sql = "Update presente set idPessoa = ?, dataModificacao = ? where id = ? ";
        
        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setLong(1, pessoa.getId());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(Datas.pegaDataAgora()));
            stmt.setLong(3, id);
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean adicionaPresente(Presente novoPresente, String tipoUsuario, UsuarioDAO usuarioDAO) {
        String sql = "insert into presente (nome,tipo,dataCriacao,dataModificacao) values (?,?,?,?)";
        
        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            if (usuarioDAO.confereTipoUsuario(tipoUsuario) == true) {
                
                stmt.setString(1, novoPresente.getNome());
                stmt.setString(2, novoPresente.getTipo());
                stmt.setTimestamp(3, java.sql.Timestamp.valueOf(novoPresente.getDataCriacao()));
                stmt.setTimestamp(4, java.sql.Timestamp.valueOf(novoPresente.getDataModificao()));
                
                return stmt.executeUpdate() > 0;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    public List<Presente> listaPresente(PessoaDAO pessoaDAO) {
        String sql = "select * from presente";
        
        List<Presente> listaPresente = new ArrayList<>();
        
        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet resultado = stmt.executeQuery()) {
            
            while (resultado.next()) {
                Presente presente = new Presente();
                
                presente.setId(resultado.getLong("id"));
                presente.setNome(resultado.getString("nome"));
                presente.setTipo(resultado.getString("tipo"));
                presente.setComprador(pessoaDAO.buscaPessoaId(resultado.getInt("idPessoa")));
                presente.setDataCriacao(resultado.getTimestamp("dataCriacao").toLocalDateTime());
                presente.setDataModificao(resultado.getTimestamp("dataModificacao").toLocalDateTime());
                
                listaPresente.add(presente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPresente;
    }
    
    public String mostraListaPresente(PessoaDAO pessoaDAO) {
        boolean vazio = true;
        
        List<Presente> listaPresente = listaPresente(pessoaDAO);
        
        String texto = "LISTA DE PRESENTES\n";
        
        for (Presente presente : listaPresente) {
            if (presente != null) {
                texto += "\n" + presente.toString();
                vazio = false;
            }
        }
        if (vazio == true) {
            return null;
        } else {
            return texto;
        }
    }
    
    public boolean validaIdPresente(long id) {
        String sql = "select * from presente where id = ?";
        
        try (Connection conexao = new ConnectionFactory().getConnection(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setLong(1, id);
            
            try (ResultSet resultado = stmt.executeQuery()) {
                return resultado.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
