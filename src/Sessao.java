public class Sessao {
	
	private String nomeFilme;
	private int horario;
	private char[][] sala = new char[10][20];
	
	public Sessao(int horario, char[][] sala) {
		super();
		this.horario = horario;
		this.sala = sala;
	}
	
	public Sessao(String nomeFilme, int horario, char[][] sala){
		super();
		this.nomeFilme = nomeFilme;
		this.horario = horario;
		this.sala = sala;
	}

	public int getHorario() {
		return horario;
	}

	public void setHorario(int horario) {
		this.horario = horario;
	}

	public char[][] getSala() {
		return sala;
	}

	public void setSala(char[][] sala) {
		this.sala = sala;
	}

	public String getNomeFilme() {
		return nomeFilme;
	}

	public void setNomeFilme(String nomeFilme) {
		this.nomeFilme = nomeFilme;
	}
		
}