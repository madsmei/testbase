package com.zg.safepublic;

import sun.misc.Unsafe;

/**
 * 不安全的发布
 */
public class UnSafePublish {
    private final UserVo user = new UserVo();

    public UserVo getUser() {
        return user;
    }

    public static void main(String[] args) {
		UnSafePublish unSafePublish = new UnSafePublish();
        UserVo user = unSafePublish.getUser();
		System.out.println(user);
        user.setAge(26);
		System.out.println(user);
		System.out.println(unSafePublish.getUser());

	}
}
