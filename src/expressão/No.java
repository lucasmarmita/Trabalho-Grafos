package expressão;

public class No {
	
	private No esquerdo;
	private No direito;
	private String operacao;
	
	public No() {
		this.esquerdo = null;
		this.direito = null;
		this.operacao = null;
	}
	
	public No(No esquerdo, No direito, String operacao) {
		this.esquerdo = esquerdo;
		this.direito = direito;
		this.operacao = operacao;
	}

	public No getEsquerdo() {
		return esquerdo;
	}

	public void setEsquerdo(No esquerdo) {
		this.esquerdo = esquerdo;
	}

	public No getDireito() {
		return direito;
	}

	public void setDireito(No direito) {
		this.direito = direito;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
	public String toString() {
		String dir;
		String esq;
		
		if(direito == null) {
			dir = "nulo";
		}else {
			dir = direito.toString();
		}
		
		if(esquerdo == null) {
			esq = "nulo";
		}else {
			esq = esquerdo.toString();
		}
		
		return "\nRaiz: " + operacao + "\nDireito: " + dir + "\nEsquerdo: " + esq + "\n";
	}
}
