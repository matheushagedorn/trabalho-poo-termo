import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ModelTermo {

    public String palavraSorteada; // Variável para armazenar a palavra sorteada
    String[] palavraArray;
    String letraDigitada;
    int indiceLetra;
    int isPalavraCorreta;

    public ModelTermo() {
        this.palavraSorteada = sorteiaPalavra().toUpperCase();
        palavraArray = palavraSorteada.split("");
    }

    public String sorteiaPalavra() {
        String nomeArquivo = "palavrasJogo.txt"; // Nome do arquivo com as palavras a serem sorteadas

        // Lista para armazenar as palavras do arquivo
        ArrayList<String> palavras = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                palavras.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

        // Verifica se há palavras no arquivo
        if (palavras.isEmpty()) {
            System.out.println("O arquivo está vazio.");
            return "";
        }

        // Sorteia uma palavra aleatoriamente
        Random rand = new Random();
        int indiceSorteado = rand.nextInt(palavras.size());
        String palavraSorteada = palavras.get(indiceSorteado);

        System.out.println("A palavra sorteada é: " + palavraSorteada);
        return palavraSorteada;
    }

    public void verificaPalavra(int linha) {

        int indiceLetra;
        isPalavraCorreta = 0;
        int contador = 0;
        String[] letra = new String[5];
        int[] contagemDeLetras = new int[5];

        // Crie um mapa para armazenar os caracteres e suas contagens
        Map<Character, Integer> contagemCaracteres = new HashMap<>();

        // Itere pela string para contar os caracteres
        for (char caractere : palavraSorteada.toCharArray()) {
            if (contagemCaracteres.containsKey(caractere)) {
                // Se o caractere já estiver no mapa, incremente a contagem
                contagemCaracteres.put(caractere, contagemCaracteres.get(caractere) + 1);
            } else {
                // Se o caractere não estiver no mapa, adicione-o com contagem 1
                contagemCaracteres.put(caractere, 1);
            }
        }

        // Itere pelo mapa e exiba cada caractere e sua contagem
        for (Map.Entry<Character, Integer> entry : contagemCaracteres.entrySet()) {
            contagemDeLetras[contador] = entry.getValue();
            letra[contador] = entry.getKey().toString();
            System.out.println("Caractere: " + contagemDeLetras[contador] + ", Quantidade: " + letra[contador]);
            contador++;
        }

        for (int i = 0; i < 5; i++) {
            indiceLetra = palavraSorteada.indexOf(JFrameTermo.matriz[linha][i].getText());
            // System.out.println(JFrameTermo.matriz[linha][i].getText());
            // System.out.println(palavraArray[i]);

            if (palavraArray[i].equals(JFrameTermo.matriz[linha][i].getText())) {
                JFrameTermo.matriz[linha][i].verificarLetra(2);
                isPalavraCorreta++;

            } else if (indiceLetra == -1) {
                JFrameTermo.matriz[linha][i].verificarLetra(1);
            } else {

                JFrameTermo.matriz[linha][i].verificarLetra(3);
            }
        }

    }

    public boolean verificaFinalizado(int linha) {
        if (linha == 6 || isPalavraCorreta == 5) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isGanhou() {
        if (isPalavraCorreta == 5) {
            return true;
        } else {
            return false;
        }
    }

    public boolean temCaractereRepetido() {
        Map<Character, Integer> frequenciaCaracteres = new HashMap<>();

        for (char c : palavraSorteada.toCharArray()) {
            if (frequenciaCaracteres.containsKey(c)) {
                frequenciaCaracteres.put(c, frequenciaCaracteres.get(c) + 1);
            } else {
                frequenciaCaracteres.put(c, 1);
            }
        }

        return false; // Retorna falso se nenhum caractere for repetido
    }
}
