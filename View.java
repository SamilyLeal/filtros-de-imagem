import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class View extends JFrame {

    private JLabel imagemLabel;
    private BufferedImage imagemOriginal;
    private BufferedImage imagemAtual;

    public View() {
        setTitle("Projeto de PLA");
        setSize(700, 500);
        iniciar();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new View());

    }

    private void iniciar() {

        imagemLabel = new JLabel();

        JButton selecionarImagem = new JButton("Selecionar imagem");

        JButton escalaDeCinza = new JButton("Escala de Cinza");
        JButton negativo = new JButton("Negativo");
        JButton suavizar = new JButton("Suavizar");

        selecionarImagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirFoto();
            }
        });

        escalaDeCinza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicarFiltro(Filter::escalaDeCinza);
            }
        });

        negativo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicarFiltro(Filter::negativo);
            }
        });


        suavizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicarFiltro(Filter::suavizacao);
            };
        });

        JPanel btnPanel = new JPanel();

        btnPanel.add(selecionarImagem);

        btnPanel.add(escalaDeCinza);
        btnPanel.add(negativo);
        btnPanel.add(suavizar);

        setLayout(new BorderLayout());
        add(imagemLabel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

    }

    private void abrirFoto() {

        JFileChooser chooser = new JFileChooser();
        int check = chooser.showOpenDialog(this);

        if (check == JFileChooser.APPROVE_OPTION) {

            File arquivo = chooser.getSelectedFile();

            try {

                imagemOriginal = ImageIO.read(arquivo);
                imagemAtual = imagemOriginal;

                exibirImagem();

            } catch (IOException e) {

                System.out.println("Erro ao tentar abrir arquivo: " + e.getMessage());

            }
        }

    }

    private void aplicarFiltro(FilterFunction filtro) {

        if (imagemAtual != null) {

            imagemAtual = filtro.aplicar(imagemAtual);
            
            exibirImagem();
        }

    }

    interface FilterFunction {

        BufferedImage aplicar(BufferedImage imagem);

    }

    private void exibirImagem() {

        ImageIcon imagemIcon = new ImageIcon(imagemAtual);
        imagemLabel.setIcon(imagemIcon);
        
    }

}
