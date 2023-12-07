package ir.sajjadyosefi.android.xTubeless.classes.example.designPattern.factory;

import ir.sajjadyosefi.android.xTubeless.classes.example.designPattern.factory.model.Staff;

public class MainClass {

    public void mainMethod(){
        Staff saleWoman = StaffFactory.getStaff("SAL-2321");
        Staff technicalSupport = StaffFactory.getStaff("SUP-2349");
        Staff sysAdmin = StaffFactory.getStaff("TEC-7984");

//        System.out.println(saleWoman instanceof Sale); // true
//        System.out.println(technicalSupport instanceof Support); // true
//        System.out.println(sysAdmin instanceof Technical); // true
    }
}
