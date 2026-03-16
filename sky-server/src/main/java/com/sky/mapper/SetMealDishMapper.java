package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetMealDishMapper {

    /**
     * 根据菜品id查询包含该菜品的套餐id列表
     * @param dishIds
     * @return
     */
    List<Long> getSetMealIdsByDishIds(List<Long> dishIds);

    /**
     * 批量插入套餐和菜品的关联关系数据
     * @param setmealDishes
     */
    void insertBatch(List<SetmealDish> setmealDishes);
}
