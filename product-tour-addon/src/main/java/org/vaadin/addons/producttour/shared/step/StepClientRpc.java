package org.vaadin.addons.producttour.shared.step;

import com.vaadin.shared.communication.ClientRpc;

public interface StepClientRpc extends ClientRpc {

  void cancel();

  void complete();

  void hide();

  void show();

  void scrollTo();
}
