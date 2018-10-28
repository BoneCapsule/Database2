package operate;

import config.MapperFactory;
import dao.*;
import model.*;
import model.DataEnum.DataType;
import model.DataEnum.EffectType;
import model.DataEnum.RecordType;
import model.bill.Bill;
import model.bill.RecordBill;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class Operation {

    /**
     * 获取当前所有可用套餐
     * @return
     */
    public List<Plan> getPlans() {
        try {
            PlanDao planDao = MapperFactory.getSession().getMapper(PlanDao.class);
            return planDao.findAllPlans();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取用户使用中的套餐
     * @param id (用户ID)
     * @return
     */
    public List<UserPlan> getUserPlan(int id) {
        try {
            UserPlanDao userPlanDao = MapperFactory.getSession().getMapper(UserPlanDao.class);
            return userPlanDao.getUserPlan(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取用户订购过的套餐
     * @param id
     * @param nowTime
     * @return
     */
    public List<HistoryPlan> getHistoryPlan(int id, LocalDateTime nowTime) {
        try {
            UserPlanDao userPlanDao = MapperFactory.getSession().getMapper(UserPlanDao.class);
            return userPlanDao.getHistoryPlan(id, nowTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用户立即订购套餐
     * @param id
     * @param planId
     */
    public void orderPlan(int id, int planId, LocalDateTime localDateTime) {
        try {
            OrderDao orderDao = MapperFactory.getSession().getMapper(OrderDao.class);
            orderDao.insertOrder(id, planId, localDateTime);
            UserPlanDao userPlanDao = MapperFactory.getSession().getMapper(UserPlanDao.class);
            userPlanDao.insertUserPlan(id, planId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户取消套餐
     * @param id
     * @param planId
     * @param cancelTime
     * @param effectType
     */
    public void cancelPlan(int id, int planId, LocalDateTime cancelTime, EffectType effectType) {
        try {
            CancelDao cancelDao = MapperFactory.getSession().getMapper(CancelDao.class);
            cancelDao.CancelPlan(id, planId, cancelTime, effectType.value);
            UserPlanDao userPlanDao = MapperFactory.getSession().getMapper(UserPlanDao.class);
            userPlanDao.deleteUserPlan(id, planId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取使用情况
     * @param id
     * @param userPlans
     * @param nowTime
     * @param recordType 记录类型
     * @return
     * @throws IOException
     */
    public RecordBill getRecordBill(int id, List<UserPlan> userPlans, LocalDateTime nowTime,
                                    RecordType recordType, BasicCharge basicCharge) throws IOException {
        RecordDao recordDao = MapperFactory.getSession().getMapper(RecordDao.class);
        PlanDao planDao = MapperFactory.getSession().getMapper(PlanDao.class);

        // 电话使用情况
        if (recordType.equals(RecordType.CALL)) {
            // 所有套餐电话时长总量
            int planTotal = 0;
            for (UserPlan up : userPlans) {
                Plan plan = planDao.findPlanById(up.getPid());
                planTotal += plan.getCall();
            }

            int totalUsed = 0;  // 总使用量
            List<CallRecord> callRecords = recordDao.getCallRecord(id, nowTime);
            for (CallRecord cr : callRecords) {
                int number = cr.getNumber();
                totalUsed += number;
            }

            int extraUsed = 0;      // 额外使用量
            int remian = 0;      // 套餐剩余量
            double CallCharge = 0;   // 费用
            if (planTotal < totalUsed) {
                extraUsed = totalUsed - planTotal;
                CallCharge = extraUsed * basicCharge.getCallCharge();   // 通话费用：0.5元/分钟（仅拨打收费，接听免费）
            }
            else {
                remian = planTotal - totalUsed;
            }

            return new RecordBill(totalUsed, CallCharge, planTotal, extraUsed, remian);
        }

        // 短信使用情况
        else if (recordType.equals(RecordType.SMS)) {
            // 所有套餐短信总量
            int planTotal = 0;
            for (UserPlan up : userPlans) {
                Plan plan = planDao.findPlanById(up.getPid());
                planTotal += plan.getMessages();
            }
            int totalUsed = 0;  // 总使用量
            List<SMSRecord> smsRecords = recordDao.getSMSRecord(id, nowTime);
            for (SMSRecord sms : smsRecords) {
                int number = sms.getNumber();
                totalUsed += number;
            }
            int extraUsed = 0;      // 额外使用量
            int remian = 0;      // 套餐剩余量
            double SMSCharge = 0;   // 费用
            if (planTotal < totalUsed) {
                extraUsed = totalUsed - planTotal;
                SMSCharge = extraUsed * basicCharge.getSMSCharge();   // 短信费用：0.1元/条
            }
            else {
                remian = planTotal - totalUsed;
            }

            return new RecordBill(totalUsed, SMSCharge, planTotal, extraUsed, remian);
        }

        else {
            // 所有套餐总量
            double planTotalLocal = 0;
            double planTotalGlobal = 0;
            for (UserPlan up : userPlans) {
                Plan plan = planDao.findPlanById(up.getPid());
                planTotalLocal += plan.getLocalData();
                planTotalGlobal += plan.getGlobalData();
            }
            double totalLocalUsed = 0;  // 本地流量总使用量
            double totalGlobalUsed = 0;  // 国内流量总使用量
            List<DataRecord> dataRecords = recordDao.getDataRecord(id, nowTime);
            for (DataRecord dr : dataRecords) {
                double number = dr.getNumber();
                if (dr.getDataType().equals(DataType.LOCAL)) {
                    totalLocalUsed += number;
                }
                if (dr.getDataType().equals(DataType.GLOBAL)) {
                    totalGlobalUsed += number;
                }
            }
            double extraLocalUsed = 0;      // 本地额外使用量
            double localRemian = 0;      // 本地剩余量
            double extraGlobalUsed = 0;      // 国内额外使用量
            double globalRemian = 0;      // 国内剩余量
            double localCharge = 0;   // 本地流量费用
            double globalCharge = 0;   // 国内流量费用

            if (planTotalLocal < totalLocalUsed) {
                extraLocalUsed = totalLocalUsed - planTotalLocal;
                localCharge = extraLocalUsed * basicCharge.getLocalDataCharge();   // 本地流量费用（仅考虑4G流量）：2元/M
            }
            else {
                localRemian = planTotalLocal - totalLocalUsed;
            }

            if (planTotalGlobal < totalGlobalUsed) {
                extraGlobalUsed = totalGlobalUsed - planTotalGlobal;
                globalCharge = extraGlobalUsed * basicCharge.getGlobalDataCharge();  // 国内流量费用（仅考虑4G流量）：5元/M
            }
            else {
                globalRemian = planTotalGlobal - totalGlobalUsed;
            }

            if (recordType.equals(RecordType.LOCALDATA)) {
                return new RecordBill(totalLocalUsed, localCharge, planTotalLocal, extraLocalUsed, localRemian);
            }
            else {
                return new RecordBill(totalGlobalUsed, globalCharge, planTotalGlobal, extraGlobalUsed, globalRemian);
            }
        }
    }

    /**
     * 获取本月份账单
     * @param id
     * @return
     */
    public Bill getBill(int id) throws IOException {
        UserDao userDao = MapperFactory.getSession().getMapper(UserDao.class);
        BasicChargeDao basicChargeDao = MapperFactory.getSession().getMapper(BasicChargeDao.class);
        UserPlanDao userPlanDao = MapperFactory.getSession().getMapper(UserPlanDao.class);
        PlanDao planDao = MapperFactory.getSession().getMapper(PlanDao.class);
        User user = userDao.findUserById(id);
        BasicCharge basicCharge = basicChargeDao.getBasicCharge(id);
        List<UserPlan> userPlans = userPlanDao.getUserPlan(id);

        double totolPlanCharges = 0;
        for (UserPlan up : userPlans) {
            Plan plan = planDao.findPlanById(up.getPid());
            totolPlanCharges += plan.getCost();
        }

        RecordBill callBill = getRecordBill(id, userPlans, LocalDateTime.now(), RecordType.CALL, basicCharge);
        RecordBill smsBill = getRecordBill(id, userPlans, LocalDateTime.now(), RecordType.SMS, basicCharge);
        RecordBill localBill = getRecordBill(id, userPlans, LocalDateTime.now(), RecordType.LOCALDATA, basicCharge);
        RecordBill globalBill = getRecordBill(id, userPlans, LocalDateTime.now(), RecordType.GLOBALDATA, basicCharge);



        LocalDate today = LocalDate.now();

        Bill bill = new Bill();
        bill.setId(id);
        bill.setName(user.getName());
        bill.setUserPlans(userPlans);
        bill.setStartDate(LocalDate.now());
        bill.setEndDate(today.with(TemporalAdjusters.firstDayOfMonth()));
        bill.setCallBill(callBill);
        bill.setSmsBill(smsBill);
        bill.setLocalDataBill(localBill);
        bill.setGlobalDataBill(globalBill);
        bill.setTotalCharge(totolPlanCharges + callBill.getCharge() + smsBill.getCharge()
                + localBill.getCharge() + globalBill.getCharge());
        return bill;
    }


    /**
     *用户的某次电话记录
     * @param id
     * @param startTime
     * @param endTime
     */
    public void CallRecord(int id, LocalDateTime startTime, LocalDateTime endTime) throws IOException {
        RecordDao recordDao = MapperFactory.getSession().getMapper(RecordDao.class);
        Duration duration = Duration.between(startTime, endTime);
        int minutes = (int) duration.toMinutes();
        if (duration.toMinutes() > minutes) {
            minutes += 1;
        }
        recordDao.insertCallRecord(id, minutes, startTime, endTime);
    }
}
