/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oyatsumi
 */
import java.util.Random;

public class Processando {
    private static String[] novoarray = new String[5];

    
    public static String RetornarFrase(String atrib){
            Random random = new Random();  
            int valor = random.nextInt(4)+1; //variando de 1 a 5
        if (atrib.equalsIgnoreCase("boasvindas")){
            novoarray[0] = "Quer perguntar algo?";
            novoarray[1] = "Seja bem-vindo(a)! Tente fazer uma pergunta e Roboesperto tentará responder para você.";
            novoarray[2] = "Robosperto foi construído para ajudar a responder as suas perguntas! Tente agora mesmo.";
            novoarray[3] = "Gostaria que Robosperto respondesse alguma coisa? Por favor, utilize o português de forma correta para que Robosperto possa decodificar.";
            novoarray[4] = "Faça uma pergunta agora mesmo, caso Robosperto não saiba a resposta, ele aprenderá para servir você.";
        }else if (atrib.equalsIgnoreCase("espera")){
            novoarray[0] = "Tem alguém aí?";
            novoarray[1] = "O que deseja perguntar?";
            novoarray[2] = "Vamos conversar.";
            novoarray[3] = "Alô? Alô?";
            novoarray[4] = "Robosperto está aguardando...";
        }else if (atrib.equalsIgnoreCase("naoachoupergunta")){
            novoarray[0] = "Hmm...Desculpe, Robosperto não possui conhecimento suficiente para responder sua pergunta.";
            novoarray[1] = "Robosperto desiste! Não foi possível encontrar uma resposta para sua pergunta.";
            novoarray[2] = "Robosperto queimou muitos neurônios elétricos e não conseguiu responder sua pergunta.";
            novoarray[3] = "Não foi possível achar uma resposta para sua pergunta, tente simplificá-la um pouco mais.";
            novoarray[4] = "S.O.S, nenhuma resposta encontrada!";
        }  
        
       return novoarray[valor];     
    }

    
    public static String EncriptarString(String frase) {
        int metade;
        if ((frase.length() % 2) > 0) {
            metade = (frase.length() - 1) / 2;
        } else {
            metade = (frase.length()) / 2;
        }
        frase = frase.substring(metade, frase.length()).concat(frase.substring(0, metade));
        StringBuilder novo = new StringBuilder(frase);
        novo.reverse();
        frase = novo.toString();
        
        frase = frase.replaceAll("a", "§¢³");
        frase = frase.replaceAll("c", "§£¢");
        frase = frase.replaceAll("d", "§¬¢");
        frase = frase.replaceAll("e", "§¢¢");
        frase = frase.replaceAll("g", "§¢¬");
        frase = frase.replaceAll("p", "§ºº");
        frase = frase.replaceAll("r", "§¬§");
        frase = frase.replaceAll("1", "§ºª");
        frase = frase.replaceAll("2", "§¹ª");
        frase = frase.replaceAll("5", "§ª¹");
        frase = frase.replaceAll("3", "§ª§");
        frase = frase.replaceAll("4", "§§#");
        frase = frase.replaceAll("6", "§§%");
        
        return frase;
    }
    
    public static String DecriptarString(String frasemanip){
        int metade;
        frasemanip = frasemanip.replaceAll("§§%", "6");
        frasemanip = frasemanip.replaceAll("§§#", "4");
        frasemanip = frasemanip.replaceAll("§ª§", "3");
        frasemanip = frasemanip.replaceAll("§ª¹", "5");
        frasemanip = frasemanip.replaceAll("§¹ª", "2");
        frasemanip = frasemanip.replaceAll("§ºª", "1");
        frasemanip = frasemanip.replaceAll("§¬§", "r");
        frasemanip = frasemanip.replaceAll("§ºº", "p");
        frasemanip = frasemanip.replaceAll("§¢¬", "g");
        frasemanip = frasemanip.replaceAll("§¢¢", "e");
        frasemanip = frasemanip.replaceAll("§¬¢", "d");
        frasemanip = frasemanip.replaceAll("§£¢", "c");
        frasemanip = frasemanip.replaceAll("§¢³", "a");
        StringBuilder novo = new StringBuilder(frasemanip);
        novo.reverse();
        frasemanip = novo.toString();
        if ((frasemanip.length() % 2) > 0) {
            metade = (frasemanip.length() + 1) / 2;
        } else {
            metade = (frasemanip.length()) / 2;
        }
        frasemanip = frasemanip.substring(metade, frasemanip.length()).concat(frasemanip.substring(0, metade));
        return frasemanip;
    }
}
