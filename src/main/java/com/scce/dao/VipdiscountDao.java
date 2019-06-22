package com.scce.dao;

import com.scce.pojo.Vipdiscount;
import org.apache.ibatis.annotations.Select;

/**
 * @program: IdeaProjects
 * @description:
 * @author: Lxy
 * @create: 2019-06-20 20:28
 **/
public interface VipdiscountDao {
    //根据级别查询
    @Select("select * from vipdiscount where lever=#{level}")
    public Vipdiscount getVipdiscountBylevel(int level);
}
