/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correcao.sped;

import Model.SpedTO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;

/**
 *
 * @author suporte06
 */
public class Correcao {

    // Variavel que recebe o tamanho de cada linha.
    private int tamanho = 0;
    // Variavel que seram usadas para identificar as linhas que nessecitam de correção.
    String c460 = "C460", c190 = "C190";
    private int erroCPF, erroCPFCC, erroCEB;

    public int getErroCEB() {
        return erroCEB;
    }

    public void setErroCEB(int erroCEB) {
        this.erroCEB = erroCEB;
    }

    public int getErroCPF() {
        return erroCPF;
    }

    public void setErroCPF(int erroCPF) {
        this.erroCPF = erroCPF;
    }

    public int getErroCPFCC() {
        return erroCPFCC;
    }

    public void setErroCPFCC(int erroCPFCC) {
        this.erroCPFCC = erroCPFCC;
    }

    public Correcao() {
        erroCPF = 0;
        erroCPFCC = 0;
        tamanho = 0;
        erroCEB = 0;
    }

    public String copy(File source, File dest) throws IOException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
            return "BackUp criado em: " + dest.getPath();
        } finally {
            input.close();
            output.close();
        }
    }

    public String corrigirCPF(File arquivo) throws FileNotFoundException, IOException {
        // Variavel responsavel por receber o arquivo .txt
        FileInputStream is = new FileInputStream(arquivo.getPath());
        // Variavel que recebe a leitura do arquivo .txt
        InputStreamReader irs = new InputStreamReader(is);
        // Variavel que recebe o buffer da leitura do arquivo
        BufferedReader br = new BufferedReader(irs);
        // Variavel que recebe o tamanho de cada linha.
        tamanho = 0;
        // Variavel que sera utilizada para identificar as linhas que nessecitam de correção.
        c460 = "C460";
        //Cria novo arquivo corrigido no mesmo local onde está o arquivo de origem.
        FileOutputStream os = new FileOutputStream(arquivo.getPath());
        // Variavel que escreve a leitura do arquivo .txt
        OutputStreamWriter osw = new OutputStreamWriter(os);
        // Variavel que recebe o buffer da escrita do arquivo 
        BufferedWriter bw = new BufferedWriter(osw);

        while (br != null) {
            // Variavel que recebe cada linha do arquivo .txt
            String r = br.readLine();
            // Inicia a variavel "tamanho" com o tamanho da linha.
            try {
                tamanho = (int) r.length();
            } catch (Exception e) {
                if (tamanho > 0) {

                }
            }
            try {
                // Identifica a linha C460
                if (r.substring(1, 5).toString().equals(c460)) {
                    // Condição para correção de cpf para notas emitidas.
                    if (tamanho > 45) {
                        bw.write(r.substring(0, tamanho - 16) + "" + r.substring(tamanho - 2, tamanho) + "\r\n");
                        setErroCPF(getErroCPF() + 1);
                    } else if (tamanho > 0 && tamanho <= 45) {
                        // Condição para correção de cpf para notas canceladas.
                        if (r.substring(tamanho - 21, tamanho - 16).toString().equals("|||||")) {
                            bw.write(r.substring(0, tamanho - 16) + "" + r.substring(tamanho - 2, tamanho) + "\r\n");
                            setErroCPFCC(getErroCPFCC() + 1);
                        }
                    } else {// Caso não esteja nas condições para coreção do cpf imprime a linha.
                        bw.write(r + "\r\n");
                    }
                } else {
                    bw.write(r + "\r\n");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return "Corrigido " + getErroCPF() + " erros de cpf.";
    }

    public void corrigirCaracterEmBranco(File arquivo) throws FileNotFoundException, IOException {

        // Variavel responsavel por receber o arquivo .txt
        FileInputStream is = new FileInputStream(arquivo.getPath());
        // Variavel que recebe a leitura do arquivo .txt
        InputStreamReader irs = new InputStreamReader(is);
        // Variavel que recebe o buffer da leitura do arquivo
        BufferedReader br = new BufferedReader(irs);
        // Variavel que recebe o tamanho de cada linha.
        tamanho = 0;
        // Variavel que sera utilizada para identificar as linhas que nessecitam de correção.
        c190 = "C190";
        //Cria novo arquivo corrigido no mesmo local onde está o arquivo de origem.
        FileOutputStream os = new FileOutputStream(arquivo.getPath());
        // Variavel que escreve a leitura do arquivo .txt
        OutputStreamWriter osw = new OutputStreamWriter(os);
        // Variavel que recebe o buffer da escrita do arquivo 
        BufferedWriter bw = new BufferedWriter(osw);

        while (br != null) {
            // Variavel que recebe cada linha do arquivo .txt
            String r = br.readLine();
            // Inicia a variavel "tamanho" com o tamanho da linha.
            try {
                tamanho = (int) r.length();
            } catch (Exception e) {
                if (tamanho > 0) {
                    return;
                }
            }
            try {
                if (r.substring(1, 5).toString().equals(c190)) {
                    // Condição para correção de erro numero em branco.
                    if (r.substring(6, 7).toString().equals(" ")) {
                        bw.write(r.substring(0, 6) + "0" + r.substring(7, tamanho) + "\r\n");
                        setErroCEB(getErroCEB() + 1);
                    } // Caso não esteja nas condições para coreção do numero em braqnco imprime a linha.
                    else {
                        bw.write(r + "\r\n");
                    }
                } else {// Caso não seja linha C460 ou C190 imprime a linha.
                    bw.write(r + "\r\n");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
    }

}
