public class Cliente {
	
	private String nome;
	private int sessao;
	private int meiaentrada;
	private int fileira;
	private int cadeira;
	
	public Cliente(String nome, int sessao, int meiaentrada, int fileira, int cadeira) {
		super();
		this.nome = nome;
		this.sessao = sessao;
		this.meiaentrada = meiaentrada;
		this.fileira = fileira;
		this.cadeira = cadeira;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getSessao() {
		return sessao;
	}

	public void setSessao(int sessao) {
		this.sessao = sessao;
	}

	public int getMeiaentrada() {
		return meiaentrada;
	}

	public void setMeiaentrada(int meiaentrada) {
		this.meiaentrada = meiaentrada;
	}

	public int getFileira() {
		return fileira;
	}

	public void setFileira(int fileira) {
		this.fileira = fileira;
	}

	public int getCadeira() {
		return cadeira;
	}

	public void setCadeira(int cadeira) {
		this.cadeira = cadeira;
	}
	
	public String toString(){
		if (meiaentrada == 1){
			return "Nome: " + nome + "\nSentado no assento: ["+fileira+"]["+cadeira+"]"+
					"\nPagando meia entrada";
		}else{
			return "Nome: " + nome + "\nSentado no assento: ["+fileira+"]["+cadeira+"]"+
					"\nPagando entrada inteira";
		}
	}
		
}