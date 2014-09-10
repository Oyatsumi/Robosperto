
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oyatsumi
 */
public class TratandoEntrada {
    private static String retorno = "";
    private static String operacaojuntar = "";
    public static int indiceultimapergunta;
    
    public static void Receber(String entrada){
        //indiceultimapergunta = 0;
        
        if (!retorno.equals("aprenderpergunta2")){entrada = LimparEntrada(entrada);}
      
        if (entrada.equals("cancelar") || entrada.equals("nao")){//esse tem que ficar em primeiro.
            retorno = "";
        }else if (retorno.equals("protegersenha") && entrada.equals("sim")){
            ProtegerSenha();  
        }else if (retorno.equals("apagarsenha") && entrada.equals("sim")){
            ApagarSenha();  
        }else if (retorno.equals("apagarpergunta1") && entrada.equals("sim")){
            ApagarUltimaPergunta();
            retorno = "";
        }else if (retorno.equals("aprenderpergunta1") || retorno.equals("aprenderpergunta2")){
            AprenderPergunta(entrada, retorno);
        }else if (entrada.equals("salvar|banco|dados") || entrada.equals("salvar|dados")){
            BancoDados.SalvarBanco();
        }else if (entrada.equals("desenvolvedor") || entrada.equals("quem|e|seu|desenvolvedor") || entrada.equals("quem|desenvolveu|voce") || entrada.equals("quem|seu|desenvolvedor")){
            MainFrame.Falar("Érick Oliveira Rodrigues");
        }else if (entrada.equals("que|horas|sao") || entrada.equals("que|horas|sao|agora") || entrada.equals("quantas|horas|sao")){
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat ("hh:mm:ss");
            MainFrame.Falar("São exatamente "+ft.format(dNow)+".");
        }else if (entrada.equals("que|dia|e|hoje") || entrada.equals("hoje|e|que|dia") || entrada.equals("em|que|mes|estamos") || entrada.equals("que|dia|hoje")){
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
            MainFrame.Falar("A data de hoje é "+ft.format(dNow)+".");
        }else if(entrada.equals("comandos") || entrada.equals("comandos|principais") || entrada.equals("principais|comandos") || entrada.equals("comandos|especificos")){
            MainFrame.Falar("Os comandos específicos sao (digite as frases): \n1. Aprender nova pergunta.\n2. Apagar última pergunta.\n3. Salvar banco de dados.\n4. Proteger com uma senha.\n5. Apagar senha.");
        }else if(entrada.equals("apagar|ultima|pergunta") || entrada.equals("deletar|ultima|pergunta")){
            ApagarUltimaPergunta();
        }else if(entrada.equals("proteger|com|senha") || entrada.equals("proteger|com|uma|senha") || entrada.equals("proteger|senha") || entrada.equals("proteger|uma|senha")){
            ProtegerSenha();
        }else if(entrada.equals("aprender|nova|pergunta") || entrada.equals("ensinar|nova|pergunta") || entrada.equals("nova|pergunta") || entrada.equals("aprender|pergunta") || entrada.equals("adicionar|pergunta") || entrada.equals("adicionar|nova|pergunta") || entrada.equals("adicionar")){
            AprenderPergunta(entrada, "");
        }else if(entrada.equals("apagar|senha") || entrada.equals("apagar|senha|proteçao") || entrada.equals("apagar|senha|banco") || entrada.equals("apagar|senha|banco|dados")){
            ApagarSenha();
        }else{
            BuscarPergunta(entrada);
        }
    }
    
    public static void ApagarSenha(){
        if (retorno.equals("apagarsenha")){
            BancoDados.bancoarray.remove(0);
            BancoDados.bancoarray.add(0, "");
            MainFrame.Falar("Senha deletada com sucesso.");
            retorno = "";
        }else{
            MainFrame.Falar("Tem certeza que deseja apagar a senha de proteção? (Sim/Não)");
            retorno = "apagarsenha";
        }
    }
    
    public static void ProtegerSenha(){
        if (retorno.equals("protegersenha")){
            MainFrame.ProtegerSenha();
            retorno = "";
        }else{
            MainFrame.Falar("Tem certeza que deseja proteger o banco com uma senha? (Sim/Não)");
            retorno = "protegersenha";
        }
    }
    
