package pontoJava;
import java.util.ArrayList;
import java.time.LocalDateTime;

class Colaborador {
    String nome;
    String funcao;
    ArrayList<RegistroPonto> registros;

    public Colaborador(String nome, String funcao) {
        this.nome = nome;
        this.funcao = funcao;
        this.registros = new ArrayList<>();
    }

    public String getNome() {
        return this.nome;
    }

    public void registrarEntrada() {
        // verificando se já há uma entrada sem saída registrada
        if (!registros.isEmpty() && registros.get(registros.size() - 1).getSaida() == null) {
            System.out.println("Já existe uma entrada para este registro.");
        } else {
            registros.add(new RegistroPonto(LocalDateTime.now()));
        }
    }

    public void registrarSaida() {
        if (registros.isEmpty()) {
            System.out.println("Nenhuma entrada registrada.");
            return;
        }
        RegistroPonto ultimo = registros.get(registros.size() - 1);

        if (ultimo.getSaida() != null) {
            System.out.println("Já existe uma saída para este registro.");
        } else {
            ultimo.registrarSaida(LocalDateTime.now());
        }
    }

    public void printRegistros() {
    	if(registros.isEmpty()) {System.out.println("Ainda não há nenhum registro.");
    	}
    	else{System.out.println("Registros de ponto de " + this.getNome() + ":");
	        for (RegistroPonto r : registros) {
	            System.out.println(r);
	            System.out.println("--------------------");
	        }
        }
    }
    
    public void printUltimosRegistros() {
        if (registros.isEmpty()) {
            System.out.println("Ainda não há nenhum registro.");
        } else {
            System.out.println("Últimos registros de ponto de " + this.getNome() + ":");
            int total = registros.size();
            int inicio = Math.max(0, total - 5); // garante que não passe de 0

            for (int i = total - 1; i >= inicio; i--) {
                System.out.println(registros.get(i));
                System.out.println("--------------------");
            }
        }
    }

}