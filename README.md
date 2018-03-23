# Bluetooth_Control

<p> This is a software-hardware project for controlling a small farm track bot via <b><u>bluetooth</u></b> through Android. The bot has two wheel controls for left wheel segment and right wheel segment as well as two other controls for arm level and gripper.

## Technologies used:
<ol>
<li>Android</li>
<li>Arduino</li>
<li>Bluetooth</li>
</ol>

## Process:
<ul>
<li>First the Android device connects to the Bluetooth device interfaced with the Arduino Atmega328 MCU on the bot circuit</li>
<li>Then the control buttons appear on the device screen which when pressed forms a mask on an 8 bit data</li>
<li>As any of the button is pressed or released the mask of data updates and the data is sent to the Bluetooth device</li>
<li>This data is directly provided to the PortB of MCU with minimum delay and thus speeding up the reaction time for control</li>
</ul>

