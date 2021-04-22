package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;

public class UI extends JFrame {

	private JPanel container;
	private JTextField txtDni;
	private JTextField txtStatus;
	private JTextField textField;
	private JTextField txtEnCurso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI frame = new UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 583, 320);
		container = new JPanel();
		container.setBackground(new Color(0, 191, 255));
		container.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(container);
		container.setLayout(new GridLayout(0, 5, 0, 0));
		
		txtDni = new JTextField();
		txtDni.setEditable(false);
		txtDni.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		txtDni.setBackground(new Color(0, 191, 255));
		txtDni.setHorizontalAlignment(SwingConstants.CENTER);
		txtDni.setText("DNI:");
		container.add(txtDni);
		txtDni.setColumns(10);
		txtDni.setBorder(null);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(new Color(0, 191, 255));
		container.add(panel_12);
		panel_12.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel panel_14 = new JPanel();
		panel_14.setBackground(new Color(0, 191, 255));
		panel_12.add(panel_14);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 15));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("41854325");
		panel_12.add(textField);
		textField.setColumns(10);
		textField.setBorder(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 191, 255));
		container.add(panel);
		
		txtStatus = new JTextField();
		txtStatus.setEditable(false);
		txtStatus.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		txtStatus.setBackground(new Color(0, 191, 255));
		txtStatus.setHorizontalAlignment(SwingConstants.CENTER);
		txtStatus.setText("Status:");
		container.add(txtStatus);
		txtStatus.setColumns(10);
		txtStatus.setBorder(null);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBackground(new Color(0, 191, 255));
		container.add(panel_13);
		panel_13.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel panel_15 = new JPanel();
		panel_15.setBackground(new Color(0, 191, 255));
		panel_13.add(panel_15);
		
		txtEnCurso = new JTextField();
		txtEnCurso.setEditable(false);
		txtEnCurso.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 15));
		txtEnCurso.setText("En curso");
		txtEnCurso.setHorizontalAlignment(SwingConstants.CENTER);
		panel_13.add(txtEnCurso);
		txtEnCurso.setColumns(10);
		txtEnCurso.setBorder(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 191, 255));
		container.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 191, 255));
		container.add(panel_2);
		panel_2.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(0, 191, 255));
		panel_2.add(panel_11);
		
		JPanel panel_16 = new JPanel();
		panel_2.add(panel_16);
		panel_16.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_2 = new JButton("Siguiente");
		panel_16.add(btnNewButton_2, BorderLayout.CENTER);
		btnNewButton_2.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 11));
		btnNewButton_2.setEnabled(false);
		
		JPanel panel_17 = new JPanel();
		panel_17.setBackground(new Color(0, 191, 255));
		panel_16.add(panel_17, BorderLayout.EAST);
		
		JPanel panel_18 = new JPanel();
		panel_18.setBackground(new Color(0, 191, 255));
		panel_16.add(panel_18, BorderLayout.WEST);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 191, 255));
		container.add(panel_3);
		panel_3.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel panel_11_1 = new JPanel();
		panel_11_1.setBackground(new Color(0, 191, 255));
		panel_3.add(panel_11_1);
		
		JPanel panel_21 = new JPanel();
		panel_3.add(panel_21);
		panel_21.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_2_1 = new JButton("Anular");
		panel_21.add(btnNewButton_2_1, BorderLayout.CENTER);
		btnNewButton_2_1.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 11));
		btnNewButton_2_1.setEnabled(false);
		
		JPanel panel_22 = new JPanel();
		panel_22.setBackground(new Color(0, 191, 255));
		panel_21.add(panel_22, BorderLayout.EAST);
		
		JPanel panel_23 = new JPanel();
		panel_23.setBackground(new Color(0, 191, 255));
		panel_21.add(panel_23, BorderLayout.WEST);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 191, 255));
		container.add(panel_4);
		panel_4.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel panel_11_1_1 = new JPanel();
		panel_11_1_1.setBackground(new Color(0, 191, 255));
		panel_4.add(panel_11_1_1);
		
		JPanel panel_24 = new JPanel();
		panel_4.add(panel_24);
		panel_24.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_2_1_1 = new JButton("Cancelar");
		panel_24.add(btnNewButton_2_1_1, BorderLayout.CENTER);
		btnNewButton_2_1_1.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 11));
		btnNewButton_2_1_1.setEnabled(false);
		
		JPanel panel_25 = new JPanel();
		panel_25.setBackground(new Color(0, 191, 255));
		panel_24.add(panel_25, BorderLayout.WEST);
		
		JPanel panel_26 = new JPanel();
		panel_26.setBackground(new Color(0, 191, 255));
		panel_24.add(panel_26, BorderLayout.EAST);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(0, 191, 255));
		container.add(panel_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(0, 191, 255));
		container.add(panel_6);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(0, 191, 255));
		container.add(panel_7);
		panel_7.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel panel_11_1_1_1 = new JPanel();
		panel_11_1_1_1.setBackground(new Color(0, 191, 255));
		panel_7.add(panel_11_1_1_1);
		
		JPanel panel_19 = new JPanel();
		panel_19.setBackground(new Color(0, 191, 255));
		panel_7.add(panel_19);
		panel_19.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_2_1_1_1 = new JButton("Confirmar");
		panel_19.add(btnNewButton_2_1_1_1, BorderLayout.CENTER);
		btnNewButton_2_1_1_1.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 11));
		btnNewButton_2_1_1_1.setEnabled(false);
		
		JPanel panel_27 = new JPanel();
		panel_27.setBackground(new Color(0, 191, 255));
		panel_19.add(panel_27, BorderLayout.EAST);
		
		JPanel panel_28 = new JPanel();
		panel_28.setBackground(new Color(0, 191, 255));
		panel_19.add(panel_28, BorderLayout.WEST);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(0, 191, 255));
		container.add(panel_8);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(0, 191, 255));
		container.add(panel_9);
		panel_9.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel panel_11_1_1_1_1 = new JPanel();
		panel_11_1_1_1_1.setBackground(new Color(0, 191, 255));
		panel_9.add(panel_11_1_1_1_1);
		
		JPanel panel_20 = new JPanel();
		panel_20.setBackground(new Color(0, 191, 255));
		panel_9.add(panel_20);
		panel_20.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_2_1_1_1_1 = new JButton("Finalizar");
		panel_20.add(btnNewButton_2_1_1_1_1, BorderLayout.CENTER);
		btnNewButton_2_1_1_1_1.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 11));
		
		JPanel panel_29 = new JPanel();
		panel_29.setBackground(new Color(0, 191, 255));
		panel_20.add(panel_29, BorderLayout.WEST);
		
		JPanel panel_30 = new JPanel();
		panel_30.setBackground(new Color(0, 191, 255));
		panel_20.add(panel_30, BorderLayout.EAST);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(0, 191, 255));
		container.add(panel_10);
	}

}
