package com.cn.dao.impl;

import com.cn.dao.IProcedureDao;
import com.cn.dao.IVersionDao;
import com.cn.model.Version;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("procedureDao")
public class ProcedureDaoImpl extends BaseDaoImpl implements IProcedureDao {


    @Override
    public void runStatisticsMonth() {
        getSqlSession().selectOne("com.cn.dao.ProcedureMapper.produceStatisticsMonth");
    }


}
