package com.farerboy.oa.enums;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/2/19 4:13 下午
 */
public enum ResourceTypeEnum {

    ROUTE(0,"路由"),
    MENU(1,"菜单"),

    ;

    private int code;
    private String name;

    ResourceTypeEnum(int code,String name){
        this.code = code;
        this.name = name;
    }

    public static ResourceTypeEnum get(int code){
        for(ResourceTypeEnum resourceTypeEnum : ResourceTypeEnum.values()){
            if(resourceTypeEnum.code == code){
                return resourceTypeEnum;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
