package com.ldd.multipledatademo.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>TODO</p>
 *
 * @author lidada
 * @date 2022/11/21
 */

@Data
public class OrderPageReqVO implements Serializable {


    @ApiModelProperty("订单号")
    private String orderNo;


}
