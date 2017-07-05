package es.cic.taller;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	String resultado="";
		
	
	@Override
	protected void init(VaadinRequest vaadinRequest) {
		
    
		final VerticalLayout layout = new VerticalLayout();
			
		final TextField name = new TextField();
		name.setCaption("Nombre");
		name.setMaxLength(30);
		
		final TextField apellidos = new TextField();
		apellidos.setCaption("Apellidos");
		apellidos.setMaxLength(30);

		final TextField edad = new TextField();
		edad.setCaption("Edad");
		edad.setMaxLength(3);

		final TextField domicilio = new TextField();
		domicilio.setCaption("Domicilio");
		domicilio.setMaxLength(50);
				
		final TextArea datosTxt = new TextArea();
		datosTxt.setValue(resultado);
		datosTxt.setRows(10);
		datosTxt.setSizeFull();
    
		name.addValueChangeListener(event -> actualizarResultado(name, apellidos, edad, domicilio,datosTxt));
		apellidos.addValueChangeListener(event2 -> actualizarResultado(name, apellidos, edad, domicilio,datosTxt));
		apellidos.addValueChangeListener(event3 -> actualizarResultado(name, apellidos, edad, domicilio,datosTxt));	
		domicilio.addValueChangeListener(event4 -> actualizarResultado(name, apellidos, edad, domicilio,datosTxt));
		
		datosTxt.setValue(resultado);
		
		datosTxt.addValueChangeListener(event5 -> Notification.show("Los datos han cambiado",
        String.valueOf(resultado),
        Type.TRAY_NOTIFICATION));
		
		layout.addComponents(name,apellidos,edad,domicilio,datosTxt);

		setContent(layout);
	}

	private void actualizarResultado(final TextField name, final TextField apellidos, final TextField edad,
			final TextField domicilio, final TextArea datosTxt) {
		resultado = name.getValue() + " \n" + apellidos.getValue() + " \n" + edad.getValue() + " \n"
		+ domicilio.getValue();
		datosTxt.setValue(resultado);
	}
	

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
