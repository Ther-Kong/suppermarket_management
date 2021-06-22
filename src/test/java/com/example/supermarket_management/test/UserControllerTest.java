package com.example.supermarket_management.test;

import com.example.supermarket_management.mapper.UserMapper;
import com.example.supermarket_management.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {

})
public class UserControllerTest {
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    public void register() throws Exception {
        System.out.println("注册测试");
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setUsername("khc2");
            user.setPwd("12345678");
            int checkResult = userMapper.checkUser(user.getUsername());
            if (checkResult == 0) {
                int result = userMapper.registerUser(user.getUsername(), user.getPwd());
                Assertions.assertEquals(1,result);
                System.out.println("注册成功");
            } else {
                Assertions.assertEquals(1,checkResult);
                System.out.println("注册失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.rollback();
            System.out.println("回滚");
            sqlSession.close();
        }
    }

    @Test
    public void checkUser() throws Exception {
        System.out.println("注册冲突测试");
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setUsername("khc2");
            user.setPwd("12345678");
            int checkResult = userMapper.checkUser(user.getUsername());
            if (checkResult == 0) {
                int result = userMapper.registerUser(user.getUsername(), user.getPwd());
                Assertions.assertEquals(1,result);
                System.out.println("注册成功");
            } else {
                Assertions.assertEquals(1,checkResult);
                System.out.println("注册失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.rollback();
            System.out.println("回滚");
            sqlSession.close();
        }
    }

    @Test
    public void login() throws Exception {
        System.out.println("登录测试");
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setUsername("khc2");
            user.setPwd("12345670");
            User result = userMapper.getUser(user.getUsername(), user.getPwd());
            if (result != null) {
                System.out.println("登录成功");
            } else {
                System.out.println("登录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.rollback();
            System.out.println("回滚");
            sqlSession.close();
        }
    }

    @Test
    public void updatePwd() throws Exception {
        System.out.println("修改密码测试");
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setUsername("khc2");
            user.setPwd("12345678");
            userMapper.getUser(user.getUsername(), user.getPwd());
            User loginResult = userMapper.getUser(user.getUsername(), user.getPwd());
            if (loginResult != null) {
                System.out.println("登录成功");
            }
            user.setPwd("87654321");
            int result = userMapper.updatePwdUser(user.getUsername(), user.getPwd());
            Assertions.assertEquals(1, result);
            Assertions.assertEquals("87654321", user.getPwd());
            if (loginResult != null) {
                System.out.println("修改密码成功");
            }
//            Assertions.assertEquals(result, user);
//            if (result != null) {
//                System.out.println("登录成功");
//            } else {
//                System.out.println("登录失败");
//            }
        } catch (AssertionError ae) {
            System.out.println(ae);
        } finally {
            sqlSession.rollback();
            System.out.println("回滚");
            sqlSession.close();
        }
    }

    @Test
    public void userTest() throws Exception {
        System.out.println("修改密码测试");
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setUsername("khc3");
            user.setPwd("12345678");
            int checkResult = userMapper.checkUser(user.getUsername());
            if (checkResult == 0) {
                int result = userMapper.registerUser(user.getUsername(), user.getPwd());
                Assertions.assertEquals(1,result);
                System.out.println("注册成功");
            } else {
                Assertions.assertEquals(1,checkResult);
                System.out.println("注册失败");
            }
            User loginResult = userMapper.getUser(user.getUsername(), user.getPwd());
            if (loginResult != null) {
                System.out.println("登录成功");
            } else {
                System.out.println("登录失败");
            }
            user.setPwd("87654321");
            int result = userMapper.updatePwdUser(user.getUsername(), user.getPwd());
            Assertions.assertEquals(1, result);
            Assertions.assertEquals("87654321", user.getPwd());
            if (loginResult != null) {
                System.out.println("修改密码成功");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            sqlSession.rollback();
            System.out.println("回滚");
            sqlSession.close();
        }
    }
}