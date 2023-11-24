package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=e5dadcc8";

    public void exibeMenu() {

        // DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"); exemplo de formatação de data, mês e ano.
        // LocalDateTime agora = LocalDateTime.now();
        // System.out.println(agora.format(formatador));
        
        System.out.println("Digite o nome da série para busca ?");
        var nomeSerie = leitura.nextLine();

        var consumoApi = new ConsumoApi();//se tacar var do lado esquerdo ele já vai reconhecer o tipo do objeto

        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);//dados recebe o conversor transformando o json no dados da classe record

        System.out.println(dados);
        //https://www.omdbapi.com/?t=gilmore+girls&apikey=e5dadcc8"

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dados.totalDeTemporadas(); i++) {
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);//percorrer a lista
        temporadas.forEach(t -> System.out.println());// a mesma linha de cima só que de forma não resumida (argumentos) -> { corpo-da-função }

        for (int i = 0; i < dados.totalDeTemporadas(); i++) {
            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();// pegando a temporada e os episódios  que estão lá
            for (int j = 0; j < episodiosTemporada.size(); j++) {
                System.out.println(episodiosTemporada.get(j).titulo());//pegando o título de cada episódio
            }
        }

        //atráves do forEach dentro do parenteses o java já irá saber que você irá trabalhar com um dado do tipo temporadas.
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));// nessa linha lambda é possível fazer tudo que está na linha acima comentada

        //List<String> nomes = Arrays.asList("Jaque" , "Iasmin", "Paulo", "Rodrigo", "Nico");

        //stream serve para operações encadeadas
        // nomes.stream()
        //         .sorted()//sorted para ordenar alfabeticamente
        //         .limit(3)//para impor limite e aparecer apenas os três primeiros
        //         .filter(n -> n.startsWith("N"))//para filtrar apenas com os nomes que iniciam com a letra N
        //         .map(n -> n.toUpperCase())// para transformar o nome em maiúsculo
        //         .forEach(System.out::println);//para iterar e imprimir os dados

        //Lista dadosEpisodios vai possuir todos os dados de todas as temporadas
        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
            .flatMap(t -> t.episodios().stream())
            //.toList() Lista imutável não dá pra retirar nem adicionar nada
            .collect(Collectors.toList());//possível adicionar e remover
        
        System.out.println("\n Top 10 episódios");
        //operação encadeada stream
          dadosEpisodios.stream()
            .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))//para filtrar e excluir todos que foram N/A
            .peek(e -> System.out.println("Primeiro filtro(N/A)" + e))//para ver cada etapa da operação encadeada
            .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())//para comparar a avaliação e o reversed para inverter de forma decrescente
            .peek(e -> System.out.println("Ordenação: " + e))
            .limit(10)
            .peek(e -> System.out.println("Limite: " + e))
            .map(e -> e.titulo().toUpperCase())
            .peek(e -> System.out.println("Mapeamento: " + e))
            .forEach(System.out::println);  


        // List<Episodio> episodios = temporadas.stream()
        //     .flatMap(t -> t.episodios().stream()
        //         .map(d -> new Episodio(t.numero(), d))
        //     ).collect(Collectors.toList());

        // episodios.forEach(System.out::println);

        // System.out.println("A partir de que ano você deseja ver os episódios? ");
        // var ano = leitura.nextInt();
        // leitura.nextLine();

        // LocalDate dataBusca = LocalDate.of(ano, 1, 1);//para pegar o ano, mês e dia

        // DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");//para apresentar a data no formato brasileiro

        // episodios.stream()
        //     .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
        //     .forEach(e -> System.out.println(
        //         "Temporada: " + e.getTemporada() +
        //             "Episódio: " + e.getTitulo() +
        //             "Data Lançamento: " + e.getDataLancamento().format(formatador)
        //     ));

    }
}