package org.vaadin.addons.producttour.step;

import com.vaadin.event.ConnectorEvent;

import org.vaadin.addons.producttour.provider.StepProvider;
import org.vaadin.addons.producttour.provider.TourProvider;
import org.vaadin.addons.producttour.tour.Tour;

/**
 * Base class for all events that were caused by a {@link Step}.
 */
public class StepEvent extends ConnectorEvent implements StepProvider, TourProvider {

  /**
   * Construct a new provider.
   *
   * @param source
   *     The source of the provider
   */
  public StepEvent(Step source) {
    super(source);
  }

  /**
   * Shortcut method to get the tour of the provider.
   *
   * @return The tour or <code>null</code> if the step that caused the provider is not attached to
   * any tour.
   */
  @Override
  public Tour getTour() {
    Step step = getStep();
    return step != null ? step.getTour() : null;
  }

  /**
   * Get the step that is the source of the provider.
   *
   * @return The step that caused the provider
   */
  @Override
  public Step getStep() {
    return (Step) getSource();
  }
}
