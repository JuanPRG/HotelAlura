package views;

import com.alura.controller.HuespedController;
import com.alura.controller.ReservaController;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloH;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private ReservaController reservaController;
	private HuespedController huespedController;

	private JTabbedPane panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
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
	public Busqueda() {
		this.reservaController = new ReservaController();
		this.huespedController = new HuespedController();

		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), tbReservas, null);
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");

		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), tbHuespedes, null);
		modeloH = (DefaultTableModel) tbHuespedes.getModel();
		modeloH.addColumn("Numero de Huesped");
		modeloH.addColumn("Nombre");
		modeloH.addColumn("Apellido");
		modeloH.addColumn("Fecha de Nacimiento");
		modeloH.addColumn("Nacionalidad");
		modeloH.addColumn("Telefono");
		modeloH.addColumn("Numero de Reserva");
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				MenuUsuario usuario = new MenuUsuario();
//				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Objects.equals(txtBuscar.getText(), "")) {
					JOptionPane.showMessageDialog(null,"Ingresa un numero de reserva o apellido!");
				} else {
					limpiarTabla();
					cargarTabla(txtBuscar.getText());
				}

			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modificar();
				limpiarTabla();
				cargarTabla(txtBuscar.getText());
			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminar();
				limpiarTabla();
				cargarTabla(txtBuscar.getText());
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}

	private void limpiarTabla() {
		modelo.getDataVector().clear();
		modeloH.getDataVector().clear();
	}
// ----------**************--------------------************************
private void cargarTabla(String textoBusqueda) {

	if (textoBusqueda.matches("^-?\\d+$")) {
		panel.setSelectedIndex(0);
		var reservas = this.reservaController.listar(textoBusqueda);
		modelo.addRow(new Object[] {
				"Idreserva",
				"Fecha Entrada",
				"Fecha Salida",
				"Valor",
				"Forma de Pago",

		});

		if(!reservas.isEmpty()) {
			reservas.forEach(reserva -> modelo.addRow(
					new Object[] {
							reserva.getIdReserva(),
							reserva.getFechaEntrada(),
							reserva.getFechaSalida(),
							reserva.getValor(),
							reserva.getFormaPago()}));
		} else {
			JOptionPane.showMessageDialog(null,"Idreserva no encontrado");
		}



	} else {
		panel.setSelectedIndex(1);
		var reservas = this.huespedController.listar(textoBusqueda);

		modeloH.addRow(new Object[] {
				"nombre",
				"apellido",
				"Fecha Nacimiento",
				"Nacionalidad",
				"Telefono",
				"Idreserva",
		});

		if(!reservas.isEmpty()) {
			reservas.forEach(reserva -> modeloH.addRow(
					new Object[] {
							reserva.getNombre(),
							reserva.getApellido(),
							reserva.getFechaNacimiento(),
							reserva.getNacionalidad(),
							reserva.getTelefono(),
							reserva.getIdReserva()}));
		} else {
			JOptionPane.showMessageDialog(null,"Apellido no encontrado");
		}


	}

}
	private boolean tieneFilaElegidaHuespedes() {
		return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
	}

	private boolean tieneFilaElegidaReservas() {
		return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;
	}


	private void modificar() {

		if(panel.getSelectedIndex() == 0){
			if (tieneFilaElegidaReservas()) {
				JOptionPane.showMessageDialog(this, "Por favor, elije un item");
				return;
			}

			Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer idReserva = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
						String fechaEntrada = modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString();
						String fechaSalida = modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString();
						Integer valor = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString());
						String formaPago = (String) modelo.getValueAt(tbReservas.getSelectedRow(),4);
						var filasModificadas = this.reservaController.modificarReservas(idReserva, fechaEntrada, fechaSalida,valor,formaPago);
						JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito!", filasModificadas));
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		} else if(panel.getSelectedIndex() == 1) {

			if (tieneFilaElegidaHuespedes()) {
				JOptionPane.showMessageDialog(this, "Por favor, elije un item");
				return;
			}

			Optional.ofNullable(modeloH.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
					.ifPresentOrElse(fila -> {



						String nombre = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0);
						String apellido = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 1);
						String fechaNacimiento = modeloH.getValueAt(tbHuespedes.getSelectedRow(), 2).toString();
						String nacionalidad = (String) modeloH.getValueAt(tbHuespedes.getSelectedRow(), 3);
						Integer telefono = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(),4).toString());
						Integer idReserva = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(),5).toString());
						var filasModificadas = this.huespedController.modificarHuespedes(nombre, apellido, fechaNacimiento,nacionalidad,telefono,idReserva);
						JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito!", filasModificadas));
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		}

	}

	private void eliminar() {
		if (tieneFilaElegidaHuespedes()) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un item");
			return;
		}else {
			System.out.println();
			Optional.ofNullable(modeloH.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 5).toString());
						var filasModificadas = this.huespedController.eliminar(id);

						modeloH.removeRow(tbHuespedes.getSelectedRow());
						JOptionPane.showMessageDialog(this,
								String.format("%d item eliminado con éxito!", filasModificadas));
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		}
	}
}
