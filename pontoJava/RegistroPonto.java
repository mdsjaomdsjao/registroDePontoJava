package pontoJava;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

class RegistroPonto {
    private LocalDateTime entrada;
    private LocalDateTime saida;

    public RegistroPonto(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public void registrarSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }
    
    public String calcularTempoTrabalhado() {
    if (entrada != null && saida != null) {
        Duration duracao = Duration.between(entrada, saida);
        long horas = duracao.toHours();
        long minutos = duracao.toMinutes() % 60;
        return String.format("%02d:%02d", horas, minutos);
    } else {
        return "Tempo não disponível";
    }
}


    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");// p/ uma saída mais legivel
        String entradaStr = entrada.format(formatter);
        String saidaStr = (saida != null) ? saida.format(formatter) : "----";
        return "Entrada: " + entradaStr + " | Saída: " + saidaStr + " | Trabalhado: " + calcularTempoTrabalhado();
    }
}