    public static void ApagarUltimaPergunta(){
        if (indiceultimapergunta == 0){
            MainFrame.Falar(""+indiceultimapergunta);
            MainFrame.Falar("Você não fez uma pergunta ou a última pergunta não pode ser apagada do sistema.");
        }else{
            if (retorno.equals("apagarpergunta1")){
                BancoDados.bancoarray.remove(indiceultimapergunta);
                indiceultimapergunta = 0;
                MainFrame.Falar("Última pergunta deletada do banco da memória com sucesso.");
                retorno = "";
            }else{
                String[] separar = BancoDados.bancoarray.get(indiceultimapergunta).split("::&::");
                MainFrame.Falar("Deletar resposta: " + "[" + Processando.DecriptarString(separar[1]) + "]? Digite sim ou não.");
                retorno = "apagarpergunta1";
            }
        }
    }
    
    public static void AprenderPergunta(String entrada, String retornointerno){
        if (retornointerno.equals("aprenderpergunta1")){
            operacaojuntar = entrada;
            MainFrame.Falar("Agora digite a resposta para a pergunta anterior. Ou digite 'cancelar' para encerrar o processo.");
            retorno = "aprenderpergunta2";
        }else if(retornointerno.equals("aprenderpergunta2")){
            operacaojuntar = operacaojuntar+"::&::"+Processando.EncriptarString(entrada);
            BancoDados.bancoarray.add(operacaojuntar);
            MainFrame.Falar("Sua pergunta foi armazenada com sucesso no banco da memória.");
            retorno = "";
        }else{
        MainFrame.Falar("Por favor, digite a pergunta que deseja adicionar ao banco de dados. Ou digite 'cancelar' para encerrar o processo.\nDica: quanto menor e com mais palavras específicas, mais fácil será pra achar.");
        retorno = "aprenderpergunta1";
        }
    }
    
    public static void BuscarPergunta(String entrada){
        int porcentageminterna = 0, indicecontagem = 0, porcentagem = 0;
        boolean out = true;
        String resposta = "";
        Iterator e = BancoDados.bancoarray.iterator();
        while (e.hasNext() && out) {
            String s = (String) e.next();
            if (indicecontagem > 0){
                String[] array1 = s.split("::&::");
                s = array1[0];
                String[] array2 = s.split("\\|");
                porcentageminterna = PorcentagemBusca(entrada, s);
                if (porcentageminterna == 100){out = false;}
                if (porcentageminterna > porcentagem){
                    indiceultimapergunta = indicecontagem;
                    porcentagem = porcentageminterna;
                    resposta = array1[1];
                }
  
            }
            indicecontagem += 1;
        }
        //resposta aqui.
        if (porcentagem >= 70){
            MainFrame.Falar(Processando.DecriptarString(resposta));
        }else{
            MainFrame.Falar(Processando.RetornarFrase("naoachoupergunta"));
            MainFrame.Falar("Caso queira ensinar uma nova pergunta para Robosperto, digite 'Aprender nova pergunta' ou 'Ensinar nova pergunta'.");
        }   
    }
    
    private static int PorcentagemBusca(String entrada, String palavraschave){
        int porcent;
        if (entrada.equalsIgnoreCase(palavraschave)){
            porcent = 100;   
        }else{
            int acerto = 0;
            String[] entradaarray = entrada.split("\\|");
            String[] chavearray = palavraschave.split("\\|");
            for (int i=0; i < entradaarray.length; i++){
                for (int j=0; j < chavearray.length; j++){
                    if (entradaarray[i].equals(chavearray[j])){
                        j = (chavearray.length - 1);
                        acerto += 1;
                    }
                }
            }
            Float segundaparte = ((float)entradaarray.length - (float)chavearray.length);
            if (segundaparte < 0){segundaparte = segundaparte * -1;
            }else if ((segundaparte * 2) == 0){segundaparte = (float)0.5;}
            segundaparte = 1/(segundaparte * 2);
            Float novafloat = ((((float)acerto/(float)chavearray.length) * 80.0F) + (20.0F * segundaparte));
            porcent = (int) novafloat.intValue();
        }
        return porcent;
    }
    
    private static String RemoverAcentos(String string) {
        return Normalizer.normalize(string, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
    
    private  static String LimparEntrada(String entrada){
        entrada = entrada.replaceAll(" e | a | para | pra | o | la | ca | com | de | do | no | uma | um | à |  ", " ");
        entrada = RemoverAcentos(entrada);
        entrada = entrada.toLowerCase();
        entrada = entrada.replaceAll("\\?", "");
        entrada = entrada.replaceAll("\\.", "");
        entrada = entrada.replaceAll("\\|", "");
        entrada = entrada.replaceAll(":", "");
        entrada = entrada.replaceAll("&", "");
        entrada = entrada.replaceAll("!", "");
        entrada = entrada.replaceAll(" ", "|");
        entrada = entrada.replaceAll(",", "");
        entrada = entrada.replaceAll(";", "");
        return entrada;
    }
}
