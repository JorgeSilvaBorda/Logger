
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 * @author Jorge Silva Borda
 */
public final class Ventana extends javax.swing.JFrame {

    private LinkedList<Operacion> ope;
    private File archivo;
    int arch;
    private final int ANCHO = 100;
    private int errs = 0;
    private int warns = 0;
    public Ventana() {
	initComponents();
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
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtRuta = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        salidas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Distribución v2.0");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuración"));

        jLabel1.setText("Origen de datos: ");

        txtRuta.setEditable(false);
        txtRuta.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setText("Seleccione");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ejecutar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRuta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Información"));

        salidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Evento", "Error"
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1038, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
	JFileChooser chooser = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de base de datos de Microsoft Access ", "mdb");
	chooser.setFileFilter(filter);
	chooser.setApproveButtonText("Abrir");
	chooser.setApproveButtonMnemonic('a');
	int returnVal = chooser.showOpenDialog(this);
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    archivo = chooser.getSelectedFile();
	    txtRuta.setText(archivo.getAbsolutePath());
	}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
	if (archivo != null) {
	    info("El archivo de bases de datos es: " + archivo.getAbsolutePath());
	    procesar(archivo);
	} else {
	    warn("No se ha seleccionado ningún archivo.");
	}
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
	java.awt.EventQueue.invokeLater(() -> {
	    new Ventana().setVisible(true);
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable salidas;
    private javax.swing.JTextField txtRuta;
    // End of variables declaration//GEN-END:variables

    public void procesar(File archivo) {
	LinkedList<Operacion> operaciones = new LinkedList();
	///ope = new LinkedList();
	try {
	    Database db = DatabaseBuilder.open(archivo);
	    Table tabla = db.getTable("Archivos");
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
	    db.close();
	} catch (IOException ex) {
	    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public void ejecutar(LinkedList<Operacion> operaciones) {
	operaciones.forEach((op) -> {
	    Operaciones opes = new Operaciones();
	    String[] salida = opes.procesar(op);
	    if(salida[0].equals("WARN")){
		warns ++;
	    }
	    if(salida[0].equals("ERR")){
		errs ++;
	    }
	    log(salida);
	});
	info("Advertencias: " + warns);
	info("errores: " + errs);
	if(errs == 0 && warns == 0){
	    info("Procesamiento finalizado completamente.");
	}else if(errs > 0 && warns == 0){
	    info("Procesamiento finalizado con " + errs + " errores. Por favor consultar log completo.");
	}else if(errs == 0 && warns > 0){
	    info("Procesamiento finalizado con " + errs + " advertencias. Por favor consultar log completo.");
	}else if(errs > 0 && warns > 0){
	    info("Procesamiento finalizado con " + errs + " errores y " + warns + " advertencias. Por favor consultar log completo.");
	}
	salidas.scrollRectToVisible(salidas.getCellRect(salidas.getRowCount() - 1, 0, true));
	salidas.scrollRectToVisible(salidas.getCellRect(salidas.getRowCount() - 1, 0, true));
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
}
