package org.vaadin.addons.producttour.client.button;

import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.ui.Connect;

import org.vaadin.addons.producttour.button.StepButton;
import org.vaadin.addons.producttour.client.util.MouseEventDetailsConsumer;
import org.vaadin.addons.producttour.shared.button.StepButtonServerRpc;
import org.vaadin.addons.producttour.shared.button.StepButtonState;

@Connect(StepButton.class)
public class StepButtonConnector extends AbstractExtensionConnector {

  private StepButtonServerRpc rpcProxy;

  @Override
  protected void extend(ServerConnector target) {
    rpcProxy = getRpcProxy(StepButtonServerRpc.class);
  }

  public StepButtonOptions getOptions() {
    StepButtonOptions options = StepButtonOptions.create();
    options.setText(getState().caption);
    options.setEnabled(getState().buttonEnabled);
    options.setClasses(getState().styles.toArray(new String[getState().styles.size()]));
    options.setClickListener(new MouseEventDetailsConsumer() {
      @Override
      public void accept(MouseEventDetails mouseEventDetails) {
        rpcProxy.onClick(mouseEventDetails);
      }
    });
    return options;
  }

  @Override
  public StepButtonState getState() {
    return (StepButtonState) super.getState();
  }
}
