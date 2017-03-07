package org.vaadin.addons.producttour.shared.tour;

import com.vaadin.shared.communication.ClientRpc;

public interface TourClientRpc extends ClientRpc {

  void cancel();

  void hide();

  void show(String stepId);

  void start();

  void back();

  void next();
}
