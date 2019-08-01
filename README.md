
# react-native-net-info

## Getting started

`$ npm install react-native-net-info --save`

### Mostly automatic installation

`$ react-native link react-native-net-info`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-net-info` and add `RNNetInfo.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNNetInfo.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNNetInfoPackage;` to the imports at the top of the file
  - Add `new RNNetInfoPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-net-info'
  	project(':react-native-net-info').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-net-info/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-net-info')
  	```


## Usage
```javascript
import RNNetInfo from 'react-native-net-info';

// TODO: What to do with the module?
RNNetInfo;
```
  