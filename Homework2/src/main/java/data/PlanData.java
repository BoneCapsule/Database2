package data;

import config.MapperFactory;
import dao.PlanDao;
import model.Plan;

import java.io.IOException;

public class PlanData {

    private PlanDao planDao;

    // 插入12个套餐
    public void InsertPlan() {
        Plan plan1 = new Plan("特惠套餐1", 18.8, 600, 300, 50, 100);
        Plan plan2 = new Plan("特惠套餐2", 28.8, 1000, 500, 160, 200);
        Plan plan3 = new Plan("特惠套餐3", 38.8, 2000, 1000, 240, 300);
        Plan plan4 = new Plan("特惠套餐4", 48.8, 3000, 1500, 320, 400);
        Plan plan5 = new Plan("特惠套餐5", 58.8, 5000, 3000, 400, 500);
        Plan plan6 = new Plan("超值流量套餐1", 66.6, 8000, 5000, 0, 0);
        Plan plan7 = new Plan("超值流量套餐2", 88.8, 18800, 8000, 0, 00);
        Plan plan8 = new Plan("超值通讯套餐", 30, 0, 0, 300, 300);
        Plan plan9 = new Plan("话费套餐", 20, 0, 0, 100, 0);
        Plan plan10 = new Plan("短信套餐", 10, 0, 0, 0, 200);
        Plan plan11 = new Plan("本地流量套餐", 20, 2000, 0, 0, 0);
        Plan plan12 = new Plan("国内流量套餐", 30, 0, 2000, 0, 0);

        try {
            planDao = MapperFactory.getSession().getMapper(PlanDao.class);
            planDao.insertPlan(plan1);
            planDao.insertPlan(plan2);
            planDao.insertPlan(plan3);
            planDao.insertPlan(plan4);
            planDao.insertPlan(plan5);
            planDao.insertPlan(plan6);
            planDao.insertPlan(plan7);
            planDao.insertPlan(plan8);
            planDao.insertPlan(plan9);
            planDao.insertPlan(plan10);
            planDao.insertPlan(plan11);
            planDao.insertPlan(plan12);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
