# 📃TODOList App

A simple Android TODO app built with **Jetpack Compose**, **Retrofit**, and **Room** using the **MVVM** architecture pattern.

---

## Features

- 🚀 **Instant Data Loading:** Shows cached TODO items immediately on app launch using Room database  
- 🔄 **Data Synchronization:** Automatically fetches fresh TODO items from the JSONPlaceholder API and updates the local cache  
- 📋 **Efficient List UI:** Displays a scrollable list of TODOs with their title and completion status using Jetpack Compose’s `LazyColumn`  
- 📝 **Detail Screen:** Tap on a TODO item to view detailed information with smooth navigation between screens  
- ⚠️ **Error Handling:** Shows error messages and retry options if the network call fails, while still displaying cached data if available  
- 🧩 **Clean Architecture:** Uses MVVM pattern for separation of concerns and easy maintainability  

---

## Tech Stack

- **UI:** Jetpack Compose  
- **Networking:** Retrofit with Kotlin Coroutines  
- **Local Persistence:** Room Database  
- **Architecture:** MVVM (Model-View-ViewModel)  
- **Navigation:** Jetpack Compose Navigation  

---

## Getting Started

1. **Clone the repository:**

   ```bash
   git clone https://github.com/yourusername/your-repo-name.git
2. **Open the project in Android Studio**
3. **Build and run the app on an emulator or physical device**


