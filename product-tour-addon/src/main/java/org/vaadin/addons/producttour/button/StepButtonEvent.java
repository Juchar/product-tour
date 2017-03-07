package org.vaadin.addons.producttour.button;

import com.vaadin.event.ConnectorEvent;

import org.vaadin.addons.producttour.provider.StepButtonProvider;
import org.vaadin.addons.producttour.provider.StepProvider;
import org.vaadin.addons.producttour.provider.TourProvider;
import org.vaadin.addons.producttour.step.Step;
import org.vaadin.addons.producttour.tour.Tour;

/**
 * Base class for all events that were caused by a {@link StepButton}.
 */
public class StepButtonEvent extends ConnectorEvent implements TourProvider, StepProvider,
                                                               StepButtonProvider {

  /**
   * Construct a new provider.
   *
   * @param source
   *     The source of the provider
   */
  public StepButtonEvent(StepButton source) {
    super(source);
  }

  /**
   * Shortcut method to get the tour of the provider.
   *
   * @return The tour or <code>null</code> if the button that caused the provider is not attached to
   * any step that is attached to a tour.
   */
  @Override
  public Tour getTour() {
    Step step = getStep();
    return step != null ? step.getTour() : null;
  }

  /**
   * Shortcut method to get the step of the provider.
   *
   * @return The step or <code>null</code> if the button that caused the provider is not attached to
   * any step.
   */
  @Override
  public Step getStep() {
    StepButton button = getStepButton();
    return button != null ? button.getStep() : null;
  }

  /**
   * Get the button that is the source of the provider.
   *
   * @return The button that caused the provider
   */
  @Override
  public StepButton getStepButton() {
    return (StepButton) getSource();
  }
}
