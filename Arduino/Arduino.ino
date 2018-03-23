#include <SoftwareSerial.h>

/**
 * Pin Configurations
 * 
 *   - Motor Control Pins
 *                        Positive  Negative
 *    Left Wheels    ->      0         1
 *    Right Wheels   ->      2         3
 *    Gripper Level  ->      4         5
 *    Gripper Control->      6         7
 *  
 *   - Other Pins
 *    Bluetooth (Rx|Tx) -> 8,9
 * 
 */


String number = "";

SoftwareSerial Bluetooth(8,9);

void pins(){
  int pindata = number.toInt();
  PORTD = pindata;
  number = "";
}


void setup() {
  Bluetooth.begin(38400);
  DDRD = B11111111;
}

void loop() {

  while(Bluetooth.available())
  {
    char temp = Bluetooth.read();
    if(temp == '\n'){
      break;
    }
    else if(isDigit(temp)){
      number += (char)temp;
    }
  }

  if(number != "") pins();
}
