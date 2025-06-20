package pontoJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class RegistroPonto {
    private LocalDateTime entrada;
    private LocalDateTime saida;
    final private static String senhaDB = "1234"; // Colocar senha do mySQL


    public RegistroPonto(Colaborador colaborador) {
        this.entrada = LocalDateTime.now();
        String tipo = "Entrada";
        salvarRegistroMySQL(colaborador, tipo);
        System.out.println("Entrada registrada.");
    }

    public static void registrarSaida(Colaborador colaborador) {
        String tipo = "Saída";
        salvarRegistroMySQL(colaborador,tipo);
        System.out.println("Saída registrada.");
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }
    
    public static void salvarRegistroMySQL(Colaborador colaborador,String tipo) {
        String url = "jdbc:mysql://localhost:3306/sistema_ponto";
        String user = "root";
        String password = senhaDB;

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO registros (nome, cargo, departamento, tipo, horario) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, colaborador.getNome());
                stmt.setString(2, colaborador.getCargo());
                stmt.setString(3, colaborador.getDepartamento());
                stmt.setString(4, tipo);
                stmt.setObject(5, LocalDateTime.now());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar no MySQL: " + e.getMessage());
        }
    }

    // p/ funcão que usa os dados temporarios
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");// p/ uma saída mais legivel
        String entradaStr = entrada.format(formatter);
        String saidaStr = (saida != null) ? saida.format(formatter) : "----";
        return "Entrada: " + entradaStr + " | Saída: " + saidaStr;}
}