package hongzicong.saltedfish.db;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dv00 on 2018/1/21.
 */

public class BooleanConverter implements PropertyConverter<List<Boolean>,String> {

    @Override
    public List<Boolean> convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        else {
            List<Boolean> result=new ArrayList<>();
            for(String s:Arrays.asList(databaseValue.split(","))){
                result.add((s=="1")?true:false);
            }
            return result;
        }
    }

    @Override
    public String convertToDatabaseValue(List<Boolean> entityProperty) {
        if(entityProperty==null){
            return null;
        }
        else{
            StringBuilder sb= new StringBuilder();
            for(Boolean link:entityProperty){
                sb.append((link)?"1":"0");
                sb.append(",");
            }
            return sb.toString();
        }
    }

}
