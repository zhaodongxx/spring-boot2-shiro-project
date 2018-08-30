package top.zhaodongxx.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.zhaodongxx.shiro.MyShiroRealm;

/**
 * <P></P>
 *
 * @author zhaodong
 * @version v1.0
 * @email zhaodongxx@outlook.com
 * @since 2018/3/30 22:41
 */
@Configuration
public class ShiroConfig {

    /**
     * 凭证匹配器
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        /**
         * hash算法:这里使用MD5算法;
         */
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        /**
         * 散列的次数，比如散列两次，相当于 md5(md5(""));
         */
        hashedCredentialsMatcher.setHashIterations(1);

        return hashedCredentialsMatcher;
    }

    /**
     * 自定义的Realm
     */
    @Bean(name = "myShiroRealm")
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();

        //使用单项hash
        //myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());

        //配置 BCrypt
        myShiroRealm.setCredentialsMatcher(new CredentialsMatcher() {
            @Override
            public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
                UsernamePasswordToken userToken = (UsernamePasswordToken) token;
                //要验证的明文密码
                String plaintext = new String(userToken.getPassword());
                //数据库中的加密后的密文
                String hashed = info.getCredentials().toString();

                return BCrypt.checkpw(plaintext, hashed);
            }
        });
        return myShiroRealm;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }
}
