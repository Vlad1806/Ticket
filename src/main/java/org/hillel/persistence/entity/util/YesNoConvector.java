package org.hillel.persistence.entity.util;


import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import javax.persistence.Converts;
import java.util.Objects;

//@Converter(autoApply = true)
public class YesNoConvector implements AttributeConverter<Boolean,String> {

    private enum YesNoType{
        Yes("yes",true),N0("no",false);
        private final String dbValue;
        private final boolean entityValue;

        YesNoType(String dbValue, boolean entityValue) {
            this.dbValue = dbValue;
            this.entityValue = entityValue;
        }
        private static final YesNoType getByDBValue(String value){
            if (StringUtils.isEmpty(value)) return N0;
            for(YesNoType type : values()){
                if(Objects.equals(type.dbValue,value)) return type;
            }
            return N0;
        }

        private static final YesNoType getByEntityValue(Boolean value){
            if (Objects.isNull(value)) return N0;
            for(YesNoType type : values()){
                if(Objects.equals(type.entityValue,value)) return type;
            }
            return N0;
        }
    }
    @Override
    public String convertToDatabaseColumn(Boolean aBoolean) {
        return YesNoType.getByEntityValue(aBoolean).dbValue;
    }

    @Override
    public Boolean convertToEntityAttribute(String dbValue) {
        return YesNoType.getByDBValue(dbValue).entityValue;
    }
}
