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
public class FuncionarioDAO {

    private final String stmInserirFuncionario = "INSERT INTO funcionarios (cpf,nome,endereco,email,celular,codigoEmpresa) values(?,?,?,?,?,?)";
    private final String stmEditarFuncionario = "UPDATE funcionarios SET cpf=?,nome=?, endereco=?, email=?, celular=? WHERE codigo=?";
    private final String stmExcluirFuncionario = "DELETE from funcionarios where codigo=?";
    private final String stmSelTodosFuncionarios = "SELECT * FROM funcionarios";
    private final String stmSelFuncionarioByCodigo = "SELECT * FROM funcionarios WHERE codigo=?";

    public void cadastrarFuncionario(Funcionario funcionario) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmInserirFuncionario, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, funcionario.getCpf());
            pstmt.setString(2, funcionario.getNome());
            pstmt.setString(3, funcionario.getEndereco());
            pstmt.setString(4, funcionario.getEmail());
            pstmt.setString(5, funcionario.getCelular());
            pstmt.setInt(6, funcionario.getCdEmpresa());
            pstmt.execute();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            int i = rs.getInt(1);
            funcionario.setCodigo(i);
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

    public void atualizarFuncionario(Funcionario funcionario) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmEditarFuncionario, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, funcionario.getCpf());
            pstmt.setString(2, funcionario.getNome());
            pstmt.setString(3, funcionario.getEndereco());
            pstmt.setString(4, funcionario.getEmail());
            pstmt.setString(5, funcionario.getCelular());
            pstmt.setInt(6, funcionario.getCodigo());
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

    public ArrayList<Funcionario> todosFuncionarios() {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        Funcionario funcionario;
        ArrayList<Funcionario> funcionarios = new ArrayList();
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmSelTodosFuncionarios, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setCodigo(rs.getInt("codigo"));
                funcionario.setCdEmpresa(rs.getInt("codigoEmpresa"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setCelular(rs.getString("celular"));
                funcionarios.add(funcionario);
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
        return funcionarios;
    }

    public void deletar(Funcionario funcionario) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmExcluirFuncionario, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, funcionario.getCodigo());
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

    public Funcionario funcionarioByCodigo(int codigo) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        Funcionario funcionario = new Funcionario();
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmSelFuncionarioByCodigo, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, codigo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                funcionario.setCodigo(rs.getInt("codigo"));
                funcionario.setCdEmpresa(rs.getInt("codigoEmpresa"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setCelular(rs.getString("celular"));
            } else {
                funcionario = null;
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
        return funcionario;
    }

    public ArrayList<Funcionario> funcionarioByNome(String nome) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        String stmSelFuncionarioByNome = "SELECT * FROM funcionarios WHERE nome LIKE '%" + nome + "%'";
        ArrayList<Funcionario> funcionarios = new ArrayList();
        Funcionario funcionario;
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmSelFuncionarioByNome, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setCodigo(rs.getInt("codigo"));
                funcionario.setCdEmpresa(rs.getInt("codigoEmpresa"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setCelular(rs.getString("celular"));
                funcionarios.add(funcionario);
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
        return funcionarios;
    }

}
