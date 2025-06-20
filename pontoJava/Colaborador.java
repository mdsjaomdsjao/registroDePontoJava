package pontoJava;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

class Colaborador {
    private String nome;
    private String cargo;
    private String departamento;
    final private static String senhaDB = "1234";  // Colocar senha do mySQL

    public Colaborador(String nome, String cargo, String departamento) {
        this.nome = nome;
        this.cargo = cargo;
        this.departamento = departamento;
    }

    public String getNome() {
        return this.nome;
    }
    
    public String getCargo() {
        return this.cargo;
    }
    
    public String getDepartamento() {
        return this.departamento;
    }
    
    
    public void registrarEntrada(Colaborador colaborador) {
        if (!podeRegistrarEntrada(colaborador)) {  // consulta no banco
            System.out.println("Não é possível registrar nova entrada: a última entrada ainda não tem saída.");
            return;
        }
        new RegistroPonto(colaborador);
    }   

    // verificando se já há uma entrada
    public static boolean podeRegistrarEntrada(Colaborador colaborador) {
        String url = "jdbc:mysql://localhost:3306/sistema_ponto";
        String user = "root";
        String password = senhaDB;

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT tipo FROM registros WHERE nome = ? ORDER BY horario DESC LIMIT 1";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, colaborador.getNome());
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String ultimoTipo = rs.getString("tipo");
                    return !ultimoTipo.equals("Entrada"); // Só permite se o último registro não for "Entrada"
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar último registro: " + e.getMessage());
        }
        return true; // se não houver registros, permite
    }
    
    public void registrarSaida(Colaborador colaborador) {
        if (!podeSaida(colaborador)) {
            System.out.println("Erro ao registrar saída. Ainda não houve entrada para este registro.");
            return;
        }
		RegistroPonto.registrarSaida(colaborador);
    }

    
    public static boolean podeSaida(Colaborador colaborador) {
        String url = "jdbc:mysql://localhost:3306/sistema_ponto";
        String user = "root";
        String password = senhaDB;

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT tipo FROM registros WHERE nome = ? ORDER BY horario DESC LIMIT 1";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, colaborador.getNome());
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String ultimoTipo = rs.getString("tipo");
                    return ultimoTipo.equals("Entrada"); // Só permite se o último registro não for "Entrada"
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar último registro: " + e.getMessage());
        }
        return true; // se não houver registros, permite
    }
    
    
    public static void buscarRegistrosSQL(Colaborador colaborador) {
        String url = "jdbc:mysql://localhost:3306/sistema_ponto";
        String user = "root";
        String password = senhaDB;

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM registros WHERE nome = ? ORDER BY horario DESC";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, colaborador.getNome());
                ResultSet rs = stmt.executeQuery();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                System.out.println("Registro do colaborador "+ colaborador.getNome()+":\n");
                while (rs.next()) {
                    String tipo = rs.getString("tipo");
                    LocalDateTime horario = rs.getTimestamp("horario").toLocalDateTime();
                    System.out.println(tipo + ": " + horario.format(formatter));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar registros: " + e.getMessage());
        }
    }


    public static void buscarUltimosRegistrosSQL(Colaborador colaborador) {
        String url = "jdbc:mysql://localhost:3306/sistema_ponto";
        String user = "root";
        String password = senhaDB;

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM registros WHERE nome = ? ORDER BY horario DESC LIMIT 10;";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, colaborador.getNome());
                ResultSet rs = stmt.executeQuery();
                System.out.println("Registro do colaborador "+ colaborador.getNome()+":\n");
                while (rs.next()) {
                    String tipo = rs.getString("tipo");
                    LocalDateTime horario = rs.getTimestamp("horario").toLocalDateTime();
                    System.out.println(tipo + " - " + horario.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar registros: " + e.getMessage());
        }
    }

}