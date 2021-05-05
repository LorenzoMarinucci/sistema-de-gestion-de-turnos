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
		
		/* JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		
		textDNI1 = new JTextField();
		textDNI1.setEditable(false);
		textDNI1.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 15));
		textDNI1.setHorizontalAlignment(SwingConstants.CENTER);
		textDNI1.setBackground(new Color(176, 224, 230));
		panel_2.add(textDNI1);
		textDNI1.setColumns(10);
		textDNI1.setBorder(null);
		
		textBox1 = new JTextField();
		textBox1.setEditable(false);
		textBox1.setHorizontalAlignment(SwingConstants.CENTER);
		textBox1.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 15));
		textBox1.setBackground(new Color(255, 255, 255));
		panel_2.add(textBox1);
		textBox1.setColumns(10);
		textBox1.setBorder(null);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		textDNI2 = new JTextField();
		textDNI2.setEditable(false);
		textDNI2.setHorizontalAlignment(SwingConstants.CENTER);
		textDNI2.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 15));
		textDNI2.setBackground(new Color(176, 224, 230));
		panel_3.add(textDNI2);
		textDNI2.setColumns(10);
		textDNI2.setBorder(null);
		
		textBox2 = new JTextField();
		textBox2.setEditable(false);
		textBox2.setHorizontalAlignment(SwingConstants.CENTER);
		textBox2.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 15));
		textBox2.setBackground(new Color(255, 255, 255));
		panel_3.add(textBox2);
		textBox2.setColumns(10);
		textBox2.setBorder(null);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		textDNI3 = new JTextField();
		textDNI3.setEditable(false);
		textDNI3.setHorizontalAlignment(SwingConstants.CENTER);
		textDNI3.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 15));
		textDNI3.setBackground(new Color(176, 224, 230));
		panel_4.add(textDNI3);
		textDNI3.setColumns(10);
		textDNI3.setBorder(null);
		
		textBox3 = new JTextField();
		textBox3.setEditable(false);
		textBox3.setHorizontalAlignment(SwingConstants.CENTER);
		textBox3.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 15));
		textBox3.setBackground(new Color(255, 255, 255));
		panel_4.add(textBox3);
		textBox3.setColumns(10);
		textBox3.setBorder(null);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));
		
		textDNI4 = new JTextField();
		textDNI4.setEditable(false);
		textDNI4.setHorizontalAlignment(SwingConstants.CENTER);
		textDNI4.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 15));
		textDNI4.setBackground(new Color(176, 224, 230));
		panel_5.add(textDNI4);
		textDNI4.setColumns(10);
		textDNI4.setBorder(null);
		
		textBox4 = new JTextField();
		textBox4.setEditable(false);
		textBox4.setHorizontalAlignment(SwingConstants.CENTER);
		textBox4.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 15));
		textBox4.setBackground(new Color(255, 255, 255));
		panel_5.add(textBox4);
		textBox4.setColumns(10);
		textBox4.setBorder(null);
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 2, 0, 0));
		
		textDNI5 = new JTextField();
		textDNI5.setEditable(false);
		textDNI5.setHorizontalAlignment(SwingConstants.CENTER);
		textDNI5.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 15));
		textDNI5.setBackground(new Color(176, 224, 230));
		panel_6.add(textDNI5);
		textDNI5.setColumns(10);
		textDNI5.setBorder(null);
		
		textBox5 = new JTextField();
		textBox5.setEditable(false);
		textBox5.setHorizontalAlignment(SwingConstants.CENTER);
		textBox5.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 15));
		textBox5.setBackground(new Color(255, 255, 255));
		panel_6.add(textBox5);
		textBox5.setColumns(10);
		textBox5.setBorder(null);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 2, 0, 0));
		
		textDNI6 = new JTextField();
		textDNI6.setEditable(false);
		textDNI6.setHorizontalAlignment(SwingConstants.CENTER);
		textDNI6.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 15));
		textDNI6.setBackground(new Color(176, 224, 230));
		panel_7.add(textDNI6);
		textDNI6.setColumns(10);
		textDNI6.setBorder(null);
		
		textBox6 = new JTextField();
		textBox6.setEditable(false);
		textBox6.setHorizontalAlignment(SwingConstants.CENTER);
		textBox6.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 15));
		textBox6.setBackground(new Color(255, 255, 255));
		panel_7.add(textBox6);
		textBox6.setColumns(10);
		textBox6.setBorder(null);
		
		*/
		
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
