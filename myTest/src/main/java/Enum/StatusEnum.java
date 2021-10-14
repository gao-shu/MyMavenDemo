package Enum;

public enum StatusEnum {

    OPEN(true, "是"),
    CLOSE(false, "否")
    ;

    private Boolean status;
    private String type;
    StatusEnum(Boolean status, String type){
        this.status = status;
        this.type = type;
    }
    public static StatusEnum  parse(boolean status){
        for(StatusEnum value : StatusEnum.values()){
            if(value.status == status){
                return value;
            }
        }
        return null;
    }
}
