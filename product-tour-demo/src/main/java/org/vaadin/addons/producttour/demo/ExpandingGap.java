package org.vaadin.addons.producttour.demo;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class ExpandingGap extends CustomComponent {

  private ExpandingGap() {
    Label expandingGap = new Label();
    expandingGap.setSizeFull();
    setCompositionRoot(expandingGap);
  }

  public static void add(AbstractOrderedLayout layout) {
    ExpandingGap gap = new ExpandingGap();
    layout.addComponent(gap);
    layout.setExpandRatio(gap, 1);
  }
}
