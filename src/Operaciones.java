
import com.alee.utils.FileUtils;
import java.io.File;

/**
 * @author Jorge Silva Borda
 */
public class Operaciones {

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

    public String[] copiar(Operacion op) {
	File archivo = new File(op.origen);
	if (archivo.exists()) {
	    FileUtils.copyFile(op.origen, op.destino);
	    return new String[]{"INFO", "Copiado desde: " + op.origen + " hacia: " + op.destino};
	} else {
	    return new String[]{"ERR", "No existe el archivo a copiar: " + op.origen + ""};
	}
    }

    public String[] ejecutar(Operacion op) {
	return new String[]{"", ""};
    }

    public String[] copiarEjecutar(Operacion op) {
	return new String[]{"", ""};
    }
}
