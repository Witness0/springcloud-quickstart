package com.witness.business.service;

import com.witness.business.feign.StorageFeignClient;
import org.springframework.beans.factory.annotation.Autowired;

public interface BusinessService {


    /**
     * 采购
     *
     * @param userId        用户id
     * @param commodityCode 商品编号
     * @param orderCount    购买数量
     */
    void purchase(String userId, String commodityCode, int orderCount);
}
