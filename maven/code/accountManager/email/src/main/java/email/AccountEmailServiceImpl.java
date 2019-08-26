package email;

import exception.AccountEmailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @ClassName AccountEmailServiceImpl
 * @Description TODO
 * @Author admin
 * @Date 2019-08-12 17:13
 * @Version 1.0
 **/
public class AccountEmailServiceImpl implements AccountEmailService{
    private JavaMailSender javaMailSender;

    private  String systemEmail;

    public void sendMail(String to, String subject, String htmlText) throws AccountEmailException {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(msg);

            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText("htmlText",true);
            mimeMessageHelper.setFrom(systemEmail);

            javaMailSender.send(msg);
        } catch (MessagingException e) {
            throw new AccountEmailException("Fild to send mail",e);
        }
    }

    public JavaMailSender getJavaMailSender() {
        return javaMailSender;
    }

    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String getSystemEmail() {
        return systemEmail;
    }

    public void setSystemEmail(String systemEmail) {
        this.systemEmail = systemEmail;
    }
}
