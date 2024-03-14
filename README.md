# Code19_Android_Java
 An assessment on online news app using java in android studio 

## Development Approach and Assumptions

During the development process, the following approach and assumptions were made:

- Utilized an online API for backend calls: [NewsAPI](https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=7843a06bbdf74d9eb13aa0cf97820062)
- Implemented functionality to redirect users to full article pages upon tapping on a news CardView.
- Employed Retrofit HTTP client for handling API calls efficiently.
- Incorporated the Picasso library to facilitate the loading of online images seamlessly.
- Utilized the GSON converter to ensure smooth conversion of JSON data to Java objects.
- Managed lists of objects using ArrayLists for efficient data handling.
- Removed unwanted data from the backend received data to enhance performance and reduce clutter.
- Implemented SharedPreferences for storing data locally, ensuring data persistence.
- Utilized RecyclerView to dynamically load data, enhancing the user experience.
- Implemented adapters within RecyclerViews to effectively bridge the data source with the user interface.
- Utilized Intents for smooth navigation between different activities within the application.
- Updated the application title and app icon to improve branding and user recognition.
- Implemented ConstraintLayouts and LinearLayouts for designing the user interface, ensuring responsive and visually appealing layouts.

