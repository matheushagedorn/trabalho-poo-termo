import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JFrameTermo extends JFrame {
    ModelTermo palavraSorteada = new ModelTermo();

    JPanel panelBarra;
    JPanel panelCampo;
    JPanel panelBotao;
    JLabel labelNomeBarra;
    JButton btnEnter;
    public static JTextFieldTermo[][] matriz;
    Controller ctrl;
    int espacoLateral;
    Font fonte = new Font("Arial", Font.BOLD, 45);
    Font fonteBtn = new Font("Arial", Font.PLAIN, 30);
    Color corFundo;
    Color corBtn;
    int contadorLinha = 0;
    boolean teclaBackSpacePress = false;
    
    public JFrameTermo(Controller c) {
        corFundo = new Color(110, 92, 98);
        corBtn = new Color(76, 67, 71);
        
        this.ctrl = c;
        panelBarra = new JPanel();
        panelCampo = new JPanel();
        panelBotao = new JPanel();
        btnEnter = new JButton("Enter");
        labelNomeBarra = new JLabel("TERMO");
        matriz = new JTextFieldTermo[6][5];
        espacoLateral = 50;

        labelNomeBarra.setFont(fonte);
        labelNomeBarra.setForeground(Color.WHITE);

        btnEnter.setBackground(corBtn);
        btnEnter.setForeground(Color.WHITE);
        btnEnter.setFont(fonteBtn);
        btnEnter.setMargin(new Insets(20, 20, 20, 20));

        panelCampo.setLayout(new GridLayout(6, 5, 3, 5));
        panelBotao.setBackground(corFundo);
        panelBarra.setBackground(corFundo);
        panelCampo.setBackground(corFundo);

        
        panelCampo.setBorder(new EmptyBorder(espacoLateral, espacoLateral, 0, espacoLateral));
        panelBotao.setBorder(new EmptyBorder(espacoLateral, espacoLateral, 40, espacoLateral));

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j] = new JTextFieldTermo();
                matriz[i][j].setPoicao(i, j);

                final int linha = i;
                final int coluna = j;

                matriz[i][j].addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if (matriz[linha][coluna].getText().length() == 0) {
                            if (coluna < 4 && !teclaBackSpacePress) {
                                matriz[linha][coluna + 1].requestFocusInWindow();
                            } else{
                                teclaBackSpacePress = false;
                            }
                        }
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && coluna < 5 && coluna > 0 && matriz[linha][coluna].getText().length() == 0) {
                            matriz[linha][coluna - 1].requestFocusInWindow();
                            teclaBackSpacePress = true;
                        } else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE && coluna == 0 || matriz[linha][coluna].getText().length() == 1) {
                            teclaBackSpacePress = true;
                        }

                        if(e.getKeyCode() == KeyEvent.VK_ENTER) {

                            int verificaEspacoVazio = 0;
                            for(int i = 0; i < 5; i++) {
                                if(!matriz[contadorLinha][i].getText().equals("")) {
                                    verificaEspacoVazio++;
                                }
                            }
                            if(verificaEspacoVazio == 5) {
                                c.verificaPalavra(contadorLinha);
                                contadorLinha++;

                                if(linha < 5) {
                                    matriz[linha +1][0].requestFocusInWindow();
                                }
                            }

                            if(c.verificaFinalizado(contadorLinha)) {
                                if(c.isGanhou()) {
                                    JOptionPane.showMessageDialog(null, "Parabens!! Você ganhou");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Que pena!! Você perdeu");
                                }
                                for (int i = 0; i < 6; i++) {
                                    for (int j = 0; j < 5; j++) {
                                        matriz[i][j].setEnabled(false);
                                    }
                                }
                            }
                        }

                        if(e.getKeyCode() == KeyEvent.VK_LEFT && coluna > 0) {
                            matriz[linha][coluna - 1].requestFocusInWindow();
                        }
                        if(e.getKeyCode() == KeyEvent.VK_RIGHT && coluna < 4) {
                            matriz[linha][coluna + 1].requestFocusInWindow();
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
                panelCampo.add(matriz[i][j]);
            }
        }

        btnEnter.addActionListener((e) -> {
            int verificaEspacoVazio = 0;
            for(int i = 0; i < 5; i++) {
                if(!matriz[contadorLinha][i].getText().equals("")) {
                    verificaEspacoVazio++;
                }
            }
            if(verificaEspacoVazio == 5) {
                c.verificaPalavra(contadorLinha);
                contadorLinha++;
            }

            if(c.verificaFinalizado(contadorLinha)) {
                if(c.isGanhou()) {
                    JOptionPane.showMessageDialog(null, "Parabens!! Você ganhou");
                } else {
                    JOptionPane.showMessageDialog(null, "Que pena!! Você perdeu");
                    JOptionPane.showMessageDialog(null, "A palavra era" + palavraSorteada);
                }
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 5; j++) {
                        matriz[i][j].setEnabled(false);
                    }
                }
            }
            
        });

        panelBarra.add(labelNomeBarra, BorderLayout.CENTER);
        panelBotao.add(btnEnter, BorderLayout.CENTER);


        add(panelBarra, BorderLayout.NORTH);
        add(panelCampo);
        add(panelBotao, BorderLayout.SOUTH);
        
        setVisible(true);
        setSize(600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void verificaPalavra(int linha) {
        //for(int i = 0; i < 5; i++) {
            matriz[linha][0].verificarLetra(2);
            matriz[linha][1].verificarLetra(2);
            matriz[linha][2].verificarLetra(2);
            matriz[linha][3].verificarLetra(2);
            matriz[linha][4].verificarLetra(2);
        //}
    }

}
