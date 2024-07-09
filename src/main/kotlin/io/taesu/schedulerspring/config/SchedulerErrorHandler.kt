package io.taesu.schedulerspring.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.util.ErrorHandler

/**
 * Created by itaesu on 2024. 7. 8..
 *
 * @author Lee Tae Su
 * @version scheduler-spring
 * @since scheduler-spring
 */
class SchedulerErrorHandler: ErrorHandler {
    override fun handleError(t: Throwable) {
        log.error("error during scheduling", t)
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}
