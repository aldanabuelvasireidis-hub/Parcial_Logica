package Vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import Modelos.TipoEnvio;

public class LogisticaVista extends JFrame {

    private JTable tblEnvios;
    private JPanel pnlEditarEnvio;

    private JTextField txtNumero, txtCliente, txtPeso, txtDistancia;
    private JComboBox<TipoEnvio> cmbTipo;

    private JButton btnQuitarEnvio, btnGuardarEnvio;

    public LogisticaVista() {
        setSize(650, 420);
        setTitle("Operador Logístico");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JToolBar tbLogistica = new JToolBar();

        JButton btnAgregarEnvio = new JButton();
        asignarIcono(btnAgregarEnvio, "/iconos/AgregarEnvio.png", "Agregar");
        btnAgregarEnvio.setToolTipText("Agregar Envío");
        btnAgregarEnvio.addActionListener(evt -> {
            btnAgregarEnvioClick();
        });
        tbLogistica.add(btnAgregarEnvio);

        btnQuitarEnvio = new JButton();
        asignarIcono(btnQuitarEnvio, "/iconos/QuitarEnvio.png", "Retirar");
        btnQuitarEnvio.setToolTipText("Retirar Envío");
        tbLogistica.add(btnQuitarEnvio);

        JPanel pnlEnvios = new JPanel();
        pnlEnvios.setLayout(new BoxLayout(pnlEnvios, BoxLayout.Y_AXIS));

        pnlEditarEnvio = new JPanel();
        pnlEditarEnvio.setPreferredSize(new Dimension(pnlEditarEnvio.getWidth(), 100));
        pnlEditarEnvio.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        pnlEditarEnvio.setLayout(null);

        JLabel lblNumero = new JLabel("Número");
        lblNumero.setBounds(10, 10, 100, 25);
        pnlEditarEnvio.add(lblNumero);

        txtNumero = new JTextField();
        txtNumero.setBounds(110, 10, 100, 25);
        pnlEditarEnvio.add(txtNumero);

        JLabel lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(230, 10, 100, 25);
        pnlEditarEnvio.add(lblTipo);

        cmbTipo = new JComboBox<>();
        cmbTipo.setBounds(340, 10, 120, 25);
        cmbTipo.setModel(new DefaultComboBoxModel<>(TipoEnvio.values()));
        pnlEditarEnvio.add(cmbTipo);

        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setBounds(10, 40, 100, 25);
        pnlEditarEnvio.add(lblCliente);

        txtCliente = new JTextField();
        txtCliente.setBounds(110, 40, 100, 25);
        pnlEditarEnvio.add(txtCliente);

        JLabel lblDistancia = new JLabel("Distancia en Km");
        lblDistancia.setBounds(230, 40, 110, 25);
        pnlEditarEnvio.add(lblDistancia);

        txtDistancia = new JTextField();
        txtDistancia.setBounds(340, 40, 120, 25);
        pnlEditarEnvio.add(txtDistancia);

        JLabel lblPeso = new JLabel("Peso");
        lblPeso.setBounds(10, 70, 100, 25);
        pnlEditarEnvio.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(110, 70, 100, 25);
        pnlEditarEnvio.add(txtPeso);

        btnGuardarEnvio = new JButton("Guardar");
        btnGuardarEnvio.setBounds(230, 70, 100, 25);
        pnlEditarEnvio.add(btnGuardarEnvio);

        JButton btnCancelarEnvio = new JButton("Cancelar");
        btnCancelarEnvio.setBounds(340, 70, 100, 25);
        btnCancelarEnvio.addActionListener(evt -> {
            btnCancelarEnvioClick();
        });
        pnlEditarEnvio.add(btnCancelarEnvio);

        pnlEditarEnvio.setVisible(false); // Se oculta al inicio

        tblEnvios = new JTable();
        tblEnvios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane spListaEnvios = new JScrollPane(tblEnvios);

        pnlEnvios.add(pnlEditarEnvio);
        pnlEnvios.add(spListaEnvios);

        getContentPane().add(tbLogistica, BorderLayout.NORTH);
        getContentPane().add(pnlEnvios, BorderLayout.CENTER);
    }

    public TipoEnvio getTipoSeleccionado() {
        return (TipoEnvio) cmbTipo.getSelectedItem();
    }

    public String getNumero() {
        return txtNumero.getText().trim();
    }

    public String getCliente() {
        return txtCliente.getText().trim();
    }

    public double getPeso() {
        try {
            return Double.parseDouble(txtPeso.getText().trim());
        } catch (Exception ex) {
            return -1;
        }
    }

    public double getDistancia() {
        try {
            return Double.parseDouble(txtDistancia.getText().trim());
        } catch (Exception ex) {
            return -1;
        }
    }

    public int getFilaSeleccionada() {
        return tblEnvios.getSelectedRow();
    }

    public void setGuardarEnvioClick(ActionListener escuchadorEventos) {
        btnGuardarEnvio.addActionListener(escuchadorEventos);
    }

    public void setQuitarEnvioClick(ActionListener escuchadorEventos) {
        btnQuitarEnvio.addActionListener(escuchadorEventos);
    }

    public void mostrarEnvios(String[][] datos, String[] encabezados) {
        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblEnvios.setModel(dtm);
    }

    public void ocultarEdicionEnvio() {
        pnlEditarEnvio.setVisible(false);
    }

    public void limpiarFormulario() {
        txtNumero.setText("");
        txtCliente.setText("");
        txtPeso.setText("");
        txtDistancia.setText("");
        cmbTipo.setSelectedIndex(0);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    public boolean confirmar(String mensaje) {
        return JOptionPane.showConfirmDialog(this, mensaje, "Confirmar",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    private void btnAgregarEnvioClick() {
        limpiarFormulario();
        pnlEditarEnvio.setVisible(true);
        txtNumero.requestFocus();
    }

    private void btnCancelarEnvioClick() {
        limpiarFormulario();
        ocultarEdicionEnvio();
    }

    private void asignarIcono(JButton boton, String ruta, String textoAlterno) {
        var url = getClass().getResource(ruta);
        if (url != null) {
            boton.setIcon(new ImageIcon(url));
        } else {
            boton.setText(textoAlterno);
        }
    }
}