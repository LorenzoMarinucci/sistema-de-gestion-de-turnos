package vistas.JFrame;

import vistas.Vista;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

public abstract class VistaAbstracta extends JFrame implements Vista {

	protected ActionListener actionListener;

	@Override
	public void cerrarVista() {
		this.dispose();
	}

	@Override
	public void abrirVista() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
	}

	@Override
	public void setActionListener(ActionListener actionListener){
		this.actionListener = actionListener;
	}

}
