package lucrez.ceva.service;

import lucrez.ceva.config.EmailConfiguration;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class EmailService {
    private final EmailConfiguration configuration;

    public EmailService() {
        EmailConfiguration configuration;
        try {
            configuration = EmailConfiguration.getInstance();
        } catch (IOException e) {
            configuration = null;
        }
        this.configuration = configuration;
    }

    @SuppressWarnings("WeakerAccess")
    public void sendActivationMail(String to, Long id, String uuid) {
        createThreadToWait(() ->sendActivationMailRun(to,id,uuid));
    }

    private void sendActivationMailRun(String to, Long id, String uuid) {
        try {
            trySendActivationMail(to, id, uuid);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private void trySendActivationMail(String to, Long id, String uuid) throws MessagingException {
        Message message = new MimeMessage(createNewSession());
        message.setFrom(configuration.getEmailAuthenticator().getAddress());
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(configuration.getActivationEmailTemplate().getSubject());
        message.setContent(configuration.getActivationEmailTemplate().getContent(id, uuid), "text/html");
        Transport.send(message);
    }

    private Session createNewSession() {
        return Session.getInstance(configuration.getEmailProperties(), configuration.getEmailAuthenticator());
    }

    private void createThreadToWait(Runnable runnable) {
        new Thread(() -> {
            try {
                createThreadAndWait(runnable);
            } catch (Exception ignored) { }
        }).start();
    }

    private void createThreadAndWait(Runnable runnable) throws InterruptedException {
        Thread thread = new Thread(runnable);
        thread.start();
        thread.join(1000 * configuration.getEmailTimeout());
        if (thread.isAlive())
            //noinspection deprecation
            thread.stop();
    }

    @SuppressWarnings("WeakerAccess")
    public static boolean isEmailValid(String email) {
        try {
            new InternetAddress(email).validate();
        } catch (AddressException ex) {
            return false;
        }
        return true;
    }
}
