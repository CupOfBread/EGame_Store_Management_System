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
 * 游戏资产
 * </p>
 *
 * @author CupOfBread
 * @since 2023-05-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("e_assets_game")
public class AssetsGame {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 游戏_id
     */
    private Integer game;

    /**
     * 入库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date storageTime;

    /**
     * 是否借出
     */
    private Integer isLent;
}
