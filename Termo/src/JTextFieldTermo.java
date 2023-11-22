import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

public class JTextFieldTermo extends JTextField {

    Color corFundo;
    Color corBordaPadrao;
    Color corBordaFoco;
    Color corCerta = new Color(58, 163, 148);
    Color corLugarErrado = new Color(211, 173, 105);
    Color corErrado = new Color(49, 42, 44);
    int linha;
    int coluna;
    String letra;
    
    

    public JTextFieldTermo() {
        Font fonte = new Font("Mitr", Font.BOLD, 50);
        corFundo = new Color(110, 92, 98);
        corBordaPadrao = new Color(76, 67, 71);
        corBordaFoco = Color.BLUE;
        this.setSize(70, 70);
        this.setHorizontalAlignment(this.CENTER);
        this.setFont(fonte);
        this.setBackground(corFundo);
        this.setForeground(Color.WHITE);

        this.setBorder(new LineBorder(corBordaPadrao, 4));

        this.addFocusListener((FocusListener) new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                Border outer = new LineBorder(corBordaPadrao, 2); // Borda externa padrão
                Border inner = new MatteBorder(2, 2, 6, 2, corBordaPadrao); // Borda interna com linha inferior mais espessa
                setBorder(new CompoundBorder(outer, inner));
            }

            @Override
            public void focusLost(FocusEvent e) {
                Border border = new LineBorder(corBordaPadrao, 4);
                setBorder(border);
            }
        });

        AbstractDocument doc = (AbstractDocument) this.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int maxLength = 1;
                if (currentLength + text.length() - length <= maxLength) {
                    super.replace(fb, offset, length, text.toUpperCase(), attrs);
                } else {
                    //JOptionPane.showMessageDialog(null, "Limite de caracteres atingido (máximo: " + maxLength + " caracteres).");
                }
            }
        });

    }

    // 1 = letra errada
    // 2 = letra certa no lugar certo
    // 3 = letra certa no lugar errado
    public void verificarLetra(int n) {
        switch (n) {
            case 1:
                this.setBackground(corErrado);
                break;
            case 2:
                this.setBackground(corCerta);
                break;
            case 3:
                this.setBackground(corLugarErrado); 
                break;
        }
    }

    public void setPoicao(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }
}
