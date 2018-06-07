#include <ESP8266WiFi.h>


// CONSTANTES
const int chuveiroLigado = 16;
const int chuveiroDesligado = 05;
const int botao = 12;

// Nome do seu wifi
const char* ssid = "flp"; 

// Senha do wifi
const char* password = "12345678"; 

// Host (servidor)
IPAddress server(192,168,43,94);

// Inicializa cliente
WiFiClient client;

// VARIAVEIS GLOBAIS
uint32_t tempoFinal = 0;
uint32_t tempoAtual = 0;
uint32_t tempoInicial = 0;
String codigo = "78945"; // serial do chuveiro ( ou ID )

int flagControle = 0;

// INICIO PROGRAMACAO
void setup() {
  // inicializa as portas da indicacao se o chuveiro esto ligado
  pinMode(chuveiroLigado, OUTPUT);
  pinMode(chuveiroDesligado, OUTPUT);

  // Para o node mcu esp8266. Aqui consigo monitorar e acomapanhar o processo de execuÃ§Ã£o.
  Serial.begin(9600);
  
  // inicializa porta do simulador do sensor do chuveiro
  pinMode(botao, INPUT);

  // configuraÃƒÂ§ÃƒÂ£o conexÃƒÂ£o
  configWifi();
}

void loop() {
  
  byte valor = digitalRead(botao);

  analisaTempoChuveiro(valor);
}

void analisaTempoChuveiro(byte valor) {
    if(valor == HIGH) {
      // simulacao sensor chuveiro
      digitalWrite(chuveiroLigado, HIGH);
      digitalWrite(chuveiroDesligado, LOW);  
      
      tempoAtual = millis();
      if(flagControle == 0) {
        flagControle = 1;
        tempoInicial = tempoAtual;
        Serial.println("ligou chuveiro");
      }
      
  } else {
      // simulacao sensor chuveiro
      digitalWrite(chuveiroDesligado, HIGH);
      digitalWrite(chuveiroLigado, LOW);

      if (flagControle == 1) {
        // reseta flag de controle
        flagControle = 0;
        
        // tempo utilizado do chuveiro
        tempoFinal = tempoAtual - tempoInicial;

        // reset variÃƒÂ¡vel para proximma utilizacao do chuveiro
        tempoAtual = 0;
        tempoInicial = 0;

        // envio do tempo para o servidor
        sendServidor(tempoFinal);
        Serial.println(tempoFinal);
        Serial.println("chuveiro desligado");
      }

      delay(10);
  }
}

void sendServidor(uint32_t tempo){
  if (client.connect(server, 82)) {
      Serial.println("connected");
      // cosntroi um HTTP request:
      String clausula = "GET /chuveiro-internet/comunicacao?tempo=";
      String parametros = String(tempo) + "&codigo=" + codigo + " HTTP/1.0";
      String result = clausula + parametros;
      client.println(result);
      client.println();
      Serial.println(result);
      Serial.println("Info enviado ao servidor");
    }
}

void configWifi() {
  delay(10);
  
  // Mostra no monitor serial informacoes de conexao da rede
  Serial.println();
  Serial.println();
  Serial.print("conectando em ");
  Serial.println(ssid);
  
  // Inicializando a conexao
  WiFi.begin(ssid, password); 
  
  /* Enquanto nao conseguir conectar
    imprime um ponto na tela (da a ideia de que esta carregando :)) */
  while (WiFi.status() != WL_CONNECTED) { 
    delay(500);
    Serial.print("."); 
  }

  Serial.println("");
  Serial.println("WiFi connectado");

  // Mostra o IP do servidor
  Serial.println(WiFi.localIP());

  Serial.print("MAC: ");
  Serial.println(WiFi.macAddress());
}




