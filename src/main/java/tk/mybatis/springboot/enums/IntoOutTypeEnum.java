package tk.mybatis.springboot.enums;

import com.alibaba.druid.util.StringUtils;

public enum IntoOutTypeEnum {
    /**
     * 出库
     */
    OUT("0", "out"),
    /**
     * 入库
     */
    PUT("1", "put");

    private final String value;
    private final String desc;

    IntoOutTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    public static IntoOutTypeEnum matchCode(String code) {
        for (IntoOutTypeEnum badgeFlgEnum : values()) {
            if (StringUtils.equals(badgeFlgEnum.getValue(), code)) {
                return badgeFlgEnum;
            }
        }
        return null;
    }
}
