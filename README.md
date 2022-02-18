# NYTnews

## To use the Application you need to put your own `API-Key` from [Developer Account here](https://developer.nytimes.com/)

Create a File inside your root project directory like --> `<YourFileName>.properties` <br>
inside it put your API-Key `API_KEY_STRING = "<Your Key Belongs Here>"` <br>

Then inside your `build.gradle(:app)` put the below code <br>

```gradle
def apikeyPropertiesFile = rootProject.file("<YourFileName>.properties")
def apikeyProperties = new Properties()
apikeyProperties.load(new FileInputStream(apikeyPropertiesFile))
```

Then <br>

```gradle
android {
   ...
   defaultConfig {
        ...
        // Load API Key.
        buildConfigField("String", "API_KEY_STRING", apikeyProperties['API_KEY_STRING'])
    }
```

Rebuild your project to generate buildConfig File with your Api Key. <br>

```kotlin

//You can now use your Api key inside your code.
val apiKey = BuildConfig.API_KEY_STRING

```
