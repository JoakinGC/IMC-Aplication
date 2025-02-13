# IBM Aplicaction

[![IMC3.png](https://i.postimg.cc/tRNvs8xc/IMC3.png)](https://postimg.cc/Lhh37Cvk)
[![IMC1.png](https://i.postimg.cc/Pxm3zRFP/IMC1.png)](https://postimg.cc/9RQYcJ2h)
[![IMC2.png](https://i.postimg.cc/BQyTbCMy/IMC2.png)](https://postimg.cc/ppfh1KNY)



Autor/s: Contreras Joaquin

To ensure the proper initialization of the application, the following dependencies are required:

1. **Dependency Addition:**
    
Add the following dependency in your project-level Gradle file:
    
```gradleCopy 
implementation("androidx.fragment:fragment-ktx:1.6.2") // Fragment
```
    

    
2. **Binding Configuration:**
    
 Enable view binding in your project by including the following in your module-level Gradle file:
    
```gradleCopy 
buildFeatures {
	viewBinding = true 
}

```
    
For older versions of Android Studio, use:
    
    
```
viewBinding {     
	enable = true
 }
```
    
    

Once synchronization is complete, the project can be utilized properly.

Please ensure that the main activity is titled "ImcActivity". In case it doesn't start as the main screen, verify the `AndroidManifest.xml` file. Add the activity with the name "ImcActivity" and include:

xmlCopy code

```xml
<intent-filter>  
    <action android:name="android.intent.action.MAIN" />  
  
    <category android:name="android.intent.category.LAUNCHER" />  
</intent-filter>
```


This will help designate "ImcActivity" as the main launcher activity


