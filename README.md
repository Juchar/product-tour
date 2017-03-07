# Product Tour Add-on for Vaadin 7

Product Tour is a UI component add-on for Vaadin 7.

Product Tour provides the possibility to create a product tour for your web application and introduce the user to your user interface.

You can attach the single steps of the tour to every component of your UI.

---
  
**CREDITS**  
The addon is based on [Shepherd](http://github.hubspot.com/shepherd/), a JavaScript library to create product tours.

## Online demo

Try the add-on demo at <url of the online demo>

## Download release

Official releases of this add-on are available at Vaadin Directory. For Maven instructions, download and reviews, go to http://vaadin.com/addon/product-tour

## Building and running demo

git clone <url of the Tour repository>
mvn clean install
cd demo
mvn jetty:run

To see the demo, navigate to http://localhost:8080/

## Development with Eclipse IDE

For further development of this add-on, the following tool-chain is recommended:
- Eclipse IDE
- m2e wtp plug-in (install it from Eclipse Marketplace)
- Vaadin Eclipse plug-in (install it from Eclipse Marketplace)
- JRebel Eclipse plug-in (install it from Eclipse Marketplace)
- Chrome browser

### Importing project

Choose File > Import... > Existing Maven Projects

Note that Eclipse may give "Plugin execution not covered by lifecycle configuration" errors for pom.xml. Use "Permanently mark goal resources in pom.xml as ignored in Eclipse build" quick-fix to mark these errors as permanently ignored in your project. Do not worry, the project still works fine. 

### Debugging server-side

If you have not already compiled the widgetset, do it now by running vaadin:install Maven target for product-tour-root project.

If you have a JRebel license, it makes on the fly code changes faster. Just add JRebel nature to your product-tour-demo project by clicking project with right mouse button and choosing JRebel > Add JRebel Nature

To debug project and make code modifications on the fly in the server-side, right-click the product-tour-demo project and choose Debug As > Debug on Server. Navigate to http://localhost:8080/product-tour-demo/ to see the application.

### Debugging client-side

Debugging client side code in the product-tour-demo project:
  - run "mvn vaadin:run-codeserver" on a separate console while the application is running
  - activate Super Dev Mode in the debug window of the application or by adding ?superdevmode to the URL
  - You can access Java-sources and set breakpoints inside Chrome if you enable source maps from inspector settings.
 
## Release notes

### Version 1.0-SNAPSHOT
- First release with the basic functionality

## Roadmap

This component is developed as a hobby with no public roadmap or any guarantees of upcoming releases. That said, the following features are planned for upcoming releases:
- Currently nothing planned

## Issue tracking

The issues for this add-on are tracked on its github.com page. All bug reports and feature requests are appreciated. 

## Contributions

Contributions are welcome, but there are no guarantees that they are accepted as such. Process for contributing is the following:
- Fork this project
- Create an issue to this project about the contribution (bug or feature) if there is no such issue about it already. Try to keep the scope minimal.
- Develop and test the fix or functionality carefully. Only include minimum amount of code needed to fix the issue.
- Refer to the fixed issue in commit
- Send a pull request for the original project
- Comment on the original issue that you have implemented a fix for it

## License & Author

Add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.

Tour is written by Julien Charpenel

# Developer Guide

## Getting started

Here is a simple example on how to try out the add-on component:

```java
@Theme("default")
@Title("Product Tour Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI2 extends UI {

  @Override
  protected void init(VaadinRequest request) {
    HorizontalLayout layout = new HorizontalLayout();
    layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
    layout.setSizeFull();
    setContent(layout);

    Tour tour = new Tour();

    Button button = new Button("Click to start tour", e -> tour.start());
    layout.addComponent(button);

    tour.addStep(new StepBuilder()
                     .withTitle("Step 1: title")
                     .withText("Step text")
                     .addButton(new StepButton("Back", TourActions::back))
                     .addButton(new StepButton("Next", ValoTheme.BUTTON_PRIMARY, TourActions::next))
                     .build());

    tour.addStep(new StepBuilder()
                     .withTitle("Step 2: title")
                     .withText("Step text")
                     .addButton(new StepButton("Back", TourActions::back))
                     .addButton(new StepButton("Finish", ValoTheme.BUTTON_PRIMARY, TourActions::next))
                     .build());
  }

  @WebServlet(value = "/*", asyncSupported = true)
  @VaadinServletConfiguration(productionMode = false, ui = DemoUI2.class)
  public static class Servlet extends VaadinServlet {
  }
}
```

For a more comprehensive example, see **product-tour-demo/src/main/org/vaadin/addons/producttour/demo/DemoUI.java**

## Features

### Attach to components

The steps of a tour can be attached to any `AbstractCompomponent`.

### Theming

The look and feel of the steps will be adapted to your theme automatically by using the valo theme engine.
Additionally the buttons used for steps can be styled using the valo button styles.

### Options, options, options

You can set various options for the single step like e.g. modality, text, title, anchor position, what buttons are shown.

### Events

The steps and tour fire different events for you to be able to react if needed (e.g. showing of a step, completion of a tour, etc.).
