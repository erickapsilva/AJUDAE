package com.app.armetech.ajudae.infra;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Validation {
	public static Pattern characters = Pattern.compile("[^A-Za-z ]{1,1}");
	public static Pattern blankCharacters = Pattern.compile(" {1,1}");

	public static boolean validatePassword(String input){
		if (input.length()<6 ||input == null){
			return false;
		}
		Pattern pattern = Pattern.compile(" {1,1}");
		Matcher specialCharacter = pattern.matcher(input);
		if (specialCharacter.find()){
			return false;
		}else{
			return true;
		}
	}

	public static boolean validateCharacters(String input){
		if (input.length() == 0 || input == null || input == " "){
			return false;
		}
		Pattern pattern = Pattern.compile("[^A-Za-z0-9 ]{1,1}");
		Matcher specialCharacter = pattern.matcher(input);
		if (specialCharacter.find()){
			return false;
		}else{
			return true;
		}
	}

	public static boolean validateField(String input){
		if (input == "" || input == null){
			return false;
		}else{
			return true;
		}
	}

	public static boolean validateEmail(String email){
		if (email == "" || email == null){
			return false;
		}
		Pattern formEmail = Pattern.compile("^[a-zA-Z0-9_\\.-]+@([a-zA-Z0-9]\\.)*([a-zA-Z0-9])*\\.([a-zA-Z])+");
		Matcher formatted = formEmail.matcher(email);
		Matcher withBlankCharacter = blankCharacters.matcher(email);
		if(formatted.find()){
			if(!withBlankCharacter.find()){
				return true;
			}
		}
		return false;
	}


	public static boolean validateCep(String cep){
		if (cep == "" || cep == null){
			return false;
		}
		Pattern formCep = Pattern.compile("[0-9]{5,5}-[0-9]{3,3}");
		Matcher formatted = formCep.matcher(cep);
		if(cep.length()==9){
			if (formatted.find()){
				return true;
			}
		}
		return false;
	}

	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	public static boolean validateDate(String data){
		if (data == "" || data == null){
			return false;
		}
		Pattern formCep = Pattern.compile("[0-9]{2,2}/[0-9]{2,2}/[0-9]{4,4}");
		Matcher formatted = formCep.matcher(data);
		if(data.length()==10){
			if (formatted.find()&& Validation.validateBirthYear(data)){
				return true;
			}
		}
		return false;
	}
	@RequiresApi(api = Build.VERSION_CODES.O)
	public static int getCurrentYear(){
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date data = new Date();

		try {
			String currentDate = sdf.format(data);
			String[] formattedDate = currentDate.split("/");
			int currentYear = Integer.parseInt(formattedDate[2]);
			return currentYear;
		} catch (Exception e) {
			return 0;
		}
	}

	public static int getBirthYear(String strDataNascimento){
		String[] formattedBirthYear = strDataNascimento.split("/");
		int birthYear = Integer.parseInt(formattedBirthYear[2]);
		return birthYear;
	}

	@RequiresApi(api = Build.VERSION_CODES.O)
	public static boolean validateBirthYear(String birthYear){
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setLenient(false);
		try {
			Date birthYearDate = sdf.parse(birthYear);
			return getCurrentYear() > getBirthYear(birthYear);
		}catch(ParseException e){
			return false;
		}
	}

}