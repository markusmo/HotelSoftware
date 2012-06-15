/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class MailSender
{
    /**
     * sendmail schickt via ssl
     */
    public void sendmail(String receipients, String mailmessage)
    {
        final String username = "hotel_de_la_fleur@yahoo.com";
        final String password = "roomanizer";
        
        //Vielleicht neuer mailserver :-D
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.mail.yahoo.com");
        //props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.ssl","true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try
        {
            Message message = new MimeMessage(session);
            //Email von Hotel hier :-D
            message.setFrom(new InternetAddress("hotel_de_la_fleur@yahoo.com"));
            //Empfänger hier
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receipients));
            //Betreff hier
            message.setSubject("Reservierungsbestaetigung");
            /*
             * Nachricht hier
             */
            message.setText(mailmessage);

            Transport.send(message);
        }
        
        catch (MessagingException e)
        {
            throw new RuntimeException(e);
        }
    }
}
