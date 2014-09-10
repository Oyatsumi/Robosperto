
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author oyatsumi
 */
public class BancoDados {

    public static ArrayList<String> bancoarray;
    public static String nomedoarquivo = "dados.bds";
    private static boolean verificar2 = false, verifsave = false;

    public static void CarregarBanco(String nomedoarquivoint) {
        boolean verificar = false;
        bancoarray = new ArrayList<String>();
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(nomedoarquivoint);
            in = new ObjectInputStream(fis);
            try {
                bancoarray = (ArrayList<String>) in.readObject();
            } catch (ClassNotFoundException ex) {
                //MainFrame.Falar("+ERRO{001}+ Problema ao abrir o banco de dados. Envie essa mensagem para o desenvolvedor");
                Logger.getLogger(BancoDados.class.getName()).log(Level.SEVERE, null, ex);
                verificar = true;
            }
            in.close();
        } catch (IOException ex) {
            //MainFrame.Falar("+ERRO{002}+ Problema ao abrir o banco de dados. Envie essa mensagem para o desenvolvedor");
            Logger.getLogger(BancoDados.class.getName()).log(Level.SEVERE, null, ex);
            verificar = true;
        }
        //tratar erro de abertura do arquivo.
        if (verificar) {
            if (!verificar2) {
                try {
                    Thread.sleep(200);
                    verificar2 = true;
                    CarregarBanco(nomedoarquivoint);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BancoDados.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                //tratar erro depois da segunda tentativa
                TratarCarregamento(nomedoarquivoint);
            }
        }else{verificar2 = false;}
    }

