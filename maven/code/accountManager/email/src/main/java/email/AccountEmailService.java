package email;

import exception.AccountEmailException;

/**
 * @ClassName AccountEmailService
 * @Description TODO
 * @Author admin
 * @Date 2019-08-12 17:04
 * @Version 1.0
 **/
public interface AccountEmailService {
    void sendMail(String to, String subject, String htmlText) throws AccountEmailException;
}
