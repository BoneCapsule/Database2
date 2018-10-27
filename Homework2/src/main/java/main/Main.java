package main;

import config.MapperFactory;
import dao.PlanDao;
import dao.TableDao;
import dao.UserDao;
import data.PlanData;
import data.UserData;
import model.Plan;
import model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

@Mapper
public class Main {

    private static TableDao tableDao;
    private static UserDao userDao;
    private static PlanDao planDao;

    public static void main(String[] args) {

//        init();
//
//        User user = userDao.findUserById("18851826157");
//        Plan plan = planDao.findPlan("特惠套餐5");
//
//        System.out.println(user.getName() + " " + user.getId());
//        System.out.println(plan.getId() + " " + plan.getName() + " " + plan.getCost() + " " + plan.getLocalData());

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

        System.out.println(date.toString());

    }

    public static void init() {
        try {
            tableDao = MapperFactory.getSession().getMapper(TableDao.class);
            userDao = MapperFactory.getSession().getMapper(UserDao.class);
            planDao = MapperFactory.getSession().getMapper(PlanDao.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tableDao.dropTables();
        tableDao.createUser();
        tableDao.createPlan();

        UserData userData = new UserData();
        userData.InsertUser();
        PlanData planData = new PlanData();
        planData.InsertPlan();


    }

}
