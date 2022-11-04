## TRABAJO CON ARCHIVOS Y CORREO-JAVA-EJERCICIO
Noelia Abraham

**LENGUAJE:** JAVA

**IDE:** Eclipse 2021-9 

**Librerías utilizadas:** PDFBox (Apache) y JavaMail.

**Proyecto MAVEN** –ver archivo POM.xml


El Ejercicio propuesto consta de 2 partes generales: 

     1.Separar el pdf en cada una de sus hojas

     2. Mandar desde una sola casilla de mail la hoja correspondiente al empleade.



**A. LEER Y EXTRAER CONTENIDO DE PÁGINAS DE UN PDF Y GUARDARLAS EN ARCHIVOS INDIVIDUALES.**

  **Librería utilizada:** PDFBOX. 
  
  **Link Documentación Librería:** https://pdfbox.apache.org/
  
  Apache PDFBox es una biblioteca Java de código abierto que admite el desarrollo y la conversión de documentos PDF. Con esta biblioteca, puede desarrollar     programas Java que crean, convierten y manipulan documentos PDF.


**//Implementación:** 

    En cada línea de código se comentan las clases utilizadas y qué hacen. 
    
    Si el entorno de ejecución es Eclipse, una vez corrido el programa, se debe refrescar la pantalla para poder ver los PDF generados, o bien acceder al         directorio/carpeta en la computadora y verificar que allí estén. 
    
   Básicamente, se ingresa el PDF, se extrae información de sus páginas y se guarda cada una de éstas como un nuevo documento. Adicionalmente, la información    extraída se utiliza para generar la dirección de correo, a partir del primer nombre y también para guardar el archivo PDF con el nombre correspondiente al    Empleade. 

**B.ENVIAR CORREO DESDE UNA ÚNICA CASILLA CON ADJUNTOS PARA CADA EMPLEADE**

**API:** JavaMail

**Documentación API:** (https://javaee.github.io/javamail/#API_Documentation)

**Motivo de la elección:** La API de JavaMail proporciona una plataforma independiente y marco independiente del protocolo para crear correo y mensajería, pensada para enviar correo a través de SMTP a través de cualquier servidor.

**Clases implicadas:** 

   Sessión y Transport para conectarse a gmail y enviar el mensaje
   MimeMessage, MimeMultiPart y MimeBodyPart para el asunto, los adjuntos y el cuerpo del mensaje. 

**Requisitos Previos:**

1. CONFIGURAR EL SERVIDOR DE CORREOS.

Por defecto, algunos servidores como Gmail, Yahoo, entre otros, no permiten la conexión directa mediante usuario y clave a sus servidores de envío. Por eso es necesario configurar la cuenta de Gmail y darle acceso a aplicaciones “menos seguras”.  Es por ello que estos pasos solo se indican en el caso de utilizar estos clientes de correo. 

    PASOS

    Acceso directo a este ajuste: https://myaccount.google.com/lesssecureapps?pli=1&rapt=AEjHL4N8sdXN7U7KaKoeNPEyTxt7NTkGNjM5ZFxc4Hm4zSEPkzEba5-R3DUyl1VoZGccRVxnXhJGzE8Gi89gZlvwScArdIxaeA

    Se debe ingresar a la cuenta de Gmail >> Administrar cuenta de Google >>Seguridad>>
    1.	Activar la verificación en 2 pasos (en caso de no tenerla activada), siguiendo las pasos indicados por el asistente. 
    2.	Debajo de Verificación en dos pasos, se encuentra “Contraseña de aplicaciones”. Hay que ingresar en la opción para activar el acceso. Pide ingresar       nuevamente la contraseña actual de nuestra cuenta.
    3.	Ya en el menú CONTRASEÑAS DE APLICACIONES, en “Seleccionar aplicación” se debe elegir “Otra”. 
    4.	Al presionar “Generar”, se nos proveerá de una contraseña de 16 caracteres. La misma debe ser guardada, ya que mediante ella haremos el transporte        del mensaje. 


  Al finalizar, reemplazar en la clase Main las variables:
  
    String remitente = "correodegmail@gmail.com"; 
    String contraseñaRemitente = "contraseña de 16 caracteres";


**//Ejecución**

**Obtención de Session**: Primero se necesita obtener una instancia de  Session y para ello necesitamos previamente rellenar una variable Properties
Cuando se ejecute el programa, se recibirá en la propia casilla de correo (ingresada en la variable Remitente) una notificación que dirá “No se encontró la dirección de gigi@gmail.com”, por ejemplo. 


**COMENTARIOS SOBRE SOLUCIONES Y MEJORAS A IMPLEMENTAR**

Crear clases y relaciones: Clase Empleade, Clase Empleadore, Clase ExtraerPDF, Clase EnviarCorreo.

Implementar el uso de otros servidores.


