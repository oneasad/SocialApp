# SocialApp

A simple social media application built with Kotlin, demonstrating modern Android development practices and technologies.  This app allows users to perform CRUD (Create, Read, Update, Delete) operations on posts using a remote API and local database persistence.

## Features

* Display a list of posts.
* View details of a single post.
* Create new posts.
* Update existing posts.
* Delete posts.
* Offline persistence using Room.

## Technologies Used

* **Kotlin:** The primary programming language.
* **Ktor:** For building asynchronous HTTP clients to interact with the remote API.
* **Room:** For local database persistence using SQLite.
* **Kotlinx Serialization:** For serializing and deserializing JSON data.
* **Koin:** A lightweight dependency injection framework.
* **MVVM (Model-View-ViewModel):** Architectural pattern for separating concerns.
* **Navigation Component:** For managing navigation between fragments.
* **LiveData & ViewModel:** For state management and data observation.
* **Gradle:** Build automation tool.

## Architecture

The app follows the MVVM architecture:

* **Model:** Data classes representing posts (`ApiPost`, `LocalPost`).
* **View:** Fragments (`PostListFragment`, `PostDetailFragment`) responsible for displaying the UI.
* **ViewModel:** (`PostViewModel`) Acts as an intermediary between the Model and View, handling business logic and exposing data through LiveData.

## Project Structure

```
src/main/kotlin/com/oneasad/socialapp
├── data
│   ├── api
│   │   └── KtorApiService.kt  // Handles network requests
│   ├── local
│   │   └── PostDao.kt        // Room Data Access Object
│   ├── model
│   │   ├── ApiPost.kt       // Data class for API interactions
│   │   └── LocalPost.kt      // Data class for local database
│   └── repository
│       └── PostRepository.kt // Abstraction for data access (API & DB)
├── di                      // Koin dependency injection modules
│   └── AppModule.kt
├── ui
│   ├── detail
│   │   └── PostDetailFragment.kt
│   ├── list
│   │   └── PostListFragment.kt
│   └── viewmodel
│       └── PostViewModel.kt
└── ...
```

## Setup and Installation

1. **Clone the repository:**

   ```bash
   git clone [https://github.com/oneasad/socialapp.git](https://github.com/oneasad/socialapp.git)
   cd socialapp
   ```

2. **Open in Android Studio:**

   * Open Android Studio.
   * Select "Open an existing project".
   * Navigate to the cloned repository and open it.

3. **Build and Run:**

   * Build the project (Build -> Make Project).
   * Run the app on an emulator or physical device (Run -> Run 'app').

## API Endpoints (Example - Replace with your actual API)

* `GET /posts`: Get all posts.
* `GET /posts/{id}`: Get a single post by ID.
* `POST /posts`: Create a new post.
* `PUT /posts/{id}`: Update an existing post.
* `DELETE /posts/{id}`: Delete a post.

## Dependencies

The project uses the following dependencies (see `build.gradle` for specific versions):

* Ktor
* Room
* Kotlinx Serialization
* Koin
* AndroidX libraries (AppCompat, RecyclerView, etc.)
* Navigation Component

## Future Improvements

* Implement UI testing.
* Add error handling and user feedback.
* Implement pagination for large lists of posts.
* Improve UI/UX.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

* Ktor: [https://ktor.io/](https://ktor.io/)
* Room: [https://developer.android.com/training/data-storage/room](https://developer.android.com/training/data-storage/room)
* Kotlinx Serialization: [https://github.com/Kotlin/kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization)
* Koin: [https://insert-koin.io/](https://insert-koin.io/)
* Navigation Component: [https://developer.android.com/guide/navigation](https://developer.android.com/guide/navigation)
* MVVM: [https://developer.android.com/jetpack/guide](https://developer.android.com/jetpack/guide)
