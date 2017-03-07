package org.vaadin.addons.producttour.client.step;

import com.google.gwt.dom.client.Element;

import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.Connector;
import com.vaadin.shared.ui.Connect;

import org.vaadin.addons.producttour.client.button.StepButtonConnector;
import org.vaadin.addons.producttour.client.util.Command;
import org.vaadin.addons.producttour.shared.step.StepClientRpc;
import org.vaadin.addons.producttour.shared.step.StepServerRpc;
import org.vaadin.addons.producttour.shared.step.StepState;
import org.vaadin.addons.producttour.step.Step;

@Connect(Step.class)
public class StepConnector extends AbstractExtensionConnector implements StepClientRpc {

  private StepJso stepJso;
  private StepServerRpc rpcProxy;

  @Override
  protected void extend(ServerConnector target) {
    registerRpc(StepClientRpc.class, this);
    rpcProxy = getRpcProxy(StepServerRpc.class);
  }

  public void setStepJso(StepJso stepJso) {
    this.stepJso = stepJso;
  }

  public String getStepId() {
    return getState().id != null ? getState().id : (stepJso != null ? stepJso.getId() : null);
  }

  @Override
  public void onStateChanged(StateChangeEvent stateChangeEvent) {
    super.onStateChanged(stateChangeEvent);
    redrawStep();
  }

  private void redrawStep() {
    if (stepJso != null) {
      stepJso.setOptions(getOptions());
      stepJso.reRender();
    }
  }

  public StepOptions getOptions() {
    StepOptions options = StepOptions.create();
    options.setTitle(getState().title);
    options.setText(getState().text);
    options.setModal(getState().modal);
    options.setShowCancelLink(getState().cancellable);
    options.setScrollTo(getState().scrollTo);
    options.setAttachTo(getAttachToElement(), getState().anchor);
    options.setWidth(getState().width);
    options.setHeight(getState().height);
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
    options.setShowListener(new Command() {
      @Override
      public void execute() {
        rpcProxy.onShow();
      }
    });

    for (Connector button : getState().buttons) {
      StepButtonConnector stepButtonConnector = ((StepButtonConnector) button);
      stepButtonConnector.removeStateChangeHandler(this);
      stepButtonConnector.addStateChangeHandler(this);
      options.addButtonOptions(stepButtonConnector.getOptions());
    }

    return options;
  }

  private Element getAttachToElement() {
    ComponentConnector attachTo = (ComponentConnector) getState().attachTo;
    return attachTo != null ? attachTo.getWidget().getElement() : null;
  }

  @Override
  public StepState getState() {
    return (StepState) super.getState();
  }

  @Override
  public void cancel() {
    stepJso.cancel();
  }

  @Override
  public void complete() {
    stepJso.complete();
  }

  @Override
  public void hide() {
    stepJso.hide();
  }

  @Override
  public void show() {
    stepJso.show();
  }

  @Override
  public void scrollTo() {
    stepJso.scrollTo();
  }
}
