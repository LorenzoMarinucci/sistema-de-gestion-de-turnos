package vistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class RegistroImpl extends VistaRegistroAbstracta implements MouseListener {

	private final Integer MAXIMO_DIGITOS = 8;
	private final String MENSAJE_ERROR = "El DNI introducido es inválido";
	private JPanel container;
	private JTextField txtIngreseSuDni;
	private JTextField textFieldErrorDNI;
	private JTextField textFieldDNIIngresado;
	private String DNI = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroImpl frame = new RegistroImpl();
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
	public RegistroImpl() {
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
		panel_3.add(textFieldDNIIngresado, BorderLayout.NORTH);
		textFieldDNIIngresado.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 191, 255));
		panel_3.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(4, 3, 0, 0));

		JButton btnDigito1 = new JButton("1");
		btnDigito1.addMouseListener(this);
		btnDigito1.addActionListener(this);
		btnDigito1.setBackground(UIManager.getColor("Button.shadow"));
		btnDigito1.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		panel_2.add(btnDigito1);

		JButton btnDigito2 = new JButton("2");
		btnDigito2.addMouseListener(this);
		btnDigito2.addActionListener(this);
		btnDigito2.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		panel_2.add(btnDigito2);

		JButton btnDigito3 = new JButton("3");
		btnDigito3.addMouseListener(this);
		btnDigito3.addActionListener(this);
		btnDigito3.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		panel_2.add(btnDigito3);

		JButton btnDigito4 = new JButton("4");
		btnDigito4.addMouseListener(this);
		btnDigito4.addActionListener(this);
		btnDigito4.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		panel_2.add(btnDigito4);

		JButton btnDigito5 = new JButton("5");
		btnDigito5.addMouseListener(this);
		btnDigito5.addActionListener(this);
		btnDigito5.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		panel_2.add(btnDigito5);

		JButton btnDigito6 = new JButton("6");
		btnDigito6.addMouseListener(this);
		btnDigito6.addActionListener(this);
		btnDigito6.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		panel_2.add(btnDigito6);

		JButton btnDigito7 = new JButton("7");
		btnDigito7.addMouseListener(this);
		btnDigito7.addActionListener(this);
		btnDigito7.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		panel_2.add(btnDigito7);

		JButton btnDigito8 = new JButton("8");
		btnDigito8.addMouseListener(this);
		btnDigito8.addActionListener(this);
		btnDigito8.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		panel_2.add(btnDigito8);

		JButton btnDigito9 = new JButton("9");
		btnDigito9.addMouseListener(this);
		btnDigito9.addActionListener(this);
		btnDigito9.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		panel_2.add(btnDigito9);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(0, 191, 255));
		panel_2.add(panel_6);

		JButton btnDigito0 = new JButton("0");
		btnDigito0.addMouseListener(this);
		btnDigito0.addActionListener(this);
		btnDigito0.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		panel_2.add(btnDigito0);

		JButton btnBorrar = new JButton("<-");
		btnBorrar.addMouseListener(this);
		btnBorrar.setActionCommand("Borrar");
		btnBorrar.addActionListener(this);
		btnBorrar.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		panel_2.add(btnBorrar);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(0, 191, 255));
		panel_3.add(panel_5, BorderLayout.SOUTH);

		JButton btnFinalizarRegistro = new JButton("Finalizar registro");
		btnFinalizarRegistro.addMouseListener(this);
		btnFinalizarRegistro.addActionListener(this);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	public void mouseClicked(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
		JButton boton = (JButton) e.getSource();
		String command = boton.getActionCommand();
		ActionEvent evento = new ActionEvent(boton, 0, command);
		if (command.equals("Finalizar registro")) {

			this.actionListener.actionPerformed(evento);
		} else if (command.equals("Borrar")) {
			if (!this.DNI.isEmpty()) {
				this.DNI = DNI.substring(0, DNI.length() - 1);
				this.textFieldDNIIngresado.setText(this.DNI);
			}
		} else {
			if (DNI.length() < MAXIMO_DIGITOS) {
				this.DNI += command;
				this.textFieldDNIIngresado.setText(this.DNI);
			}
		}
	}
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void DNInoValido() {
		this.textFieldErrorDNI.setText(MENSAJE_ERROR);
		this.textFieldDNIIngresado.setText("");
		this.DNI = "";
	}
	
	@Override
	public String getDNI() {
		return this.textFieldDNIIngresado.getText();
	}

	@Override
	public void abrirVista(ActionListener actionListener){
		super.abrirVista(actionListener);
		this.DNI = "";
		this.textFieldDNIIngresado.setText("");
		this.textFieldErrorDNI.setText("");
	}
	
}
