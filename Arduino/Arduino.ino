#include <SoftwareSerial.h>

/**
 * Pin Configurations
 * 
 *   - Motor Control Pins
 *                        Positive  Negative
 *    Left Wheels    ->     13        12
 *    Right Wheels   ->     11        10
 *    Gripper Level  ->      9         8
 *    Gripper Control->      7         6
 *  
 *   - Other Pins
 *    Bluetooth (Rx|Tx) -> 2,3
 *    Bluetooth State   -> 4
 * 
 */

#define LP  13;
#define LN  12;
#define RP  11;
#define RN  10;
#define GLP 9;
#define GLN 8;
#define GCP 7;
#define GCN 6;
#define BT  5;

SoftwareSerial bluetooth(2,3);

void wheel(int i){
  switch(i){
    case 0:
      digitalWrite(RP,LOW);
      digitalWrite(LP,LOW);
      digitalWrite(RN,LOW);
      digitalWrite(LN,LOW);
      digitalWrite(GLP,LOW);
      digitalWrite(GLN,LOW);
      digitalWrite(GCP,LOW);
      digitalWrite(GCN,LOW);
      break;
      
    case 1:
      digitalWrite(RP,HIGH);
      digitalWrite(LP,HIGH);
      digitalWrite(RN,LOW);
      digitalWrite(LN,LOW);
      break;
      
    case 2:
      digitalWrite(RU,HIGH);
      digitalWrite(LU,LOW);
      digitalWrite(RD,LOW);
      digitalWrite(LD,HIGH);
      break;
      
    case 3:
      digitalWrite(RU,LOW);
      digitalWrite(LU,HIGH);
      digitalWrite(RD,HIGH);
      digitalWrite(LD,LOW);
      digitalWrite(AU,LOW);
      digitalWrite(AD,LOW);
      digitalWrite(OU,LOW);
      digitalWrite(OD,LOW);
      break;
    case 4:
      digitalWrite(RU,LOW);
      digitalWrite(LU,HIGH);
      digitalWrite(RD,LOW);
      digitalWrite(LD,HIGH);
      digitalWrite(AU,LOW);
      digitalWrite(AD,LOW);
      digitalWrite(OU,LOW);
      digitalWrite(OD,LOW);
      break;
    case 5:
      digitalWrite(RU,LOW);
      digitalWrite(LU,LOW);
      digitalWrite(RD,LOW);
      digitalWrite(LD,LOW);
      digitalWrite(AU,HIGH);
      digitalWrite(AD,LOW);
      digitalWrite(OU,LOW);
      digitalWrite(OD,LOW);
      break;
    case 6:
      digitalWrite(RU,LOW);
      digitalWrite(LU,LOW);
      digitalWrite(RD,LOW);
      digitalWrite(LD,LOW);
      digitalWrite(AU,LOW);
      digitalWrite(AD,HIGH);
      digitalWrite(OU,LOW);
      digitalWrite(OD,LOW);
      break;
    case 7:
      digitalWrite(RU,LOW);
      digitalWrite(LU,LOW);
      digitalWrite(RD,LOW);
      digitalWrite(LD,LOW);
      digitalWrite(AU,LOW);
      digitalWrite(AD,LOW);
      digitalWrite(OU,HIGH);
      digitalWrite(OD,LOW);
      break;
    case 8:
      digitalWrite(RU,LOW);
      digitalWrite(LU,LOW);
      digitalWrite(RD,LOW);
      digitalWrite(LD,LOW);
      digitalWrite(AU,LOW);
      digitalWrite(AD,LOW);
      digitalWrite(OU,LOW);
      digitalWrite(OD,HIGH);
      break;
  }
}

void setup() {
  // put your setup code here, to run once:

}

void loop() {
  // put your main code here, to run repeatedly:

}
