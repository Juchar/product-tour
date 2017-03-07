package org.vaadin.addons.producttour.button;

import org.vaadin.addons.producttour.step.Step;

/**
 * Simplify the creation of a {@link Step}
 */
public class StepButtonBuilder {

  private final StepButton stepButton;

  /**
   * Create new step button builder
   */
  public StepButtonBuilder(String caption) {
    stepButton = new StepButton(caption);
  }

  /**
   * Convenience method calling {@link StepButton#setStyleName(String)}
   */
  public StepButtonBuilder withStyle(String style) {
    stepButton.setStyleName(style);
    return this;
  }

  /**
   * Convenience method calling {@link StepButton#setEnabled(boolean)}
   */
  public StepButtonBuilder withEnabled(boolean enabled) {
    stepButton.setEnabled(enabled);
    return this;
  }

  /**
   * Convenience method calling {@link StepButton#addClickListener(StepButtonClickListener)}
   */
  public StepButtonBuilder addClickListener(StepButtonClickListener listener) {
    stepButton.addClickListener(listener);
    return this;
  }

  /**
   * Build the button.
   *
   * @return The button
   */
  public StepButton build() {
    return stepButton;
  }
}
