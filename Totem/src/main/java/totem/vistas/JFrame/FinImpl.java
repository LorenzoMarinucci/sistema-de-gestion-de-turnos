package totem.vistas.JFrame;

import totem.vistas.VistaFin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
public class FinImpl extends VistaAbstracta implements VistaFin {

	private JPanel container;
	private JTextField textFieldTitulo;
	private JTextField textFieldMensaje;
	private JPanel panel;
	private Timer timer;
	private final String MENSAJE_EXITO = "Por favor, aguarde a ser llamado en la sala de espera";
	private final String TITULO_EXITO = "Registro exitoso";
	private final String TITULO_FALLO = "No ha podido realizarse el registro";

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
		textFieldTitulo.setText("");
		textFieldTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		container.add(textFieldTitulo);
		textFieldTitulo.setColumns(10);
		textFieldTitulo.setBorder(null);
		
		textFieldMensaje = new JTextField();
		textFieldMensaje.setEditable(false);
		textFieldMensaje.setBackground(new Color(0, 191, 255));
		textFieldMensaje.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 15));
		textFieldMensaje.setText("");
		textFieldMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		container.add(textFieldMensaje);
		textFieldMensaje.setColumns(10);
		textFieldMensaje.setBorder(null);
	}

	@Override
	public void iniciarTimeout(Integer milisegundos) {
		timer = new Timer(milisegundos, this.actionListener);
		timer.setActionCommand("TIMEOUT FIN");
		timer.setRepeats(false);
		timer.start();
	}

	@Override
	public void informarResultado(Boolean exitoso, String mensaje) {
		if (exitoso) {
			this.textFieldTitulo.setText(TITULO_EXITO);
			this.textFieldMensaje.setText(MENSAJE_EXITO);
		}
		else {
			this.textFieldTitulo.setText(TITULO_FALLO);
			this.textFieldMensaje.setText(mensaje);
		}
	}
}
