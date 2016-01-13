/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

import DAO.DAO;
import TO.TO;
import java.util.ArrayList;

/**
 *
 * @author suporte06
 */
public class BO {

    public String incluir(TO to) throws Exception {
        String ret = consisteDados(to);
        if (ret.equals("")) {
            DAO dao = new DAO();
            dao.inserir(to);
            return "";
        } else {
            return ret;
        }
    }
    
    public String alterar(TO to) throws Exception{
        String ret = consisteDados(to);
        if(ret.equals("")){
            DAO dao = new DAO();
            dao.alterar(to);
            return "";
        }else{
            return ret;
        }
    }
    
    public ArrayList<TO> consultarTodos(TO to) throws Exception{
        DAO dao = new DAO();
        ArrayList gern = dao.consultarTodos(to);
        return gern;
    }
    
    public TO consultarID(TO to) throws Exception {
        
    }
}
