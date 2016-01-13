/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import TO.TO;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author suporte06
 */
public class DAO {

    public String alterar(TO to) throws Exception {
        try{
            Connection con = new Conexao().getConexao(to.getArquivo().getPath());
            
            PreparedStatement ps = con.prepareStatement("UPDATE GERNFEIT SET NUMR_NFE = ?, MODL_NFECE = ?, DESC_PROD = ?, "
                                                        + "QTDE_PRODT = ?, VALR_UNITT = ?, VALR_DESCN = ?, VALR_TOTAL = ? "
                                                        + "WHERE NUMR_NFE = ?");
            ps.setString(1,to.getNum_nfe());
            ps.setString(2,to.getMod_nfece());
            ps.setString(3,to.getDes_prod());
            ps.setInt(4,to.getQtde_prod());
            ps.setDouble(5, to.getVal_unit());
            ps.setDouble(6, to.getVal_desc());
            ps.setDouble(7, to.getVal_total());
            ps.setString(8, to.getNum_nfe());
            ps.execute();
            con.close();
            return"";
        }catch(Exception ex){
            System.out.println("ERRO ao alterar \n"+ex.getMessage());
        }
        return
    }
}
