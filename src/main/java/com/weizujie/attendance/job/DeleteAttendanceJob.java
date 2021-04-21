package com.weizujie.attendance.job;


import com.weizujie.attendance.entity.Attendance;
import com.weizujie.attendance.service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 清空学生考勤的定时任务
 * @author weizujie
 * EnableScheduling: 开启定时任务注解
 */
@Slf4j
@Configuration
@EnableScheduling
public class DeleteAttendanceJob {

    private final AttendanceService attendanceService;

    public DeleteAttendanceJob(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    /**
     * 每周星期天凌晨一点执行一次
     */
    @Scheduled(cron = "0 0 1 ? * L")
    private void job() {
        log.info("开始执行【清空学生考勤】定时任务");
        // 获取所有学生的考勤
        List<Attendance> attendanceList = attendanceService.selectList();
        if (!CollectionUtils.isEmpty(attendanceList)) {
            // 删除所有考勤数据
            int count = attendanceService.deleteList(attendanceList);
            log.info(">>> 删除了 {} 条数据", count);
        }
        log.info("【清空学生考勤】定时任务结束");
    }

}
