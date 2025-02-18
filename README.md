# Scientific Calculator App
## 1. Introduction
This app is made on Android Studio, using jetpack compose framework, named "Calcul". A calculator app which do basic mathematics manipulation very easily without any lag.  It follows the BODMAS rule for accurate calculations and includes additional functions such as trigonometry, logarithms, etc. This app has a clean and user-friendly UI.

## 2. Features
Basic Arithmetic: Addition, subtraction, multiplication, and division.
BODMAS Rule Implementation: Ensures correct order of operations.
Advanced Functions: Trigonometric, logarithmic, and other scientific calculations under the "More" section.
But! I think there is some error in the code, however I have added the logic for the Advanced functions but they are not working well on the app, really apologize for that.  
User-Friendly Interface: A well-designed UI for smooth interaction.
## 3. Technologies Used
Programming Language: Kotlin

Framework: Jetpack Compose

UI Components: Material Design for an intuitive interface

On this app I have made two parts one is UI part named "Calculator.kt" , this contains only UI frontend part and another is ViewModel named "CalculatorViewModel.kt" which contains all the logic part. In the logic part I have used some AI tools and some of my mind logic like If someone do multiple clicks on multiply button continuously then it appers for only one time, etc.  Also I have heard earlier to use Rhino (Mozilla's JavaScript engine) for logic part for calulations so, I have used that by adding some dependencies.
## 4. Issue & Debugging
Currently, the additional functions (trigonometric, logarithmic, etc.) are not working as expected due to some errors in the code. Maybe this is caused by Rhino Mozilla because AI tools suggested to not use this but I have used that or another thing can be fixed later, also the rad to deg button is not working for logic part.
## 5. Future Improvements
Fixing bugs in trigonometric and logarithmic function handling.
Enhancing UI responsiveness.
Adding a history feature to track past calculations.
Maybe remove the Rhino Mozilla for evaluation and use mXparser or Exp4j for this. 
## 6. Conclusion
This scientific calculator app provides a solid foundation for performing both basic and advanced calculations. With debugging and feature enhancements, it can become a fully functional and reliable tool for mathematical operations.
