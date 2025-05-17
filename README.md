📝 TodoApp - Jetpack Compose TODO List
Features 🚀
Instant Data Display: Loads cached TODO items immediately on app launch for a seamless user experience.

Live Sync: Automatically fetches fresh TODO data from the JSONPlaceholder API and updates the UI.

Local Persistence: Uses Room database to cache TODOs, ensuring data is available offline.

Clean MVVM Architecture: Clear separation of concerns with ViewModel and Repository layers.

Elegant UI with Jetpack Compose: Smooth, modern, and responsive UI with efficient scrolling using LazyColumn.

Detail View: Tap any TODO item to see full details on a dedicated screen.

Robust Error Handling: Shows friendly messages and retry options if network calls fail.

Compose Navigation: Smooth transitions between list and detail screens with proper back navigation.

Kotlin Coroutines: Asynchronous data loading for better performance and responsiveness.

Tech Stack 🔧
Kotlin & Jetpack Compose

Retrofit for network calls

Room for local database caching

MVVM architectural pattern

Kotlin Coroutines & Flow for reactive programming

Compose Navigation for in-app navigation

How to Use ⚙️
Clone the repo

Open the project in Android Studio

Build and run the app on an emulator or device

The app loads cached TODOs instantly and refreshes from the network

Tap any TODO item to view its details

