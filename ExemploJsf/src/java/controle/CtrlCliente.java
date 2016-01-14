package controle;

import bo.ClienteBo;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import to.ClienteTo;


@ManagedBean
@SessionScoped
public class CtrlCliente implements Serializable {

    private ClienteTo clienteTo;
    private List<ClienteTo> clientes;
    private int operacao;
    private String labelBotao;

    public String getLabelBotao() {
        return labelBotao;
    }

    public void setLabelBotao(String labelBotao) {
        this.labelBotao = labelBotao;
    }
    
    

  

    public CtrlCliente() {
        clienteTo = new ClienteTo();
        setLabelBotao("Incluir");
        operacao=1;
    }

    public List<ClienteTo> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteTo> clientes) {
        this.clientes = clientes;
    }

    public ClienteTo getClienteTo() {
        return clienteTo;
    }

    public void setClienteTo(ClienteTo clienteTo) {
        this.clienteTo = clienteTo;
    }

    public void incluir() {
        String ret = "";
        try {
            ClienteBo clienteBo = new ClienteBo();
            ret = clienteBo.incluir(clienteTo);
            limpaTela();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente incluído com sucesso!", "Atenção"));;
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "Atenção"));
        }

    }

   public void alterar() {
        String ret = "";
        try {
            ClienteBo clienteBo = new ClienteBo();
            ret = clienteBo.alterar(clienteTo);
            //limpaTela();
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente alterado com sucesso!", "Atenção"));;
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "Atenção"));
        }

    } 
    
    public String consultarTodos() {
        ClienteBo clienteBo = new ClienteBo();
        try {
            clientes = clienteBo.consultarTodos();
        } catch (Exception ex) {
        }
        return "consulta";
    }

    public String excluir() {
        ClienteBo clienteBo = new ClienteBo();
        try {
            clienteBo.excluirID(clienteTo.getId());
            clientes = clienteBo.consultarTodos();
            limpaTela();
        } catch (Exception ex) {
        }

        return "consulta";
    }

    public String index() {
        return "/index";
    }

    private void limpaTela() {
        //clienteTo.setNome("");
        //clienteTo.setTelefone("");
        //clienteTo.setEndereco("");
        clienteTo = null;
        clienteTo = new ClienteTo();
        setLabelBotao("Incluir");
        operacao=1;
    }
    
    
    public String editar() {
        operacao = 2;
        setLabelBotao("Alterar");
        
        return "index";
    }
    
    public String voltar() {
        limpaTela();
        return  "index";
    }
    public String manter() {
        if (operacao == 1) {
            incluir();
            return "index";
            
        }
        else {
            alterar();
            //consultarTodos();
            return "consulta";
        }
        //return "";
    }
}
