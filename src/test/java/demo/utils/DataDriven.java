package demo.utils;

import net.serenitybdd.core.Serenity;

import java.util.*;

public class DataDriven {
    public DataDriven() {
    }

    public static List<HashMap<String, String>> getDataFromDataTable(String keyDataTable, List<List<String>> dataTable) {
        List<HashMap<String, String>> dataReturn = new ArrayList();

        for (int i = 1; i < dataTable.size(); ++i) {
            List<String> dataRow = dataTable.get(i);
            if ((dataRow.get(0)).equalsIgnoreCase(keyDataTable)) {
                HashMap<String, String> rowDataReturn = new HashMap();
                for (int j = 1; j < dataRow.size(); ++j) {
                    rowDataReturn.put((String) ((List) dataTable.get(0)).get(j), dataRow.get(j));
                }

                dataReturn.add(rowDataReturn);
            }
        }

        return dataReturn;
    }

    public static String getTestDataByCol(String colName) {
        String id = (String) Serenity.sessionVariableCalled("id");
        Map<String, String> rowData = (Map) Serenity.sessionVariableCalled(id);
        return (String) rowData.get(colName);
    }

    public static Map<String, String> getTestDataRow() {
        String id = (String) Serenity.sessionVariableCalled("id");
        return (Map) Serenity.sessionVariableCalled(id);
    }

    public static Map<String, String> getTestDataRowWithId(String id) {
        return (Map) Serenity.sessionVariableCalled(id);
    }

    public static List<Map<String, String>> getTestDataListWithId(String id) {
        return (List) Serenity.sessionVariableCalled(id);
    }

    private static LinkedHashMap<Integer, List<String>> loadDataTable(List<List<String>> _dataTable) {
        LinkedHashMap<Integer, List<String>> hashMap = new LinkedHashMap();

        for (int i = 0; i < _dataTable.size(); ++i) {
            hashMap.put(i, _dataTable.get(i));
        }

        System.out.println("***** INFO ***** Load data successfully!");
        return hashMap;
    }

    public static LinkedHashMap<Integer, List<String>> getDataTbRowsByValEqualInCol(List<List<String>> dataTable, String _colName, String _givenVal) {
        LinkedHashMap<Integer, List<String>> lhTempSessionDataTable = loadDataTable(dataTable);
        LinkedHashMap<Integer, List<String>> lhOuput = new LinkedHashMap();
        int colIndex = ((List) lhTempSessionDataTable.get(0)).indexOf(_colName);
        if (colIndex == -1) {
            System.out.println("***ERROR*** Column [" + _colName + "] not found in Data Table .");
            throw new IllegalArgumentException("Column [" + _colName + "] not found in Data Table]. Re check data !");
        } else {
            Iterator var6 = lhTempSessionDataTable.keySet().iterator();

            while (var6.hasNext()) {
                int key = (Integer) var6.next();
                List<String> lTemp = (List) lhTempSessionDataTable.get(key);
                if (lTemp.size() == 0) {
                    System.out.println("***ERROR*** Data Row not found in Data Table.[" + key + "].");
                    throw new IllegalArgumentException("Data Row not found in Data Table [" + key + "]. Re check data !");
                }

                if (((String) lTemp.get(colIndex)).equals(_givenVal)) {
                    lhOuput.put(key, lTemp);
                }
            }

            return lhOuput;
        }
    }

    public static LinkedHashMap<Integer, List<String>> getDataTbRowsNoHeader(List<List<String>> dataTable) {
        LinkedHashMap<Integer, List<String>> ret = loadDataTable(dataTable);
        ret.remove(0);
        return ret;
    }

    public static String getDataTbVal(List<List<String>> dataTable, int _rowIndex, String _colName) {
        String ret = "";
        int colIndex = ((List) dataTable.get(0)).indexOf(_colName);
        if (colIndex == -1) {
            System.out.println("***WARNING*** Column [" + _colName + "] not found in Data Table.");
            return ret;
        } else {
            try {
                ret = (String) ((List) dataTable.get(_rowIndex)).get(colIndex);
            } catch (Exception var6) {
                ret = "";
                System.out.println("***WARNING*** Row [" + _rowIndex + "] not found in Data Table.");
            }

            return ret;
        }
    }
}