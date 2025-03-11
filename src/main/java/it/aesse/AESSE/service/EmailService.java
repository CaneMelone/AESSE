package it.aesse.AESSE.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Classe di servizio per l'invio di email.
 */

@Service
@RequiredArgsConstructor
public class EmailService
{
    private final JavaMailSender mailSender;

    /**
     * Invia un'email con il destinatario, l'oggetto e il testo specificati.
     *
     * @param to      l'indirizzo email del destinatario
     * @param subject l'oggetto dell'email
     * @param text    il contenuto testuale dell'email
     */
    public void sendEmail(String to, String subject, String text)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    /**
     * Invia un'email notificando la creazione di una polizza al destinatario specificato.
     *
     * @param Destinatario l'indirizzo email del destinatario
     */
    public void sendEmailCreazionePolizza(String Destinatario)
    {
        sendEmail(Destinatario, "Creazione Polizza", "Congratulazioni, la sua polizza è stata creata con successo");
    }
}