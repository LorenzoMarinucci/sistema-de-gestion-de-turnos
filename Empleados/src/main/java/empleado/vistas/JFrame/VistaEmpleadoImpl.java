package empleado.vistas.JFrame;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dependencias.atencion.Atencion;
import empleado.sesion.Sesion;
import empleado.vistas.VistaEmpleado;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.SQLClientInfoException;

public class VistaEmpleadoImpl extends JFrame implements MouseListener, VistaEmpleado {

    private JPanel container;
    private JTextField txtDni;
    private JTextField txtStatus;
    private JTextField textFieldDNI;
    private JTextField txtFieldStatus;
    private JButton btnFinalizar;
    private JButton btnConfirmar;
    private JButton btnSiguiente;
    private JButton btnCancelar;
    private JButton btnAnular;
    private ActionListener actionListener;
    private JTextField txtMensaje;
    private JTextField textFieldNroBox;
    private JTextField txtNombre;
    private JTextField textFieldNombre;
    private JTextField txtCategora;
    private JTextField textFieldCategoria;

    public VistaEmpleadoImpl(Integer box) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 583, 320);
        container = new JPanel();
        container.setBackground(new Color(0, 191, 255));
        container.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(container);
        container.setLayout(new BorderLayout(0, 0));

        JPanel panelDeOpciones = new JPanel();
        container.add(panelDeOpciones);
        panelDeOpciones.setLayout(new GridLayout(0, 5, 0, 0));
        panelDeOpciones.setBackground(new Color(0, 191, 255));

        txtDni = new JTextField();
        panelDeOpciones.add(txtDni);
        txtDni.setEditable(false);
        txtDni.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
        txtDni.setBackground(new Color(0, 191, 255));
        txtDni.setHorizontalAlignment(SwingConstants.CENTER);
        txtDni.setText("DNI:");
        txtDni.setColumns(10);
        txtDni.setBorder(null);

        JPanel panel_12 = new JPanel();
        panelDeOpciones.add(panel_12);
        panel_12.setBackground(new Color(0, 191, 255));
        panel_12.setLayout(new GridLayout(3, 0, 0, 0));

        JPanel panel_14 = new JPanel();
        panel_14.setBackground(new Color(0, 191, 255));
        panel_12.add(panel_14);

        textFieldDNI = new JTextField();
        textFieldDNI.setEditable(false);
        textFieldDNI.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 15));
        textFieldDNI.setHorizontalAlignment(SwingConstants.CENTER);
        panel_12.add(textFieldDNI);
        textFieldDNI.setColumns(10);
        textFieldDNI.setBorder(null);
        this.textFieldDNI.setText("");

        JPanel panel = new JPanel();
        panelDeOpciones.add(panel);
        panel.setBackground(new Color(0, 191, 255));

        txtStatus = new JTextField();
        panelDeOpciones.add(txtStatus);
        txtStatus.setEditable(false);
        txtStatus.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 20));
        txtStatus.setBackground(new Color(0, 191, 255));
        txtStatus.setHorizontalAlignment(SwingConstants.CENTER);
        txtStatus.setText("Status:");
        txtStatus.setColumns(10);
        txtStatus.setBorder(null);

        JPanel panel_13 = new JPanel();
        panelDeOpciones.add(panel_13);
        panel_13.setBackground(new Color(0, 191, 255));
        panel_13.setLayout(new GridLayout(3, 0, 0, 0));

        JPanel panel_15 = new JPanel();
        panel_15.setBackground(new Color(0, 191, 255));
        panel_13.add(panel_15);

        txtFieldStatus = new JTextField();
        txtFieldStatus.setEditable(false);
        txtFieldStatus.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 15));
        txtFieldStatus.setHorizontalAlignment(SwingConstants.CENTER);
        panel_13.add(txtFieldStatus);
        txtFieldStatus.setColumns(10);
        txtFieldStatus.setBorder(null);
        this.txtFieldStatus.setText("");
        
        txtNombre = new JTextField();
        txtNombre.setText("Nombre:");
        txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
        txtNombre.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtNombre.setEditable(false);
        txtNombre.setColumns(10);
        txtNombre.setBorder(null);
        txtNombre.setBackground(new Color(0, 191, 255));
        panelDeOpciones.add(txtNombre);
        
        JPanel panel_12_1 = new JPanel();
        panel_12_1.setBackground(new Color(0, 191, 255));
        panelDeOpciones.add(panel_12_1);
        panel_12_1.setLayout(new GridLayout(3, 0, 0, 0));
        
        JPanel panel_14_1 = new JPanel();
        panel_14_1.setBackground(new Color(0, 191, 255));
        panel_12_1.add(panel_14_1);
        
        textFieldNombre = new JTextField();
        textFieldNombre.setText("");
        textFieldNombre.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldNombre.setFont(new Font("Dialog", Font.PLAIN, 15));
        textFieldNombre.setEditable(false);
        textFieldNombre.setColumns(10);
        textFieldNombre.setBorder(null);
        panel_12_1.add(textFieldNombre);
        
        JPanel panel_31 = new JPanel();
        panel_31.setBackground(new Color(0, 191, 255));
        panelDeOpciones.add(panel_31);
        
        txtCategora = new JTextField();
        txtCategora.setText("Categoría:");
        txtCategora.setHorizontalAlignment(SwingConstants.CENTER);
        txtCategora.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtCategora.setEditable(false);
        txtCategora.setColumns(10);
        txtCategora.setBorder(null);
        txtCategora.setBackground(new Color(0, 191, 255));
        panelDeOpciones.add(txtCategora);
        
        JPanel panel_13_1 = new JPanel();
        panel_13_1.setBackground(new Color(0, 191, 255));
        panelDeOpciones.add(panel_13_1);
        panel_13_1.setLayout(new GridLayout(3, 0, 0, 0));
        
        JPanel panel_15_1 = new JPanel();
        panel_15_1.setBackground(new Color(0, 191, 255));
        panel_13_1.add(panel_15_1);
        
        textFieldCategoria = new JTextField();
        textFieldCategoria.setText("");
        textFieldCategoria.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldCategoria.setFont(new Font("Dialog", Font.PLAIN, 15));
        textFieldCategoria.setEditable(false);
        textFieldCategoria.setColumns(10);
        textFieldCategoria.setBorder(null);
        panel_13_1.add(textFieldCategoria);

        JPanel panel_1 = new JPanel();
        panelDeOpciones.add(panel_1);
        panel_1.setBackground(new Color(0, 191, 255));

        JPanel panel_2 = new JPanel();
        panelDeOpciones.add(panel_2);
        panel_2.setBackground(new Color(0, 191, 255));
        panel_2.setLayout(new GridLayout(3, 1, 0, 0));

        JPanel panel_11 = new JPanel();
        panel_11.setBackground(new Color(0, 191, 255));
        panel_2.add(panel_11);

        JPanel panel_16 = new JPanel();
        panel_2.add(panel_16);
        panel_16.setLayout(new BorderLayout(0, 0));

        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addMouseListener(this);
        panel_16.add(btnSiguiente, BorderLayout.CENTER);
        btnSiguiente.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 11));

        JPanel panel_17 = new JPanel();
        panel_17.setBackground(new Color(0, 191, 255));
        panel_16.add(panel_17, BorderLayout.EAST);

        JPanel panel_18 = new JPanel();
        panel_18.setBackground(new Color(0, 191, 255));
        panel_16.add(panel_18, BorderLayout.WEST);

        JPanel panel_3 = new JPanel();
        panelDeOpciones.add(panel_3);
        panel_3.setBackground(new Color(0, 191, 255));
        panel_3.setLayout(new GridLayout(3, 0, 0, 0));

        JPanel panel_11_1 = new JPanel();
        panel_11_1.setBackground(new Color(0, 191, 255));
        panel_3.add(panel_11_1);

        JPanel panel_21 = new JPanel();
        panel_3.add(panel_21);
        panel_21.setLayout(new BorderLayout(0, 0));

        btnAnular = new JButton("Anular");
        btnAnular.addMouseListener(this);
        panel_21.add(btnAnular, BorderLayout.CENTER);
        btnAnular.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 11));
        btnAnular.setEnabled(false);

        JPanel panel_22 = new JPanel();
        panel_22.setBackground(new Color(0, 191, 255));
        panel_21.add(panel_22, BorderLayout.EAST);

        JPanel panel_23 = new JPanel();
        panel_23.setBackground(new Color(0, 191, 255));
        panel_21.add(panel_23, BorderLayout.WEST);

        JPanel panel_4 = new JPanel();
        panelDeOpciones.add(panel_4);
        panel_4.setBackground(new Color(0, 191, 255));
        panel_4.setLayout(new GridLayout(3, 0, 0, 0));

        JPanel panel_11_1_1 = new JPanel();
        panel_11_1_1.setBackground(new Color(0, 191, 255));
        panel_4.add(panel_11_1_1);

        JPanel panel_24 = new JPanel();
        panel_4.add(panel_24);
        panel_24.setLayout(new BorderLayout(0, 0));

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addMouseListener(this);
        panel_24.add(btnCancelar, BorderLayout.CENTER);
        btnCancelar.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 11));
        btnCancelar.setEnabled(false);

        JPanel panel_25 = new JPanel();
        panel_25.setBackground(new Color(0, 191, 255));
        panel_24.add(panel_25, BorderLayout.WEST);

        JPanel panel_26 = new JPanel();
        panel_26.setBackground(new Color(0, 191, 255));
        panel_24.add(panel_26, BorderLayout.EAST);

        JPanel panel_5 = new JPanel();
        panelDeOpciones.add(panel_5);
        panel_5.setBackground(new Color(0, 191, 255));

        JPanel panel_6 = new JPanel();
        panelDeOpciones.add(panel_6);
        panel_6.setBackground(new Color(0, 191, 255));

        JPanel panel_7 = new JPanel();
        panelDeOpciones.add(panel_7);
        panel_7.setBackground(new Color(0, 191, 255));
        panel_7.setLayout(new GridLayout(3, 0, 0, 0));

        JPanel panel_11_1_1_1 = new JPanel();
        panel_11_1_1_1.setBackground(new Color(0, 191, 255));
        panel_7.add(panel_11_1_1_1);

        JPanel panel_19 = new JPanel();
        panel_19.setBackground(new Color(0, 191, 255));
        panel_7.add(panel_19);
        panel_19.setLayout(new BorderLayout(0, 0));

        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addMouseListener(this);
        panel_19.add(btnConfirmar, BorderLayout.CENTER);
        btnConfirmar.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 11));
        btnConfirmar.setEnabled(false);

        JPanel panel_27 = new JPanel();
        panel_27.setBackground(new Color(0, 191, 255));
        panel_19.add(panel_27, BorderLayout.EAST);

        JPanel panel_28 = new JPanel();
        panel_28.setBackground(new Color(0, 191, 255));
        panel_19.add(panel_28, BorderLayout.WEST);

        JPanel panel_8 = new JPanel();
        panelDeOpciones.add(panel_8);
        panel_8.setBackground(new Color(0, 191, 255));

        JPanel panel_9 = new JPanel();
        panelDeOpciones.add(panel_9);
        panel_9.setBackground(new Color(0, 191, 255));
        panel_9.setLayout(new GridLayout(3, 0, 0, 0));

        JPanel panel_11_1_1_1_1 = new JPanel();
        panel_11_1_1_1_1.setBackground(new Color(0, 191, 255));
        panel_9.add(panel_11_1_1_1_1);

        JPanel panel_20 = new JPanel();
        panel_20.setBackground(new Color(0, 191, 255));
        panel_9.add(panel_20);
        panel_20.setLayout(new BorderLayout(0, 0));

        btnFinalizar = new JButton("Finalizar");
        btnFinalizar.addMouseListener(this);
        btnFinalizar.setEnabled(false);
        panel_20.add(btnFinalizar, BorderLayout.CENTER);
        btnFinalizar.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 11));

        JPanel panel_29 = new JPanel();
        panel_29.setBackground(new Color(0, 191, 255));
        panel_20.add(panel_29, BorderLayout.WEST);

        JPanel panel_30 = new JPanel();
        panel_30.setBackground(new Color(0, 191, 255));
        panel_20.add(panel_30, BorderLayout.EAST);

        JPanel panel_10 = new JPanel();
        panelDeOpciones.add(panel_10);
        panel_10.setBackground(new Color(0, 191, 255));

        txtMensaje = new JTextField();
        txtMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(txtMensaje, BorderLayout.SOUTH);
        txtMensaje.setColumns(10);
        txtMensaje.setBorder(null);
        txtMensaje.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 15));
        txtMensaje.setEditable(false);
        
        textFieldNroBox = new JTextField();
        textFieldNroBox.setEditable(false);
        textFieldNroBox.setBackground(new Color(0, 191, 255));
        container.add(textFieldNroBox, BorderLayout.NORTH);
        textFieldNroBox.setColumns(10);
        textFieldNroBox.setBorder(null);
        textFieldNroBox.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldNroBox.setFont(new Font("Roboto Slab SemiBold", Font.PLAIN, 25));
        textFieldNroBox.setText("Box: " + box.toString());

        this.setVisible(true);
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
        this.actionListener.actionPerformed(evento);
    }

    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    @Override
    public void informarMensaje(String mensaje) {
        SwingUtilities.invokeLater(() -> this.txtMensaje.setText(mensaje));
	}

    @Override
    public void cancelarAtencion() {
        this.textFieldDNI.setText("");
        this.txtFieldStatus.setText("");
        this.textFieldCategoria.setText("");
        this.textFieldNombre.setText("");
        this.btnAnular.setEnabled(false);
        this.btnCancelar.setEnabled(false);
        this.btnConfirmar.setEnabled(false);
        this.btnSiguiente.setEnabled(true);
        this.txtMensaje.setText("");
    }

    @Override
    public void confirmarAtencion(Atencion atencion) {
        this.txtFieldStatus.setText(atencion.getEstado().toString());
        this.btnAnular.setEnabled(false);
        this.btnCancelar.setEnabled(false);
        this.btnConfirmar.setEnabled(false);
        this.btnFinalizar.setEnabled(true);
        this.txtMensaje.setText("");
    }

    @Override
    public void finalizarAtencion() {
        this.textFieldDNI.setText("");
        this.txtFieldStatus.setText("");
        this.textFieldCategoria.setText("");
        this.textFieldNombre.setText("");
        this.btnFinalizar.setEnabled(false);
        this.btnSiguiente.setEnabled(true);
        this.txtMensaje.setText("");
    }

    @Override
    public void asignarAtencion(Atencion atencion) {
        this.textFieldDNI.setText(atencion.getCliente().getDNI().toString());
        this.textFieldNombre.setText(atencion.getCliente().getNombre());
        this.textFieldCategoria.setText(atencion.getCliente().getCategoria().toString());
        this.txtFieldStatus.setText(atencion.getEstado().toString());
        this.btnAnular.setEnabled(true);
        this.btnCancelar.setEnabled(true);
        this.btnConfirmar.setEnabled(true);
        this.btnSiguiente.setEnabled(false);
        this.txtMensaje.setText("");
    }

    @Override
    public void anularAtencion() {
        this.textFieldDNI.setText("");
        this.txtFieldStatus.setText("");
        this.textFieldCategoria.setText("");
        this.textFieldNombre.setText("");
        this.btnAnular.setEnabled(false);
        this.btnCancelar.setEnabled(false);
        this.btnConfirmar.setEnabled(false);
        this.btnSiguiente.setEnabled(true);
        this.txtMensaje.setText("");
    }

}
