package main;

import dao.RecordDao;
import data.InitData;
import model.DataEnum.DataType;
import model.DataEnum.EffectType;
import model.HistoryPlan;
import model.UserPlan;
import model.bill.Bill;
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
        main.getBill(1);
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
        computingTime(()-> {
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
        computingTime(()->{
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
        computingTime(()->{
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
        computingTime(()->{
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
     */
    public double CallCharge(int id) {
        System.out.println("用户3从2018-10-28 22:00:00开始打电话");
        System.out.println("在2018-10-28 22:08:33时结束电话");
        LocalDateTime startTime = LocalDateTime.of(2018, 10, 28, 22, 00, 00);
        LocalDateTime endTime = LocalDateTime.of(2018, 10, 28, 22, 8, 33);
        Duration duration = Duration.between(startTime, endTime);
        int minutes = (int) duration.toMinutes();
        if (duration.toMinutes() > minutes) {
            minutes += 1;
        }

        // 计算运行时间
        final double[] charge = {0};
        int finalMinutes = minutes;
        computingTime(()-> {
            charge[0] = operation.CallRecord(id, startTime, endTime);
            System.out.println("用户3这次电话打了" + finalMinutes + "分钟！");
            System.out.println("电话资费：" + "0.5元/分钟");
            System.out.println("电话资费：" + charge[0] + "元");
        });

        return charge[0];
    }

    /**
     * 用户在使用流量情况下的资费生成
     * 这里假设用户3(没有订购任何套餐)从2018-10-29 08:00:00开始打使用国内流量
     * 在2018-10-29 10:22:35时结束使用
     * 假设流量使用量为300M
     * 计算这次流量使用的资费
     * @param id
     * @param number
     * @return
     */
    public double DataCharge(int id, double number) {
        System.out.println("用户3从2018-10-29 08:00:00开始打使用国内流量");
        System.out.println("在2018-10-29 10:22:35时结束使用国内流量");
        LocalDateTime startTime = LocalDateTime.of(2018, 10, 29, 8, 00, 00);
        LocalDateTime endTime = LocalDateTime.of(2018, 10, 29, 10, 22, 35);

        // 计算运行时间
        final double[] charge = {0};

        computingTime(()-> {
            charge[0] = operation.DataRecord(id, startTime, endTime, number, DataType.GLOBAL);
            System.out.println("用户3这次国内流量使用了" + number + "M！");
            System.out.println("国内流量资费：" + "5.0元/M");
            System.out.println("流量资费：" + charge[0] + "元");
            System.out.println();
        });

        return charge[0];
    }

    /**
     * 获取用户当前的账单，选择用户1
     * @param id
     * @return 账单
     */
    public Bill getBill(int id) {
        // 这里先插入多条使用记录
        try {
            RecordDao recordDao = MapperFactory.getSession().getMapper(RecordDao.class);
            // 四个流量使用记录
            recordDao.insertDataRecord(1, LocalDateTime.of(2018, 10, 25, 22, 30, 00),
                    LocalDateTime.of(2018, 10, 25, 23, 44, 55), 22.1,
                    DataType.GLOBAL.value);
            recordDao.insertDataRecord(1, LocalDateTime.of(2018, 10, 26, 8, 15, 00),
                    LocalDateTime.of(2018, 10, 26, 10, 35, 55), 15.0,
                    DataType.LOCAL.value);
            recordDao.insertDataRecord(1, LocalDateTime.of(2018, 10, 26, 12, 8, 20),
                    LocalDateTime.of(2018, 10, 26, 13, 47, 5), 35.8,
                    DataType.GLOBAL.value);
            recordDao.insertDataRecord(1, LocalDateTime.of(2018, 10, 28, 14, 26, 6),
                    LocalDateTime.of(2018, 10, 28, 15, 27, 35), 26.8,
                    DataType.LOCAL.value);

            // 三个短信记录
            recordDao.insertSMSRecord(1, 2,
                    LocalDateTime.of(2018, 10, 28, 13, 43, 18));
            recordDao.insertSMSRecord(1, 1,
                    LocalDateTime.of(2018, 10, 28, 16, 17, 29));
            recordDao.insertSMSRecord(1, 1,
                    LocalDateTime.of(2018, 10, 29, 8, 4, 14));

            // 三个电话记录
            recordDao.insertCallRecord(1, 7,
                    LocalDateTime.of(2018, 10, 24, 6, 16, 41),
                    LocalDateTime.of(2018, 10, 24, 6, 23, 26));
            recordDao.insertCallRecord(1, 4,
                    LocalDateTime.of(2018, 10, 26, 17, 23, 50),
                    LocalDateTime.of(2018, 10, 26, 17, 27, 11));
            recordDao.insertCallRecord(1, 9,
                    LocalDateTime.of(2018, 10, 28, 13, 49, 13),
                    LocalDateTime.of(2018, 10, 28, 13, 58, 00));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 订购和退订套餐
        operation.orderPlan(1, 3, LocalDateTime.of(2018, 10, 25, 18, 44, 16));
        operation.cancelPlan(1, 5, LocalDateTime.of(2018, 10, 27, 20, 45, 52),
                EffectType.IMMEDIATE);

        final Bill[] bill = {null};
        computingTime(()->{
            bill[0] = operation.getBill(1);
            System.out.println(bill[0]);
            System.out.println();
        });

        return bill[0];
    }

    /**
     * 计算运行时间
     * @param action
     */
    private void computingTime(Runnable action) {
        LocalDateTime startTime = LocalDateTime.now();
        action.run();
        LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(startTime, endTime);

        System.out.println("开始时间：" + startTime);
        System.out.println("运行时间：" + duration.toMillis() + " ms");
        System.out.println("结束时间：" + endTime);
    }

}
