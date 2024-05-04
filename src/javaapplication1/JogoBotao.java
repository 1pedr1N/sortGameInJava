import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class JogoBotao extends JFrame {

    private ArrayList<Integer> sequencia = new ArrayList<>();
    private int index = 0;
    private boolean primeiroClicado = false;

    public JogoBotao() {
        setTitle("Jogo de Botões");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(2, 2));

        JButton[] botoes = new JButton[4];
        for (int i = 0; i < 4; i++) {
            botoes[i] = new JButton("?");
            add(botoes[i]);
        }

        ArrayList<Integer> posicoes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            posicoes.add(i);
        }
        Collections.shuffle(posicoes);

        gerarSequencia();

        for (int i = 0; i < 4; i++) {
            final int valor = sequencia.get(i);
            botoes[posicoes.get(i)].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton botaoClicado = (JButton) e.getSource();
                    if (!primeiroClicado) {
                        if (valor == 1) {
                            botaoClicado.setText(String.valueOf(valor));
                            index++;
                            primeiroClicado = true;
                        }
                    } else {
                        if (botaoClicado.getText().equals("?")) {
                            botaoClicado.setText(String.valueOf(valor));
                            if (valor == index + 1) {
                                index++;
                                if (index == 4) {
                                    JOptionPane.showMessageDialog(null, "ganhou
                                    reiniciarJogo();
                                }
                            }
                        }
                    }
                }
            });
        }

        setVisible(true);
    }

    private void gerarSequencia() {
        sequencia.clear();
        for (int i = 1; i <= 4; i++) {
            sequencia.add(i);
        }
        Collections.shuffle(sequencia);
        index = 0;
        primeiroClicado = false;
    }

    private void reiniciarJogo() {
        gerarSequencia();
        for (Component componente : getContentPane().getComponents()) {
            if (componente instanceof JButton) {
                JButton botao = (JButton) componente;
                botao.setText("?");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JogoBotao();
            }
        });
    }
}
