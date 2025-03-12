package it.aesse.AESSE;

import it.aesse.AESSE.service.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    @Test
    public void testSendEmail() {
        // Arrange
        String to = "test@example.com";
        String subject = "Test Subject";
        String text = "Test Message";

        // Act
        emailService.sendEmail(to, subject, text);

        // Assert
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    public void testSendEmailCreazionePolizza() {
        // Arrange
        String destinatario = "cliente@example.com";

        // Act
        emailService.sendEmailCreazionePolizza(destinatario);

        // Assert
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}
