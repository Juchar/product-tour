package org.vaadin.addons.producttour.client.tour;

import com.google.gwt.core.client.JavaScriptObject;

import org.vaadin.addons.producttour.client.step.StepOptions;
import org.vaadin.addons.producttour.client.util.Command;
import org.vaadin.addons.producttour.client.util.StringBiConsumer;

public class TourOptions extends JavaScriptObject {

  protected TourOptions() {}

  public static native TourOptions create() /*-{
    return {when: {}};
  }-*/;

  public final native void setDefaultOptions(StepOptions defaultOptions) /*-{
    this.defaults = defaultOptions;
  }-*/;

  public final native void setCancelListener(Command command) /*-{
    this.when.cancel = function () {
      command.@org.vaadin.addons.producttour.client.util.Command::execute()();
    }
  }-*/;

  public final native void setCompleteListener(Command command) /*-{
    this.when.complete = function () {
      command.@org.vaadin.addons.producttour.client.util.Command::execute()();
    }
  }-*/;

  public final native void setHideListener(Command command) /*-{
    this.when.hide = function () {
      command.@org.vaadin.addons.producttour.client.util.Command::execute()();
    }
  }-*/;

  public final native void setShowListener(StringBiConsumer consumer) /*-{
    this.when.show = function (e) {
      var pId = e.previous != null ? e.previous.id : '';
      var cId = e.step != null ? e.step.id : '';
      consumer.@org.vaadin.addons.producttour.client.util.StringBiConsumer::accept(*)(pId, cId);
    }
  }-*/;

  public final native void setStartListener(Command command) /*-{
    this.when.start = function () {
      command.@org.vaadin.addons.producttour.client.util.Command::execute()();
    }
  }-*/;
}
