package io.taesu.schedulerspring

import net.javacrumbs.shedlock.core.LockAssert
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

/**
 * Created by itaesu on 2024. 7. 8..
 *
 * @author Lee Tae Su
 * @version scheduler-spring
 * @since scheduler-spring
 */
@Component
class TestScheduler1 {
    @SchedulerLock(
        name = "testScheduler1",
        lockAtMostFor = "PT10S",
        lockAtLeastFor = "PT5M"
    )
    @Scheduled(fixedDelay = 1000)
    fun triggerException() {
        LockAssert.assertLocked();
        if (1 == 1) {
            throw RuntimeException("test exception")
        }
    }
}
