# ButterflyEffect
## Software Capstone Design (F028-1) Project

### Title : Touchable Memory
### Team Name : ButterflyEffect

Team Member
====================
+ 201323162 Minjung Sim
+ 201420995 Sungmi Park
+ 201120862 Sungsoo Ahn 
+ 201220920 Ganghyeok Lee

Description
====================
The system of this project has server component, arduino component, and android application component.  
This repository is related with android application component.  
Other repositories URL is as follows:
> server component : https://github.com/ParkSungmi/ButterflyEffect-server  
> arduino component : https://github.com/ParkSungmi/ButterflyEffect-arduino  


Develop environment
====================
 + Android Studio 2.3.1
 + Java
 + Gradle version : 2.3.1
 + Compile SDK, Target SDK version : 25
 + Min SDK : 16
 + Test Device : Galaxy S6 Edge (size : 5.1 inch, resolution : 1440 x 2560)

# Files
 ### < java files >
 + CustomAdapter  
 > Custom adapter for View pager in activity_gallery
 + Gallery  
 > Search pictures in android device and display in grid layout
 + HttpUtil 
 > Communicate with Server (send picture)
 + HttpUtil2  
 > Communicate with Server (regist device)
 + MainActivity  
 > Java code for activity_main
 + Picture  
 > Display selected picture to user and send the picture
 + Regist  
 > Send device information to server for regist server

### < layout files >
 + activity_gallery
 > layout for display picture list
 + activity_main
 > layout for main page
 + activity_picture
 > layout for display selected picture and send it
 + activity_regist
 > layout for regist device
 + viewpager_childview
 > layout for viewpager in acitivity_gallery


License
====================
[MIT][License]

[License]: https://en.wikipedia.org/wiki/MIT_License

