package br.com.alura.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados{

    private ObjectMapper mapper = new ObjectMapper(); // no Jackson é necessário um ObjectMapper, no Gson é diferente

    @Override
    public <T> T obterDados(String json, Class<T> classe) { //T Generics
        try {
            return mapper.readValue(json,classe);//para ler o Json repassado e transformar na classe informada
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
