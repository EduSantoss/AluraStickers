import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        ExtratorConteudo extrator = new ExtratorConteudoIMDB();
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

       //  ExtratorConteudo extrator = new ExtratorConteudoNasa();
       // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD-JamesWebbSpaceTelescope.json";
 
        var http = new ClienteHttp();
        String json = http.buscaDados(url);
      
        var diretorio = new File("figurinhas/");
        diretorio.mkdir();

        // exibir e manipular os dados 
        List<Conteudo> conteudos = extrator.extraiConteudos(json);
        var gerador = new GeradorFigurinhas();

        for (int i = 0; i < 10; i++) {
             
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "figurinhas/" + conteudo.getTitulo() + ".png";

            gerador.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println("---------------------------");
        }

    }
}
