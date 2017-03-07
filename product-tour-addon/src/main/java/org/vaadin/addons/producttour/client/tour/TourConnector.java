package org.vaadin.addons.producttour.client.tour;


import com.google.gwt.core.client.Scheduler;

import com.vaadin.client.ServerConnector;
import com.vaadin.client.annotations.OnStateChange;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.Connector;
import com.vaadin.shared.ui.Connect;

import org.vaadin.addons.producttour.client.step.StepConnector;
import org.vaadin.addons.producttour.client.step.StepJso;
import org.vaadin.addons.producttour.client.step.StepOptions;
import org.vaadin.addons.producttour.client.util.Command;
import org.vaadin.addons.producttour.client.util.StringBiConsumer;
import org.vaadin.addons.producttour.shared.tour.TourClientRpc;
import org.vaadin.addons.producttour.shared.tour.TourServerRpc;
import org.vaadin.addons.producttour.shared.tour.TourState;
import org.vaadin.addons.producttour.tour.Tour;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Connect(Tour.class)
public class TourConnector extends AbstractExtensionConnector implements TourClientRpc {

  private final List<StepConnector> steps;

  private TourJso tourJso;
  private TourServerRpc rpcProxy;

  public TourConnector() {
    steps = new LinkedList<StepConnector>();
  }

  @Override
  protected void extend(ServerConnector target) {
    registerRpc(TourClientRpc.class, this);
    rpcProxy = getRpcProxy(TourServerRpc.class);

    tourJso = createTour(getOptions());
  }

  private native TourJso createTour(TourOptions tourOptions) /*-{
    var tour = new $wnd.Shepherd.Tour(tourOptions);
    var when = tourOptions.when;
    if (when) {
      for (var event in when) {
        if ({}.hasOwnProperty.call(when, event)) {
          var handler = when[event];
          tour.on(event, handler, this);
        }
      }
    }

    tour.on('inactive', function () {
      for (var i = 0; i < tour.steps.length; ++i) {
        tour.steps[i].destroy();
      }
    });

    tour.on('complete', function () {
      for (var i = 0; i < tour.steps.length; ++i) {
        tour.steps[i].destroy();
      }
    });

    tour.on('cancel', function () {
      for (var i = 0; i < tour.steps.length; ++i) {
        tour.steps[i].destroy();
      }
    });

    return tour;
  }-*/;

  public TourOptions getOptions() {
    StepOptions defaultStepOptions = StepOptions.create();
    defaultStepOptions.setButtons(false);
    defaultStepOptions.setClasses("shepherd-theme-valo");

    TourOptions options = TourOptions.create();
    options.setDefaultOptions(defaultStepOptions);
    options.setCancelListener(new Command() {
      @Override
      public void execute() {
        rpcProxy.onCancel();
      }
    });
    options.setCompleteListener(new Command() {
      @Override
      public void execute() {
        rpcProxy.onComplete();
      }
    });
    options.setHideListener(new Command() {
      @Override
      public void execute() {
        rpcProxy.onHide();
      }
    });
    options.setShowListener(new StringBiConsumer() {
      @Override
      public void accept(String s, String s2) {
        rpcProxy.onShow(s, s2);
      }
    });
    options.setStartListener(new Command() {
      @Override
      public void execute() {
        rpcProxy.onStart();
      }
    });
    return options;
  }

  @Override
  public void cancel() {
    tourJso.cancel();
  }

  @Override
  public void hide() {
    tourJso.hide();
  }

  @Override
  public void show(String stepId) {
    tourJso.show(stepId);
  }

  @Override
  public void start() {
    tourJso.start();
  }

  @Override
  public void back() {
    tourJso.back();
  }

  @Override
  public void next() {
    tourJso.next();
  }

  @OnStateChange("steps")
  private void updateSteps() {
    List<StepConnector> addedSteps = getAddedSteps();
    for (StepConnector step : addedSteps) {
      addStep(step);
    }

    List<StepConnector> removedSteps = getRemovedSteps();
    for (StepConnector step : removedSteps) {
      removeStep(step);
    }
  }

  private List<StepConnector> getAddedSteps() {
    List<StepConnector> addedSteps = new LinkedList<StepConnector>();

    List<Connector> stateSteps = getState().steps;
    for (Connector stateStep : stateSteps) {
      if (!steps.contains(stateStep)) {
        addedSteps.add((StepConnector) stateStep);
      }
    }

    return addedSteps;
  }

  private void addStep(StepConnector stepConnector) {
    StepJso stepJso = tourJso.addStep(stepConnector.getStepId(), stepConnector.getOptions());
    stepConnector.setStepJso(stepJso);
    steps.add(stepConnector);
  }

  private List<StepConnector> getRemovedSteps() {
    List<StepConnector> removedSteps = new LinkedList<StepConnector>();

    List<Connector> stateSteps = getState().steps;
    for (StepConnector step : steps) {
      if (!stateSteps.contains(step)) {
        removedSteps.add(step);
      }
    }

    return removedSteps;
  }

  private void removeStep(StepConnector stepConnector) {
    tourJso.removeStep(stepConnector.getStepId());
    stepConnector.setStepJso(null);
    steps.remove(stepConnector);
  }

  @OnStateChange("currentStep")
  private void updateCurrentStep() {
    StepConnector currentStepFromState = (StepConnector) getState().currentStep;
    StepJso currentStepFromTour = tourJso.getCurrentStep();

    final String stepIdFromState = currentStepFromState != null
                                       ? currentStepFromState.getStepId()
                                       : "";
    final String stepIdFromTour = currentStepFromTour != null ? currentStepFromTour.getId() : "";

    if (!Objects.equals(stepIdFromState, stepIdFromTour)) {
      Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
        @Override
        public void execute() {
          tourJso.show(stepIdFromState, false);
        }
      });
    }
  }

  @Override
  public void onUnregister() {
    super.onUnregister();
    tourJso.done();
    steps.clear();
  }

  @Override
  public TourState getState() {
    return (TourState) super.getState();
  }
}
