package org.vaadin.addons.producttour.shared.tour;

import com.vaadin.shared.communication.ServerRpc;

public interface TourServerRpc extends ServerRpc {

  void onCancel();

  void onComplete();

  void onHide();

  void onShow(String previousStepId, String currentStepId);

  void onStart();
}
