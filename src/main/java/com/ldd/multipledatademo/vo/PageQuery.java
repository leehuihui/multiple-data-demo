package com.ldd.multipledatademo.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * <p>PageQuery</p>
 *
 * @author lidada
 * @date 2022/11/17
 */

@Data
public class PageQuery<T> implements Serializable {

    private static final long serialVersionUID = -7069194135830031070L;
    @Range(min = 0, max = Integer.MAX_VALUE, message = "超出页数大小")
    private int pageNum;
    @Range(min = 0, max = 1000, message = "超出页记录数大小")
    private int pageSize;
    @Valid
    private T condition;
}