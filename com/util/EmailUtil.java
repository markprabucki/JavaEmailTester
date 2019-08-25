package com.util;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil extends Object
{
	public static void main(String[] args){

		boolean result = SendMessage("YourEmail@domain.com"
				                    ,"YourEmail@domain.com"
				                    ,"test"
				                    ,"test"
				                    ,"test");
		System.out.println("Email send result:" + result);
    }

    public static boolean SendMessage(String toAddress, String fromAddress,  String subject, String messageText, String errMsg)
    {
        Properties properties = System.getProperties();
        Session session = Session.getInstance(properties, null);
        boolean success = true;

        try
        {
            errMsg = null;
            properties.put("mail.smtp.host", "Your dns or IP Address");
            MimeMessage message = new MimeMessage(session);
            message.setSubject(subject);
            message.setText(messageText);
            message.setFrom(new InternetAddress(fromAddress));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            Transport.send(message);
        }
        catch (NoSuchProviderException npe)
        {
        	npe.printStackTrace();
            success = false;
        }
        catch (AddressException ae)
        {
        	ae.printStackTrace();
            success = false;
        }
        catch (MessagingException me)
        {
        	me.printStackTrace();
            success = false;
        }
        catch (Exception e)
        {
        	e.printStackTrace();
            success = false;
        }

        return success;
    }
}
