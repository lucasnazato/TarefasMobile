package com.example.md_aula_2022_03_25.dao;

import com.example.md_aula_2022_03_25.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    // Declarar variaveis
    private final static List<Personagem> personagens = new ArrayList<>();  // Lista para conter os personagens
    private static int contadorDeIds = 1;                                   // Var para contar ids da lista

    // Define a ID de um personagem e salva adicona ele na lista, e cham o metodo para incrementar o id
    public void salva(Personagem personagemSalvo){
        personagemSalvo.setId(contadorDeIds);
        personagens.add(personagemSalvo);
        atualizaId();
    }

    // Incrementa o contador de id da lista
    private void atualizaId() { contadorDeIds++; }

    // Verifica se o personagem recebido pelo metodo é valido e se for edita o personagem na posição indicada na lista
    public void edita(Personagem personagem){
        Personagem personagemEncontrado = buscaPersonagemId(personagem);
        if(personagemEncontrado != null){
            int posicaoDoPersonagem = personagens.indexOf(personagemEncontrado);
            personagens.set(posicaoDoPersonagem,personagem);
        }
    }

    // Faz um loop pela lista de personagens verificando as ids e retorna o personagem com a id correta
    private Personagem buscaPersonagemId(Personagem personagem){
        for (Personagem p:
                personagens) {
                if (p.getId() == personagem.getId()) {
                    return p;
                }
            }
        return null;
    }

    //
    public List<Personagem> todos() {return new ArrayList<>(personagens);}

    // Verifica se o personagem recebido pelo metodo é valido e se for remove ele da lista
    public void remove(Personagem personagem){
        Personagem personagemDevolvido = buscaPersonagemId(personagem);
        if (personagemDevolvido != null){
            personagens.remove(personagemDevolvido);
        }
    }
}
