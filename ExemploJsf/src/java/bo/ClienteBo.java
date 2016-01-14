package bo;
import dao.ClienteDao;
import interfaces.IClienteDao;
import java.util.ArrayList;
import to.ClienteTo;

public class ClienteBo {
    public String incluir(ClienteTo clienteTo) throws Exception{
        //CONSISTE OS DADOS DO TO
        //**************************
        String ret = consisteDados(clienteTo);
        //**************************
        if (ret.equals("")) //DADOS OK
        {
            //SOLICITA AO DAO QUE FAÇA A INCLUSÃO DO CLIENTE NO BANCO DE DADOS
            //****************************************
            IClienteDao clienteDao = new ClienteDao();
            clienteDao.incluir(clienteTo);
            //****************************************
            return "";//OPERAÇÃO EXECUTADA COM SUCESSO.
        }
        else
        {//DADOS COM INCONSISTÊNCIA
            return ret;
        }
    }

    public String alterar(ClienteTo clienteTo) throws Exception{
        //CONSISTE OS DADOS DO TO
        //**************************
        String ret = consisteDados(clienteTo);
        //**************************
        if (ret.equals("")) //DADOS OK
        {
            //SOLICITA AO DAO QUE FAÇA A ALTERAÇÃO DO CLIENTE NO BANCO DE DADOS
            //****************************************
            IClienteDao clienteDao = new ClienteDao();
            clienteDao.alterar(clienteTo);
            //****************************************
            return "";//OPERAÇÃO EXECUTADA COM SUCESSO.
        }
        else
        {//DADOS COM INCONSISTÊNCIA
            return ret;
        }
    }

    public ArrayList <ClienteTo> consultarTodos() throws Exception {
        //SOLICITA AO DAO OS CLIENTES CADASTRADOS
        //DAO RETORNA UM ARRAYLIST DE TO´s DE CLIENTE
        //QUE É RETORNADO AO SERVLET
        IClienteDao clienteDao = new ClienteDao();
        ArrayList clientes = clienteDao.consultarTodos();
        return clientes;
    }

        public ClienteTo consultarID(long id) throws Exception {
        //SOLICITA AO DAO O CLIENTE COM O ID INFORMADO
        //E RETORNA UM TO DE CLIENTE AO SERVLET
        IClienteDao clienteDao = new ClienteDao();
        ClienteTo clienteTo = clienteDao.consultarID(id);
        return clienteTo;
    }

    public void excluirID (long id) throws Exception {
        //SOLICITA AO DAO QUE EXCLUA O CLIENTE COM A ID 
        //PASSADO COMO PARÂMETRO.
        IClienteDao clienteDao = new ClienteDao();
        clienteDao.excluirID(id);
    }


    private String consisteDados(ClienteTo clienteTo) {
        //FAZ A CONSISTÊNCIA DOS DADOS DO TO
        //**********************************
        if (clienteTo.getNome().equals("")) {
            return "Nome do cliente não informado"; //NOME NÃO PREENCHIDO
        }

        if (clienteTo.getEndereco().equals("")) {
            return "Endereço do cliente não informado"; //ENDEREÇO NÃO PREENCHIDO
        }
        if (clienteTo.getTelefone().equals("")) {
            return "Telefone do cliente não informado"; //TELEFONE NÃO PREENCHIDO
        }
        return "";//DADOS CONSISTENTES - RETORNA UMA STRING VAZIA.
        //**********************************
    }
}
