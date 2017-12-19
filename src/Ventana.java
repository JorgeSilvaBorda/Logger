
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

/**
 * @author Jorge Silva Borda
 */
public final class Ventana extends javax.swing.JFrame {
    private LinkedList<Operacion> op;
    private File archivo;
    int arch;

    public Ventana() {
	initComponents();
	//System.out.println(System.getProperty("os.arch"));
	if (System.getProperty("os.arch").contains("64")) {
	    arch = 64;
	    log("Sistema de 64 bits.");
	} else {
	    arch = 32;
	    log("Sistema de 32 bits.");
	}
	log("Por favor seleccione el archivo de datos para la carga.");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtRuta = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        salidas = new javax.swing.JTextArea();

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
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jButton2))
                .addGap(0, 198, Short.MAX_VALUE))
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Información"));

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setForeground(new java.awt.Color(0, 204, 0));

        salidas.setBackground(new java.awt.Color(0, 0, 0));
        salidas.setColumns(20);
        salidas.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        salidas.setForeground(new java.awt.Color(0, 204, 51));
        salidas.setRows(5);
        salidas.setCaretColor(new java.awt.Color(0, 204, 0));
        salidas.setDisabledTextColor(new java.awt.Color(0, 153, 153));
        salidas.setSelectedTextColor(new java.awt.Color(51, 204, 0));
        salidas.setSelectionColor(new java.awt.Color(153, 153, 153));
        jScrollPane1.setViewportView(salidas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
	    log("El archivo de bases de datos es: " + archivo.getAbsolutePath());
	    procesar(archivo);
	} else {
	    log("No se ha seleccionado ningún archivo.");
	}
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		new Ventana().setVisible(true);
	    }
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea salidas;
    private javax.swing.JTextField txtRuta;
    // End of variables declaration//GEN-END:variables

    public void procesar(File archivo) {
	LinkedList<Operacion> operaciones = new LinkedList();
	op = new LinkedList();
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
	    Thread hilo = new Thread(){
		
		public void run(){
		    ejecutar(operaciones);
		}
	    };
	    hilo.start();
	    //ejecutar(operaciones);
	    db.close();
	} catch (IOException ex) {
	    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public void ejecutar(LinkedList<Operacion> operaciones) {
	operaciones.forEach((op) -> {
	    log(op.toString());
	    
	    Operaciones opes = new Operaciones();
	    log(opes.procesar(op));
	});
    }
    
    public static void log(String mensaje){
	salidas.append(mensaje);
	salidas.append(System.getProperty("line.separator"));
	salidas.setCaretPosition(salidas.getDocument().getLength() - 1);
    }
}
