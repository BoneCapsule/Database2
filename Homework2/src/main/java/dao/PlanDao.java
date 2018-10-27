package dao;

import model.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PlanDao {

    void insertPlan(Plan plan);

    Plan findPlan(@Param("name") String name);
}
