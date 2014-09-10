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
            novoarray[1] = "Seja bem-vindo(a)! Tente fazer uma pergunta e Roboesperto tentar� responder para voc�.";
            novoarray[2] = "Robosperto foi constru�do para ajudar a responder as suas perguntas! Tente agora mesmo.";
            novoarray[3] = "Gostaria que Robosperto respondesse alguma coisa? Por favor, utilize o portugu�s de forma correta para que Robosperto possa decodificar.";
            novoarray[4] = "Fa�a uma pergunta agora mesmo, caso Robosperto n�o saiba a resposta, ele aprender� para servir voc�.";
        }else if (atrib.equalsIgnoreCase("espera")){
            novoarray[0] = "Tem algu�m a�?";
            novoarray[1] = "O que deseja perguntar?";
            novoarray[2] = "Vamos conversar.";
            novoarray[3] = "Al�? Al�?";
            novoarray[4] = "Robosperto est� aguardando...";
        }else if (atrib.equalsIgnoreCase("naoachoupergunta")){
            novoarray[0] = "Hmm...Desculpe, Robosperto n�o possui conhecimento suficiente para responder sua pergunta.";
            novoarray[1] = "Robosperto desiste! N�o foi poss�vel encontrar uma resposta para sua pergunta.";
            novoarray[2] = "Robosperto queimou muitos neur�nios el�tricos e n�o conseguiu responder sua pergunta.";
            novoarray[3] = "N�o foi poss�vel achar uma resposta para sua pergunta, tente simplific�-la um pouco mais.";
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
        
        frase = frase.replaceAll("a", "���");
        frase = frase.replaceAll("c", "���");
        frase = frase.replaceAll("d", "���");
        frase = frase.replaceAll("e", "���");
        frase = frase.replaceAll("g", "���");
        frase = frase.replaceAll("p", "���");
        frase = frase.replaceAll("r", "���");
        frase = frase.replaceAll("1", "���");
        frase = frase.replaceAll("2", "���");
        frase = frase.replaceAll("5", "���");
        frase = frase.replaceAll("3", "���");
        frase = frase.replaceAll("4", "��#");
        frase = frase.replaceAll("6", "��%");
        
        return frase;
    }
    
    public static String DecriptarString(String frasemanip){
        int metade;
        frasemanip = frasemanip.replaceAll("��%", "6");
        frasemanip = frasemanip.replaceAll("��#", "4");
        frasemanip = frasemanip.replaceAll("���", "3");
        frasemanip = frasemanip.replaceAll("���", "5");
        frasemanip = frasemanip.replaceAll("���", "2");
        frasemanip = frasemanip.replaceAll("���", "1");
        frasemanip = frasemanip.replaceAll("���", "r");
        frasemanip = frasemanip.replaceAll("���", "p");
        frasemanip = frasemanip.replaceAll("���", "g");
        frasemanip = frasemanip.replaceAll("���", "e");
        frasemanip = frasemanip.replaceAll("���", "d");
        frasemanip = frasemanip.replaceAll("���", "c");
        frasemanip = frasemanip.replaceAll("���", "a");
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
