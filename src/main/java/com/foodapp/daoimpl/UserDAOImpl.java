package com.foodapp.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.UserDAO;
import com.foodapp.model.User;
import com.foodapp.util.DBConnection;

public class UserDAOImpl implements UserDAO {

    @Override
    public int addUser(User user) {

        String sql = """
                INSERT INTO users
                (full_name,email,password,phone,address,role)
                VALUES(?,?,?,?,?,?)
                """;

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getRole());

            return ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public User login(String email, String password) {

        String sql="SELECT * FROM users WHERE email=? AND password=?";

        try(Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){

            ps.setString(1,email);
            ps.setString(2,password);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){

                User user=new User();

                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getString("role"));

                return user;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User getUserById(int userId){

        String sql="SELECT * FROM users WHERE user_id=?";

        try(Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){

            ps.setInt(1,userId);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){

                User user=new User();

                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getString("role"));

                return user;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User getUserByEmail(String email){

        String sql="SELECT * FROM users WHERE email=?";

        try(Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){

            ps.setString(1,email);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){

                User user=new User();

                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));

                return user;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<User> getAllUsers(){

        List<User> list=new ArrayList<>();

        String sql="SELECT * FROM users";

        try(Connection con=DBConnection.getConnection();
            Statement st=con.createStatement()){

            ResultSet rs=st.executeQuery(sql);

            while(rs.next()){

                User user=new User();

                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getString("role"));

                list.add(user);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public int updateUser(User user){

        String sql="""
                UPDATE users
                SET full_name=?,
                    phone=?,
                    address=?
                WHERE user_id=?
                """;

        try(Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){

            ps.setString(1,user.getFullName());
            ps.setString(2,user.getPhone());
            ps.setString(3,user.getAddress());
            ps.setInt(4,user.getUserId());

            return ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int deleteUser(int userId){

        String sql="DELETE FROM users WHERE user_id=?";

        try(Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)){

            ps.setInt(1,userId);

            return ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }

        return 0;
    }

}