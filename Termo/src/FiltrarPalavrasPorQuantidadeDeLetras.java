import java.io.*;

public class FiltrarPalavrasPorQuantidadeDeLetras {
    public static void main(String[] args) {
        String nomeArquivoEntrada = "Palavras.txt"; // Substitua pelo nome do seu arquivo de entrada
        String nomeArquivoSaida = "PalavraModificada.txt"; // Nome do arquivo de saída

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArquivoEntrada), "UTF-8"));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nomeArquivoSaida), "UTF-8"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String palavra = linha.trim(); // Remove espaços em branco do início e do fim da linha
                int quantidadeLetras = palavra.length();
                
                // Defina o limite de letras desejado (5 letras)
                int limiteLetras = 5;

                if (quantidadeLetras == limiteLetras) {
                    writer.write(palavra); // Escreve a palavra no arquivo de saída se atender ao critério
                    writer.newLine(); // Adiciona uma nova linha após cada palavra
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
