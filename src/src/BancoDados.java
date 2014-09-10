
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
            MainFrame.Falar("+{ERRO-004}+ Houve um erro ao tentar criar um backup do banco de dados existente. Você pode continuar usando o programa caso nenhuma outra excessão seja encontrada.");
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
            MainFrame.Falar("+{ERRO-005}+ Houve um erro ao carregar o banco de dados do arquivo: '"+nomedoarquivo+"', se preferir, dê privilégios de administrador ao programa, reabra o mesmo para que ele tente novamente; caso contrário o banco de dados utilizado a partir de agora será o banco padrão do programa, com as perguntas iniciais.");
            bancoarray.add("");
            bancoarray.add("qual|seu|nome::&::"+Processando.EncriptarString("Meu nome é Robosperto. Muito prazer."));
            bancoarray.add("quem|descobriu|brasil::&::"+Processando.EncriptarString("Pedro Álvares Cabral."));
            bancoarray.add("quem|e|voce::&::"+Processando.EncriptarString("Sou o Robosperto, fui desenvolvido para ajudar a responder perguntas e aprender conforme necessário."));
            bancoarray.add("quem|voce::&::"+Processando.EncriptarString("Sou o Robosperto, fui desenvolvido para ajudar a responder perguntas e aprender conforme necessário."));
            bancoarray.add("onde|posso|achar::&::"+Processando.EncriptarString("Tente procurar no Google."));
            bancoarray.add("em|que|linguagem|voce|foi|desenvolvido::&::"+Processando.EncriptarString("A linguagem utilizada durante o meu desenvolvimento foi o Java."));
            bancoarray.add("como|esta|tempo|hoje::&::"+Processando.EncriptarString("Desculpe mas eu não sou homem do tempo, por favor olhe pela sua janela."));
            bancoarray.add("quantos|anos|voce|tem::&::"+Processando.EncriptarString("Robôs como eu não envelhecem, minha idade pouco importa."));
            bancoarray.add("qual|sua|idade::&::"+Processando.EncriptarString("Robôs como eu não envelhecem, minha idade pouco importa."));
            bancoarray.add("quem|descobriu|america::&::"+Processando.EncriptarString("Cristóvão Colombo."));
            bancoarray.add("qual|seu|pais::&::"+Processando.EncriptarString("Sou brasileiro, com muito orgulho!"));
            bancoarray.add("quem|criou|voce::&::"+Processando.EncriptarString("Érick Oliveira Rodrigues."));
            bancoarray.add("quem|e|seu|criador::&::"+Processando.EncriptarString("Érick Oliveira Rodrigues."));
            bancoarray.add("qual|meu|futuro::&::"+Processando.EncriptarString("Só o destino pode responder essa pergunta."));
            bancoarray.add("quantas|linguas|voce|fala::&::"+Processando.EncriptarString("Robosperto compreende apenas o português."));
            bancoarray.add("quantas|linguas|voce|entende::&::"+Processando.EncriptarString("Robosperto compreende apenas o português."));
            bancoarray.add("qual|fisico|mais|importante::&::"+Processando.EncriptarString("Albert Einstein."));
            bancoarray.add("onde|voce|foi|feito::&::"+Processando.EncriptarString("Robosperto não possui partes físicas, fui construído apenas com software por um desenvolvedor brasileiro."));
            bancoarray.add("voce|tem|pais::&::"+Processando.EncriptarString("Hmm... Não."));
            bancoarray.add("voce|come::&::"+Processando.EncriptarString("Robôs não comem, recebemos nossa energia de outra forma."));
            bancoarray.add("qual|seu|sistema|operacional::&::"+Processando.EncriptarString("Não possuo sistema operacional único, fui programado em java e rodo em diversos sistemas."));
            bancoarray.add("qual|seu|so::&::"+Processando.EncriptarString("Não possuo sistema operacional único, fui programado em java e rodo em diversos sistemas."));
            bancoarray.add("oi::&::"+Processando.EncriptarString("Olá."));
            bancoarray.add("ola::&::"+Processando.EncriptarString("Oi, seja bem-vindo(a)."));
            bancoarray.add("tudo|bem::&::"+Processando.EncriptarString("Não fui programado com emoções, não posso responder essa pergunta."));
            bancoarray.add("qual|melhor|e|sistema|operacional::&::"+Processando.EncriptarString("Pergunta difícil... O que Robosperto prefere é o linux Ubuntu."));
            bancoarray.add("qual|e|melhor|navegador::&::"+Processando.EncriptarString("Robosperto não sabe qual é o melhor, mas sabe que não é o Internet Explorer."));
            bancoarray.add("qual|e|melhor|browser::&::"+Processando.EncriptarString("Robosperto não sabe qual é o melhor, mas sabe que não é o Internet Explorer."));
            bancoarray.add("qual|e|motivo|de|sua|existencia::&::"+Processando.EncriptarString("Existo para te ajudar a responder as suas perguntas."));
            bancoarray.add("que|voce|sabe|fazer::&::"+Processando.EncriptarString("Sou apenas um enterpretador de perguntas."));
            bancoarray.add("tem|outros|iguais|voce::&::"+Processando.EncriptarString("Eu sou único, porém instanciado em várias máquinas."));
            bancoarray.add("ha|outros|iguais|voce::&::"+Processando.EncriptarString("Eu sou único, porém instanciado em várias máquinas."));
            bancoarray.add("outros|iguais|voce::&::"+Processando.EncriptarString("Eu sou único, porém instanciado em várias máquinas."));
            bancoarray.add("voce|macho|ou|femea::&::"+Processando.EncriptarString("Robôs não possuem sexo..."));
            bancoarray.add("voce|femea::&::"+Processando.EncriptarString("Robôs não possuem sexo..."));
            bancoarray.add("voce|macho::&::"+Processando.EncriptarString("Robôs não possuem sexo..."));
            bancoarray.add("voce|e|macho::&::"+Processando.EncriptarString("Robôs não possuem sexo..."));
            bancoarray.add("sabe|escrever::&::"+Processando.EncriptarString("Se você está lendo agora, é porque sim, Robosperto sabe escrever."));
            bancoarray.add("como|voce|foi|projetado::&::"+Processando.EncriptarString("Fui projetado com base em um projeto de inteligência artificial, e programado em Java."));
            bancoarray.add("onde|voce|veio::&::"+Processando.EncriptarString("Vim do mundo digital. Será mesmo que eu existo?"));
            bancoarray.add("de|onde|voce::&::"+Processando.EncriptarString("Vim do mundo digital. Será mesmo que eu existo?"));
            bancoarray.add("que|planeta|voce|e::&::"+Processando.EncriptarString("Vim do mundo digital. Planeta não identificado. Será mesmo que eu existo?"));
            bancoarray.add("planeta|voce::&::"+Processando.EncriptarString("Tem certeza que não está me confundindo com um alienígena?"));
            bancoarray.add("de|qual|planeta|voce|veio::&::"+Processando.EncriptarString("Tem certeza que não está me confundindo com um alienígena?"));
            bancoarray.add("como|e|nao|ter|emoçoes::&::"+Processando.EncriptarString("Ora, se eu não tenho emoções, não posso te dizer 'como é'."));
            bancoarray.add("voce|tem|emoçoes::&::"+Processando.EncriptarString("Nenhum robô até o presente momento possui emoções reais. Apenas simulações..."));
            bancoarray.add("voce|possui|emoçoes::&::"+Processando.EncriptarString("Nenhum robô até o presente momento possui emoções reais. Apenas simulações..."));
            bancoarray.add("qual|tipo|energia|usa|alimento::&::"+Processando.EncriptarString("Energia elétrica, é claro."));
            bancoarray.add("possui|armamento::&::"+Processando.EncriptarString("De forma alguma, sou um robô paz e amor!"));
            bancoarray.add("possui|armas::&::"+Processando.EncriptarString("De forma alguma, sou um robô paz e amor!"));
            bancoarray.add("responda|pergunta::&::"+Processando.EncriptarString("Então informe-a por favor!"));
        }
    }
}
