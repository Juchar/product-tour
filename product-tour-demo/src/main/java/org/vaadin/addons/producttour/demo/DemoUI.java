package org.vaadin.addons.producttour.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import org.vaadin.addons.producttour.actions.TourActions;
import org.vaadin.addons.producttour.button.StepButton;
import org.vaadin.addons.producttour.button.StepButtonBuilder;
import org.vaadin.addons.producttour.shared.step.ContentMode;
import org.vaadin.addons.producttour.shared.step.StepAnchor;
import org.vaadin.addons.producttour.step.Step;
import org.vaadin.addons.producttour.step.StepBuilder;
import org.vaadin.addons.producttour.tour.Tour;

import java.util.Arrays;
import java.util.List;

import javax.servlet.annotation.WebServlet;

@Theme("default")
@Title("Product Tour Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

  @Override
  protected void init(VaadinRequest request) {
    Tour tour = new Tour();

    VerticalLayout rootLayout = getRootLayout();
    setContent(rootLayout);

    Label title = getTitleLabel();
    rootLayout.addComponent(title);

    ComboBox themeChooser = getThemeChooserComboBox();
    rootLayout.addComponent(themeChooser);

    ExpandingGap.add(rootLayout);

    Button startButton = getStartButton(tour);
    rootLayout.addComponent(startButton);

    ExpandingGap.add(rootLayout);

    tour.addStep(getStep1(themeChooser));
    tour.addStep(getStep2(startButton));
    tour.addStep(getStep3(startButton));
    tour.addStep(getStep4(startButton));
    tour.addStep(getStep5(startButton));
  }

  private VerticalLayout getRootLayout() {
    VerticalLayout rootLayout = new VerticalLayout();
    rootLayout.setDefaultComponentAlignment(Alignment.TOP_CENTER);
    rootLayout.setSpacing(true);
    rootLayout.setMargin(true);
    rootLayout.setSizeFull();
    return rootLayout;
  }

  private Label getTitleLabel() {
    Label title = new Label("Product Tour Add-on Demo");
    title.setWidthUndefined();
    title.addStyleName(ValoTheme.LABEL_H1);
    return title;
  }

  private ComboBox getThemeChooserComboBox() {
    List<String> themeList = Arrays.asList("Blueprint", "Dark", "Default", "Facebook", "Flat",
                                           "Flat-Dark", "Light", "Metro");
    ComboBox<String> themeChooser = new ComboBox<>("Choose theme", themeList);
    themeChooser.setWidth(200, Unit.PIXELS);
    themeChooser.setValue("Default");
    themeChooser.addValueChangeListener(e -> setTheme(e.getValue().toLowerCase()));
    return themeChooser;
  }

  private Button getStartButton(Tour tour) {
    Button startButton = new Button("Click to start demo", e -> {
      if (tour.getCurrentStep() != null) {
        tour.cancel();
      }
      tour.start();
    });
    startButton.setWidth(200, Unit.PIXELS);
    startButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
    return startButton;
  }

  private Step getStep1(AbstractComponent attachTo) {
    return new StepBuilder()
               .withAttachTo(attachTo)
               .withWidth(400, Unit.PIXELS)
               .withHeight(400, Unit.PIXELS)
               .withTitle("Tour started!")
               .withText(
                   "Great, you managed to start the tour!<br><br>The steps are styled according " +
                   "to your style definitions. If you customized valo, the steps will take over " +
                   "your custom style. Try to switch the theme using the box above.<br><br>Please" +
                   " use the buttons below to cancel the tour or go to the next step.")
               .addButton(new StepButton("Cancel", TourActions::back))
               .addButton(new StepButton("Next", ValoTheme.BUTTON_PRIMARY, TourActions::next))
               .withAnchor(StepAnchor.TOP)
               .build();
  }

  private Step getStep2(AbstractComponent attachTo) {
    return new StepBuilder()
               .withAttachTo(attachTo)
               .withWidth(600, Unit.PIXELS)
               .withHeight(400, Unit.PIXELS)
               .withTitle("Anchors")
               .withText(
                   "As you can see the steps can be attached to any component.<br><br>The anchor " +
                   "can be either on the right, bottom, left or top. Use the button below to " +
                   "change the anchor position.<br><br>If there is not enough space, the step " +
                   "will be repositioned automatically (e.g. at the opposite position)" +
                   ".<br><br>Additionally the step has not to be attached at all. If not attached" +
                   " it will be shown in the middle of the screen.")
               .withTextContentMode(ContentMode.HTML)
               .addButton(new StepButton("Back", TourActions::back))
               .addButton(new StepButton("Change Anchor", ValoTheme.BUTTON_PRIMARY, e -> {
                 Step step = e.getStep();
                 int ordinal = step.getAnchor().ordinal();
                 StepAnchor[] values = StepAnchor.values();
                 ordinal = ordinal < values.length - 1 ? ordinal + 1 : 0;
                 step.setAnchor(values[ordinal]);
               }))
               .addButton(new StepButton("Detach step", ValoTheme.BUTTON_PRIMARY, e -> {
                 Step step = e.getStep();
                 if (step.getAttachedTo() != null) {
                   step.setDetached();
                   e.getStepButton().setCaption("Attach step");
                 } else {
                   step.setAttachedTo(attachTo);
                   e.getStepButton().setCaption("Detach step");
                 }
               }))
               .addButton(new StepButton("Next", TourActions::next))
               .build();
  }

  private Step getStep3(AbstractComponent attachTo) {
    return new StepBuilder()
               .withAttachTo(attachTo)
               .withWidth(800, Unit.PIXELS)
               .withTitle("Button styles")
               .withText("For the buttons you can use the styles provided by Valo.")
               .addButton(new StepButtonBuilder("Disabled").withEnabled(false).build())
               .addButton(new StepButton("Friendly", ValoTheme.BUTTON_FRIENDLY))
               .addButton(new StepButton("Danger", ValoTheme.BUTTON_DANGER))
               .addButton(new StepButton("Tiny", ValoTheme.BUTTON_TINY))
               .addButton(new StepButton("Small", ValoTheme.BUTTON_SMALL))
               .addButton(new StepButton("Large", ValoTheme.BUTTON_LARGE))
               .addButton(new StepButton("Huge", ValoTheme.BUTTON_HUGE))
               .addButton(new StepButton("Borderless", ValoTheme.BUTTON_BORDERLESS))
               .addButton(new StepButton("Borderless Colored", ValoTheme.BUTTON_BORDERLESS_COLORED))
               .addButton(new StepButton("Quiet", ValoTheme.BUTTON_QUIET))
               .addButton(new StepButton("Link style", ValoTheme.BUTTON_LINK))
               .addButton(new StepButton("Quiet", ValoTheme.BUTTON_QUIET))
               .addButton(new StepButton("Back", TourActions::back))
               .addButton(new StepButton("Next", ValoTheme.BUTTON_PRIMARY, TourActions::next))
               .build();
  }

  private Step getStep4(AbstractComponent attachTo) {
    return new StepBuilder()
               .withAttachTo(attachTo)
               .withWidth(400, Unit.PIXELS)
               .withTitle("Content <b>styles</b>")
               .withText(
                   "The <b>content mode</b> of <i>title</i> and <i>text</i> can be changed to " +
                   "HTML, TEXT and PREFORMATTED.")
               .withTitleContentMode(ContentMode.HTML)
               .withTextContentMode(ContentMode.HTML)
               .addButton(new StepButton("Back", TourActions::back))
               .addButton(new StepButton("Next", ValoTheme.BUTTON_PRIMARY, TourActions::next))
               .build();
  }

  private Step getStep5(AbstractComponent attachTo) {
    return new StepBuilder()
               .withAttachTo(attachTo)
               .withWidth(400, Unit.PIXELS)
               .withHeight(400, Unit.PIXELS)
               .withTitle("Cancellable, Scroll To, Modal")
               .withText(
                   "The steps can be set to be <ul><li>cancellable (small cross in the upper " +
                   "right corner)</li><li>scroll to (if th step is show the view will be scrolled" +
                   " to the element it is attached to)</li><li>modal</li></ul>")
               .withTextContentMode(ContentMode.HTML)
               .withCancellable(true)
               .withModal(true)
               .addButton(new StepButton("Back", TourActions::back))
               .addButton(new StepButton("Finish", ValoTheme.BUTTON_PRIMARY, TourActions::next))
               .build();
  }

  @WebServlet(value = "/*", asyncSupported = true)
  @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
  public static class Servlet extends VaadinServlet {
  }
}
