package com.kill.malldemo.Service.Impl;

import com.alibaba.druid.sql.dialect.oracle.ast.OracleDataTypeIntervalYear;
import com.kill.malldemo.Dao.CollectDao;
import com.kill.malldemo.Exception.BusinessException;
import com.kill.malldemo.Exception.ErrorCode;
import com.kill.malldemo.Pojo.Collect;
import com.kill.malldemo.Pojo.Product;
import com.kill.malldemo.Service.CollectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * @Description TODO
 * @Author lishen
 * @Date 3/8/21 8:41 pm
 **/
@Service("collectService")
public class CollectServiceImpl implements CollectService {

    @Resource(name = "collectDao")
    private CollectDao collectDao;

    @Override
    public void addCollect(String userId, String productId) {
        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(userId));
        collect.setProductId(Integer.parseInt(productId));

        //查看是否已经收藏过
        Collect c = collectDao.queryCollectByUserIdAndProductId(userId, productId);
        if (c != null) {
            throw new BusinessException(ErrorCode.SAVE_COLLECT_REUSE);
        }

        collect.setCollectTime(new Date().getTime());
        try {
            collectDao.addCollect(collect);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.SAVE_COLLECT_ERROR);
        }
    }

    @Override
    public void delCollect(String userId, String productId) {
        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(userId));
        collect.setProductId(Integer.parseInt(productId));
        try {
            collectDao.delCollect(collect);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.DELETE_COLLECT_ERROR);
        }
    }

}
