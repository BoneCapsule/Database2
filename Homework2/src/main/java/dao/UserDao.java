package dao;

import model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserDao {

    void insertUser(User user);

    User findUserById(@Param("id") String id);

    User findUserByName(@Param("name") String name);

}
