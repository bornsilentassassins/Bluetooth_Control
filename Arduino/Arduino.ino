#include <SoftwareSerial.h>

/**
 * Pin Configurations
 * 
 *   - Motor Control Pins
 *                        Positive  Negative
 *    Left Wheels    ->      7         6
 *    Right Wheels   ->      5         4
 *    Gripper Level  ->      3         2
 *    Gripper Control->      1         0
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
