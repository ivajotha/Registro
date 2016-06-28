package mx.ivajotha.desarrollo.model;

/**
 * Created by jonathan on 25/06/16.
 */
public class ModelUser {
    public Integer userId;
    public String userName;
    public String password;
    public String lastLogin;

    public ModelUser(String userName, String password, String llogin, Integer id) {
        this.userName = userName;
        this.password = password;
        this.lastLogin = llogin;
        this.userId = id;
    }
}
