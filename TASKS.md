✅ Internship Task Log – MountReach Android Internship (June–July 2024)

This file tracks the tasks completed during my internship at MountReach Pvt. Ltd., along with their status and brief notes.  
Each task has its own folder under the `Project-tasks/` directory for detailed documentation.

📱 The app Epic Verse is currently a locally run Android application and has not been deployed.

------------------------------------------------------------

📌 Completed Tasks

🟢 Task 1: Splash Screen, Login & Registration (with Validation)  
Status: ✅ Completed  
Folder: Project-tasks/task1-login-registration  
Summary:  
- Added a clean splash screen to introduce the app  
- Built login and registration screens using EditText, Button, and basic UI components in XML  
- Implemented input validation (email, password, confirm password, etc.)  
- Used Java for logic handling  
- Displayed validation errors via Toast messages  
- Laid foundation for later API integration

------------------------------------------------------------

🟢 Task 2: Navigation & Dialog Features  
Status: ✅ Completed  
Folder: Project-tasks/task2-navigation-dialogs  
Summary:  
- Implemented double-tap back press to exit the app safely  
- Created a "one-time login" logic (user shown welcome screen/dialog only on first login)  
- Designed a custom welcome dialog menu using AlertDialog  
- Integrated Lottie animations to enhance dialog presentation and splash experience  
- Added Back Stack handling for smoother navigation and lifecycle awareness

------------------------------------------------------------

🟢 Task 3: Bottom Navigation using Fragments  
Status: ✅ Completed  
Folder: Project-tasks/task3-bottom-navigation  
Summary:  
- Integrated Bottom Navigation using BottomNavigationView and menu_bottom_navigation.xml  
- Used Fragments for switching between main sections of the app  
- Managed Fragment Transactions manually using FragmentManager for better control  
- Ensured UI consistency and smooth transitions  
Note: In this task, the app name appears as "My Aircooler" due to previous iteration. It refers to the same base app Epic Verse continued for the internship tasks.

------------------------------------------------------------

🟢 Task 4: Google Maps Integration  
Status: ✅ Completed  
Folder: Project-tasks/task4-google-map  
Summary:  
- Integrated Google Maps SDK using SupportMapFragment  
- Placed custom markers for office and college locations with icons  
- Zoomed and animated camera focus between markers  
- Connected both locations using Polyline  
- Added radius indicators using CircleOptions  
- Implemented all logic inside MyLocationActivity.java  
Note: API key is hidden.

------------------------------------------------------------

🟢 Task 5: Music Player UI & Media Playback  
Status: ✅ Completed  
Folder: Project-tasks/task5-music-player  
Summary:  
- Designed a CardView-based Music Player UI with ImageView, TextView, and SeekBar  
- Used custom drawables for Play, Pause, Forward, and Backward buttons  
- Implemented local audio playback using MediaPlayer  
- Enabled seek functionality via SeekBar and synchronized playback time using Handler  
- Added previous/next track switching, as well as 10s forward/backward jump features  
- Applied custom logic for dynamic UI updates (song name, cover image, time labels)  
- Released MediaPlayer responsibly to avoid memory leaks  
Note: Audio and image resources used are stored locally within res/raw and res/drawable

------------------------------------------------------------

🟢 Task 6: Image Slider & Video Playback  
Status: ✅ Completed  
Folder: Project-tasks/task6-image-slider-video  
Summary:  
- Integrated an Image Slider/Carousel using the third-party library ImageSlideshow  
- Images automatically slide with smooth transitions and allow manual swipe  
- Used Lottie animation while media loads for enhanced user feedback  
- Added VideoView to play a sample MP4 video from local storage  
- Included media controls like Play, Pause, and Seek using MediaController  
- Handled lifecycle events (pause/resume) for stable playback  
- Ensured all assets (images, video) are stored in res/drawable and res/raw respectively  
Note: The image slider and video are part of a visually rich media section inside a scrollable layout

------------------------------------------------------------

🟢 Task 7: Speech-to-Text Feature  
Status: ✅ Completed  
Folder: Project-tasks/task7-speech-to-text  
Summary:  
- Implemented speech recognition using Android’s built-in SpeechRecognizer API  
- Added voice input button that triggers microphone and starts listening  
- Captured spoken words and converted them to live text in a TextView  
- Used Intent-based recognition (RecognizerIntent) with proper language model  
- Displayed intermediate and final results to the user in real-time  
- Integrated error handling for unsupported devices or speech failures  
- Ensured app handles permission checks for microphone access  
- Built a clean and accessible UI with minimal distractions  
Note: This feature lays the foundation for future AI/NLP-based voice command modules

------------------------------------------------------------

🟢 Task 8: Profile Photo Picker  
Status: ✅ Completed  
Folder: Project-tasks/task8-profile-photo-picker  
Summary:  
- Implemented functionality to allow users to select a profile image from device storage  
- Added a circular ImageView to display the chosen profile photo  
- Handled permission checks for reading external storage (Android 13+ compatible)  
- Used `ActivityResultLauncher` for selecting image safely  
- Stored image URI in SharedPreferences to retain user choice across app restarts  
- Updated UI dynamically with Glide for smooth image loading and cropping  
Note: This feature enhances user personalization and helps in identifying users locally

------------------------------------------------------------

🟢 Task 9: QR Code Generator  
Status: ✅ Completed  
Folder: Project-tasks/task9-qr-generator  
Summary:  
- Developed a screen to dynamically generate a QR code using user data  
- Fetched the stored `username` from SharedPreferences after login  
- Used the ZXing (Zebra Crossing) library to generate a scannable QR bitmap  
- Displayed QR image in a full-screen responsive layout using ImageView  
- Included user greeting (`Welcome <username>`) and app branding (e.g., "Polytechnic Amravati")  
- QR can be scanned by any standard scanner; content is plain text (username only)  
- Added a back button to return to the home screen smoothly  
- Ensured logic is encapsulated in a clean QRActivity.java with proper error handling  
Note: This feature can be extended to encode more complex data like authentication tokens or URLs in future versions.

------------------------------------------------------------

📂 Note: From Task 3 onwards, only files with major logic/code changes are uploaded to avoid duplication. Supporting files may exist in earlier task folders.  
📱 Device Note: The project was continued and pushed to GitHub after a year due to a device change post-internship. Folder contents are restored manually and logically structured.

“Late is better than never — learning is always worth documenting.” 💡
