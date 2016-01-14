package dao;

import interfaces.IClienteDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import to.ClienteTo;

public class ClienteDao implements IClienteDao {

    public String incluir(ClienteTo clienteTO) throws Exception {
        try {
            //ABRE CONEXAO COM O BANCO
            Connection conn = new Conexao().getConexao();
            //*********************************************

            //PREPARA O SQL PARA EXECUÇÃO
            PreparedStatement ps = conn.prepareStatement("INSERT INTO CLIENTE (CLIENTE_NOME,CLIENTE_ENDERECO,CLIENTE_TELEFONE) VALUES (?,?,?)");
            ps.setString(1, clienteTO.getNome());//PEGA O NOME DO CLIENTE DO TO
            ps.setString(2, clienteTO.getEndereco());//PEGA O ENDEREÇO DO CLIENTE DO TO
            ps.setString(3, clienteTO.getTelefone());//PEGA O TELEFONE DO CLIENTE DO TO
            ps.execute();//EXECUTA O INSERT
            conn.close();
            return "";
        } catch (Exception ex) {
            throw ex;
        }
    }

    public String alterar(ClienteTo clienteTO) throws Exception {
        try {
            //ABRE CONEXAO COM O BANCO
            Connection conn = new Conexao().getConexao();
            //*********************************************

            //PREPARA O SQL PARA EXECUÇÃO
            PreparedStatement ps = conn.prepareStatement("UPDATE CLIENTE SET CLIENTE_NOME = ?, CLIENTE_ENDERECO = ?, CLIENTE_TELEFONE = ? WHERE CLIENTE_ID = ?");
            ps.setString(1, clienteTO.getNome());//PEGA O NOME DO CLIENTE DO TO
            ps.setString(2, clienteTO.getEndereco());//PEGA O ENDEREÇO DO CLIENTE DO TO
            ps.setString(3, clienteTO.getTelefone());//PEGA O TELEFONE DO CLIENTE DO TO
            ps.setLong(4, clienteTO.getId());//PEGA O ID DO CLIENTE DO TO
            ps.execute();//EXECUTA O UPDATE
            conn.close();
            return "";
        } catch (Exception ex) {
            throw ex;
        }
    }

    public ArrayList<ClienteTo> consultarTodos() throws Exception {
        ArrayList clientes = new ArrayList();
        //ABRE CONEXAO COM O BANCO
        //*************************
        Connection conn = new Conexao().getConexao();
        //*********************************************
        //RECUPERA TODOS OS CLIENTES DO BANCO
        //*********************************************
        ResultSet rs;
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM CLIENTE ORDER BY CLIENTE_NOME");
        rs = ps.executeQuery();
        //***********************************************
        //PARA CADA CLIENTE MONTA UM TO E ADICONA O MESMO AO ARRAYLIST
        //************************************************************
        while (rs.next()) {
            ClienteTo clienteTo = new ClienteTo();
            clienteTo.setId(rs.getLong("cliente_id"));
            clienteTo.setEndereco(rs.getString("cliente_endereco"));
            clienteTo.setNome(rs.getString("cliente_nome"));
            clienteTo.setTelefone(rs.getString("cliente_telefone"));
            clientes.add(clienteTo);
        }
        //************************************************************

        //RETORNA O ARRAYLIST PARA O BO
        conn.close();
        return clientes;
    }

    public ClienteTo consultarID(long id) throws Exception {
        //ABRE CONEXAO COM O BANCO
        //*************************
        Connection conn = new Conexao().getConexao();
        //*********************************************
        //RECUPERA TODOS OS CLIENTES DO BANCO
        //*********************************************
        ResultSet rs;
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM CLIENTE WHERE CLIENTE_ID = ?");
        ps.setLong(1, id);
        rs = ps.executeQuery();
        //***********************************************
        //PARA CADA CLIENTE MONTA UM TO E ADICONA O MESMO AO ARRAYLIST
        //************************************************************
        ClienteTo clienteTo = new ClienteTo();
        if (rs.next()) {

            clienteTo.setId(rs.getLong("cliente_id"));
            clienteTo.setEndereco(rs.getString("cliente_endereco"));
            clienteTo.setNome(rs.getString("cliente_nome"));
            clienteTo.setTelefone(rs.getString("cliente_telefone"));
        }
        //************************************************************
        conn.close();
        //RETORNA O ARRAYLIST PARA O BO
        return clienteTo;
    }

    public void excluirID(long ID) throws Exception {

        //EXCLUI UM CLIENTE DO BANCO (COM O ID PASSADO COMO PARÂMETRO)
        //************************************************************
        //ABRE CONEXAO COM O BANCO
        //************************************************************
        Connection conn = new Conexao().getConexao();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM CLIENTE WHERE CLIENTE_ID = ?");
        ps.setLong(1, ID);
        ps.execute();//EXCLUI O CLIENTE
        conn.close();
    }

}
