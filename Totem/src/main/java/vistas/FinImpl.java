package vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FinImpl extends VistaAbstracta {

	private JPanel container;
	private JTextField textFieldTitulo;
	private JTextField textFieldMensaje;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinImpl frame = new FinImpl();
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
	public FinImpl() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		container = new JPanel();
		container.setBackground(new Color(0, 191, 255));
		container.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(container);
		container.setLayout(new GridLayout(4, 1, 0, 0));
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 191, 255));
		container.add(panel);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setEditable(false);
		textFieldTitulo.setBackground(new Color(0, 191, 255));
		textFieldTitulo.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 40));
		textFieldTitulo.setText("Registro exitoso");
		textFieldTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		container.add(textFieldTitulo);
		textFieldTitulo.setColumns(10);
		textFieldTitulo.setBorder(null);
		
		textFieldMensaje = new JTextField();
		textFieldMensaje.setEditable(false);
		textFieldMensaje.setBackground(new Color(0, 191, 255));
		textFieldMensaje.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 15));
		textFieldMensaje.setText("Por favor, aguarde en la sala de espera a ser llamado");
		textFieldMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		container.add(textFieldMensaje);
		textFieldMensaje.setColumns(10);
		textFieldMensaje.setBorder(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
