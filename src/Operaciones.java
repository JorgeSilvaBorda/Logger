
import com.alee.utils.FileUtils;
import java.io.File;
import java.io.IOException;

/**
 * @author Jorge Silva Borda
 */
public class Operaciones {

    boolean silencio = true;

    public Operaciones() {

    }

    public Operaciones(boolean silencioso) {
	this.silencio = silencioso;
    }

    /**
     * Distribuye la carga en sub módulos para procesar los parámetros de
     * distribución.
     *
     * @param operacion {@code Operacion}. La operación que se evalúa para
     * ejecución.
     * @return {@code String[]} con el tipo de información para el log.
     */
    public String[] procesar(Operacion operacion) {
	switch (operacion.tipoOperacion) {
	    case "copiar":
		return copiar(operacion);
	    case "copiarEjecutar":
		return copiarEjecutar(operacion);
	    case "ejecutar":
		return ejecutar(operacion);
	}
	return new String[]{"", ""};
    }

    /**
     * Copia un archivo desde un lugar a otro en Distribución.
     *
     * @param op {@code Operacion}. La operación que contiene la info del
     * archivo que se desea copiar.
     * @return {@code String[]} con el tipo de información para el log.
     */
    public String[] copiar(Operacion op) {
	File archivo = new File(op.origen);
	if (archivo.exists()) {
	    FileUtils.copyFile(op.origen, op.destino);

	    return new String[]{"INFO", "Copiado desde: " + op.origen + " hacia: " + op.destino};
	} else {
	    return new String[]{"ERR", "No existe el archivo a copiar: " + op.origen + ""};
	}
    }

    /**
     * Ejecuta el comando en caso de que la operación lo requiera.
     *
     * @param op {@code Operacion}. La operación que contiene lo que se desea
     * ejecutar.
     * @return {@code String[]} con el tipo de información para el log.
     */
    public String[] ejecutar(Operacion op) {

	String comando = corregirComando(op);
	if (!op.destino.endsWith("BFAImage.dll")) { //Prevenir ejecución con BFAImage.dll
	    try {
		System.out.println(op);
		System.out.println("Comando: " + comando);
		Object o = Runtime.getRuntime().exec(comando);
		System.out.println("Salida: " + o);
		System.out.println("");
		return new String[]{"INFO", "Comando ejecutado: " + comando};
	    } catch (IOException ex) {
		return new String[]{"ERR", "No se ha podido ejecutar el comando: " + comando + "." + System.getProperty("line.separator") + ex};
	    }
	}else{
	    return new String[]{"WARN", "No se ha incluído el archivo BFAImage.dll por problemas en su ejecución."};
	}
    }

    /**
     * Resume la operación de copiar/ejecutar llamando a los métodos ya
     * declarados.
     *
     * @param op {@code Operacion}. La operación que se desea procesar.
     * @return {@code String[]} con el tipo de información para el log.
     */
    public String[] copiarEjecutar(Operacion op) {
	String[] copia = copiar(op);
	String[] ejecucion = ejecutar(op);

	if (ejecucion[0].equals("INFO") && copia[0].equals("INFO")) {
	    return new String[]{"INFO", "Copiado desde: " + op.origen + " hacia: " + op.destino + ". Comando ejecutado: " + op.comando};
	} else {
	    return new String[]{"ERR", "No se ha podido ejecutar la operación de copia y ejecución: " + System.getProperty("line.separator") + copia + ". Comando: " + ejecucion};
	}
    }

    /**
     * IMPORTANTE!!!!!!!!!!!!!!!-----------------------------------------------
     * En distribución, para aplicar el comando regsvr32, se debe indicar la
     * ruta completa de la librería que se desea registrar. En la base de datos,
     * venía el comando mal implementado, por lo que se corrige previo a su
     * ejecución. En caso de que se decida reparar desde la base de datos
     * (distribucion.mdb), se debe quitar este corrector, puesto que va a
     * generar comandos con mala sintáxis.
     *
     * En otro caso se debe verificar a qué carpeta corresponden las librerías
     * en sistemas de 64 bits.
     *
     * @param o La operación a ejecutar.
     * @return {@code String} con el comando "corregido".
     * @see Operacion
     */
    public String corregirComando(Operacion o) {

	if (o.comando == null || o.comando.equals("null") || o.comando.equals("false")) {
	    return null;
	} else if (o.destino.toLowerCase().endsWith(".bat")) {
	    // es un BAT, no lleva comando por delante. Ejecutar "Ruta destino"
	    return o.destino;
	} else if ((o.destino.toLowerCase().endsWith(".dll") || o.destino.toLowerCase().endsWith(".ocx")) && !o.destino.toLowerCase().endsWith("BFAImage.dll")) {
	    //Es una DLL... Lleva el comando regsvr32 y la ruta en la que se supone 
	    //debe caer. Ejecutar la regsvr32 [RutaCompleta]. (Verificar si corresponde a 32 o 64 bits).
	    return "regsvr32 " + (silencio == true ? "/s" : "") + " " + "\"" + o.destino + "\"";
	} else if (o.destino.toLowerCase().endsWith("BFAImage.dll")) {
	    return "regsvr 32 " + (silencio == true ? "/s" : "") + " /i /n " + "\"" + o.destino + "\"";
	    //return "";
	}
	return "";
    }
}
