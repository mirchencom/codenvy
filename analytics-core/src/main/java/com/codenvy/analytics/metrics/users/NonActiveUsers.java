/*
 *
 * CODENVY CONFIDENTIAL
 * ________________
 *
 * [2012] - [2013] Codenvy, S.A.
 * All Rights Reserved.
 * NOTICE: All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any. The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */
package com.codenvy.analytics.metrics.users;

import com.codenvy.analytics.datamodel.LongValueData;
import com.codenvy.analytics.datamodel.ValueData;
import com.codenvy.analytics.datamodel.ValueDataUtil;
import com.codenvy.analytics.metrics.*;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;

/** @author <a href="mailto:abazko@codenvy.com">Anatoliy Bazko</a> */
@RolesAllowed({"system/admin", "system/manager"})
public class NonActiveUsers extends CalculatedMetric implements Expandable {

    public NonActiveUsers() {
        super(MetricType.NON_ACTIVE_USERS, new MetricType[]{MetricType.TOTAL_USERS,
                                                            MetricType.ACTIVE_USERS});
    }

    @Override
    public ValueData getValue(Context context) throws IOException {
        LongValueData total = ValueDataUtil.getAsLong(basedMetric[0], context.cloneAndRemove(Parameters.FROM_DATE));
        LongValueData active = ValueDataUtil.getAsLong(basedMetric[1], context);
        return total.subtract(active);
    }

    @Override
    public Class<? extends ValueData> getValueDataClass() {
        return LongValueData.class;
    }

    @Override
    public String getDescription() {
        return "Non-active users";
    }

    @Override
    public ValueData getExpandedValue(Context context) throws IOException {
        // get all documents from start date of logging to date defined in context
        ValueData total = ((Expandable)basedMetric[0]).getExpandedValue(context.cloneAndRemove(Parameters.FROM_DATE));
        ValueData active = ((Expandable)basedMetric[1]).getExpandedValue(context);
        return total.subtract(active);
    }

    @Override
    public String getExpandedField() {
        return USER;
    }
}
