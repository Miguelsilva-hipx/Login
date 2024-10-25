package br.com.loja.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TelaLogin extends JFrame {

    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton verificarButton;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JTextField textField2;
    private JPasswordField passwordField2;
    private JButton verificarButtonCadastro;
    private JButton BotaoTroca;

    public TelaLogin() {
        // Inicializando componentes
        textField1 = new JTextField(15);
        passwordField1 = new JPasswordField(15);
        verificarButton = new JButton("Verificar");

        // Estilizando o painel principal
        panel1 = new JPanel(new GridBagLayout());
        panel1.setBackground(new Color(240, 240, 240));
        panel1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Adicionando componentes ao painel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel1.add(new JLabel("Usuário:"), gbc);

        gbc.gridx = 1;
        panel1.add(textField1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel1.add(new JLabel("Senha:"), gbc);

        gbc.gridx = 1;
        panel1.add(passwordField1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        verificarButton.setBackground(new Color(70, 130, 180));
        verificarButton.setForeground(Color.WHITE);
        verificarButton.setFocusPainted(false);
        panel1.add(verificarButton, gbc);

        // Configurando a janela
        setTitle("Tela de Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(350, 250));
        setResizable(false);
        add(panel1);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Adicionando ação ao botão
        verificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarLogin();
            }
        });
    }

    private void verificarLogin() {
        String username = textField1.getText();
        String password = new String(passwordField1.getPassword());

        String url = "jdbc:mysql://localhost:3306/login";
        String user = "root";
        String dbPassword = "Prof@dm1n";

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
                Telasucesso(username);
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void Telasucesso(String username) {
        // Criando um novo painel para a tela de sucesso
        panel3 = new JPanel();
        panel3.setBackground(new Color(240, 240, 240));
        panel3.add(new JLabel("Login Sucesso! Seja bem-vindo " + username));

        JFrame sucessoFrame = new JFrame("Sucesso");
        sucessoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sucessoFrame.setPreferredSize(new Dimension(250, 150));
        sucessoFrame.setResizable(false);
        sucessoFrame.add(panel3);
        sucessoFrame.pack();
        sucessoFrame.setLocationRelativeTo(null);
        sucessoFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaLogin::new);
    }
}
