
# **NodeMCU Home_Automation_System**
  
  In this IoT project, I have explained how to make Smart Home with Google Assistant and Alexa using **NodeMCU ESP8266 and Sinric Pro**. With this NodeMCU ESP8266 project, you can control 3 home appliances with **Google Assistant**, **Alexa**, and **manual switches**. You can also control the relays from **Google Home and Amazon Alexa App** from anywhere in the world. You can control the relay module from the manual switches if there is no internet available.

And you don’t need any Google Nest or Amazon Echo Dot devices for this voice control home automation project.
 
 ![Alt text](https://iotcircuithub.com/wp-content/uploads/2021/04/NodeMCU-Alexa-Google-Home-Control-P-6-1024x263.jpg)

With this home automation project, you can control & monitor the **real-time feedback** of the relays in the **Google Home** and **Alexa App** from anywhere in the world. If the WiFi is available, the **NodeMCU** will automatically connect with the Wi-Fi.

---


 




## Circuit of the NodeMCU Home Automation

![Alt text](https://iotcircuithub.com/wp-content/uploads/2021/04/NodeMCU-control-4-relays-circuit-02.jpg)

The circuit is very simple, I have used **D1, D2, D5 & D6** GPIO to control the 4-channel relay module.

And the GPIO **SD3, D3, D7 & RX** are connected with manual switches to control the relay module manually.

I have used the **INPUT_PULLUP** function in Arduino IDE instead of using the pull-up resistors with each switch.

As per the source code, when the control pins of the relay module receive the **LOW signal** the respective relay will **turn on** and the relay will **turn off** for the **HIGH** signal in the control pin.

I have used a 5V 2Amp mobile charger to supply the circuit.

**The Boot will fail if SD3 and D3 are grounded during the Boot process. So manual switch-S1 and switch-S2 must be OFF during NodeMCU Boot.**

Now, if you want to use **pushbuttons** then just connect the pushbuttons across the GPIO pins and GND pin instead of switches.

---
## Required Components for the NodeMCU projects

1. NodeMCU
2. 4-channel 5V SPDT Relay Module
3. Manual Switches or Pushbuttons
4. Amazon Echo Dot 
5. Google Nest Mini
## SinricPro (ESP8266 / ESP32 / RP2040)

[![arduino-library-badge](https://www.ardu-badge.com/badge/SinricPro.svg?)](https://www.arduino.cc/reference/en/libraries/sinricpro) [![PlatformIO Registry](https://badges.registry.platformio.org/packages/sinricpro/library/SinricPro.svg)](https://registry.platformio.org/libraries/sinricpro/SinricPro)

[![Platform ESP8266](https://img.shields.io/badge/Platform-Espressif8266-orange)](#) [![Platform ESP32](https://img.shields.io/badge/Platform-Espressif32-orange)](#)
[![Raspberry Pi RP2040](https://img.shields.io/badge/Platform-Raspberry_Pi_RP2040-orange)](#)

[![Framework](https://img.shields.io/badge/Framework-Arduino-blue)](https://www.arduino.cc/)

[![GitHub release (latest by date)](https://img.shields.io/github/v/release/sinricpro/esp8266-esp32-sdk)](https://github.com/sinricpro/esp8266-esp32-sdk/releases)

[![Build](https://github.com/sinricpro/esp8266-esp32-sdk/actions/workflows/build-esp8266-esp32.yml/badge.svg)](https://github.com/sinricpro/esp8266-esp32-sdk/actions/workflows/build-esp8266-esp32.yml)

[![Build](https://github.com/sinricpro/esp8266-esp32-sdk/actions/workflows/build-rpipicow.yml/badge.svg)](https://github.com/sinricpro/esp8266-esp32-sdk/actions/workflows/build-rpipicow.yml)

---
# Installation 

### VS Code & PlatformIO:
1. Install [VS Code](https://code.visualstudio.com/)  
2. Install [PlatformIO](https://platformio.org/platformio-ide)  
3. Create a new Project
4. Use the [Library Manager](https://docs.platformio.org/en/latest/librarymanager/) to add the library to your project or add `sinricpro/SinricPro` manually to your `lib_deps` in `platformio.ini`.

![sinricpro library manager](https://raw.githubusercontent.com/sinricpro/images/master/platformio-install-sinricpro.png)

### ArduinoIDE
1. Open Library Manager (*Tools / Manage Libraries*)  
2. Search for *SinricPro* and click *Install*  
3. Repeat step 2 for all [dependent libraries](#dependencies)!
4. Open example in ArduinoIDE (*File / Examples / SinricPro / ...*)  

![ArduinoIDE Library Manager](https://raw.githubusercontent.com/sinricpro/images/master/ArduinoIDE-Library-Manager.png)

---

## Full user documentation
Please see here for [full user documentation](https://sinricpro.github.io/esp8266-esp32-sdk-documentation/)

---

## Examples

Enter the **APP KEY** and **APP SECRET** with Wi-Fi name and Wi-Fi password in the code.

You can get the **APP KEY** and **APP SECRET** under the **Credentials** menu in Sinric Pro
```
#define WIFI_SSID         "YOUR-WIFI-NAME"    
#define WIFI_PASS         "YOUR-WIFI-PASSWORD"
#define APP_KEY           "YOUR-APP-KEY"
#define APP_SECRET        "YOUR-APP-SECRET"
 ```

Also, enter the device id in the code. You will find the **Device ID** from the Devices menu.
```
//Enter the device IDs here
#define device_ID_1   "SWITCH_ID_NO_1_HERE"
#define device_ID_2   "SWITCH_ID_NO_2_HERE"
#define device_ID_3   "SWITCH_ID_NO_3_HERE"
#define device_ID_4   "SWITCH_ID_NO_4_HERE"
```

When you add a device in Sinric Pro, a unique ID is assigned to that device. If you create 3 devices, then there will be 3 unique device IDs.


## Usage

### Include SinricPro-Library (SinricPro.h) and SinricPro-Device-Libraries (eg. SinricProSwitch.h)
```C++
#include <SinricPro.h>
#include <SinricProSwitch.h>
```

### Define your credentials from SinricPro-Portal (portal.sinric.pro)
```C++
#define APP_KEY    "YOUR-APP-KEY"    // Should look like "de0bxxxx-1x3x-4x3x-ax2x-5dabxxxxxxxx"
#define APP_SECRET "YOUR-APP-SECRET" // Should look like "5f36xxxx-x3x7-4x3x-xexe-e86724a9xxxx-4c4axxxx-3x3x-x5xe-x9x3-333d65xxxxxx"
#define SWITCH_ID  "YOUR-DEVICE-ID"  // Should look like "5dc1564130xxxxxxxxxxxxxx"
```

### Define callback routine(s)
```C++
bool onPowerState(const String &deviceId, bool &state) {
  Serial.printf("device %s turned %s\r\n", deviceId.c_str(), state?"on":"off");
  return true; // indicate that callback handled correctly
}
```

### In setup()
```C++
  // create and add a switch to SinricPro
  SinricProSwitch& mySwitch = SinricPro[SWITCH_ID];
  // set callback function
  mySwitch.onPowerState(onPowerState);
  // startup SinricPro
  SinricPro.begin(APP_KEY, APP_SECRET);

```

### In loop()
```C++
  SinricPro.handle();
```

---
## How to add a device?
Syntax is  
```C++
  DeviceType& myDevice = SinricPro[DEVICE_ID];
```
Example  
```C++
  SinricProSwitch& mySwitch = SinricPro["YOUR-SWITCH-ID-HERE"];
```

---
## How to retrieve a device for sending an event?
Syntax is  
```C++
  DeviceType& myDevice = SinricPro[DEVICE_ID];
```
Example 
```C++
  SinricProDoorbell& myDoorbell = SinricPro["YOUR-DOORBELL-ID-HERE"];
  myDoorbell.sendDoorbellEvent();
```

## How to send a push notification?
```C++
SinricProSwitch& mySwitch = SinricPro[SWITCH_ID];
mySwitch.sendPushNotification("Hello SinricPro!");
```

---

## Device Types
* [Switch]
* [Dimmable Switch]
* [Light]
* [TV]
* [Speaker]
* [Thermostat]
* [Fan]
* [Lock]
* [Doorbell]
* [Temperature Sensor]
* [Motion Sensor]
* [Contact Sensor]
* [Windows Air Conditioner]
* [Blinds]
* [Garage Door]
* [Custom devices]
* [Camera]

### Other
* [OTA]
* [MultiWiFi]
* [Health]
---

## License and Credits
* The Arduino IDE is developed and maintained by the Arduino team. The IDE is licensed under GPL.
* [ArduinoJson] is licensed under the MIT.
* [WebSockets] is licensed under the GNU LGPL.
* The [PlatformIO] is developed and maintained by the PlatformIO team. The Core is licensed under Apache License 2.0.


