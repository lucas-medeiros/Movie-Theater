import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	
	static Scanner teclado = new Scanner(System.in);//para pegar dados do usuario
	static ArrayList<Cliente> al = new ArrayList<Cliente> ();//array list de clientes (al)
	static ArrayList<Sessao> al2 = new ArrayList<Sessao> ();//array list de sessoes (al2)
	
	public static void main(String[] args) {

		int x = -1;//registra a resposta pro menu
		Sessao sala1 = new Sessao(14, new char[10][20]);//cria os objetos das sessoes
		Sessao sala2 = new Sessao(16, new char[10][20]);
		Sessao sala3 = new Sessao(18, new char[10][20]);
		Sessao sala4 = new Sessao(20, new char[10][20]);
		al2.add(sala1);//adiciona as sessoes no array list de sessoes (al2)
		al2.add(sala2);
		al2.add(sala3);
		al2.add(sala4);
		for (int i = 0; i < al2.size(); i++) {//esvazia todas as salas
			EsvaziaSala(al2.get(i).getSala());
		}
		do{//menu inicial de opcoes
			System.out.println("\n ---Menu---");
			System.out.println("0 - sair");
			System.out.println("1 - cadastrar filme");
			System.out.println("2 - comprar ingresso");
			System.out.println("3 - listar ocupacao da sessao");
			System.out.println("4 - resultado financeiro da sessao");
			System.out.println("5 - resultado financeiro total");
			System.out.print("Informe a opcao desejada: ");
			x = teclado.nextInt();
			teclado.nextLine();//evita erros de entrada de dados
			System.out.println();
			switch(x){
			case 0:
				System.out.println("\n Ate a proxima! \n");
				break;
			case 1:
				CadastrarFilme();
				break;
			case 2:
				ComprarIngresso();			
				break;
			case 3:
				 ListarOcupacao();
				break;
			case 4:
				ResultadoFinanceiroSessao();
				break;
			case 5:
				 ResultadoFinanceiroGeral();
				break;
			default:
				System.out.println("Opcao invalida, por favor insira outro numero\n");
			}
		}while(x != 0);
	}
	
	private static int SelecionaSessao(){//pergunta qual sessao usuario deseja
		int sessao = -1; //evita uma entrada de dados vazia
		do{
			System.out.println("0 - 14h");
			System.out.println("1 - 16h");
			System.out.println("2 - 18h");
			System.out.println("3 - 20h");
			sessao = teclado.nextInt();
			if ((sessao > 3) || (sessao < 0)){
				System.out.println("Opcao invalida, por favor insira outra");
			}
		}while((sessao > 3) || (sessao < 0));//enquanto nao for inserido um valor valido pede novamente
		teclado.nextLine();//evita erro de entrada de dados
		return sessao;//retorna um numero de entre 0 e 3
	}
	
	private static void EsvaziaSala(char[][] sala){//deixa todos os lugares como livres
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				sala[i][j] = 'L';
			}
		}
	}
	
	private static void ImprimeSala(char[][] sala){//imprime matriz mandada por parametro
		System.out.println("\n'L' indica os assentos livres\n"
				+ "'O' indica os assentos ocupados\n");
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				System.out.print(sala[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private static void CadastrarFilme(){//insere nome para o filme de determinada sessao
		System.out.println("Insira o nome do filme: ");
		String nomefilme = teclado.nextLine();
		System.out.println("Em qual sessao deseja cadastrar o filme?");
		al2.get(SelecionaSessao()).setNomeFilme(nomefilme);
		//SelecionaSessao() retorna um numero x entre 0 e 3
		//Chama o setNomeFilme para o objeto na posicao x do array list de Sessao 
	}
	
	private static void ComprarIngresso(){//cadastra novos clientes
		int meiaentrada, sessao, i ,j;
		System.out.println("Insira seu nome:");
		String nome = teclado.nextLine();
		System.out.println("Deseja comprar um ingresso normal ou de meia entrada?");
		System.out.println("0 - entrada normal\n1 - meia entrada");//menu de selecao para meia entrada
		meiaentrada = teclado.nextInt();
		if (meiaentrada != 1){//em caso de entrada de dados invalida cadastra como entrada normal
			meiaentrada = 0;
		}
		teclado.nextLine();//evita erro de entrada de dados com o teclado.nextInt();
		System.out.println("Para qual sessao deseja comprar o ingresso?");
		sessao = SelecionaSessao();//sessao recebe um numero entre 0 e 3
		System.out.println("Opcoes de lugares:\n ");
		ImprimeSala(al2.get(sessao).getSala());//chama o getSala sobre a Sessao na posicao do al2
		System.out.println();
		do{
			System.out.println("Qual fileira deseja? (digite um numero entre 0 e 9)");
			i = teclado.nextInt();//i registra o numero da fileira
			System.out.println("Qual cadeira deseja na fieira " + i + "?"
					+ " (numero entre 0 e 19)");
			j = teclado.nextInt();//j registra o numero da cadeira na fileira
			if (al2.get(sessao).getSala()[i][j] == 'O'){
				System.out.println("Posicao ocupada, por favor escolha outra");
			}//imprime mensagem de erro caso o lugar esteja ocupado
		}while (al2.get(sessao).getSala()[i][j] == 'O');//nao permite escolher lugares ja ocupados
		Cliente c = new Cliente(nome, sessao, meiaentrada, i, j);//cria novo cliente
		al.add(c);//adiciona o cliente criado ao array list de clientes
		al2.get(sessao).getSala()[i][j] = 'O';//marca o lugar do cliente adicinado como ocupado
		System.out.println("Obrigado por sua compra!");//mensagem de agradecimento
		}

	private static void ListarOcupacao(){//imprime a sala desejada
		int x = -1, sessao, i , j;
		System.out.println("Para qual sessao deseja verificar a ocupacaoo?");
		sessao = SelecionaSessao();
		ImprimeSala(al2.get(sessao).getSala());//imprime a sala desejada
		do{//loop permitindo consultar mais de um lugar por vez
			System.out.println("Deseja consutar os dados de alguma poltrona ocupada?");
			System.out.println("0 - sair\n1 - consultar dados");
			x = teclado.nextInt();
			teclado.nextLine();
			if(x != 0){
				System.out.println("Em qual fileira deseja buscar? (digite um numero entre 0 e 9)");
				i = teclado.nextInt();//i registra o numero da fileira
				teclado.nextLine();
				System.out.println("Em qual cadeira da fieira " + i + " deseja buscar?"
						+ " (numero entre 0 e 19)");
				j = teclado.nextInt();//j registra o numero da cadeira na fileira
				teclado.nextLine();
				if (al2.get(sessao).getSala()[i][j] == 'L'){//imprime mensagem de erro se o lugar estiver livre
					System.out.println("A posicao requisitada nao se encontra ocupada");
				}else{
					for (int k = 0; k < al.size(); k++) {//percorre o array list de clientes
						if(((al.get(k).getFileira()==i)&&(al.get(k).getCadeira())==j)&&(al.get(k).getSessao()==sessao)){
							System.out.println("Sessao: " + al2.get(sessao).getHorario() + "h");
							System.out.println(al.get(k));//chama o toString redefinido
						}//procura o cliente ocupado o lugar [i][j] na sessao requisitada
					}
				}
			}
		}while (x != 0);
	}
	
	private static void ResultadoFinanceiroSessao(){//imprime lucro gerado pela sessao escolhida
		int sessao, lucro = 0, meiaentradas = 0, entradasnormais = 0;
		System.out.println("Para qual sessao deseja consultar o resultado financeiro?");
		sessao = SelecionaSessao();//indica a posicao no array list de Sessao
		for (int i = 0; i < al.size(); i++) {//percorre todo o array list de clientes
			if (al.get(i).getSessao() == sessao){//se o cliente i esta na sessao requisitada
				if(al.get(i).getMeiaentrada() == 1){//se o cliente esta pagando meia entrada
					lucro += 11;//adiciona 11 reais (preço da meia) ao lucro total
					meiaentradas ++;//conta quantos cliente estao pagando meia entrada
				}else{//se o cliente esta pagando entrada inteira
					lucro += 22;//adiciona 22 reais (preco da entrada inteira) ao lucro total
					entradasnormais++;//conta quantos clientes estao pagando inteira
				}
			}
		}
		System.out.println("Na sessao das " + al2.get(sessao).getHorario() + 
							"h, foram comprados:");
		System.out.println(meiaentradas + ": meias entradas, ao valor de R$11,00 cada");
		System.out.println(entradasnormais + ": entradas normais, ao valor de R$22,00 cada");
		System.out.println("Totalizando:\nO resultado financeiro para a sessao das " +
							al2.get(sessao).getHorario() + "h, foi de: R$" + lucro + ",00");
	}//imprime a sessao, quantos clientes pagaram cada tipo de ingresso e o luro total da sessao
	
	private static void ResultadoFinanceiroGeral(){//imprime lucro geral e de cada sessao separada
		int i, lucrogeral = 0;//contador para os 'for' e para o lucro geral
		int [] lucroV = {0,0,0,0};//vetor com os lucros de cada sessao separadamente
		for (i = 0; i < al.size(); i++) {//percorre todo o array list de clientes
			if(al.get(i).getMeiaentrada() == 1){//compara se o cliente esta pagando meia ou nao
				lucroV[(al.get(i).getSessao())] += 11;//indice de lucroV = sessao do cliente 
			}else{
				lucroV[(al.get(i).getSessao())] += 22;
			}
		}
		for (i = 0; i < lucroV.length; i++) {
			lucrogeral += lucroV[i];//soma o lucro de cada sessao ao lucro geral do cinema
			System.out.println("O resultado financeiro da sessao das " + al2.get(i).getHorario()
								+ "h foi de: R$" + lucroV[i] + ",00");
		}//imprime o resultado financeiro de cada sessao	
		System.out.println("\nO resultado financeiro geral foi de: R$" + lucrogeral + ",00");
	}//imprime tambem o resultado financeiro geral
	
}