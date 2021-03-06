/*
 * Copyright (c) 2002-2009 Gargoyle Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gargoylesoftware.htmlunit.javascript.host.css;

import org.w3c.dom.css.CSSStyleSheet;

import com.gargoylesoftware.htmlunit.javascript.host.MediaList;
import com.gargoylesoftware.htmlunit.javascript.host.Stylesheet;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLElement;

/**
 * A JavaScript object for a CSSImportRule.
 *
 * @version $Revision: 4859 $
 * @author Daniel Gredler
 */
public class CSSImportRule extends CSSRule {

    private static final long serialVersionUID = -3352769444872087531L;

    private MediaList media_;
    private Stylesheet importedStylesheet_;

    /**
     * Creates a new instance. JavaScript objects must have a default constructor.
     */
    @Deprecated
    public CSSImportRule() {
        // Empty.
    }

    /**
     * Creates a new instance.
     * @param stylesheet the Stylesheet of this rule.
     * @param rule the wrapped rule
     */
    protected CSSImportRule(final Stylesheet stylesheet, final org.w3c.dom.css.CSSRule rule) {
        super(stylesheet, rule);
    }

    /**
     * Returns the URL of the imported style sheet.
     * @return the URL of the imported style sheet
     */
    public String jsxGet_href() {
        return getImportRule().getHref();
    }

    /**
     * Returns the media types that the imported CSS style sheet applies to.
     * @return the media types that the imported CSS style sheet applies to
     */
    public MediaList jsxGet_media() {
        if (media_ == null) {
            final Stylesheet parent = this.jsxGet_parentStyleSheet();
            final org.w3c.dom.stylesheets.MediaList ml = getImportRule().getMedia();
            media_ = new MediaList(parent, ml);
        }
        return media_;
    }

    /**
     * Returns the style sheet referred to by this rule.
     * @return the style sheet referred to by this rule
     */
    public Stylesheet jsxGet_styleSheet() {
        if (importedStylesheet_ == null) {
            final Stylesheet owningSheet = this.jsxGet_parentStyleSheet();
            final HTMLElement ownerNode = owningSheet.jsxGet_ownerNode();
            final CSSStyleSheet importedStylesheet = getImportRule().getStyleSheet();
            importedStylesheet_ = new Stylesheet(ownerNode, importedStylesheet, owningSheet.getUri());
        }
        return importedStylesheet_;
    }

    /**
     * Returns the wrapped rule, as an import rule.
     * @return the wrapped rule, as an import rule
     */
    private org.w3c.dom.css.CSSImportRule getImportRule() {
        return (org.w3c.dom.css.CSSImportRule) getRule();
    }

}
