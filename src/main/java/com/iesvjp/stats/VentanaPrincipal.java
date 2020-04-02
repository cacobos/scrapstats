package com.iesvjp.stats;

import javax.swing.JButton;
import javax.swing.JFrame;

public class VentanaPrincipal {
	private JFrame ventana;
	
	private JButton boton;
	
	public VentanaPrincipal(){
		
	}
	
	public void inicializar() {
		inicializarComponentes();
		inicializarListeners();
	}

	private void inicializarComponentes() {
		ventana=new JFrame();
		ventana.setBounds(100, 100, 700, 500);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		ventana.setVisible(true);
	}

	private void inicializarListeners() {
		// TODO Auto-generated method stub
		
	}
}
