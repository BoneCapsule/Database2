package model;

import java.time.LocalDateTime;
import java.time.Duration;

public class CallRecord {

    private int userId;
    private int number;   // 通话时长，按分钟计
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    public CallRecord() {
        Duration duration = Duration.between(startTime, endTime);

        // 通话时长不足一分钟按一分钟算
        number = (int) duration.toMinutes();
        if (duration.toMillis() - number > 0) {
            number += 1;
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
