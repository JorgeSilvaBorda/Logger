/**
 *
 * @author Jorge Silva Borda
 */
public class Operacion {
    public String tipoOperacion;
    public String comando;
    public String origen;
    public String destino;

    public Operacion(String tipoOperacion, String comando, String origen, String destino) {
	this.tipoOperacion = tipoOperacion;
	this.comando = comando;
	this.origen = origen;
	this.destino = destino;
    }
    
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
