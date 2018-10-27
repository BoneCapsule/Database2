package dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TableDao {

    void createUser();

    void createPlan();

    void createOrder();

    void createUserPlan();

    void createCancel();

    void createBasicCharge();

    void createCallRecord();

    void createSMSRecord();

    void createDataRecord();

    void dropTables();
}
