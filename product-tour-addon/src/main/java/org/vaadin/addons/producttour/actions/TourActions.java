package org.vaadin.addons.producttour.actions;

import org.vaadin.addons.producttour.provider.TourProvider;
import org.vaadin.addons.producttour.tour.Tour;

import java.util.Optional;

/**
 * Contains shortcut actions for cleaner code
 */
public class TourActions {

  /**
   * Please use directly the static methods.
   */
  private TourActions() {
    // Prevent instantiation
  }

  public static void back(TourProvider provider) {
    Optional.ofNullable(provider.getTour()).ifPresent(Tour::back);
  }

  public static void cancel(TourProvider provider) {
    Optional.ofNullable(provider.getTour()).ifPresent(Tour::cancel);
  }

  public static void hide(TourProvider provider) {
    Optional.ofNullable(provider.getTour()).ifPresent(Tour::hide);
  }

  public static void next(TourProvider provider) {
    Optional.ofNullable(provider.getTour()).ifPresent(Tour::next);
  }

  public static void start(TourProvider provider) {
    Optional.ofNullable(provider.getTour()).ifPresent(Tour::start);
  }
}
