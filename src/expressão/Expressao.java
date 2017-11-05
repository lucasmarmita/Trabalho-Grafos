package expressão;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Expressao {
	
	public Expressao() {}
	
	public static Expressao create() {
		return new Expressao();
	}
	
	public boolean analisaSintaxe(String expressao) {
		
		boolean inicio = expressao.matches("^\\(.*|^\\d.*|^-\\d.*|^[a-zA-Z].*|^-[a-zA-Z].*"); // Só pode começar com ( ou numero ou -numero ou qualquerLetra ou -qualquerLetra 
		boolean fim = expressao.matches(".*\\d$|.*\\)$|.*[a-zA-Z]$");
		
		boolean resultado = inicio && fim;
		
		if(!resultado) {
			System.out.println("Erro 01");
			return false;
		}
		
		if(resultado) {
			
			int parenteses = 0; //contagem para saber se os parenteses foram fechados
			
			List<String> caracteres = new ArrayList<>(Arrays.asList(expressao.split("")));
			
			for (int i=0; i<caracteres.size()-1; i++) {
				if(caracteres.get(i).matches("\\(|-") && caracteres.get(i+1).matches("\\+|\\/|\\*|\\)")) { // Não pode existir (+ ou (/ ou (* ou () ou -+ ou -/ ou -* ou -)
					System.out.println("Erro 02");
					return false;
				}
				
				if(caracteres.get(i).matches("\\+|\\/|\\*|-") && !caracteres.get(i+1).matches("\\(|\\d|[a-zA-Z]|-")) { //Apenas existe operadores com numeros ou letras ou - ou (
					System.out.println("Erro 03");
					return false;
				}
				
				if(i < caracteres.size()-2) {
					if(caracteres.get(i).equals("-") && caracteres.get(i+1).equals("-") && !caracteres.get(i+2).matches("\\d|[a-zA-Z]")) { //Apenas existe --numero ou --letra 
						System.out.println("Erro 04");
						return false;
					}
				}
				
				if(caracteres.get(i).equals(")") && !caracteres.get(i+1).matches("\\+|\\/|\\*|-|\\)")) { //Apenas existe )+ ou )- ou )/ ou )- ou ))
					System.out.println("Erro 05");
					return false;
				}
				
				if(caracteres.get(i).matches("\\d|[a-zA-Z]") && !caracteres.get(i+1).matches("\\+|\\/|\\*|-|\\)")) { //Apenas existe numero ou letra seguido de + ou - ou * ou / ou )
					System.out.println("Erro 06");
					return false;
				}
				
				if(caracteres.get(i).equals("(")) {
					parenteses ++;
				}else if(caracteres.get(i).equals(")")) {
					parenteses --;
				}
			}
			
			if(caracteres.get(caracteres.size()-1).equals(")")){
				parenteses --;
			}
			
			if(parenteses != 0) {
				System.out.println(parenteses);
				return false;
			}
		}
		
		
		return true;
	}
	
	public No transformaEmArvore(String expressao) {
		//expressao = expressao.replaceAll(" ", "");// Remove todos os espaços
		
		List<String> caracteres = new ArrayList<>(Arrays.asList(expressao.split("")));
		String direita = "";
		String esquerda = "";
		
		No primeiro = new No();
		
		
		
		for (int i=0; i<caracteres.size(); i++) {
			if(caracteres.get(i).matches("\\+|-|\\*|\\/")) {
				for(int j=0; j<i; j++) {
					direita += caracteres.get(j);
				}
				
				for(int k=i+1; k<caracteres.size(); k++) {
					esquerda += caracteres.get(k);
				}
				
				primeiro.setOperacao(caracteres.get(i));
				
				primeiro.setEsquerdo(transformaEmArvore(esquerda));
				primeiro.setDireito(transformaEmArvore(direita));
				
				break;
			}
		}
		
		return primeiro;
	}
	
	public List<String> encontraExpressao(List<String> caracteres) {
		
		int parenteses = 1; //contagem para saber se os parenteses foram fechados
		
		List<String> subList = new ArrayList<>();
		
		if(caracteres.get(0).equals("(")) {
			
			for(int i=1; i<caracteres.size(); i++) {
				if(caracteres.get(i).equals("(")) {
					parenteses ++;
				}else if(caracteres.get(i).equals(")")) {
					parenteses --;
				}
				
				if(parenteses != 0) {
					subList.add(caracteres.get(i));
				}
				
				
			}
		}
		
		
		
		
		return null;
	}
}
