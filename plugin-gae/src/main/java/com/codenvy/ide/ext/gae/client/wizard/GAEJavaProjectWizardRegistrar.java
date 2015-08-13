/*******************************************************************************
 * Copyright (c) 2012-2015 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package com.codenvy.ide.ext.gae.client.wizard;

import org.eclipse.che.api.project.shared.dto.ImportProject;
import org.eclipse.che.ide.api.project.type.wizard.ProjectWizardRegistrar;
import org.eclipse.che.ide.api.wizard.WizardPage;
import org.eclipse.che.ide.collections.Array;
import org.eclipse.che.ide.collections.Collections;
import com.codenvy.ide.ext.gae.client.wizard.java.GAEJavaPagePresenter;
import com.google.inject.Inject;
import com.google.inject.Provider;

import javax.annotation.Nonnull;

import static com.codenvy.ide.ext.gae.shared.GAEConstants.GAE_JAVA_ID;
import static org.eclipse.che.ide.ext.java.shared.Constants.JAVA_CATEGORY;

/**
 * Provides information for registering GAE Java project type in project wizard.
 *
 * @author Artem Zatsarynnyy
 */
public class GAEJavaProjectWizardRegistrar implements ProjectWizardRegistrar {
    private final Array<Provider<? extends WizardPage<ImportProject>>> wizardPages;

    @Inject
    public GAEJavaProjectWizardRegistrar(Provider<GAEJavaPagePresenter> mavenPagePresenter) {
        wizardPages = Collections.createArray();
        wizardPages.add(mavenPagePresenter);
    }

    @Nonnull
    public String getProjectTypeId() {
        return GAE_JAVA_ID;
    }

    @Nonnull
    public String getCategory() {
        return JAVA_CATEGORY;
    }

    @Nonnull
    public Array<Provider<? extends WizardPage<ImportProject>>> getWizardPages() {
        return wizardPages;
    }
}
