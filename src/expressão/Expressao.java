/* Classe responsável por analizar a sintaxe da expressão e transformar a mesma em uma árvore binária.
 * 
 * Autores: Lucas Claro / Marcelo Rezin
 * 
 * Definições importantes:
 * 
 * #Grau/Nivel do operador: Significa a ordem da resolução matemática que o operador possui: / e * são resolvidos primeiro e possuem maior grau,
 * consequentemente + e - são resolvidos após e possuiem menor grau.
 * 
 * #Grau/Nivel da expressao: Representa a ordem das subexpressoes a serem resolvidas. Ex.: ((c)b)a, onde a é de nivel 0 e deve ser transformada em 
 * no primeiro, b é de nivel 1 e assim respectivamente.
 */

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
				return false;
			}
		}
		
		
		return true;
	}
	
	public No transformaEmArvore(String expressao, boolean considerarMenorGrau) {
		expressao = expressao.replaceAll(" ", "");// Remove todos os espaços
		
		if(!analisaSintaxe(expressao)) {
			return null;
		}
		
		boolean menorGrau = false; //flag para saber se existe sinal de + ou -
		List<String> caracteres = new ArrayList<>(Arrays.asList(expressao.split("")));
		String direita = "";
		String esquerda = "";
		
		No primeiro = new No();
		
		int parenteses = 0;
		
		if(expressao.matches("\\d*|[a-zA-Z]*")) { //caso não exista mais operações
			primeiro.setOperacao(expressao);
			return primeiro;
		}
		
		for (int i=0; i<caracteres.size(); i++) {
			
			/*Contagem de parenteses para saber o nivel da expressao*/
			if(caracteres.get(i).equals("(")) {
				parenteses++;
				
				continue;
			}else if(caracteres.get(i).equals(")")) {
				parenteses--;
				
				continue;
			}
			
			/*Quando estiver no nivel mais baixo*/
			if(parenteses == 0) {
				if(menorGrau == false && caracteres.get(i).matches("\\+|-")) { //marca a flag para saber que existe operador de baixo nivel
					menorGrau = true;
				}
				
				if(caracteres.get(i).matches((considerarMenorGrau==false ? "\\*|\\/" : "\\+|-"))) { //Define o nó conforme a flag de nivel de operador 
					
					/*Encontra todo o lado esquerdo da expressao*/
					for(int j=0; j<i; j++) { 
						esquerda += caracteres.get(j);
					}
					
					/*Encontra todo o lado direito da expressao*/
					for(int k=i+1; k<caracteres.size(); k++) {
						direita += caracteres.get(k);
					}
					
					primeiro.setOperacao(caracteres.get(i));
					
					/*Verifica se é necessario a criação de nós na direita e esquerda*/
					if(!esquerda.trim().isEmpty()) {
						primeiro.setEsquerdo(transformaEmArvore(esquerda, false));
					}
					if(!direita.trim().isEmpty()) {
						primeiro.setDireito(transformaEmArvore(direita, false));
					}
					
					break;
				}
			}
		}
		
		/*
		 * Se o nó chegar vazio, exitem 2 motivos:
		 * 1o- Não existe expressao no nivel atual e é preciso remover parenteses
		 * 2o- Significa que é necessário avalizar operadores de baixo nivel; 
		 */
		if(primeiro.getOperacao()==null) {
			if(menorGrau == false) {
				primeiro = transformaEmArvore(removeParenteses(caracteres),false);
			}else {
				primeiro = transformaEmArvore(expressao, true);
			}
		}
		
		return primeiro;
	}
	
	public String removeParenteses(List<String> caracteres) {
		
		String expressao="";
		
		for(int i=1; i<caracteres.size()-1; i++) {
			expressao += caracteres.get(i);
		}
		
		return expressao;
	}
}
