package org.vaadin.addons.producttour.tour;

import com.vaadin.event.ConnectorEvent;

import org.vaadin.addons.producttour.provider.TourProvider;

/**
 * Base class for all events that were caused by a {@link Tour}.
 */
public class TourEvent extends ConnectorEvent implements TourProvider {

  /**
   * Construct a new provider.
   *
   * @param source
   *     The source of the provider
   */
  public TourEvent(Tour source) {
    super(source);
  }

  /**
   * Get the tour that is the source of the provider.
   *
   * @return The tour that caused the provider
   */
  @Override
  public Tour getTour() {
    return (Tour) getSource();
  }
}
