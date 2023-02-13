# JavaFX Dashboard

This JavaFX Dashboard is a flexible and versatile template, designed to be used as a starting point for your own custom dashboard projects. It features a theme changer with both dark and light modes, a customizable table builder, and a user-friendly interface.

**[Features](#-features) • [Themes](#-themes) • [Theme Changer](#-theme-changer) • [Table Builder](#-table-builder) • [Adding Tabs](#-adding-tabs) • [Building and Running](#-building-and-running) • [License](#-license)**

![thumbnail](.github/thumbnail.png)

## 💫 Features

- Custom title bar
- Rounded movable window
- Resizeable
- Dark/Light [Theme Changer](#-theme-changer) (with local storage)
- [Table Builder](#-table-builder)

## 🎨 Themes

| Theme    | Light                                                | Dark                                                    |
|----------|------------------------------------------------------|---------------------------------------------------------|
| Standard | ![standard-light](.github/themes/standard-light.png) | ![json-standard-dark](.github/themes/standard-dark.png) |
| Backify  | ![backify-light](.github/themes/backify-light.png)   | ![backify-dark](.github/themes/backify-dark.png)        |
    
## 🌗 Theme Changer

![theme](.github/theme.png)

To add a new theme, you need to create a new enumerated value in the [Theme](src/main/java/com/jannikbuscha/dashboard/util/Theme.java) enum:

```java
public enum Theme {
    STANDARD, BACKIFY, /* YOUR_NEW_THEME */;
    // ...
}
```

Then create the corresponding CSS files:

- `dark.css` (Color variables for the dark theme)
- `light.css` (Color variables for the light theme)
- `theme.css` (Semantic color variables and font for the theme)

in the [theme](src/main/resources/com/jannikbuscha/dashboard/css/theme) directory:

```
 📦theme
 ┣ 📂backify
 ┣ 📂standard
 ┗ 📂your_new_theme
 ┃ ┣ 📜dark.css
 ┃ ┣ 📜light.css
 ┃ ┗ 📜theme.css
```

How to structure the content of these files can be found in the [standard theme](src/main/resources/com/jannikbuscha/dashboard/css/theme/standard).

After changing the theme in the dashboard, it is stored locally in `java.io.tmpdir` properties using the [LocalUserData](src/main/java/com/jannikbuscha/dashboard/user/LocalUserData.java) class.

## 🛠 Table Builder

The table methods in FXUtils simplify the process of building tables and populating the TableView, making it easier to create a well-formatted table of data from the ObservableList.

Example usage:

```java
final ObservableList<User> data = FXCollections.observableArrayList(
        new User("Violet", 56, "USA"),
        new User("Brianna", 44, "Germany"),
        // ...
);

List<TableColumn<User, ?>> columns = Arrays.asList(
        FXUtil.createColumn("Name", User::getName),
        FXUtil.createColumn("Age", User::getAge),
        FXUtil.createColumn("Country", User::getCountry)
);

TableView<User> table = FXUtil.createTable(data, columns);
```

More context can be found for this in the [Options](src/main/java/com/jannikbuscha/dashboard/tab/Options.java)-Tab class.

## 🗂 Adding Tabs

To create a new tab, you must first create a new `HBox` in the [Dashboard FXML](src/main/resources/com/jannikbuscha/dashboard/fxml/dashboard.fxml) as follows in the `VBox fx:id="vbxMenuNavigation"`:

```xml
<HBox alignment="CENTER_LEFT" spacing="16.0" styleClass="sidebar-tab">
    <StackPane maxHeight="20.0" maxWidth="20.0" minHeight="20.0" minWidth="20.0">
        <!-- Your Icon (e.g. SVGPath) -->
    </StackPane>
    <Label styleClass="regular" text="Your New Tab" />
</HBox>
```

Then the Tab class in the [Tab](src/main/java/com/jannikbuscha/dashboard/tab) package must be created as follows:

```java
public class YourNewTab extends StackPane {
    public YourNewTab() {
        this.getChildren().add(new Label(this.getClass().getSimpleName()));
    }
}
```

Finally, create an object in the `initialize()` method of the [Dashboard Controller](src/main/java/com/jannikbuscha/dashboard/controller/DashboardController.java) inside the tabs Pane array:

```java
Pane[] tabs = {new Home(), new Users(), new Builder(), new Options(), /*new YourNewTab()*/};
```

## 🔨 Building and Running

The recommended IDE for building and running this project is [IntelliJ Idea](https://www.jetbrains.com/idea/) with [JDK 8](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html).

This project uses Maven, so building it from source is very easy. Once you have everything set up, follow these simple steps:
- Build binary: `mvn clean package`
- Run binary: `mvn exec:java`

## 📝 License

[MIT](LICENSE)