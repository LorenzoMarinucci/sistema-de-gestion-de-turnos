package vistas;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

public abstract class VistaAbstracta extends JFrame implements Vista {
	
	protected ActionListener actionListener;

	@Override
	public void cerrarVista() {
		this.dispose();
	}

	@Override
	public void abrirVista(ActionListener actionListener) {
		this.actionListener = actionListener;
		this.setVisible(true);
	}

}
