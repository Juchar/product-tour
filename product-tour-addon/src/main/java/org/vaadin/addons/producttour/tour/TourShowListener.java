package org.vaadin.addons.producttour.tour;

import com.vaadin.util.ReflectTools;

import org.vaadin.addons.producttour.step.Step;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface TourShowListener extends Serializable {

  Method SHOW_METHOD = ReflectTools.findMethod(TourShowListener.class,
                                               "onShow",
                                               ShowEvent.class);

  /**
   * Fired if a step of a {@link Tour} is shown.
   *
   * @param event
   *     An provider containing information about the showing
   */
  void onShow(ShowEvent event);

  /**
   * Event class that contains information about showing.
   */
  class ShowEvent extends TourEvent {

    private final Step previousStep;
    private final Step currentStep;

    public ShowEvent(Tour source, Step previousStep, Step currentStep) {
      super(source);
      this.previousStep = previousStep;
      this.currentStep = currentStep;
    }

    public Step getPreviousStep() {
      return previousStep;
    }

    public Step getCurrentStep() {
      return currentStep;
    }
  }
}
