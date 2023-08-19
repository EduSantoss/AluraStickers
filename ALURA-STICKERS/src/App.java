import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexao HTTP, permissao para acessar conteudo e buscar os top 250 filmes 

       String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
       URI endereco = URI.create(url);
       HttpClient client = HttpClient.newHttpClient(); // ou  poderia usar var client = HttpClient.newHttpClient();
       HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
       HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
       String body = response.body();

        // pegar os dados que interessam dessa string, (titulo, poster, classificaçao/rating) extrair os dados/ parsear os dados
        // terá que ser feito por expressoes regulares, mas poderia usar alguma lib como jackson
        JsonParser parser = new JsonParser(); 
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
       
        var diretorio = new File("figurinhas/");
        diretorio.mkdir();

        // exibir e manipular os dados 
        for (Map<String,String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "figurinhas/" + titulo + ".png";

            GeradorFigurinhas gerador = new GeradorFigurinhas();
            gerador.cria(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println(filme.get("imDbRating"));
            System.out.println("---------------------------");
        }

    }
}
