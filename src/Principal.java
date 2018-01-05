
import com.alee.laf.WebLookAndFeel;

/**
 * @author Jorge Silva Borda
 */
public class Principal {
    
    public static void main(String[] args){
	
	WebLookAndFeel.install();
	WebLookAndFeel.setDecorateAllWindows(true);
	WebLookAndFeel.setDecorateDialogs(true);
	WebLookAndFeel.setDecorateFrames(true);
	Ventana v = new Ventana();
	v.setLocationRelativeTo(null);
	v.setVisible(true);
    }
}