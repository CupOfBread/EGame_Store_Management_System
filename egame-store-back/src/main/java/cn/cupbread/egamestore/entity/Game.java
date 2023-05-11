package cn.cupbread.egamestore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 游戏
 * </p>
 *
 * @author CupOfBread
 * @since 2023-05-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("e_game")
public class Game {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 游戏名(中文)
     */
    private String nameCn;

    /**
     * 游戏名(英文)
     */
    private String nameEn;

    /**
     * 年龄限制
     */
    private Integer limitAge;

    /**
     * 游戏机类型_id
     */
    private Integer gameConsoleId;

    /**
     * 游戏类型_id
     */
    private Integer gameTypeId;

    /**
     * 发行日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date listingDate;

    /**
     * 发行商
     */
    private String publisher;

    /**
     * 开发商
     */
    private String developer;

    /**
     * 简介
     */
    private String introduction;
}
