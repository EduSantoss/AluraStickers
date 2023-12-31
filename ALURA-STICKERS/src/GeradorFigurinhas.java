import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradorFigurinhas {

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception  {
        // leitura da imagem
        // por algum motivo estava dando erro ao botar pathname:"entrada/filme.jpg"
        // InputStream inputStream = 
        //               new FileInputStream(new File("entrada/filme.jpg"));
        // InputStream inputStream = 
        //              new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/    TopMovies_1.jpg").openStream();
        // inputStream apresenta bem o o conceito de polimorfismo, nesse caso, poder ler bytes de varias formas/fontes
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // criar nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova imagem (em memória) drawimage para escrever sobre a imagem antiga
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.PINK);
        graphics.setFont(fonte);

        // escrever uma frase na nova imagem 
        graphics.drawString("Bom Filme", 100, novaAltura - 100);

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));

    }

}
