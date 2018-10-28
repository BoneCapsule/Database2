package dao;

import model.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlanDao {

    void insertPlan(Plan plan);

    Plan findPlan(@Param("name") String name);

    Plan findPlanById(@Param("id") int id);

    List<Plan> findAllPlans();
}
