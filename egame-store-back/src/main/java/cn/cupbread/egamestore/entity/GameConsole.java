package cn.cupbread.egamestore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 游戏机
 * </p>
 *
 * @author CupOfBread
 * @since 2023-05-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("e_game_console")
public class GameConsole {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 游戏机名
     */
    private String name;

    /**
     * 上市时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date listingDate;

    /**
     * 生产商
     */
    private String manufacturer;

    /**
     * 开发商
     */
    private String developer;

    /**
     * 备注
     */
    private String remark;
}
