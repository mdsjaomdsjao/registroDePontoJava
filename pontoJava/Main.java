package pontoJava;

public class Main {
    public static void main(String[] args) {
        Colaborador alberto = new Colaborador("Alberto", "Assistente");
        alberto.registrarEntrada();
        alberto.registrarSaida();
        alberto.printRegistros();
        
        
    }
}