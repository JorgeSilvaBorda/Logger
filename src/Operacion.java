/**
 * @author Jorge Silva Borda
 * Clase para almacenar las filas de la base de datos de distribución.
 */
public class Operacion {
    
    public String tipoOperacion;
    public String comando;
    public String origen;
    public String destino;

    /**
     * Constructor
     * @param tipoOperacion {@code String}. Parámetro creado en duro para la 
     * clase.
     * Según lo analizado de distribución.mdb, existen dos tipos de operación:
     * <ul>
     * <li>Copiar</li>
     * <li>Copiar y ejecutar</li>
     * </ul>
     * @param comando {@code String}, el comando en caso de que la operación sea
     * de copiar y ejecutar @param origen El origen del archivo que se desea 
     * copiar. 
     * @param origen {@code String}. El origen donde se encuentra el archivo.
     * @param destino {@code String}. El destino del archivo que se desea 
     * copiar.
     */
    public Operacion(String tipoOperacion, String comando, String origen, String destino) {
	this.tipoOperacion = tipoOperacion;
	this.comando = comando;
	this.origen = origen;
	this.destino = destino;
    }
    
    /**
     * Método para convertir la clase en String en caso de que se requiera para 
     * pruebas o debugging.
     * @return {@code String}, con la información del contenido de la operación.
     */
    @Override
    public String toString(){
	StringBuilder builder = new StringBuilder();
	builder.append("[TIPO OPERACION : ").append(tipoOperacion).append("]").append(System.getProperty("line.separator"));
	builder.append("[COMANDO : ").append(comando).append("]").append(System.getProperty("line.separator"));
	builder.append("[ORIGEN : ").append(origen).append("]").append(System.getProperty("line.separator"));
	builder.append("[DESTINO : ").append(destino).append("]").append(System.getProperty("line.separator"));
	return builder.toString();
    }
}
