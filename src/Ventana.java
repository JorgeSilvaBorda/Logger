
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 * @author Jorge Silva Borda
 */
public final class Ventana extends javax.swing.JFrame {

    private int total;
    private LinkedList<Operacion> ope;
    private File archivo;
    private Database data;
    int arch;
    private final int ANCHO = 100;
    private int errs = 0;
    private int warns = 0;
    private final String RUTA_MDB = "\\\\vmdvorak\\Aplicaciones\\UltimasVersiones60";

    public Ventana() {
	initComponents();
	ajustesIniciales();
	cargarConfig();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        chkSilencio = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        progreso = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        salidas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Distribución v2.0");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuración"));

        jButton2.setText("Ejecutar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        chkSilencio.setSelected(true);
        chkSilencio.setText("Modo silencioso");

        jLabel2.setText("Progreso: ");

        progreso.setBackground(new java.awt.Color(51, 51, 51));
        progreso.setForeground(new java.awt.Color(51, 51, 51));

        jLabel3.setForeground(new java.awt.Color(0, 153, 255));
        jLabel3.setText("Qué es esto?");
        jLabel3.setToolTipText("Click para información");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(progreso, javax.swing.GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(chkSilencio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(chkSilencio)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(progreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Información"));

        salidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Evento", "Detalle"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        salidas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(salidas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
	if (abrirArchivo()) {
	    info("El archivo de bases de datos es: " + archivo.getAbsolutePath());
	    try {
		data = DatabaseBuilder.open(archivo);
	    } catch (IOException ex) {
		System.out.println(ex);
	    }
	    procesar(archivo);
	}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
	popInfo("El modo silencioso previente la muestra de ventanas de información a medida que se registran las librerías o surgen errores.\nDesactívelo para poder ver las ventanas de confirmación de windows.");
    }//GEN-LAST:event_jLabel3MouseClicked

    public static void main(String args[]) {
	java.awt.EventQueue.invokeLater(() -> {
	    new Ventana().setVisible(true);
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkSilencio;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JProgressBar progreso;
    private javax.swing.JTable salidas;
    // End of variables declaration//GEN-END:variables

    /**
     * Levanta el diálogo para poder buscar el archivo MDB que contiene los
     * parámetros de distribución.
     */
    public boolean abrirArchivo() {
	this.archivo = new File(RUTA_MDB);
	if (this.archivo.exists()) {
	    return true;
	}
	System.out.println("No se puede encontrar el archvo '" + RUTA_MDB + "'");
	return false;
    }

    public void procesar(File archivo) {
	LinkedList<Operacion> operaciones = new LinkedList();
	try {
	    try (Database db = DatabaseBuilder.open(archivo)) {
		Table tabla = db.getTable("Archivos");
		this.total = tabla.getRowCount();
		this.progreso.setMaximum(this.total);
		for (Row row : tabla) {
		    Operacion o = new Operacion(
			    row.getString("tipoOperacion"),
			    row.getString("Comando"),
			    row.getString("Origen"),
			    row.getString("Destino")
		    );
		    operaciones.add(o);
		}
		Thread hilo = new Thread() {
		    @Override
		    public void run() {
			ejecutar(operaciones);
		    }
		};
		hilo.start();

	    }
	} catch (IOException ex) {
	    error("No se ha podido ejecutar: " + ex);
	}
    }

    public void ejecutar(LinkedList<Operacion> operaciones) {
	operaciones.forEach((op) -> {
	    Operaciones opes = new Operaciones(this.chkSilencio.isSelected());
	    String[] salida = opes.procesar(op);
	    if (salida[0].equals("WARN")) {
		warns++;
	    }
	    if (salida[0].equals("ERR")) {
		errs++;
	    }
	    log(salida);

	    progreso.setValue(progreso.getValue() + 1);
	    int porcentaje = (progreso.getValue() * 100) / this.total;
	    progreso.setString(porcentaje + "% completado");
	    //progreso.setStringPainted(true);
	});
	info("Advertencias: " + warns);
	info("errores: " + errs);
	if (errs == 0 && warns == 0) {
	    info("Procesamiento finalizado completamente.");
	} else if (errs > 0 && warns == 0) {
	    info("Procesamiento finalizado con " + errs + " errores. Por favor consultar log completo.");
	} else if (errs == 0 && warns > 0) {
	    info("Procesamiento finalizado con " + errs + " advertencias. Por favor consultar log completo.");
	} else if (errs > 0 && warns > 0) {
	    info("Procesamiento finalizado con " + errs + " errores y " + warns + " advertencias. Por favor consultar log completo.");
	}
	salidas.scrollRectToVisible(salidas.getCellRect(salidas.getRowCount() - 1, 0, true));
	salidas.scrollRectToVisible(salidas.getCellRect(salidas.getRowCount() - 1, 0, true));
    }
    
    public void ajustesIniciales() {
	TableColumnModel columnModel = salidas.getColumnModel();
	columnModel.getColumn(0).setPreferredWidth(ANCHO);
	columnModel.getColumn(0).setMaxWidth(ANCHO);
	columnModel.getColumn(0).setWidth(ANCHO);
	columnModel.getColumn(0).setMinWidth(ANCHO);
	salidas.setColumnModel(columnModel);
	if (System.getProperty("os.arch").contains("64")) {
	    arch = 64;
	    info("Sistema de 64 bits.");
	} else {
	    arch = 32;
	    info("Sistema de 32 bits.");
	}
	info("Por favor seleccione el archivo de datos para la carga.");
	progreso.setString("0% completado");
	progreso.setStringPainted(true);
    }
    
    public void cargarConfig(){
	try {
	    File fileConfig = new File(recursos.config.Config.RUTA_ARCHIVO_MDB + recursos.config.Config.NOMBRE_ARCHIVO_MDB);
	    System.out.println("Ruta: " + recursos.config.Config.RUTA_ARCHIVO_MDB + recursos.config.Config.NOMBRE_ARCHIVO_MDB);
	    if(fileConfig.exists()){
		System.out.println("Se puede leer");
		
	    }else{
		System.out.println("No se puede leer");
	    }
	} catch (Exception ex) {
	    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    //Logging-------------------------------------------------------------------
    public void log(String mensaje) {
	log("", mensaje);
    }

    private void log(String a, String mensaje) {
	if (mensaje.length() > 0) {
	    String[] data = {a, mensaje};
	    DefaultTableModel modelo = (DefaultTableModel) salidas.getModel();
	    modelo.addRow(data);
	    salidas.setModel(modelo);
	    salidas.scrollRectToVisible(salidas.getCellRect(salidas.getRowCount() - 1, 0, true));
	}
    }

    private void log(String[] mensaje) {
	if (mensaje[1].length() > 0) {
	    String mens = mensaje[1];
	    switch (mensaje[0]) {
		case "INFO":
		    info(mens);
		    break;
		case "WARN":
		    warn(mens);
		    break;
		case "ERR":
		    error(mens);
		    break;
		default:
		    log(mens);
		    break;
	    }
	}
    }

    public void info(String mensaje) {
	log("INFORMACIÓN", mensaje);
    }

    public void warn(String mensaje) {
	log("ADVERTENCIA", mensaje);
    }

    public void error(String mensaje) {
	log("ERROR", mensaje);
    }

    public void popInfo(String mensaje) {
	JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void popAlert(String mensaje) {
	JOptionPane.showMessageDialog(this, mensaje, "Alerta", JOptionPane.WARNING_MESSAGE);
    }
}
