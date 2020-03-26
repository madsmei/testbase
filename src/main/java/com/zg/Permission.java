package com.zg;

/**
 * @Description:  了解 位运算 在权限控制方面的运用
 * @Date 2020/2/19
 * @Version V1.0
 * @Author Mads
 **/
public class Permission {

    private static int ALLOW_SELECT = 1<<0;//1
    private static int ALLOW_INSERT = 1<<1;//10
    private static int ALLOW_UPDATE = 1<<2;//100
    private static int ALLOW_DELETE = 1<<3;//1000

    //当前权限的状态
    private int flag = 0;

    public void setMission(int mission) {
        this.flag = mission;
    }

    /*添加权限*/
    public void addMission(int mission) {
        flag = flag | mission;
    }

    /*撤销权限*/
    public void disableMission(int mission) {
        flag =  flag &~ mission;
    }

    /*是否拥有权限*/
    public boolean isAllow(int mission) {
        return (flag & mission) == mission;
    }

    /*是否不拥有 权限*/
    public boolean isNotAllow(int mission) {
        return (flag & mission) == 0;
    }


    public static void main(String[] args) {
        //所有权限。
        int flag = 15;

        Permission permission = new Permission();
        permission.setMission(flag);
        permission.disableMission(ALLOW_DELETE | ALLOW_SELECT);

        System.out.println("ALLOW_SERLECT="+permission.isAllow(ALLOW_SELECT));
        System.out.println("ALLOW_INSERT="+permission.isAllow(ALLOW_INSERT));
        System.out.println("ALLOW_UPDATE="+permission.isAllow(ALLOW_UPDATE));
        System.out.println("ALLOW_DELETE="+permission.isAllow(ALLOW_DELETE));


        System.out.println((17 & 1 )== 1);

    }

}
