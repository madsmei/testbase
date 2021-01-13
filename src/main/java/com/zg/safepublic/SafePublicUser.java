package com.zg.safepublic;

/**
 * 仿Collections对容器的包装，将内部成员对象进行线程安全包装
 */
public class SafePublicUser {
    private final UserVo user;

    public UserVo getUser() {
        return user;
    }

    public SafePublicUser(int a, String b, UserVo user) {
        this.user = new SynUser(user);
    }

    /*线程安全的类*/
    private static class SynUser extends UserVo {
        private final UserVo userVo;
        private final Object lock = new Object();

        public SynUser(UserVo userVo) {
            this.userVo = userVo;
        }

        @Override
        public int getAge() {
            synchronized (lock) {
                return userVo.getAge();
            }
        }

        @Override
        public void setAge(int age) {
            synchronized (lock) {
                userVo.setAge(age);
            }
        }
    }

}
