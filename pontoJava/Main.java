package pontoJava;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Colaborador colaborador = new Colaborador("Pedro","Assistente");
    	Scanner input = new Scanner(System.in);
    	int entrada = 0;
    	
    	while(entrada != 5) {
        System.out.println("--- SISTEMA DE PONTO ---\n1 - Adicionar entrada no sistema\n" 
        		+ "2- Adicionar saída no sistema\n"
        		+ "3 - Verificar últimos pontos\n"
        		+ "4 - Verificar TODOS os pontos\n"
        		+ "5 - Sair");
                try {
                    entrada = Integer.parseInt(input.nextLine());
                    menu(entrada, colaborador);
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Por favor, digite um número.");
                }
        
    	}
    	input.close();
    	System.out.println("Sistema finalizado.");
    }
    // recebendo o comando do usuário
    private static void menu(int entrada, Colaborador colaborador){
    	switch(entrada) {
    	case 1: colaborador.registrarEntrada(); break;
    	case 2: colaborador.registrarSaida(); break;
    	case 3: colaborador.printUltimosRegistros(); break;
    	case 4: colaborador.printRegistros(); break;
    	default: System.out.println("Digite uma entrada válida.");
    	}
    	
    }
}

