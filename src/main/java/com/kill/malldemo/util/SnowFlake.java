package com.kill.malldemo.util;

/**
 * @Description TODO
 * @Author lishen
 * @Date 6/8/21 5:43 pm
 **/

import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * 基于twitter的snowflake算法
 */
public class SnowFlake {

    private final static long START_TIME = 1480166465631L; //起始时间戳

    // 机器标识位数
    private final static long MACHINE_BIT = 5L;
    // 数据中心标识位数
    private final static long DATACENTER_BIT = 5L;
    // 序列号占用的位数
    private final static long SEQUENCE_BIT = 12L;

    /**
     * 每一部分的最大值
     */
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;                                  //向左偏移12位
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;                 //向左偏移17位
    private final static long TIMESTAMP_LEFT = SEQUENCE_BIT + MACHINE_BIT + DATACENTER_BIT; //向左偏移22位

    private long datacenterId;  //数据中心
    private long machineId;     //机器标识
    private long sequence = 0L; //序列号 并发
    private long lastStamp = -1L;//上一次时间戳

//    public SnowFlake(){
//        this.datacenterId = getDatacenterId(MAX_DATACENTER_NUM);
//        this.machineId = getMaxMachineId(datacenterId, MAX_MACHINE_NUM);
//    }

    public SnowFlake(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    /**
     * 产生下一个Id
     */
    public synchronized long nextId() {
        long currStamp = getNewStamp();
        if (currStamp < lastStamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStamp == lastStamp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStamp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStamp = currStamp;

        return (currStamp - START_TIME) << TIMESTAMP_LEFT //时间戳部分
                | datacenterId << DATACENTER_LEFT       //数据中心部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }

    private long getNextMill() {
        long mill = getNewStamp();
        while (mill <= lastStamp) {
            mill = getNewStamp();
        }
        return mill;
    }

    private long getNewStamp() {
        return System.currentTimeMillis();
    }

//    /**
//     * <p>
//     * 获取 maxWorkerId
//     * </p>
//     */
//    protected static long getMaxMachineId(long datacenterId, long maxMachineId) {
//        StringBuffer mpid = new StringBuffer();
//        mpid.append(datacenterId);
//        String name = ManagementFactory.getRuntimeMXBean().getName();
//        if (!name.isEmpty()) {
//            /*
//             * GET jvmPid
//             */
//            mpid.append(name.split("@")[0]);
//        }
//        /*
//         * MAC + PID 的 hashcode 获取16个低位
//         */
//        return (mpid.toString().hashCode() & 0xffff) % (maxMachineId + 1);
//    }
//
//    /**
//     * <p>
//     * 数据标识id部分
//     * </p>
//     */
//    protected static long getDatacenterId(long maxDatacenterId) {
//        long id = 0L;
//        try {
//            InetAddress ip = InetAddress.getLocalHost();
//            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
//            if (network == null) {
//                id = 1L;
//            } else {
//                byte[] mac = network.getHardwareAddress();
//                id = ((0x000000FF & (long) mac[mac.length - 1])
//                        | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
//                id = id % (maxDatacenterId + 1);
//            }
//        } catch (Exception e) {
//            System.out.println("ERROR getDatacenterId: " + e.getMessage());
//        }
//        return id;
//    }

}
