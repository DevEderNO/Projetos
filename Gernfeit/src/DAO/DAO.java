/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import TO.TO;
import TO.dbfTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author suporte06
 */
public class DAO {

    private dbfTO dbf = new dbfTO();

    public String inserir(TO to) throws Exception {
        try {
            Connection con = new Conexao().getConexao(dbf.getArquivo().getPath());

            PreparedStatement ps = con.prepareStatement("INSERT INTO GERNFEIT (NUMR_NFE,MODL_NFECE,DESC_PROD,QTDE_PRODT,"
                    + "VALR_UNITT,VALR_DESCN,VALR_TOTAL) VALUE (?,?,?,?,?,?,?)");
            ps.setString(1, to.getNum_nfe());
            ps.setString(2, to.getMod_nfece());
            ps.setString(3, to.getDes_prod());
            ps.setInt(4, to.getQtde_prod());
            ps.setDouble(5, to.getVal_unit());
            ps.setDouble(6, to.getVal_desc());
            ps.setDouble(7, to.getVal_total());
            ps.execute();
            con.close();
            return "Inserção OK";
        } catch (Exception ex) {
            System.out.println("Erro ao inserir \n" + ex.getMessage());
        }
        return "";
    }

    public String alterar(TO to) throws Exception {
        try {
            Connection con = new Conexao().getConexao(dbf.getArquivo().getPath());

            PreparedStatement ps = con.prepareStatement("UPDATE GERNFEIT SET NUMR_NFE = ?, MODL_NFECE = ?, DESC_PROD = ?, "
                    + "QTDE_PRODT = ?, VALR_UNITT = ?, VALR_DESCN = ?, VALR_TOTAL = ? "
                    + "WHERE NUMR_NFE = ?");
            ps.setString(1, to.getNum_nfe());
            ps.setString(2, to.getMod_nfece());
            ps.setString(3, to.getDes_prod());
            ps.setInt(4, to.getQtde_prod());
            ps.setDouble(5, to.getVal_unit());
            ps.setDouble(6, to.getVal_desc());
            ps.setDouble(7, to.getVal_total());
            ps.setString(8, to.getNum_nfe());
            ps.execute();
            con.close();
            return "Alteração OK";
        } catch (Exception ex) {
            System.out.println("ERRO ao alterar \n" + ex.getMessage());
        }
        return "";
    }

    public ArrayList<TO> consultarTodos(TO to) throws Exception {
        ArrayList gern = new ArrayList();
        Connection con = new Conexao().getConexao(dbf.getArquivo().getPath());
        ResultSet rs;
        PreparedStatement ps = con.prepareStatement("SELECT * FROM GERNFEIT ORDER BY NUMR_NFE");
        rs = ps.executeQuery();
        while (rs.next()) {
            TO to2 = new TO();
            to2.setNum_nfe(rs.getString("NUMR_NFE"));
            to2.setMod_nfece(rs.getString("MODL_NFECE"));
            to2.setDes_prod(rs.getString("DESC_PROD"));
            to2.setQtde_prod(rs.getInt("QTDE_PRODT"));
            to2.setVal_unit(rs.getDouble("VALR_UNITT"));
            to2.setVal_desc(rs.getDouble("VALR_DESCN"));
            to2.setVal_total(rs.getDouble("VALR_TOTAL"));
            gern.add(to2);
        }
        con.close();
        return gern;
    }

    public TO consultaID(String id) throws Exception {
        Connection con = new Conexao().getConexao(dbf.getArquivo().getPath());
        ResultSet rs;
        PreparedStatement ps = con.prepareStatement("SELECT * FROM GERNFEIT WHERE NUMR_NFE = ?");
        ps.setString(1, id);
        rs = ps.executeQuery();
        TO to2 = new TO();
        if (rs.next()) {
            to2.setNum_nfe(rs.getString("NUMR_NFE"));
            to2.setMod_nfece(rs.getString("MODL_NFECE"));
            to2.setDes_prod(rs.getString("DESC_PROD"));
            to2.setQtde_prod(rs.getInt("QTDE_PRODT"));
            to2.setVal_unit(rs.getDouble("VALR_UNITT"));
            to2.setVal_desc(rs.getDouble("VALR_DESCN"));
            to2.setVal_total(rs.getDouble("VALR_TOTAL"));
        }
        con.close();
        return to2;
    }

    public void excluirID(String id) throws Exception {
        Connection con = new Conexao().getConexao(dbf.getArquivo().getPath());
        PreparedStatement ps = con.prepareStatement("DELET FROM GERNFEIT WHERE NUMR_NFE = ?");
        ps.setString(1, id);
        ps.execute();
        con.close();
    }
}
