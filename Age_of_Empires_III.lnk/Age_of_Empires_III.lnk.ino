#define BUTTON_1_PIN      3
#define BUTTON_2_PIN      4

char inputs   [20];
char oldInputs[20];

void setup() {
    Serial.begin(115200);
    
    pinMode(BUTTON_1_PIN, INPUT);
    pinMode(BUTTON_2_PIN, INPUT);
}

void getInputs(){   
    sprintf(inputs, "SS:%c:%c",
        digitalRead(BUTTON_1_PIN)?'1':'0',
        digitalRead(BUTTON_2_PIN)?'1':'0'
    );
}

void loop() {   
    getInputs();

if( strcmp(inputs, oldInputs) != 0){
 strcpy(oldInputs, inputs);
        Serial.println(inputs);
 }    
  delay(10);
}
