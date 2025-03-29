# Moxfield Android App

A lightweight Android application that provides a native wrapper for the Moxfield website.

## Overview

This application uses Android's WebView component to display the Moxfield website (https://www.moxfield.com) within a native Android application. Moxfield is a popular platform for building and sharing Magic: The Gathering decks.

## Features

- Loads the Moxfield website in a native Android container
- Full JavaScript support for complete website functionality
- DOM storage enabled for modern web app features
- Responsive design with proper viewport settings
- Built-in zoom controls for better readability
- Website caching for improved performance and offline capability
- Error handling with automatic retry after connection failures
- Back button integration for seamless website navigation

## Requirements

- Android SDK 21 or higher (Android 5.0 Lollipop)
- Internet connection to access Moxfield content

## Installation

1. Clone this repository or download the source code
2. Open the project in Android Studio
3. Build and run the application on your device or emulator

Or

1. Download the APK file

## Usage

Once installed, simply open the app to access Moxfield. The application handles all navigation within the WebView, so you can use it just like the website:

- Use the Android back button to navigate backward through pages
- The app will attempt to reload if connection errors occur
- Website data is cached when possible for faster loading

## Permissions

The application requires the following permission:
- `android.permission.INTERNET`: Required to load web content

## Code Structure

- `MainActivity.kt`: Contains the WebView implementation and configuration
- `AndroidManifest.xml`: Declares app permissions and activity information

## Customization

If you want to modify the application:

- Change the target URL in `MainActivity.kt` to load a different website
- Adjust WebView settings to enable/disable features as needed
- Modify error handling behavior in the WebViewClient implementation

## License

[Your chosen license]

## Acknowledgments

- Moxfield for their excellent Magic: The Gathering deck building platform
