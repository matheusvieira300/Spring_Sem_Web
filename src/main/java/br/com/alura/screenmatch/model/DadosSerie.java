package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)//para Ignorar os dados Json que eu não quiser usar para não ocorrer uma Exception
public record DadosSerie(
                        
                        @JsonAlias("Title") String titulo,//Json Alias apelido para quando for consumir a API ele,
                         //entender que esse atributo se refere a Title na API DESSERIALIZADO
                        @JsonAlias("totalSeasons")Integer totalDeTemporadas,
                        @JsonAlias("imdbRating")String avaliacao
                        
                        ) {
}
//@JsonProperty("imdbVotes") ele serve para pegar o Json e apresentar o nome da forma que ele é escrito, no caso imdbVotes independente do nome do atributo
//SERIALIZADO
