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
 * 游戏机资产
 * </p>
 *
 * @author CupOfBread
 * @since 2023-05-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("e_assets_console")
public class AssetsConsole {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 租借状态
     */
    private Integer status;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 游戏机类型_id
     */
    private Integer gameConsole;

    /**
     * 入库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date storageTime;

    /**
     * 单价(小时)
     */
    private BigDecimal unitPrice;
}
