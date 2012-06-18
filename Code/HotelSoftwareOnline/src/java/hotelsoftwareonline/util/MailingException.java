/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.util;

import javax.mail.MessagingException;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class MailingException extends Exception
{

    public MailingException(MessagingException e)
    {
    }
    
}
