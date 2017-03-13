package org.vaadin.addons.producttour.client.util;

import com.google.gwt.dom.client.PreElement;

import com.vaadin.client.WidgetUtil;

import org.vaadin.addons.producttour.shared.step.ContentMode;

public final class ContentFormatter {

  private ContentFormatter() {}

  public static String format(String text, ContentMode contentMode) {
    switch (contentMode) {
      case PREFORMATTED:
        return "<" + PreElement.TAG + ">" + text + "</" + PreElement.TAG + ">";
      case TEXT:
        return WidgetUtil.escapeHTML(text);
      case HTML:
        return text;
      default:
        return "";
    }
  }
}
