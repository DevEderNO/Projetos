/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correcao.sped;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author suporte06
 */
public class CorrecaoSped {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        int contLinha = 0;
        // Variavel responsavel por receber o arquivo .txt
        InputStream is = new FileInputStream("C:\\SPED.TXT");
        // Variavel que recebe a leitura do arquivo .txt
        InputStreamReader irs = new InputStreamReader(is);
        // Variavel que recebe o buffer da leitura do arquivo
        BufferedReader br = new BufferedReader(irs);
        // Variavel que recebe o tamanho de cada linha.
        int tamanho = 0, cont = 0;
        // Variavel que seram usadas para identificar as linhas que nessecitam de correção.
        String c460 = "C460", c190 = "C190", r2 = "";
        // Laço que percore todas as linhas, testa as condições e corrige as mesmas.
        while (br != null) {
            // Variavel que recebe cada linha do arquivo .txt
            String r = br.readLine();
            // Inicia a variavel "tamanho" com o tamanho da linha.
            contLinha = contLinha + 1;
            try {
                tamanho = (int) r.length();
            } catch (Exception e) {
                if (tamanho > 0) {
                    return;
                }
            }
            try {
                // Identifica a linha C460
                if (r.substring(1, 5).toString().equals(c460)) {
                    // Condição para correção de cpf para notas emitidas.
                    if (tamanho > 45) {
                        System.out.println(r.substring(0, tamanho - 16) + "" + r.substring(tamanho - 2, tamanho));
                    } else if (tamanho > 0 && tamanho <= 45) {
                        // Condição para correção de cpf para notas canceladas.
                        if(r.substring(tamanho-21,tamanho-16).toString().equals("|||||")){
                            System.out.println(r.substring(0, tamanho - 16) + "" + r.substring(tamanho - 2, tamanho));
                        }
                    }else{// Caso não esteja nas condições para coreção do cpf imprime a linha.
                        System.out.println(r);
                    }
                    // Identifica a linha C190
                } else if (r.substring(1, 5).toString().equals(c190)) {
                    // Condição para correção de erro numero em branco.
                    if (r.substring(6, 7).toString().equals(" ")) {
                        System.out.println(r.substring(0, 6) + "0" + r.substring(7, tamanho));
                    }
                    // Caso não esteja nas condições para coreção do numero em braqnco imprime a linha.
                    else {
                        System.out.println(r);
                    }
                } else {// Caso não seja linha C460 ou C190 imprime a linha.
                    System.out.println(r);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
