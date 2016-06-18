/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author sdgdsgd
 */
public class EmpresaDAO {

    private final String stmInserirEmpresa = "INSERT INTO empresas (cnpj,razao,endereco,email) values(?,?,?,?)";
    private final String stmEditarEmpresa = "UPDATE empresas SET cnpj=?, razao=?, endereco=?, email=? WHERE codigo=?";
    private final String stmExcluirEmpresa = "DELETE from empresas where codigo=?";
    private final String stmSelTodasEmpresas = "SELECT * FROM empresas";
    private final String stmSelEmpresaByCodigo = "SELECT * FROM empresas WHERE codigo=?";
   

    public void cadastrarEmpresa(Empresa empresa) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmInserirEmpresa, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, empresa.getCnpj());
            pstmt.setString(2, empresa.getRazao());
            pstmt.setString(3, empresa.getEndereco());
            pstmt.setString(4, empresa.getEmail());

            pstmt.execute();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            int i = rs.getInt(1);
            empresa.setCodigo(i);
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

    public void atualizarEmpresa(Empresa empresa) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmEditarEmpresa, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, empresa.getCnpj());
            pstmt.setString(2, empresa.getRazao());
            pstmt.setString(3, empresa.getEndereco());
            pstmt.setString(4, empresa.getEmail());
            pstmt.setInt(5, empresa.getCodigo());
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

    public ArrayList<Empresa> todasEmpresas() {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        Empresa empresa;
        ArrayList<Empresa> empresas = new ArrayList();
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmSelTodasEmpresas, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                empresa = new Empresa();
                empresa.setCodigo(rs.getInt("codigo"));
                empresa.setCnpj(rs.getString("cnpj"));
                empresa.setRazao(rs.getString("razao"));
                empresa.setEndereco(rs.getString("endereco"));
                empresa.setEmail(rs.getString("email"));
                empresas.add(empresa);
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
        return empresas;
    }
    
    public Empresa empresaByCodigo(int codigo) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        Empresa empresa = new Empresa();
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmSelEmpresaByCodigo, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, codigo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                empresa.setCodigo(rs.getInt("codigo"));
                empresa.setCnpj(rs.getString("cnpj"));
                empresa.setRazao(rs.getString("razao"));
                empresa.setEndereco(rs.getString("endereco"));
                empresa.setEmail(rs.getString("email"));
            }
            else{
                empresa = null;
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
        return empresa;
    }
    
    public ArrayList<Empresa> empresaByRazao(String razao) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        String stmSelEmpresaByRazao = "SELECT * FROM empresas WHERE razao LIKE '%"+ razao + "%'";
        ArrayList<Empresa> empresas = new ArrayList();
        Empresa empresa;
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmSelEmpresaByRazao, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                empresa = new Empresa();
                empresa.setCodigo(rs.getInt("codigo"));
                empresa.setCnpj(rs.getString("cnpj"));
                empresa.setRazao(rs.getString("razao"));
                empresa.setEndereco(rs.getString("endereco"));
                empresa.setEmail(rs.getString("email"));
                empresas.add(empresa);
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
        return empresas;
    }

    public void deletar(int codigo) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = DbConexao.getConection();
            pstmt = conexao.prepareStatement(stmExcluirEmpresa, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, codigo);
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
