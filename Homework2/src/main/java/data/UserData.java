package data;

import config.MapperFactory;
import dao.UserDao;
import model.User;

import java.io.IOException;

public class UserData {

    private UserDao userDao;

    public UserData() {
        try {
            userDao = MapperFactory.getSession().getMapper(UserDao.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将生成的电话号码、姓名、本月话费和余额随机组合
     * 插入10000条数据进User表中
     */
    public void InsertUser() {
        for (int i = 0; i < 10000; i++) {

            // 顺序生成前缀固定 "1885182" 的电话号码
            long prefix = 1885182;
            long number = (long) (prefix * 10000) + i;



            String phoneNumber = String.valueOf(number);
            String randomName = getRandomName();
            int randCharge = (int)(Math.random() * 100);
            int randBalance = (int)(Math.random() * 100);
            User user = new User(phoneNumber, randomName, randCharge, randBalance);
            userDao.insertUser(user);
        }
    }

    /**
     * 从百家姓的前72姓随机取三个组成姓名
     * @return
     */
    private String getRandomName() {
        String[] FamilyNames = {"赵", "钱", "孙", "李", "周", "吴",
                                "郑", "王", "冯", "陈", "褚", "卫",
                                "蒋", "沈", "韩", "杨", "朱", "秦",
                                "尤", "许", "何", "吕", "施", "张",
                                "孔", "曹", "严", "华", "金", "魏",
                                "陶", "姜", "戚", "谢", "邹", "喻",
                                "柏", "水", "窦", "章", "云", "苏",
                                "潘", "葛", "奚", "范", "彭", "郎",
                                "鲁", "韦", "昌", "马", "苗", "凤",
                                "花", "方", "俞", "任", "袁", "柳",
                                "酆", "鲍", "史", "唐", "费", "廉",
                                "岑", "薛", "雷", "贺", "倪", "汤"
                                };
        StringBuilder RandomName = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int rand = (int) (Math.random() * 72);
            RandomName.append(FamilyNames[rand]);
        }
        return RandomName.toString();
    }
}
