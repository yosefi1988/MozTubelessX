package ir.sajjadyosefi.android.xTubeless.classes.example.designPattern.factory;

import ir.sajjadyosefi.android.xTubeless.classes.example.designPattern.factory.model.Staff;

public class StaffFactory {
    private final static String SUPPORT = "SUP";
    private final static String TECHNICAL = "TEC";
    private final static String SALE = "SAL";

    //https://zerotohero.ir/article/android/%D8%A2%D8%B4%D9%86%D8%A7%DB%8C%DB%8C-%D8%A8%D8%A7-factory-design-pattern-%D8%AC%D8%A7%D9%88%D8%A7/
    public static Staff getStaff(String code) {
//        Database database = new Database();
//        if (code.startsWith(SUPPORT)) {
//            return database.getSupport(code);
//        } else if (code.startsWith(TECHNICAL)) {
//            return database.getTehcnical(code);
//        } else if (code.startsWith(SALE)) {
//            return database.getSale(code);
//        }
        return null;
    }
}
