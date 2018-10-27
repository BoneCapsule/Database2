package dao;

import model.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao {

    void insertOrder(Order order);
}
