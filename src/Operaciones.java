
import com.alee.utils.FileUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jorge Silva Borda
 */
public class Operaciones {

    public String procesar(Operacion operacion) {
	switch (operacion.tipoOperacion) {
	    case "copiar":
		return copiar(operacion);
	    case "copiarEjecutar":
		return copiarEjecutar(operacion);
	    case "ejecutar":
		return ejecutar(operacion);
	}
	return "";
    }

    public String copiar(Operacion op) {
	File archivo = new File(op.origen);
	if (archivo.exists()) {
	    FileUtils.copyFile(op.origen, op.destino);
	    return "Copiado desde " + op.origen + " hacia " + op.destino;
	} else {
	    return "No existe el archivo a copiar. [" + op.origen + "]";
	}
    }

    public String ejecutar(Operacion op) {
	return "";
    }

    public String copiarEjecutar(Operacion op) {
	return "";
    }
}
