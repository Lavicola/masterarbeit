
## Beschreibung
Das folgende Repository enthält den Codebasis für das Query Repository sowie dem dazugehörigen Frontend.

### Query Repository
Das Query Repository ist der in Java Spring geschriebene Modulith mit Gradle als Buildtool .
Das Query Repository besteht aus drei Komponent:
- Data Mart (Integration von Datenbankabfrage und Metadaten)
- DeviceConfiguration (Verwaltung der Geräte)
- DatastreamMetric (Datenstrom und Metriken)


### Architektur und Schnittstellen des Query repository

#### Architektur
![Query Repository als Modulith](https://github.com/Lavicola/masterarbeit/blob/master/Architektur/modulith.png)

#### Schnittstellen

##### Data Mart
![Data Mart Schnittstellen](https://github.com/Lavicola/masterarbeit/blob/master/Architektur/dataMart.drawio.png)

##### Device Configuration
![Device Configuration Schnittstellen](https://github.com/Lavicola/masterarbeit/blob/master/Architektur/DeviceConfig.drawio.png)

##### Datastream
![Datastream Schnittstellen](https://github.com/Lavicola/masterarbeit/blob/master/Architektur/Datastream.drawio.png)


#### Aufbau Des Query Repository
Jede Komponent besitzt folgende Ordnerstruktur:
- Controller
    - Abgesehen von den ...Impl Klassen wurden alle Klassen mittels OpenAPI generiert.
- Dto
    - Dieser Ordner besitzt alle Transferobjekte für das Frontend. Auch hier wurden die Objekte mittels OpenAPI erstellt. ACHTUNG: Manche Transferobjekte werden im Gradle Task gemappt, sodass das Modell im Query Repository genutzt wird.
- Models
    - Enthält die ORM Modelle des Query Repository sowie häufig die Validierungslogik z.B. für das hinzufügen eines Kostenmodells.
- Repository
    - die Repositoryklassen ermöglichen den Zugriff auf die unterschiedlichen Objekte.
- Service
    - Die Service beinhalten die Logik. Hier werden z.B. bei der Data Mart die Datenbankabfragen und deren Ausführungspläne integriert. Zusätzlich beinhaltet die Klasse die unterschiedlichen Schnittstellen zum Nachrichtenbroker (RabbitMQ). Die Nachrichten werden versendet und empfangen.
- OpenAPI
    - In diesem Ordner befindet sich die Spezifikation für die Frontend Kommunikation und Transferobjekte
- AsyncAPI
    - In diesem Ordner befindet sich die Spezifikation der Nachrichten und Warteschlangen die das Query Repository versteht bzw. unterstützt.
- Messages
    - In diesem Ordner sind die generierten Nachrichten(= Transferobjekte) für die Warteschlangen. 
 

#### Codegenerierung mit OpenAPI
Für die Codegenerierung im Query Repository können die Gradle Tasks in build.gradle genutzt werden. Dabei steht für jede Komponente ein Task zur Verfügung.
Wichtig zu beachten ist, dass bei der Ausführung des Tasks die Datei "application.properties" überschrieben wird (sofern das stört --> .openapi-generator-ignore ausfüllen).


#### Codegenerierung mit AsyncAPI
**Vorraussetzung: Es wird npm gebraucht und das Paket npm install -g @asyncapi/cli  muss installiert worden sein.**

Prinzipiell hat AsyncAPI einen dokumentierenden charakter. Jedoch wurden die Transferobjekte in /Messages mit AsyncAPI erzeugt. Dies wurde jedoch nicht mit Tasks gemacht. Grund dafür ist, das AsyncAPI viel "Müll" erzeugt bei der Generierung. Die Erstellung wurde daher mittels dem cli gemacht, wofür folgender Befehl verwendet werden kann:<br>**asyncapi generate fromTemplate >pathToTemplate< @asyncapi/java-spring-template**
-    AsyncAPI erstellt Payload und die Nachricht, also für jedes Transferobjekt 2 Klassen.
 
#### Hinweise für das Query Repository
- Die Grundkonfiguration kam vom spring initializer. Dieser hat u.a. für die Annotationen eine pom.xml erstellt. Diese wird nicht gebraucht, um das Query Repository zu starten. Es wird jedoch gebraucht, wenn das Projekt via IDE gestartet werden soll. 
	- genutzte Konfiguration:
java 17 -cp queryrepository.main fau.de.queryrepository.KrakenRepositoryApplication)
- java/org.openapitools/RFC3339DateFormat wird gebraucht, ansonsten funktioniert der ModelMapper nicht (siehe application.properties)
- Abgesehen von der AsyncAPI sind auch alle Warteschlangen in application.properties zu finden.
- CorsFilter wird gebraucht, da die generierten Service im Frontend via OpenAPI sonst die Antwort nicht akzeptieren.
#### Starten des Query Repository
**./gradlew bootRun**


### Frontend-Query-Repository
Das Frontend wurde mittels Angular entwickelt.<br>
**Jedes Codefeld kann mittels F11 in Fullscreen angezeigt werden.**


#### Starten des Frontends und Hinweis
ng serve <br>Hinweis:  Ich denke **node_modules**   ist sinnvoll auf Gitlab zu haben, um eine Reproduzierbarkeit zu garantieren. 
 
#### Codegenerierung mit OpenAPI
Im Frontend wurde mittels Terminal die Transferobjekte sowie Service erstellt:<br>
**ng-openapi-gen --input >pathToYaml< output >folderName<**<br>
nachdem der Code erzeugt wurde:
- **fn** Ordner in /src/app/fn platzieren
- **models** Dateien in /src/app/models platzieren
- **services** Datei in /src/app/services platzieren


### Allgemeiner Hinweis
Die Tatsächliche Abgabe ist der letzte Commit (Dienstag 13.02.2024).
Gerade für das Testen des Frontends ist es jedoch nützlich dummy Daten zu haben.
Daher rate ich für die Überprüfung den Commit **cc1cd2fc999dac7b6d5cc6520928936d15a09da3** zu betrachten. Dort sind dummy Daten und Methoden enthalten für einige Seiten. Zur Erklärung: <br>
Starte das Query Repository und das Frontend und anschließend:
- Besuche die Seite *Query Execution* und führe ein Query aus.
	- Im Backend werden 1000 Queries erstellt und ein Ausführungsplan
		- 	Um die Filterung im Verlauf zu prüfen, reduziert sich das Datum (Timestamp des Queries) nach jeden erstellten Query um einen Tag.
	- Das Backend antwortet mit einen Query und zwei Ausführungspläne. Diese können anschließend im Frontend betrachtet werden.
- Nach Betrachtung der Seite kann *Query History* geöffnet werden, um die Verlaufshistorie zu überprüfen.
- In der Verlaufshistorie kann zusätzlich auf *analyze* geklickt werden.
- Hier wird man auf die Seite für die Analyse eines Ausführungsplans weitergeleitet. Im Frontend ist dabei ein (Dummy) Ausführungsplan mit 10 Schritten enthalten. Damit kann die Funktionalität im Frontend geprüft werden
- Abschließend: gehe auf *Datastreams* und erstelle einen neuen Datenstrom. Anschließend klicke auf "Go to Metrics". 
- Auf der Metrikseite sind erneut Dummy Daten im Frontend abgespeichert. Hier kann im Dropdownmenü die einzige Metrik ausgewählt werden, um die Messungen zu visualisieren.
