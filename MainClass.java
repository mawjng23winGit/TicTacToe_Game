
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainClass extends JFrame {
    private int count = 0;
    private List<JButton> listButton;
    public MainClass() {
        super("TicTacToe");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuItem newGame = createNewGameMenu();
        JMenuItem exitGame = createExitGameMenu();
        JMenu menu = new JMenu("Menu");
        menu.add(newGame);
        menu.add(exitGame);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        setJMenuBar(menuBar);

        setLayout(new GridLayout(3, 3));
        listButton = new ArrayList<>();
        for (int i=0 ; i<9 ; i++){
            createButton(this);
        }
        setVisible(true);
    }
    private JMenuItem createNewGameMenu(){
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(e -> {
            dispose();
            new MainClass();
        });
        return newGame;
    }
    private JMenuItem createExitGameMenu(){
        JMenuItem exitGame = new JMenuItem("Exit");
        exitGame.addActionListener(e -> System.exit(0));
        return exitGame;
    }
    private void createButton(JFrame frame){
        JButton button = new JButton();
        button.setFont(new Font("Arial", Font.BOLD, 30));
        button.addActionListener(e -> {
            while (button.getText().isEmpty()){
                button.setText(count % 2 == 0 ? "x" : "o");
                count++;
            }
            checkWin();
        });
        listButton.add(button);
        frame.add(button);
    }
    private List<String> getButtonData(){
        List<String> buttonData = new ArrayList<>();
        for (JButton button: listButton){
            buttonData.add(button.getText());
        }
        return buttonData;
    }
    private void checkWin() {
        List<String> data = getButtonData();
        for (int i = 0; i < 3; i++) {
            if (!data.get(3 * i).equals("") && data.get(3 * i).equals(data.get(3 * i + 1)) && data.get(3 * i).equals(data.get(3 * i + 2))) {
                JOptionPane.showMessageDialog(null, "Player " + (data.get(3 * i).equals("x") ? "1" : "2") + " win");
                dispose();
                new MainClass();
                return;
            }
            if (!data.get(i).equals("") && data.get(i).equals(data.get(3 + i)) && data.get(i).equals(data.get(6 + i))) {
                JOptionPane.showMessageDialog(null, "Player " + (data.get(i).equals("x") ? "1" : "2") + " win");
                dispose();
                new MainClass();
                return;
            }
        }
        if (!data.get(0).equals("") && data.get(0).equals(data.get(4)) && data.get(0).equals(data.get(8))) {
            JOptionPane.showMessageDialog(null, "Player " + (data.get(0).equals("x") ? "1" : "2") + " win");
            dispose();
            new MainClass();
            return;
        }
        if (!data.get(2).equals("") && data.get(2).equals(data.get(4)) && data.get(2).equals(data.get(6))) {
            JOptionPane.showMessageDialog(null, "Player " + (data.get(2).equals("x") ? "1" : "2") + " win");
            dispose();
            new MainClass();
            return;
        }
        if (count >= 9) {
            JOptionPane.showMessageDialog(null, "Tie Game");
            dispose();
            new MainClass();
        }
    }

    public static void main(String[] args) {
        new MainClass();
    }
}