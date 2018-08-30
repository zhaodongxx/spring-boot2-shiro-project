package top.zhaodongxx.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;
import top.zhaodongxx.domain.User;
import top.zhaodongxx.service.ShiroService;

import javax.annotation.Resource;

/**
 * <P></P>
 *
 * @author zhaodong zhaodongxx@outlook.com
 * @version v1.0
 * @since 2018/3/30 22:55
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private ShiroService shiroService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return new SimpleAuthorizationInfo();
    }

    /**
     * 登录认证
     * <p>
     * 四个参数
     * username：认证的实体信息。object,一般存放用户信息对象。可以通过SecurityUtils.getSubject().getPrincipal()获取。
     * password：数据库中保存的密码
     * realmName：当前realm对象的name，调用父类的getName()方法即可
     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        //获取用户账号
//        String username = token.getPrincipal().toString();
//
//        String password = shiroService.getPasswordByUsername(username);
//        if (password != null) {
//
//            String realmName = getName();
//            return new SimpleAuthenticationInfo(username, password, realmName);
//        }
//        return null;
//    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户账号
        String username = token.getPrincipal().toString();

        User user = shiroService.getUserByUsername(username);
        if (!ObjectUtils.isEmpty(user)) {

            /**
             * 四个参数
             * principal：认证的实体信息，可以是username，也可以是数据库表对应的用户的实体对象
             * credentials：数据库中的密码（经过加密的密码）
             * credentialsSalt：盐值（使用用户名）
             * realmName：当前realm对象的name，调用父类的getName()方法即可
             */

            String realmName = getName();
            String credentials = user.getPassword();
            ByteSource credentialsSalt = ByteSource.Util.bytes(username);

            return new SimpleAuthenticationInfo(username, credentials, credentialsSalt, realmName);
        }
        return null;
    }
}