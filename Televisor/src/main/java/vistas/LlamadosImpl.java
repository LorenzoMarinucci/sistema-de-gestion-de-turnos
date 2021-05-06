package vistas;

import dependencias.atencion.Atencion;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class LlamadosImpl extends JFrame implements VistaLlamados {

	private final String AUDIO_PATH = "audio/campana.wav";

	private JPanel container;
	private JTextField txtLlamados;
	private JTextField txtDni;
	private JTextField txtBox;
	private Integer lugaresTelevisor;
	
	private Map<Integer, JTextField> textFieldsDNI = new HashMap<>();
	private Map<Integer, JTextField> textFieldsBox = new HashMap<>();

	public LlamadosImpl(Integer lugaresTelevisor) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		container = new JPanel();
		container.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(container);
		container.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		container.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(lugaresTelevisor + 1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel_1.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		txtDni = new JTextField();
		txtDni.setEditable(false);
		txtDni.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		txtDni.setHorizontalAlignment(SwingConstants.CENTER);
		txtDni.setText("N\u00FAmero de DNI");
		txtDni.setBackground(new Color(51, 153, 255));
		panel.add(txtDni);
		txtDni.setColumns(10);
		txtDni.setBorder(null);
		
		txtBox = new JTextField();
		txtBox.setEditable(false);
		txtBox.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		txtBox.setHorizontalAlignment(SwingConstants.CENTER);
		txtBox.setText("Box");
		txtBox.setBackground(new Color(51, 153, 255));
		panel.add(txtBox);
		txtBox.setColumns(10);
		txtBox.setBorder(null);
		
		inicializarTextFields(lugaresTelevisor, panel_1);
		this.lugaresTelevisor = lugaresTelevisor;
		
		txtLlamados = new JTextField();
		txtLlamados.setEditable(false);
		txtLlamados.setBackground(new Color(51, 102, 255));
		txtLlamados.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
		txtLlamados.setHorizontalAlignment(SwingConstants.CENTER);
		txtLlamados.setText("LLAMADOS");
		container.add(txtLlamados, BorderLayout.NORTH);
		txtLlamados.setColumns(10);
		txtLlamados.setBorder(null);
		
		this.setVisible(true);
	}
	
	private void inicializarTextFields(Integer cantidad, JPanel panelBase) {
		for (int i = 0; i < cantidad; i++) {
			JPanel panel = new JPanel();
			panelBase.add(panel);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			
			JTextField textDNI = new JTextField();
			textDNI.setEditable(false);
			textDNI.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 15));
			textDNI.setHorizontalAlignment(SwingConstants.CENTER);
			textDNI.setBackground(new Color(176, 224, 230));
			panel.add(textDNI);
			textDNI.setColumns(10);
			textDNI.setBorder(null);
			
			JTextField textBox = new JTextField();
			textBox.setEditable(false);
			textBox.setHorizontalAlignment(SwingConstants.CENTER);
			textBox.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 15));
			textBox.setBackground(new Color(255, 255, 255));
			panel.add(textBox);
			textBox.setColumns(10);
			textBox.setBorder(null);
			
			textFieldsDNI.put(i, textDNI);
			textFieldsBox.put(i, textBox);
		}
		
	};

	@Override
	public void cargarLlamado(Atencion atencion, Integer posicion) {
		textFieldsDNI.get(posicion).setText(atencion.getDNI().toString());
		textFieldsBox.get(posicion).setText(atencion.getBox().toString());
		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(AUDIO_PATH).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void quitarLlamado(Integer posicion) {
		for (int i = posicion; i < lugaresTelevisor - 1; i++) {
			textFieldsDNI.get(posicion).setText(textFieldsDNI.get(posicion + 1).getText());
			textFieldsBox.get(posicion).setText(textFieldsDNI.get(posicion + 1).getText());
		}
		textFieldsDNI.get(lugaresTelevisor - 1).setText("");
		textFieldsBox.get(lugaresTelevisor - 1).setText("");
	}

}
