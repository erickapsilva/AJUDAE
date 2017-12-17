package com.app.armetech.ajudae;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public final class Validacao {
	public static Pattern letras = Pattern.compile("[^A-Za-z ]{1,1}");
	public static Pattern espaco = Pattern.compile(" {1,1}");
	
	
	//TODAS AS FUNÇÔES RETORNAM TRUE CASO O CAMPO ESTEJA VALIDO, E FALSE CASO O CAMPO ESTEJA CAGADO


	//função que verifica se há caracteres especiais na String
	public static boolean validaSenha(String input){
		if (input.length()<6 ||input == null){
			return false;
		}
		Pattern padrao = Pattern.compile(" {1,1}");
		Matcher caracterespecial = padrao.matcher(input);
		if (caracterespecial.find()){
			return false;
		}else{
			return true;
		}
	}
	
	//função que verifica se há caracteres especiais na String
	public static boolean validaCaracteres(String input){
		if (input.length() == 0 || input == null){
			return false;
		}
		Pattern padrao = Pattern.compile("[^A-Za-z0-9 ]{1,1}");
		Matcher caracterespecial = padrao.matcher(input);
		if (caracterespecial.find()){
			return false;
		}else{
			return true;
		}
	}
	
	
	//verifica se o campo ta vazio
	public static boolean validaCampo(String input){
		if (input == "" || input == null){
			return false;
		}else{
			return true;
		}
	}
    
    public static boolean validaEmail(String email){
		if (email == "" || email == null){
			return false;
		}
    	//aqui da o caralho, o padrão é essa merda ai 
    	//antes só eu e Deus entendia, agora só Deus entende
     	Pattern formemail = Pattern.compile("^[a-zA-Z0-9_\\.-]+@([a-zA-Z0-9]\\.)*([a-zA-Z0-9])*\\.([a-zA-Z])+");
    	Matcher formatado = formemail.matcher(email);
    	Matcher comespaco = espaco.matcher(email);
    	if(formatado.find()){
    		if(!comespaco.find()){
    			return true;
    		}
    	}
    	return false;
    }
    
   
	public static boolean validaCep(String cep){
		if (cep == "" || cep == null){
			return false;
		}
		//cria o padrão: 5 numeros um "-" e mais 3 numeros
		Pattern formcep = Pattern.compile("[0-9]{5,5}-[0-9]{3,3}");
		Matcher formatado = formcep.matcher(cep);
		if(cep.length()==9){
			if (formatado.find()){
				return true;
			}
		}
		return false;
	}

	public static boolean validaData(String data){
		if (data == "" || data == null){
			return false;
		}
		//cria o padrão: 5 numeros um "-" e mais 3 numeros
		Pattern formcep = Pattern.compile("[0-9]{2,2}/[0-9]{2,2}/[0-9]{4,4}");
		Matcher formatado = formcep.matcher(data);
		if(data.length()==10){
			if (formatado.find()){
				return true;
			}
		}
		return false;
	}
}