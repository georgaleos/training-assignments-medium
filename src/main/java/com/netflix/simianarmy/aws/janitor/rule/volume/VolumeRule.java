package com.netflix.simianarmy.aws.janitor.rule.volume;

import com.netflix.simianarmy.Resource;
import com.netflix.simianarmy.janitor.Rule;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author <a href="mailto:george@elefth.com">Eleftheriadis Georgios</a>
 */
public abstract class VolumeRule implements Rule {

    private static final Logger LOGGER = LoggerFactory.getLogger(OldDetachedVolumeRule.class);

    /** The date format used to print or parse the user specified termination date. **/
    private static final DateTimeFormatter TERMINATION_DATE_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd");


    Boolean checkJanitorTag(Resource resource, String janitorTag) {
        if (janitorTag != null) {
            if ("donotmark".equals(janitorTag)) {
                LOGGER.info(String.format("The volume %s is tagged as not handled by Janitor",
                        resource.getId()));
                return true;
            }
            try {
                // Owners can tag the volume with a termination date in the "janitor" tag.
                Date userSpecifiedDate = new Date(
                        TERMINATION_DATE_FORMATTER.parseDateTime(janitorTag).getMillis());
                resource.setExpectedTerminationTime(userSpecifiedDate);
                resource.setTerminationReason(String.format("User specified termination date %s", janitorTag));
                return false;
            } catch (Exception e) {
                LOGGER.error(String.format("The janitor tag is not a user specified date: %s", janitorTag));
            }
        }
        return null;
    }
}
