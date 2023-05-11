package cn.cupbread.egamestore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 游戏类型
 * </p>
 *
 * @author CupOfBread
 * @since 2023-05-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("e_game_type")
public class GameType {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 游戏类型
     */
    private String name;

    /**
     * 备注
     */
    private String remark;
}
