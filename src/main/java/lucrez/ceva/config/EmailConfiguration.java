package lucrez.ceva.config;

import lombok.Getter;
import lucrez.ceva.model.EmailTemplate;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

@Getter
public class EmailConfiguration {
    private static final String PROPERTIES_FILE = "/email.properties";
    private static final String SMTP_PORT = "mail.smtp.port";
    private static final String SMTP_HOST = "mail.smtp.host";
    private static final String SMTP_AUTH = "mail.smtp.auth";
    private static final String SMTP_STARTLE = "mail.smtp.starttls.enable";
    private static final String SSL_SOCKET_FACTORY_CLASS = "mail.smtp.socketFactory.class";
    private static final String MAIL_SUBJECT = "activation.mail.subject";
    private static final String MAIL_CONTENT_FORMAT = "activation.mail";
    private static final String MAIL_TIMEOUT = "mail.timeout";
    private static final String HOST = "activation.mail.host";

    private static EmailConfiguration instance;

    private final EmailAuthenticator emailAuthenticator;
    private final Properties emailProperties;
    private final Integer emailTimeout;
    private final EmailTemplate activationEmailTemplate;

    public static EmailConfiguration getInstance() throws IOException{
        if (instance == null){
            instance = new EmailConfiguration();
        }
        return instance;
    }

    private EmailConfiguration() throws IOException{
        Properties properties = new Properties();
        properties.load(getClass().getResourceAsStream(PROPERTIES_FILE));
        emailAuthenticator = new EmailAuthenticator(properties);
        emailProperties = getEmailProperties(properties);
        emailTimeout = Integer.valueOf(properties.getProperty(MAIL_TIMEOUT));
        activationEmailTemplate = getActivationEmailTemplate(properties);
    }

    private static Properties getEmailProperties(Properties properties) {
        Properties props = new Properties();
        props.put(SMTP_AUTH, properties.getProperty(SMTP_AUTH));
        props.put(SMTP_STARTLE, properties.getProperty(SMTP_STARTLE));
        props.put(SMTP_HOST, properties.getProperty(SMTP_HOST));
        props.put(SMTP_PORT, properties.getProperty(SMTP_PORT));
        props.put(SSL_SOCKET_FACTORY_CLASS, properties.getProperty(SSL_SOCKET_FACTORY_CLASS));
        return props;
    }

    private static EmailTemplate getActivationEmailTemplate(Properties properties) {
        return new EmailTemplate(Collections.singletonList(properties.getProperty(HOST)),
                new ArrayList<>(),
                properties.getProperty(MAIL_CONTENT_FORMAT),
                properties.getProperty(MAIL_SUBJECT));
    }

    public static class EmailAuthenticator extends Authenticator {
        private static final String MAIL_SENDER = "activation.mail.sender";
        private static final String MAIL_PASS = "activation.mail.pass";
        private final String username;
        private final String password;

        private EmailAuthenticator(Properties properties) {
            username = properties.getProperty(MAIL_SENDER);
            password = properties.getProperty(MAIL_PASS);
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }

        public InternetAddress getAddress() {
            try {
                return new InternetAddress(username);
            } catch (AddressException e) {
                return null;
            }
        }
    }
}
