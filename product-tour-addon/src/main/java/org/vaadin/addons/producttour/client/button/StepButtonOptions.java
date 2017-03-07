package org.vaadin.addons.producttour.client.button;

import com.google.gwt.core.client.JavaScriptObject;

import org.vaadin.addons.producttour.client.util.MouseEventDetailsConsumer;

public class StepButtonOptions extends JavaScriptObject {

  protected StepButtonOptions() {}

  public static native StepButtonOptions create() /*-{
    return {};
  }-*/;

  public final native void setText(String text) /*-{
    this.text = text;
  }-*/;

  public final native void setClasses(String[] classes) /*-{
    this.classes = classes.join(' ');
  }-*/;

  public final native void setEnabled(boolean enabled) /*-{
    this.buttonEnabled = enabled;
  }-*/;

  public final native void setClickListener(MouseEventDetailsConsumer consumer)/*-{
    this.events = {
      'click': function (e) {
        var mouseEventDetails = @com.vaadin.client.MouseEventDetailsBuilder::buildMouseEventDetails(Lcom/google/gwt/dom/client/NativeEvent;Lcom/google/gwt/dom/client/Element;)(e, e.target);
        consumer.@org.vaadin.addons.producttour.client.util.MouseEventDetailsConsumer::accept(*)(mouseEventDetails);
      }
    };
  }-*/;
}
