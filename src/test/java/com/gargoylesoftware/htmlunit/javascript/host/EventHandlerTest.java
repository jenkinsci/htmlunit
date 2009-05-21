package com.gargoylesoftware.htmlunit.javascript.host;

import com.gargoylesoftware.htmlunit.WebTestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kohsuke Kawaguchi
 */
public class EventHandlerTest extends WebTestCase {

    /**
     * Tests box object attributes relating to HTML elements: firstChild, lastChild, previousSibling, etc.
     * @throws Exception if an error occurs
     */
    @Test
    public void testElementAttributes() throws Exception {
        final String html =
              "<html><body>\n" +
              "<div id=\"foo\" onclick=\"alert('foo')\"></div>\n" +
              "<script>\n" +
              "var foo = document.getElementById('foo');\n" +
              "foo.onclick.call(foo);\n" +
              "</script>\n" +
              "</body></html>";
        final List<String> actual = new ArrayList<String>();
        loadPage(html, actual);
        assertEquals(new String[]{"foo"}, actual);
    }
}