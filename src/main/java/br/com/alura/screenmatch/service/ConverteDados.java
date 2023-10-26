package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.model.DadosSerie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados{
    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json,classe);//para ler o Json repassado e transformar na classe informada
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
