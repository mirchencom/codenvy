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

package com.codenvy.analytics;

import org.apache.pig.data.Tuple;
import org.apache.pig.data.TupleFactory;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static com.mongodb.util.MyAsserts.assertTrue;
import static org.testng.Assert.assertEquals;

/** @author <a href="mailto:abazko@exoplatform.com">Anatoliy Bazko</a> */
public class BaseTest {

    protected final TupleFactory     tupleFactory = TupleFactory.getInstance();
    protected final DateFormat       dateFormat   = new SimpleDateFormat("yyyyMMdd");
    protected final DateFormat       timeFormat   = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
    protected final SimpleDateFormat dirFormat    =
            new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd");

    public static final String BASE_DIR = "target";

    protected void assertTuples(Iterator<Tuple> iter, String[] tuplesAsString) {
        Set<String> tuples = new HashSet<>(Arrays.asList(tuplesAsString));

        int count = 0;
        while (iter.hasNext()) {
            count++;
            String tuple = iter.next().toString();
            assertTrue(tuples.contains(tuple), tuple);
        }

        assertEquals(tuples.size(), count);
    }
}
