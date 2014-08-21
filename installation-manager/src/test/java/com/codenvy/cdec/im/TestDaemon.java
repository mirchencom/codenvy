/*
 * CODENVY CONFIDENTIAL
 * __________________
 *
 *  [2012] - [2014] Codenvy, S.A.
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */
package com.codenvy.cdec.im;

import com.codenvy.cdec.Daemon;
import com.codenvy.cdec.restlet.InstallationManagerService;
import com.codenvy.cdec.restlet.RestletClientFactory;

import org.restlet.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

/**
 * @author Dmytro Nochevnov
 */
public class TestDaemon {    
    protected static final Logger      LOG = LoggerFactory.getLogger(TestDaemon.class);

    private InstallationManagerService installationManagerServiceProxy;
    
    @BeforeMethod
    public void setUp() throws Exception {
        Daemon.start();
        
        installationManagerServiceProxy = RestletClientFactory.createServiceProxy(InstallationManagerService.class);
    }
    
    @AfterMethod
    public void tearDown() throws Exception {
        Daemon.stop();
    }
    
    @Test
    public void testObtainChallengeRequest() throws Exception {
        try {
            installationManagerServiceProxy.obtainChallengeRequest();
        } catch (ResourceException re) {
            fail(re.getStatus().toString() + ". " + re.getMessage());
        }
    }
}