package top.zhaodongxx.service;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import top.zhaodongxx.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zhaodong zhaodongxx@outlook.com
 * @version v1.0
 * @since 2018/3/30 22:59
 */
@Service
public class ShiroService {

    public static List<User> userList = new ArrayList<User>();

    @Deprecated
    public String getPasswordByUsername(String username) {
        switch (username) {
            case "liming":
                return "123";
            case "hanli":
                return "456";
            default:
                return null;
        }
    }

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    public User getUserByUsername(String username) {
        System.out.println("登陆的用户 " + username);
        for (User user : userList) {
            if (username.equals(user.getUsername())) {
                return user;
            }
        }
        return null;
    }

    /**
     * 添加用户
     *
     * @param username
     * @param password
     * @return
     */
    public User addUser(String username, String password) {
        String newpwd = encodeByBCrypt(password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(newpwd);
        userList.add(user);

        System.out.println(user);
        return user;
    }

    public String encodeByMD5(String password) {
        return new SimpleHash("MD5", password).toHex();
    }

    public String encodeByMD5AndSalt(String password, String salt) {
        return new SimpleHash("MD5", password, ByteSource.Util.bytes(salt)).toHex();
    }

    public String encodeByBCrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
