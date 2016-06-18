/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import beans.*;
import java.util.ArrayList;

/**
 *
 * @author sdgdsgd
 */
public class UsuarioDAO {
    
    private final String stmInserirUsuario = "INSERT INTO usuarios (nome,login,senha) values(?,?,?)";
    private final String stmEditarUsuario = "UPDATE usuarios SET nome=?, login=?, senha=? WHERE codigo=?";
    private final String stmExcluirUsuario = "DELETE from usuarios where id=?";
    private final String stmLogin = "SELECT * FROM usuarios WHERE login=? AND senha=?";
    private final String stmSelTodosUsuarios = "SELECT * FROM usuarios";
    
    public Usuario login(String login, String senha) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        Usuario usuario = new Usuario();
        try {
            conexao = DbConexao.getConection();

            pstmt = conexao.prepareStatement(stmLogin, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, login);
            pstmt.setString(2, senha);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario.setCodigo(rs.getInt("codigo"));
                usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
            } else {
                usuario = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                pstmt.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
            try {
                conexao.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
        }
        return usuario;
    }
    
    public void cadastrarUsuario(Usuario usuario) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmInserirUsuario, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getLogin());
            pstmt.setString(3, usuario.getSenha());
            pstmt.execute();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            int i = rs.getInt(1);
            usuario.setCodigo(i);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                pstmt.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
            try {
                conexao.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
        }
    }

    public void atualizarUsuario(Usuario usuario) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmEditarUsuario, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getLogin());
            pstmt.setString(3, usuario.getSenha());
            pstmt.setInt(4, usuario.getCodigo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                pstmt.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
            try {
                conexao.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
        }
    }
    
    public ArrayList<Usuario> todosUsuarios() {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        Usuario usuario;
        ArrayList<Usuario> usuarios = new ArrayList();
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmSelTodosUsuarios, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                usuario= new Usuario();
                usuario.setCodigo(rs.getInt("codigo"));
                usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                pstmt.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
            try {
                conexao.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
        }
        return usuarios;
    }
    
    public void deletar(Usuario usuario){
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmExcluirUsuario, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, usuario.getCodigo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                pstmt.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
            try {
                conexao.close();
            } catch (Exception ex) {
                System.out.println("Erro:" + ex.getMessage());
            }
        }
    }
    
}
