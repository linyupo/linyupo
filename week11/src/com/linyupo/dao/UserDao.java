package com.linyupo.dao;

import com.linyupo.model.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UserDao implements IUserDao{
    @Override
    public boolean saveUser(Connection con, User user) throws SQLException {
        String sql="insert into usertable values ('"+user.getId()+"','"+user.getUsername()+"','"+user.getPassword()+"','"+user.getEmail()+"','"+user.getGender()+"','"+user.getBirthdate()+"')";
        PreparedStatement st=con.prepareStatement(sql);
        int  rs=st.executeUpdate();
        if(rs!=0)return true;
        else return false ;
    }

    @Override
    public int deleteUser(Connection con, User user) throws SQLException {
        String sql="delete usertable where id='"+user.getId()+"' ";
        PreparedStatement st=con.prepareStatement(sql);
        int rs=st.executeUpdate();
        return rs;
    }

    @Override
    public int updateUser(Connection con, User user) throws SQLException {
        DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
        String birth=df.format(user.getBirthdate());
        String sql="update usertable set username='"+user.getUsername()+"',password='"+user.getPassword()+"',email='"+user.getEmail()+"',gender='"+user.getGender()+"',birthdate='"+birth+"' where id ='"+user.getId()+"' ";
        PreparedStatement st=con.prepareStatement(sql);
        int rs=st.executeUpdate();
        return rs;
    }

    @Override
    public User findById(Connection con, Integer id) throws SQLException {
        User user=null;
        String sql="select *from usertable where id='"+id+"' ";
        PreparedStatement st=con.prepareStatement(sql)  ;
        ResultSet rs=st.executeQuery();
        if(rs.next()){
            user=new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("gende"),rs.getDate("birthdate"));

        }
        return user;
    }

    @Override
    public User findByUsernamePassword(Connection con, String username, String password) throws SQLException {
        String sql="select id,username,password,email,gender,birthdate from usertable where username ='"+username+"' and password='"+password+"' ";
        PreparedStatement st=con.prepareStatement(sql);
        ResultSet rs=st.executeQuery()  ;
        User user =null;
        if(rs.next()){
            user=new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthdate(rs.getDate("birthdate"));
        }
        return user;
    }

    @Override
    public List<User> findByUsername(Connection con, String username) throws SQLException {
        List<User>list=new ArrayList<>();
        String sql="select *from usertable where username='"+username+"' ";
        PreparedStatement st=con.prepareStatement(sql)  ;
        ResultSet rs=st.executeQuery();
        while(rs.next()){
            User user=new User();
            user=new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("gende"),rs.getDate("birthdate"));
            list.add(user);
        }
        return list;
    }

    @Override
    public List<User> findByPassword(Connection con, String password) throws SQLException {
        List<User>list=new ArrayList<>();
        String sql="select *from usertable where password='"+password+"' ";
        PreparedStatement st=con.prepareStatement(sql)  ;
        ResultSet rs=st.executeQuery();
        while(rs.next()){
            User user=new User();
            user=new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("gende"),rs.getDate("birthdate"));
            list.add(user);
        }
        return list;
    }

    @Override
    public List<User> findByEmail(Connection con, String email) throws SQLException {
        List<User>list=new ArrayList<>();
        String sql="select *from usertable where email='"+email+"' ";
        PreparedStatement st=con.prepareStatement(sql)  ;
        ResultSet rs=st.executeQuery();
        while(rs.next()){
            User user=new User();
            user=new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("gende"),rs.getDate("birthdate"));
            list.add(user);
        }
        return list;
    }

    @Override
    public List<User> findByGender(Connection con, String gender) throws SQLException {
        List<User>list=new ArrayList<>();
        String sql="select *from usertable where gender='"+gender+"' ";
        PreparedStatement st=con.prepareStatement(sql)  ;
        ResultSet rs=st.executeQuery();
        while(rs.next()){
            User user=new User();
            user=new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("gende"),rs.getDate("birthdate"));
            list.add(user);
        }
        return list;
    }

    @Override
    public List<User> findByBirthdate(Connection con, Date birthDate) throws SQLException {
        List<User>list=new ArrayList<>();
        String sql="select *from usertable where birthdate='"+birthDate+"' ";
        PreparedStatement st=con.prepareStatement(sql)  ;
        ResultSet rs=st.executeQuery();
        while(rs.next()){
            User user=new User();
            user=new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("gende"),rs.getDate("birthdate"));
            list.add(user);
        }
        return list;
    }

    @Override
    public List<User> findAllUser(Connection con) throws SQLException {
        List<User>list=new ArrayList<>();
        String sql="select *from usertable  ";
        PreparedStatement st=con.prepareStatement(sql)  ;
        ResultSet rs=st.executeQuery();
        while(rs.next()){
            User user=new User();
            user=new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("email"),rs.getString("gende"),rs.getDate("birthdate"));
            list.add(user);
        }
        return list;
    }
}
