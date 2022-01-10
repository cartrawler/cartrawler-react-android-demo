
import React, { Component} from 'react';

import {
  AppRegistry,
  Button,
  NativeEventEmitter,
  NativeModules,
  Platform,
  Text,
  StyleSheet,
  View
} from 'react-native';


const ctSDKStarterModule = NativeModules.CTSDKStarterModule;
const eventEmitterModule = NativeModules.EventEmitterModule;

export default class CTSDKDemoComponent extends Component {
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.title}>Reactive Native Screen</Text>
        <View style={styles.buttonContainer}>
          <Button color="#004958" style={styles.btn}
            onPress={() => {
                if(Platform.OS === 'ios'){

                }else{
                  ctSDKStarterModule.startBookingFlow(false)
                }
              }
            }
            title='Start Standalone flow'
          />
          <Button color="#004958" style={styles.btn}
            onPress={() => {
              if(Platform.OS === 'ios'){

              }else{
                ctSDKStarterModule.startBookingFlow(true)
              }
            } 
          }
            title='Start InPath flow'
          />
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  buttonContainer: {
    height: 100,
    width: "80%",
    justifyContent: 'space-between',
    marginTop: 5,
  },
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#E5ECFF',
  },
  title: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },

  btn:{
    marginTop: 10,
    color: "#FFFFFF"
  }
});

AppRegistry.registerComponent('CTSDKDemoComponent', () => CTSDKDemoComponent);

const eventEmitter = new NativeEventEmitter(eventEmitterModule);
eventEmitter.addListener("BookingEvent", (params) => {
  alert("Hello from React Native side, the booking details were received"+ "\n\n" + params);
});