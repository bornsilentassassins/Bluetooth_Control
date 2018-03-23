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
<li>This data is directly provided to the PortD of MCU with minimum delay and thus speeding up the reaction time for control</li>
</ul>

## Controls:

<table>
  <tr align=center>
    <td rowspan=2><b>Control</b></td>
    <td colspan=2><b>8 bit mask</b></td>
  </tr>
  <tr>
    <td><b>OR Mask</b></td>
    <td><b>AND Mask</b></td>
  </tr>
  
  <tr>
  <td>Left Up</td>
  <td>00000001</td>
  <td>11111101</td>
  </tr>
  
  <tr>
  <td>Left Down</td>
  <td>00000010</td>
  <td>11111110</td>
  </tr>
  
  <tr>
  <td>Left Stop</td>
  <td>00000000</td>
  <td>11111100</td>
  </tr>
  
  <tr>
  <td>Right Up</td>
  <td>00000100</td>
  <td>11110111</td>
  </tr>
  
  <tr>
  <td>Right Down</td>
  <td>00001000</td>
  <td>11111011</td>
  </tr>
  
  <tr>
  <td>Right Stop</td>
  <td>00000000</td>
  <td>11110011</td>
  </tr>
  
  <tr>
  <td>Arm Up</td>
  <td>00010000</td>
  <td>11011111</td>
  </tr>
  
  <tr>
  <td>Arm Down</td>
  <td>00100000</td>
  <td>11101111</td>
  </tr>
  
  <tr>
  <td>Arm Stop</td>
  <td>00000000</td>
  <td>11001111</td>
  </tr>
  
  <tr>
  <td>Gripper Open</td>
  <td>01000000</td>
  <td>01111111</td>
  </tr>
  
  <tr>
  <td>Gripper Close</td>
  <td>10000000</td>
  <td>10111111</td>
  </tr>
  
  <tr>
  <td>Gripper Stop</td>
  <td>00000000</td>
  <td>00111111</td>
  </tr>
 
  </table>

## Schematics:
<img src="https://github.com/bornsilentassassins/Bluetooth_Control/blob/master/Images/Schematics.png"/>
