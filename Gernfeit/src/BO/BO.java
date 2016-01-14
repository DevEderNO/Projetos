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
    
    public TO consultarID(String id) throws Exception {
        DAO dao = new DAO();
        TO to = dao.consultaID(id);
        return to;
    }
    
    public void excluirID(String id) throws Exception {
        DAO dao = new DAO();
        dao.excluirID(id);
    }
    
    private String consisteDados(TO to){
        if(to.getNum_nfe().equals("")){
            return "Numero da nota não informado!!";
        }
        if(to.getMod_nfece().equals("")){
            return "Modelo da nota não informado!!";
        }
        if(to.getDes_prod().equals("")){
            return "Descrição do produto não informado!!";
        }
        if(to.getQtde_prod()<0){
            return "Quantidade de produto invalida";
        }
        if(to.getVal_unit()<0){
            return "Valor da unidade do produto invalida";
        }
        if(to.getVal_desc()<0){
            return "Valor do desconto do produto invalido";
        }
        if(to.getVal_total()<0){
            return "Total invalido";
        }
        return "";
    }
}
