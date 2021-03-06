package enno.operation.core.DataConversion;

import enno.operation.core.common.LogUtil;

/**
 * Created by v-zoli on 2015/11/11.
 */
public class DataConversionFactory<T> {
    public IDataConversion<T> createDataConversion(String className) {
        try {
            Class cls = Class.forName(className);
            return createDataConversion(cls);
        } catch (Exception ex) {
            getClass().getGenericSuperclass();
            //ex.printStackTrace();
            LogUtil.SaveLog(DataConversionFactory.class.getName(), ex);
        }
        return null;
    }

    public IDataConversion<T> createDataConversion(Class cls) {
        try {
            IDataConversion<T> dataConversion = (IDataConversion<T>) cls.newInstance();
            return dataConversion;
        } catch (Exception ex) {
            //ex.printStackTrace();
            LogUtil.SaveLog(DataConversionFactory.class.getName(), ex);
        }
        return null;
    }
}
