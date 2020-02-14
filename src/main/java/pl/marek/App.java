package pl.marek;

import pl.marek.ui.Menu;

import javax.swing.*;

public class App {

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(Menu::new);
    }
}
