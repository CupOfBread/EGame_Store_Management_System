package cn.cupbread.egamestore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author CupOfBread
 * @since 2023-05-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("e_order")
public class Order {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 客户手机
     */
    private String customerPhone;

    /**
     * 游戏机
     */
    private Integer consoleAsset;

    /**
     * 租用时间(分钟)
     */
    private Integer leaseTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 是否支付
     */
    private Integer paid;

    /**
     * 完成情况(0:进行中1:已完成2:过期)
     */
    private Integer finished;
}
