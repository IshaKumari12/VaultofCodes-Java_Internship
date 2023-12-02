import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
    private JButton[][] buttons;
    private char currentPlayer;
    private JLabel statusLabel;

    public TicTacToeGUI() {
        // Initialize the frame
        setTitle("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize components
        buttons = new JButton[3][3];
        currentPlayer = 'X';
        statusLabel = new JLabel("Player " + currentPlayer + "'s turn");

        // Create buttons and add them to the frame
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttonClicked(row, col);
                    }
                });
                buttonPanel.add(buttons[i][j]);
            }
        }

        // Add components to the frame
        add(statusLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void buttonClicked(int row, int col) {
        if (buttons[row][col].getText().equals("")) {
            buttons[row][col].setText(String.valueOf(currentPlayer));

            if (checkWin(row, col)) {
                gameOver("Player " + currentPlayer + " wins!");
            } else if (checkTie()) {
                gameOver("It's a tie!");
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                statusLabel.setText("Player " + currentPlayer + "'s turn");
            }
        }
    }

    private boolean checkWin(int row, int col) {
        // Check row
        if (buttons[row][(col + 1) % 3].getText().equals(String.valueOf(currentPlayer))
                && buttons[row][(col + 2) % 3].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }

        // Check column
        if (buttons[(row + 1) % 3][col].getText().equals(String.valueOf(currentPlayer))
                && buttons[(row + 2) % 3][col].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }

        // Check diagonals
        if (row == col && buttons[(row + 1) % 3][(col + 1) % 3].getText().equals(String.valueOf(currentPlayer))
                && buttons[(row + 2) % 3][(col + 2) % 3].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }

        if (row + col == 2 && buttons[(row + 1) % 3][(col + 2) % 3].getText().equals(String.valueOf(currentPlayer))
                && buttons[(row + 2) % 3][(col + 1) % 3].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }

        return false;
    }

    private boolean checkTie() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false; // There is an empty space, the game is not a tie yet
                }
            }
        }
        return true; // All spaces are filled, it's a tie
    }

    private void gameOver(String message) {
        statusLabel.setText(message);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TicTacToeGUI ticTacToeGUI = new TicTacToeGUI();
                ticTacToeGUI.setVisible(true);
            }
        });
    }
}
