package main;

import config.MapperFactory;
import dao.*;
import data.InitData;
import model.DataEnum.DataType;
import model.DataEnum.EffectType;
import model.HistoryPlan;
import model.Plan;
import model.UserPlan;
import operate.Operation;
import org.apache.ibatis.annotations.Mapper;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public class Main {

    private Operation operation = new Operation();

    public static void main(String[] args) {
        init();
        Main main = new Main();
        main.operation.orderPlan(1, 3, LocalDateTime.now());
        main.CancelPlan(1, 5);
    }

    // 初始化数据
    public static void init() {
        InitData initData = new InitData();
        initData.initTables();
        initData.initBasicCharge();
        initData.InsertPlan();
        initData.InsertUser();
        initData.OrderPlan();
    }

    /**
     * 获取用户历史账单
     * @param id
     */
    public void getHistoryPlan(int id) {
        System.out.println("用户" + id + "的所有历史套餐：" );
        countTime(()-> {
            List<HistoryPlan> historyPlans = operation.getHistoryPlan(id, LocalDateTime.now());
            for (HistoryPlan hp : historyPlans) {
                System.out.println(hp.toString());
            }
        });
    }

    /**
     * 获取用户正在使用的套餐
     * @param id
     */
    public void getUserPlan(int id) {
        System.out.println("用户" + id + "的正在使用的套餐：" );
        countTime(()->{
            List<UserPlan> userPlans = operation.getUserPlan(id);
            for (UserPlan up : userPlans) {
                System.out.println(up.toString());
            }
        });

    }

    /**
     * 用户订购套餐, 立即生效
     * @param id
     * @param pid
     */
    public void OrderPlan(int id, int pid) {
        System.out.println("用户" + id + "开始订购套餐" + pid + ":");
        countTime(()->{
            operation.orderPlan(id, pid, LocalDateTime.now());
        });
        System.out.println("订购成功！");
        System.out.println("下面查看用户所有套餐:");
        List<HistoryPlan> historyPlans = operation.getHistoryPlan(id, LocalDateTime.now());
        for (HistoryPlan hp : historyPlans) {
            System.out.println(hp.toString());
        }
        System.out.println("我们发现，套餐" + pid + "已经被用户" + id + "订购！" );
    }

    /**
     * 用户此刻取消套餐, 立即取消
     * @param id
     * @param pid
     */
    public void CancelPlan(int id, int pid) {
        System.out.println("用户" + id + "开始退订套餐" + pid + ":");
        countTime(()->{
            operation.cancelPlan(id, pid, LocalDateTime.now(), EffectType.IMMEDIATE);
        });
        System.out.println("退订成功！");
        System.out.println("下面查看用户所有套餐:");
        List<UserPlan> userPlans = operation.getUserPlan(id);
        for (UserPlan up : userPlans) {
            System.out.println(up.toString());
        }
        System.out.println("我们发现，套餐" + pid + "已经被用户" + id + "退订成功！" );
    }

    /**
     * 用户在通话情况下的资费生成
     * 这里假设用户3(没有订购任何套餐)从2018-10-28 22:00:00开始打电话
     * 在2018-10-28 22:08:33时结束电话
     * 计算这次电话的资费
     * @param id
     * @param startTime
     * @param endTime
     */
    public void CallCharge(int id, LocalDateTime startTime, LocalDateTime endTime) {
        System.out.println("用户3从2018-10-28 22:00:00开始打电话");
        System.out.println("在2018-10-28 22:08:33时结束电话");
        startTime = LocalDateTime.of(2018, 10, 28, 22, 00, 00);
        endTime = LocalDateTime.of(2018, 10, 28, 22, 8, 33);

    }

    /**
     * 计算运行时间
     * @param action
     */
    private void countTime(Runnable action) {
        LocalDateTime startTime = LocalDateTime.now();
        action.run();
        LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(startTime, endTime);

        System.out.println("开始时间：" + startTime);
        System.out.println("运行时间：" + duration.toMillis() + " ms");
        System.out.println("结束时间：" + endTime);
    }

}
