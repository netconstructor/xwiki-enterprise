/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.it.ui.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Represents the NewPage panel.
 * 
 * @version $Id$
 * @since 2.4M1
 */
public class CreatePagePanel extends ViewPage
{
    /**
     * The text field used to input the space name.
     * <p>
     * NOTE: We can't find the space name text field by name because there is a meta tag with the same name. We can't
     * find the space name text field by id either because the create page panel uses random identifiers to prevent
     * conflicts with the page content.
     */
    @FindBy(xpath = "//input[@type = 'text' and @name = 'space']")
    private WebElement spaceNameTextField;

    /**
     * The text field used to input the page name.
     */
    @FindBy(xpath = "//input[@type = 'text' and @name = 'page']")
    private WebElement pageNameTextField;

    /**
     * Navigates to the Panels.NewPage page, if not already there, and creates a new object to control it.
     */
    public CreatePagePanel()
    {
        String createPagePanelURL = getUtil().getURL("Panels", "NewPage");
        if (!getDriver().getCurrentUrl().equals(createPagePanelURL)) {
            getDriver().get(createPagePanelURL);
        }
    }

    /**
     * Fills the form on the NewPage panel with the given information and submits the form.
     * 
     * @param spaceName the name of the space where to create the page
     * @param pageName the name of page to create
     * @return the WYSIWYG edit page for the specified page
     */
    public WYSIWYGEditPage createPage(String spaceName, String pageName)
    {
        // Clean the default space name value.
        spaceNameTextField.clear();
        spaceNameTextField.sendKeys(spaceName);
        pageNameTextField.clear();
        pageNameTextField.sendKeys(pageName);
        pageNameTextField.submit();
        return new WYSIWYGEditPage();
    }
}
