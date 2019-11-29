package com.app.model.redisHash;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Api 호출 제한 체크 해시
 * @author Dong-Min Seol
 * @since  2019-11-29
 */
@Getter
@RedisHash(value = "ApiCall", timeToLive = 3600*24)
public class ApiCall implements Serializable {

    @Id
    private String id;
    private Integer callCnt;
    private LocalDateTime lastCalledTime;

    public ApiCall() {}
    public ApiCall(String id) {
        this.id             = id;
        this.callCnt        = 1;
        this.lastCalledTime = LocalDateTime.now();
    }

    public boolean refresh () {
        if(this.callCnt < 5) {
            this.callCnt++;
            this.lastCalledTime = LocalDateTime.now();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ApiCall{" +
                "id='" + id + '\'' +
                ", callCnt=" + callCnt +
                ", lastCalledTime=" + lastCalledTime +
                '}';
    }
}
