import source.jewelry;
import source.location;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JGUI extends JFrame {

    private JPanel contentPane;

    public JGUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 611, 426);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblRedjacksJewelryCrafter = new JLabel(
                "RedJack's Jewelry Crafter");
        lblRedjacksJewelryCrafter.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblRedjacksJewelryCrafter.setBounds(10, 11, 575, 62);
        contentPane.add(lblRedjacksJewelryCrafter);

        JLabel lblLocation = new JLabel("Location:");
        lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblLocation.setBounds(10, 84, 178, 41);
        contentPane.add(lblLocation);

        JLabel lblItem = new JLabel("Item:");
        lblItem.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblItem.setBounds(10, 177, 178, 41);
        contentPane.add(lblItem);

        final JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(location.values()));
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        comboBox.setBounds(10, 127, 219, 39);
        contentPane.add(comboBox);

        final JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setModel(new DefaultComboBoxModel(jewelry.values()));
        comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        comboBox_1.setBounds(10, 224, 219, 39);
        contentPane.add(comboBox_1);

        JButton btnBegin = new JButton("Begin");
        btnBegin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                RedJackJewelry.loc = ((location) comboBox.getSelectedItem()).getName();
                RedJackJewelry.furnaceID = ((location) comboBox.getSelectedItem()).getFurnaceID();
                RedJackJewelry.gemId = ((jewelry) comboBox_1.getSelectedItem()).getGem();
                RedJackJewelry.intid = ((jewelry) comboBox_1.getSelectedItem()).getInterface();
                RedJackJewelry.item = comboBox_1.getSelectedItem().toString();
                RedJackJewelry.ready = true;
                dispose();
            }
        });
        btnBegin.setBounds(10, 284, 575, 92);
        contentPane.add(btnBegin);

        JTextArea txtrLol = new JTextArea();
        txtrLol.setForeground(Color.RED);
        txtrLol.setFont(new Font("Monospaced", Font.PLAIN, 11));
        txtrLol.setText("Welcom to RedJack's Jewelry Maker\r\n\r\nMake sure you post your proggies and feedback :)\r\n\r\nChanges from previous version:\r\n - First public release\r\n - Supports all jewelry from sapphire to diamond\r\n - Only supports Edgeville right now\r\n\r\nThanks,\r\n  RedJack");
        txtrLol.setBounds(244, 11, 341, 252);
        contentPane.add(txtrLol);
    }
}
