package exception;

/**
 * @ClassName AccountEmailException
 * @Description TODO
 * @Author admin
 * @Date 2019-08-12 17:07
 * @Version 1.0
 **/
public class AccountEmailException extends Exception {
    public AccountEmailException(){

    }

   public  AccountEmailException(String message){
        super(message);
    }

   public  AccountEmailException(String message, Exception e) {
        super(message,e);
    }

}