    public static void SalvarBanco() {
        
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            CopiarArquivo(nomedoarquivo, nomedoarquivo+".backup");
        } catch (IOException ex) {
            Logger.getLogger(BancoDados.class.getName()).log(Level.SEVERE, null, ex);
            MainFrame.Falar("+{ERRO-004}+ Houve um erro ao tentar criar um backup do banco de dados existente. Voc� pode continuar usando o programa caso nenhuma outra excess�o seja encontrada.");
        }
        try {
            fos = new FileOutputStream(nomedoarquivo);
            out = new ObjectOutputStream(fos);
            out.writeObject(bancoarray);
            out.close();
            MainFrame.Falar("Banco de dados salvo com sucesso.");
        } catch (IOException ex) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex1) {
                Logger.getLogger(BancoDados.class.getName()).log(Level.SEVERE, null, ex1);
            }
            if (verifsave){verifsave = false;SalvarBanco();}else{
                MainFrame.Falar("+{ERRO-003}+ Problema ao salvar o banco de dados. Os dados novos serao perdidos. Envie essa mensagem para o desenvolvedor");
                Logger.getLogger(BancoDados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    private static void CopiarArquivo(String origem, String destino) throws IOException {
        File sourceFile = new File(origem);
        File destFile = new File(destino);
        
        //substitui o arquivo
        destFile.createNewFile();

        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
     }
    
    
    
    public static void TratarCarregamento(String narquivo) {
        verificar2 = false;
        if (narquivo.equals(nomedoarquivo)){
            CarregarBanco(nomedoarquivo+".backup");
        }else{
            //nao foi possivel abrir de jeito nenhum o banco e agora vamos tratar com as frases padroes.
            MainFrame.Falar("+{ERRO-005}+ Houve um erro ao carregar o banco de dados do arquivo: '"+nomedoarquivo+"', se preferir, d� privil�gios de administrador ao programa, reabra o mesmo para que ele tente novamente; caso contr�rio o banco de dados utilizado a partir de agora ser� o banco padr�o do programa, com as perguntas iniciais.");
            bancoarray.add("");
            bancoarray.add("qual|seu|nome::&::"+Processando.EncriptarString("Meu nome � Robosperto. Muito prazer."));
            bancoarray.add("quem|descobriu|brasil::&::"+Processando.EncriptarString("Pedro �lvares Cabral."));
            bancoarray.add("quem|e|voce::&::"+Processando.EncriptarString("Sou o Robosperto, fui desenvolvido para ajudar a responder perguntas e aprender conforme necess�rio."));
            bancoarray.add("quem|voce::&::"+Processando.EncriptarString("Sou o Robosperto, fui desenvolvido para ajudar a responder perguntas e aprender conforme necess�rio."));
            bancoarray.add("onde|posso|achar::&::"+Processando.EncriptarString("Tente procurar no Google."));
            bancoarray.add("em|que|linguagem|voce|foi|desenvolvido::&::"+Processando.EncriptarString("A linguagem utilizada durante o meu desenvolvimento foi o Java."));
            bancoarray.add("como|esta|tempo|hoje::&::"+Processando.EncriptarString("Desculpe mas eu n�o sou homem do tempo, por favor olhe pela sua janela."));
            bancoarray.add("quantos|anos|voce|tem::&::"+Processando.EncriptarString("Rob�s como eu n�o envelhecem, minha idade pouco importa."));
            bancoarray.add("qual|sua|idade::&::"+Processando.EncriptarString("Rob�s como eu n�o envelhecem, minha idade pouco importa."));
            bancoarray.add("quem|descobriu|america::&::"+Processando.EncriptarString("Crist�v�o Colombo."));
            bancoarray.add("qual|seu|pais::&::"+Processando.EncriptarString("Sou brasileiro, com muito orgulho!"));
            bancoarray.add("quem|criou|voce::&::"+Processando.EncriptarString("�rick Oliveira Rodrigues."));
            bancoarray.add("quem|e|seu|criador::&::"+Processando.EncriptarString("�rick Oliveira Rodrigues."));
            bancoarray.add("qual|meu|futuro::&::"+Processando.EncriptarString("S� o destino pode responder essa pergunta."));
            bancoarray.add("quantas|linguas|voce|fala::&::"+Processando.EncriptarString("Robosperto compreende apenas o portugu�s."));
            bancoarray.add("quantas|linguas|voce|entende::&::"+Processando.EncriptarString("Robosperto compreende apenas o portugu�s."));
            bancoarray.add("qual|fisico|mais|importante::&::"+Processando.EncriptarString("Albert Einstein."));
            bancoarray.add("onde|voce|foi|feito::&::"+Processando.EncriptarString("Robosperto n�o possui partes f�sicas, fui constru�do apenas com software por um desenvolvedor brasileiro."));
            bancoarray.add("voce|tem|pais::&::"+Processando.EncriptarString("Hmm... N�o."));
            bancoarray.add("voce|come::&::"+Processando.EncriptarString("Rob�s n�o comem, recebemos nossa energia de outra forma."));
            bancoarray.add("qual|seu|sistema|operacional::&::"+Processando.EncriptarString("N�o possuo sistema operacional �nico, fui programado em java e rodo em diversos sistemas."));
            bancoarray.add("qual|seu|so::&::"+Processando.EncriptarString("N�o possuo sistema operacional �nico, fui programado em java e rodo em diversos sistemas."));
            bancoarray.add("oi::&::"+Processando.EncriptarString("Ol�."));
            bancoarray.add("ola::&::"+Processando.EncriptarString("Oi, seja bem-vindo(a)."));
            bancoarray.add("tudo|bem::&::"+Processando.EncriptarString("N�o fui programado com emo��es, n�o posso responder essa pergunta."));
            bancoarray.add("qual|melhor|e|sistema|operacional::&::"+Processando.EncriptarString("Pergunta dif�cil... O que Robosperto prefere � o linux Ubuntu."));
            bancoarray.add("qual|e|melhor|navegador::&::"+Processando.EncriptarString("Robosperto n�o sabe qual � o melhor, mas sabe que n�o � o Internet Explorer."));
            bancoarray.add("qual|e|melhor|browser::&::"+Processando.EncriptarString("Robosperto n�o sabe qual � o melhor, mas sabe que n�o � o Internet Explorer."));
            bancoarray.add("qual|e|motivo|de|sua|existencia::&::"+Processando.EncriptarString("Existo para te ajudar a responder as suas perguntas."));
            bancoarray.add("que|voce|sabe|fazer::&::"+Processando.EncriptarString("Sou apenas um enterpretador de perguntas."));
            bancoarray.add("tem|outros|iguais|voce::&::"+Processando.EncriptarString("Eu sou �nico, por�m instanciado em v�rias m�quinas."));
            bancoarray.add("ha|outros|iguais|voce::&::"+Processando.EncriptarString("Eu sou �nico, por�m instanciado em v�rias m�quinas."));
            bancoarray.add("outros|iguais|voce::&::"+Processando.EncriptarString("Eu sou �nico, por�m instanciado em v�rias m�quinas."));
            bancoarray.add("voce|macho|ou|femea::&::"+Processando.EncriptarString("Rob�s n�o possuem sexo..."));
            bancoarray.add("voce|femea::&::"+Processando.EncriptarString("Rob�s n�o possuem sexo..."));
            bancoarray.add("voce|macho::&::"+Processando.EncriptarString("Rob�s n�o possuem sexo..."));
            bancoarray.add("voce|e|macho::&::"+Processando.EncriptarString("Rob�s n�o possuem sexo..."));
            bancoarray.add("sabe|escrever::&::"+Processando.EncriptarString("Se voc� est� lendo agora, � porque sim, Robosperto sabe escrever."));
            bancoarray.add("como|voce|foi|projetado::&::"+Processando.EncriptarString("Fui projetado com base em um projeto de intelig�ncia artificial, e programado em Java."));
            bancoarray.add("onde|voce|veio::&::"+Processando.EncriptarString("Vim do mundo digital. Ser� mesmo que eu existo?"));
            bancoarray.add("de|onde|voce::&::"+Processando.EncriptarString("Vim do mundo digital. Ser� mesmo que eu existo?"));
            bancoarray.add("que|planeta|voce|e::&::"+Processando.EncriptarString("Vim do mundo digital. Planeta n�o identificado. Ser� mesmo que eu existo?"));
            bancoarray.add("planeta|voce::&::"+Processando.EncriptarString("Tem certeza que n�o est� me confundindo com um alien�gena?"));
            bancoarray.add("de|qual|planeta|voce|veio::&::"+Processando.EncriptarString("Tem certeza que n�o est� me confundindo com um alien�gena?"));
            bancoarray.add("como|e|nao|ter|emo�oes::&::"+Processando.EncriptarString("Ora, se eu n�o tenho emo��es, n�o posso te dizer 'como �'."));
            bancoarray.add("voce|tem|emo�oes::&::"+Processando.EncriptarString("Nenhum rob� at� o presente momento possui emo��es reais. Apenas simula��es..."));
            bancoarray.add("voce|possui|emo�oes::&::"+Processando.EncriptarString("Nenhum rob� at� o presente momento possui emo��es reais. Apenas simula��es..."));
            bancoarray.add("qual|tipo|energia|usa|alimento::&::"+Processando.EncriptarString("Energia el�trica, � claro."));
            bancoarray.add("possui|armamento::&::"+Processando.EncriptarString("De forma alguma, sou um rob� paz e amor!"));
            bancoarray.add("possui|armas::&::"+Processando.EncriptarString("De forma alguma, sou um rob� paz e amor!"));
            bancoarray.add("responda|pergunta::&::"+Processando.EncriptarString("Ent�o informe-a por favor!"));
        }
    }
}
