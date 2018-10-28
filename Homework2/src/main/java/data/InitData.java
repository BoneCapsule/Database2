package data;

import config.MapperFactory;
import dao.*;
import model.BasicCharge;
import model.Plan;
import model.User;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InitData {

    private OrderDao orderDao;

    private PlanDao planDao;

    private UserDao userDao;

    private TableDao tableDao;

    private BasicChargeDao basicChargeDao;

    private UserPlanDao userPlanDao;

    public InitData() {
        try {
            orderDao = MapperFactory.getSession().getMapper(OrderDao.class);
            planDao = MapperFactory.getSession().getMapper(PlanDao.class);
            userDao = MapperFactory.getSession().getMapper(UserDao.class);
            tableDao = MapperFactory.getSession().getMapper(TableDao.class);
            basicChargeDao = MapperFactory.getSession().getMapper(BasicChargeDao.class);
            userPlanDao = MapperFactory.getSession().getMapper(UserPlanDao.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initTables() {
        tableDao.dropTables();
        tableDao.createPlan();
        tableDao.createUser();
        tableDao.createBasicCharge();
        tableDao.createCallRecord();
        tableDao.createSMSRecord();
        tableDao.createDataRecord();
        tableDao.createUserPlan();
        tableDao.createOrder();
        tableDao.createCancel();
    }

    // 插入5个套餐
    public void InsertPlan() {
        Plan plan1 = new Plan("话费套餐", 20.0, 0, 0, 100, 0);
        Plan plan2 = new Plan("短信套餐", 10.0, 0, 0, 0, 200);
        Plan plan3 = new Plan("本地流量套餐", 20.0, 2000, 0, 0, 0);
        Plan plan4 = new Plan("国内流量套餐", 30.0, 0, 2000, 0, 0);
        Plan plan5 = new Plan("叠加套餐", 66.6, 3000, 3000, 200, 300);

        try {
            planDao = MapperFactory.getSession().getMapper(PlanDao.class);
            planDao.insertPlan(plan1);
            planDao.insertPlan(plan2);
            planDao.insertPlan(plan3);
            planDao.insertPlan(plan4);
            planDao.insertPlan(plan5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void InsertUser() {
        User user1 = new User("用户1", 0, 50);
        User user2 = new User("用户2", 0, 50);
        User user3 = new User("用户3", 0, 50);
        userDao.insertUser(user1);
        userDao.insertUser(user2);
        userDao.insertUser(user3);
    }

    /**
     * 插入订购数据
     */
    public void OrderPlan() {
        LocalDateTime time1 = LocalDateTime.of(2018, 10, 25, 22, 00);
        LocalDateTime time2 = LocalDateTime.of(2018, 10, 25, 22, 00);
        LocalDateTime time3 = LocalDateTime.of(2018, 10, 20, 00, 00);
        orderDao.insertOrder(1, 1, time1);
        orderDao.insertOrder(1, 5, time3);
        orderDao.insertOrder(2, 5, time2);
        userPlanDao.insertUserPlan(1, 1);
        userPlanDao.insertUserPlan(1, 5);
        userPlanDao.insertUserPlan(2, 5);
    }

    public void initBasicCharge() {
        basicChargeDao.insertBasicCharge(0.5, 0.1, 2.0, 5.0);
    }
}
