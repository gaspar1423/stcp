stcp
====

Android App for STCP

*************************************************************************************************************************
This app was created as a Colleg project for Universidade Fernando Pessoa in Porto, Portugal.

This is a private app created by students as an evaluation component for a subject.

It's purpose is to calculate the shortest route between two locations using only the bus lines from the STCP company in 
Porto.
*************************************************************************************************************************


Before running project please add to workspace de existing android code (import->Android->Existing Android Code Into Workspace) google_play_services_lib and android-support-v7-appcompat located at:

  -android-support-v7-appcompat :  (sdk_location)/extras/android/support/v7
  -google_play_services_lib  :  (sdk_location)/extras/google/google_play_services/libproject

After import:
  -google_play_services_lib Manifest.xml change the minimumSDK from 8 to 11;
  -Add both projects to the stcp build path:
      -RightClick 'stcp' and chose Properties;
      -Click Android and in Libraty click Add and chose a'ndroid-support-v7-appcompat'
      -Repeat process now to add 'google_play_services_lib'
  -Go to Eclipse Preferences and Navigate to Android -> Build:
    -Add the custom debug keystore by clicking Browse and navigate to debug.keystore file and click 'Apply'
    
    
