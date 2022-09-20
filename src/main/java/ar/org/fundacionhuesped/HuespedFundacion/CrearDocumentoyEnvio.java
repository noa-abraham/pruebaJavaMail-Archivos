package ar.org.fundacionhuesped.HuespedFundacion;

import org.apache.pdfbox.multipdf.Splitter;   
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;   
import java.io.IOException;   
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


import java.util.ArrayList;
import java.util.Iterator;  

public class CrearDocumentoyEnvio {
			
	public static void main(String[] args)throws IOException {

		ArrayList <String> listaDeCorreos = new ArrayList<String>();
		String remitente = "noeliasabraham@gmail.com"; //completar con la dirección de correo
		String passwordRemitente = "wvokcydocncviqrl"; //completar con la contraseña de 16 caracteres
		
		/* PARA EXTRAER PAG. DEL PDF, GUARDARLAS COMO DOCUMENTOS NUEVOS Y TOMAR LA INFORMACIÓN DE LOS MISMOS */
	
		//Carga de pdf existente   
		File file = new File("Nombres.pdf");   
		PDDocument document = PDDocument.load(file);  

		// Crea instancia de la clase  Splitter
		//Divide las páginas del PDF y las devuelve en forma de lista
		Splitter splitter = new Splitter();  
		List<PDDocument>Pages = splitter.split(document);  
		          
		//Recorre la lista 
		Iterator<PDDocument>iterator = Pages.listIterator(); 
		      
		//Guarda cada página como un documento individual
		while(iterator.hasNext()) {  
			PDDocument pd = iterator.next(); 

		    //Extrae solo el primer nombre, lo pone en minúscula, le agrega @ 
		    PDFTextStripper pdfStripper = new PDFTextStripper();
		    String nombreApellido = pdfStripper.getText(pd);
		    String[] nombreSolo = nombreApellido.split(" "); 
		    String direccionCorreo = nombreSolo[0].toLowerCase() + "@" + "gmail.com"; 
		        
		    //Guarda cada archivo PDF con el nombre del Empleade 
		    pd.save("Empleade" + nombreSolo[0].toString() + ".pdf");
		    
		    //Guarda las direcciones de correo en el ArrayList
		    listaDeCorreos.add(direccionCorreo);
		    System.out.println (direccionCorreo);
		    
		}
		document.close();
		
		enviarDesdeGmail (remitente, passwordRemitente, listaDeCorreos); 
		
	}

	/* PARA ENVIAR CORREOS CON ADJUNTOS */
		
		private static void enviarDesdeGmail (String remitente, String passwordRemitente, ArrayList <String> listaDeCorreos) {
		
		try {
			Iterator <String> iterarListaCorreos = listaDeCorreos.iterator();
			String correoDestinatarie; 
	
			while (iterarListaCorreos.hasNext()) {
				correoDestinatarie = iterarListaCorreos.next(); 
			
	    
				// //Establece la dirección IP del host y habilita la autenticación
				Properties props = new Properties();
				props.put("mail.smtp.ssl.trust", "smtp.gmail.com"); //El servidor SMTP de Google
				props.setProperty("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
				props.setProperty("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
				props.setProperty("mail.smtp.user", remitente); //Remitente o Cuenta origen
				props.setProperty("mail.smtp.auth", "true"); //Usar autenticación mediante usuario y clave

          
				//Obtener una sesión con las propiedades anteriormente definidas
				//El método estático getDefaultInstance devuelve siempre el mismo objeto Session (no crea una sesion nueva)
				Session session = Session.getDefaultInstance(props, null);
         

				//Texto del mensaje
				BodyPart texto = new MimeBodyPart();
				texto.setText("Texto del mensaje");
				
				

				//Adjuntar el archivo
				BodyPart adjunto = new MimeBodyPart();
				adjunto.setDataHandler(
						new DataHandler(new FileDataSource("Nombres.pdf")));
				adjunto.setFileName("Nombres.pdf");

				//Una MultiParte para agrupar texto y archivo adjunto
				MimeMultipart multiParte = new MimeMultipart();
				multiParte.addBodyPart(texto);
				multiParte.addBodyPart(adjunto);

				//Message: clase abstracta que modela el mensaje con sus atributos.
				//Establece la dirección de remitente y destinatario
				//MimeMessage hereda de Message y permite incluir adjuntos, imágenes, ect. 
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(remitente));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestinatarie)); 
				//varios destinatarios pero de a 1
				message.setSubject("Envio con adjunto");
				message.setContent(multiParte);

				//Transport: clase encargada de enviar el correo.
				//Se realiza  la conexión dando usuarix -correo de la cuenta que estemos usando- y la clave.
				Transport t = session.getTransport("smtp");
				t.connect("smtp.gmail.com", remitente, passwordRemitente);
				t.sendMessage(message, message.getAllRecipients());
				System.out.println ("¡Correo enviado a " + correoDestinatarie + "!"); 
				t.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System .out.println( "Error en el envío de correo: " + e);
		}
	}
}