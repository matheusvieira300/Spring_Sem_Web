package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {//Command line runner indica tarefa a ser executada logo na inicialização do spring

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// var consumoApi = new ConsumoApi();//se tacar var do lado esquerdo ele já vai reconhecer o tipo do objeto
		// var json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=e5dadcc8");
		// json = consumoApi.obterDados("https://coffee.alexflipnote.dev/random.json"); //imagem aleatória de café
		// System.out.println(json);


		// ConverteDados conversor = new ConverteDados();
		// DadosSerie dados = conversor.obterDados(json,DadosSerie.class);//dados recebe o conversor transformando o json no dados da classe record
		// System.out.println(dados); // me apresentando apenas o que eu desejo


		// json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=e5dadcc8");
		// DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
		// System.out.println(dadosEpisodio);
		// System.out.println(json);


		Principal principal = new Principal();
		principal.exibeMenu();
	}

}
		

