package expressão;

public class Expressao {
	
	public boolean analiseSintaxe(String expressao) {
		
		boolean resultado = expressao.matches("^\\(.*|^\\d.*");
		
		if(resultado) {
			
		}
		
		
		return resultado;
	}
}
