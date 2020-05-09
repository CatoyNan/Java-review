package Data;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @description: 集合注入
 * @author: xjn
 * @create: 2019-04-25 09:43
 **/
public class CollectionData {
    private Set setData;
    private List listData;
    private Map mapData;
    private Properties properties;

    public Set getSetData() {
        return setData;
    }

    public void setSetData(Set setData) {
        this.setData = setData;
    }

    public List getListData() {
        return listData;
    }

    public void setListData(List listData) {
        this.listData = listData;
    }

    public Map getMapData() {
        return mapData;
    }

    public void setMapData(Map mapData) {
        this.mapData = mapData;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "CollectionData{" +
                ",\nsetData=" + setData +
                ",\nlistData=" + listData +
                ",\nmapData=" + mapData +
                ",\npropertyData=" + properties +
                '}';
    }
}
