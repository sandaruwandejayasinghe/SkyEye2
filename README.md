# SkyEye2 Simple Weather App
Completed for BIT External degree UOM Question 4
 
SkyEye is a simple weather application that provides real-time weather information based on the user entered city location. The app fetches weather data from the OpenWeatherMap API and displays it in a user-friendly interface.

## Features

- Fetches current weather data including temperature, humidity, wind speed, and more.
- Displays weather conditions with corresponding images.
- Automatically updates weather data based on the entered location.
- Provides the ability to manually refresh the weather data.

## Installation

1. Clone the repository:

   git clone https:/sandaruwandejayasinghe/github.com//skyeye2.git

2. Open the project in Android Studio.

3. Add your OpenWeatherMap API key:
   - Open the  "WeatherFetcher.java " file.
   - Replace  "your_api_key_here " with your actual API key:
     
     String weatherUrl = "https://api.openweathermap.org/data/2.5/weather?q{city}&appid={your_api_key_here}";
     
4. Build and run the app on your Android device or emulator.

## Usage

- On launching the app, the  "WelcomeActivity " will display a welcome screen for 2 seconds.
- The app will then navigate to the  "MainActivity " where it will request location permissions.
- Once permissions are granted, the app will fetch and display the current weather data based on the user's location.
- Users can manually refresh the weather data by clicking the "Refresh" button.

## Code Overview

### MainActivity.java

  - MainActivity: The main activity that displays weather information.
  - Initializes views and requests location permissions.
  - Handles the refresh button click to fetch new weather data.
  - Updates the UI with weather data and location information.

### LocationHandler.java

  - LocationHandler: Handles fetching the current location of the user.
  - Uses  "FusedLocationProviderClient " to get the current location.
  - Reverse geocodes the latitude and longitude to get the address.

### WeatherFetcher.java

  -WeatherFetcher: Fetches weather data from the OpenWeatherMap API.
  - Uses  "AsyncTask " to perform network operations on a background thread.
  - Parses the JSON response and updates the UI with weather data.

### WelcomeActivity.java

- WelcomeActivity: Displays a welcome screen for 2 seconds before navigating to  "MainActivity ".

### AndroidManifest.xml

  - Defines the necessary permissions and application components.
  - Permissions for internet access and location.
  - Declares  "WelcomeActivity " as the launch activity.
  - Declares  "MainActivity ".

## Dependencies

- Google Play Services for location services.
- AndroidX libraries for compatibility and UI components.

## ScreenShots


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- [OpenWeatherMap](https://openweathermap.org/) for providing the weather API.
- [Google Play Services](https://developers.google.com/android/guides/overview) for location services.

