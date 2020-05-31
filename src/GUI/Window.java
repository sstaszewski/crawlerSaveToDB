package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import connection.AddCar;
import model.Crawler;

public class Window extends JFrame {


    String[] tablica = {"ford focus","opel astra"};

    public Window() {
        JPanel mainPanel = new JPanel();
        JLabel selectedModel = new JLabel("Dane ktorego modelu zapisac do bazy danych?");
        JComboBox modelList = new JComboBox(tablica);
        JButton saveIntoDBButton = new JButton("Zapisz do bazy danych");
        setSize(300, 300);
        setLocation(100, 100);
        setTitle("ZAPISYWANIE DO BAZY DANCYH");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        saveIntoDBButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel savedPanel = new JPanel();
                JLabel saved= new JLabel("zapisano");
                String selectedCarModel = (String) modelList.getSelectedItem();
                try {
					AddCar.executeInsertCarDetails(Crawler.generateCarList(selectedCarModel));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
                mainPanel.setVisible(false);
                savedPanel.add(saved);
                add(savedPanel);
            }
        });
        mainPanel.setLayout(new GridLayout(3,3));
        mainPanel.add(selectedModel);
        mainPanel.add(modelList);
        mainPanel.add(saveIntoDBButton);
        add(mainPanel);
    }

}
