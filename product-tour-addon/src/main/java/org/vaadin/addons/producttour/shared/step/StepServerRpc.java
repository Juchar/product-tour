package org.vaadin.addons.producttour.shared.step;

import com.vaadin.shared.communication.ServerRpc;

public interface StepServerRpc extends ServerRpc {

  void onCancel();

  void onComplete();

  void onHide();

  void onShow();
}
