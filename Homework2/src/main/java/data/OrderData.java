package data;

import config.MapperFactory;
import dao.OrderDao;
import model.Order;
import model.Plan;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderData {

    private OrderDao orderDao;

    public OrderData() throws IOException {
        orderDao = MapperFactory.getSession().getMapper(OrderDao.class);
    }

    public void InsertOrderData() {
        for (int i = 0; i < 10000; i++) {
            // 顺序生成前缀固定 "1885182" 的电话号码
            long prefix = 1885182;
            long number = (long) (prefix * 10000) + i;
            String phoneNumber = String.valueOf(number);

            int planNum = (int) (Math.random() * 4);  // 用户订购的套餐个数，可能订购0、1、2、3个套餐
            ArrayList<Integer> plans = new ArrayList<>();

            // 去除重复套餐
            for (int j = 0; j < planNum; j++) {
                int planId = (int) (Math.random() * 12) + 1;
                if (!plans.contains(planId)) plans.add(planId);
            }
            for (int j = 0; j < planNum - plans.size(); j++) {
                int planId = (int) (Math.random() * 12) + 1;
                if (!plans.contains(planId)) plans.add(planId);
            }

//            // 插入订购记录
//            for (int j = 0; j < plans.size(); j++) {
//                Order order = new Order(phoneNumber, plans.get(j), getRandomTime());
//            }

        }
    }

    private LocalDateTime getRandomTime() {

        // 随机日期
        String[] year = {"2015", "2016", "2017", "2018"};
        String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        int[] day = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int randYear = (int) (Math.random() * 4);
        int randMonth = (int) (Math.random() * 12);
        int randDay = (int) (Math.random() * day[randMonth]) + 1;
        StringBuilder date = new StringBuilder();
        date.append(year[randYear]);
        date.append("-");
        date.append(month[randMonth]);
        date.append("-");
        if (randDay < 10) date.append("0");
        date.append(randDay);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return null;
    }


}
