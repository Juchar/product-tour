package org.vaadin.addons.producttour.shared.step;

public enum ContentMode {

  /**
   * Content mode, where the step contains only plain text.
   */
  TEXT,

  /**
   * Content mode, where the step contains preformatted text. In this mode
   * newlines are preserved when rendered on the screen.
   */
  PREFORMATTED,

  /**
   * Content mode, where the step contains HTML.
   */
  HTML

}
