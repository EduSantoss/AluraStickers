import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoNasa implements ExtratorConteudo{

    public List<Conteudo> extraiConteudos(String json) {

        // pegar os dados que interessam dessa string, (titulo, poster, classificaçao/rating) extrair os dados/ parsear os dados
        // terá que ser feito por expressoes regulares, mas poderia usar alguma lib como jackson
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

       // popular a lista de conteudos
       for (Map<String, String> atributos : listaDeAtributos) {
          String titulo = atributos.get("title");
          String urlImagem = atributos.get("url");
          var conteudo = new Conteudo(titulo, urlImagem);
          conteudos.add(conteudo);
       }
        return conteudos;
    }
}
