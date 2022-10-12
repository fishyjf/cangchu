package tk.mybatis.springboot.model.entity;

import lombok.Data;

import javax.persistence.Column;

/**
 * @author liuzh_3nofxnp
 * @since 2016-01-22 22:16
 */
@Data
public class Goods extends BaseEntity {
    /**
     * 商品名称
     */
    @Column(name = "name")
    private String name;

}
