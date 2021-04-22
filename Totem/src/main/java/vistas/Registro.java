package vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Registro extends JFrame {

	private JPanel container;
	private JTextField txtIngreseSuDni;
	private JTextField textFieldErrorDNI;
	private JTextField textFieldDNIIngresado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
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
	public Registro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 491, 447);
		container = new JPanel();
		container.setBackground(new Color(0, 191, 255));
		container.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(container);
		container.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		container.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		textFieldDNIIngresado = new JTextField();
		textFieldDNIIngresado.setBackground(new Color(255, 255, 255));
		textFieldDNIIngresado.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 30));
		textFieldDNIIngresado.setEditable(false);
		textFieldDNIIngresado.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDNIIngresado.setText("123456");
		panel_3.add(textFieldDNIIngresado, BorderLayout.NORTH);
		textFieldDNIIngresado.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 191, 255));
		panel_3.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(4, 3, 0, 0));
		
		JButton btnDigito1 = new JButton("1");
		btnDigito1.setBackground(new Color(255, 255, 255));
		btnDigito1.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		panel_2.add(btnDigito1);
		
		JButton btnDigito2 = new JButton("2");
		btnDigito2.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		panel_2.add(btnDigito2);
		
		JButton btnDigito3 = new JButton("3");
		btnDigito3.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		panel_2.add(btnDigito3);
		
		JButton btnDigito4 = new JButton("4");
		btnDigito4.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		panel_2.add(btnDigito4);
		
		JButton btnDigito5 = new JButton("5");
		btnDigito5.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		panel_2.add(btnDigito5);
		
		JButton btnDigito6 = new JButton("6");
		btnDigito6.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		panel_2.add(btnDigito6);
		
		JButton btnDigito7 = new JButton("7");
		btnDigito7.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		panel_2.add(btnDigito7);
		
		JButton btnDigito8 = new JButton("8");
		btnDigito8.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		panel_2.add(btnDigito8);
		
		JButton btnDigito9 = new JButton("9");
		btnDigito9.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		panel_2.add(btnDigito9);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(0, 191, 255));
		panel_2.add(panel_6);
		
		JButton btnDigito0 = new JButton("0");
		btnDigito0.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		panel_2.add(btnDigito0);
		
		JButton btnBorrar = new JButton("<-");
		btnBorrar.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		panel_2.add(btnBorrar);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(0, 191, 255));
		panel_3.add(panel_5, BorderLayout.SOUTH);
		
		JButton btnFinalizarRegistro = new JButton("Finalizar registro");
		btnFinalizarRegistro.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 15));
		panel_5.add(btnFinalizarRegistro);
		
		txtIngreseSuDni = new JTextField();
		txtIngreseSuDni.setEditable(false);
		txtIngreseSuDni.setBackground(new Color(0, 191, 255));
		txtIngreseSuDni.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		txtIngreseSuDni.setText("Ingrese su DNI");
		txtIngreseSuDni.setHorizontalAlignment(SwingConstants.CENTER);
		container.add(txtIngreseSuDni, BorderLayout.NORTH);
		txtIngreseSuDni.setColumns(10);
		txtIngreseSuDni.setBorder(null);
		
		textFieldErrorDNI = new JTextField();
		textFieldErrorDNI.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldErrorDNI.setText("El DNI ingresado es inv\u00E1lido");
		textFieldErrorDNI.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 15));
		textFieldErrorDNI.setEditable(false);
		textFieldErrorDNI.setBackground(new Color(0, 191, 255));
		textFieldErrorDNI.setForeground(new Color(255, 0, 0));
		container.add(textFieldErrorDNI, BorderLayout.SOUTH);
		textFieldErrorDNI.setColumns(10);
		textFieldErrorDNI.setBorder(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 191, 255));
		container.add(panel, BorderLayout.EAST);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 191, 255));
		container.add(panel_4, BorderLayout.WEST);
	}

}
