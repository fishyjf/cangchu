package tk.mybatis.springboot.enums;

import com.alibaba.druid.util.StringUtils;

public enum InventoryStatusEnum {
    /**
     * 否
     */
    NO("0", "No"),
    /**
     * 是
     */
    YES("1", "Yes");

    private final String value;
    private final String desc;

    InventoryStatusEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    public static InventoryStatusEnum matchCode(String code) {
        for (InventoryStatusEnum badgeFlgEnum : values()) {
            if (StringUtils.equals(badgeFlgEnum.getValue(), code)) {
                return badgeFlgEnum;
            }
        }
        return null;
    }
}
