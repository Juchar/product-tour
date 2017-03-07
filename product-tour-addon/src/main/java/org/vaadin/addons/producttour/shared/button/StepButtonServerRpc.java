package org.vaadin.addons.producttour.shared.button;

import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.communication.ServerRpc;

public interface StepButtonServerRpc extends ServerRpc {

  void onClick(MouseEventDetails mouseEventDetails);
}
