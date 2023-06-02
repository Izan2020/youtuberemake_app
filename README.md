# Youtube Remake

YouTube Remake is an Android application developed using Kotlin language and following the Jetpack Compose approach for building the user interface. The purpose of the application is to consume the YouTube API and display a list of popular videos. It utilizes the "listOfPopularVideos" API endpoint to fetch the required data.

The application employs the ViewModel architecture component to manage the state and provide data to the UI. It uses a ViewModel class, possibly named "YoutubeViewModel," to handle data fetching and manipulation. This allows for separation of concerns and ensures the UI remains reactive and independent of the data source. To play the videos, the application integrates the YoutubeVideoPlayer library from GitHub. This library provides the necessary functionality to play YouTube videos within the application. It is likely implemented in the UI component responsible for displaying the video, such as the "ItemVideosYoutube" component mentioned in the code snippet.

The UI of the application is built using Jetpack Compose, a modern toolkit for building native Android UIs declaratively. It offers a simplified and more efficient way of creating UI components compared to traditional XML-based layouts. The code snippet you provided showcases the UI structure of the Home screen, which includes a top app bar, a list of videos, and loading/error states.

Overall, YouTube Remake combines the power of the YouTube API, Jetpack Compose, ViewModel, and the YoutubeVideoPlayer library to create a user-friendly application that allows users to browse and watch popular YouTube videos seamlessly.
