# Distributed Datagrid Labcamp

**Configurazione laboratorio:**
* Copiare il progetto "coherence" in una cartella di lavoro (es: D:\labcamp\coherence\workspace);
* Sostiture il file di configurazione utilizzato da maven con quello fornito (settings.xml);
* Modificare nel file "settings.xml" fornito il puntamento al nuovo repository locale utilizzato per il labcamp (es: D:\labcamp\coherence\repository);
* Tramite Shell posizionarsi nella cartella di progetto e lanciare il comando `mvn clean install -DskipTests`;
* Terminata l'esecuzione del comando verificare che il nuovo repository maven si sia popolato con le dipendenze di progetto;
* Avviare l'IDE Eclipse ed importare il progetto "coherence" condiviso come "Existing Maven Projects".

**API Coherence:**
https://docs.oracle.com/middleware/12213/coherence/java-reference/toc.htm

Seguendo gli stesps del laboratorio implementare tutti i metodi commentati dei progetti coherence-cache e coherence-client. Ogni metodo da implementare ha uno unit test che lanciato tramite Eclipse certificherà il corretto sviluppo.

Completati tutti gli steps o a fine laboratorio eseguire l'applicazione.

**Steps laboratorio**
NumStep. [Nome modulo] - Descrizione attività
 
1. [coherence-cache] - Completare file src/main/resources/lab-coherence-cache-config.xml per configurare i seguenti punti:
	1. Definire un Distributed-Schema, referenziato dalle cache, configurando  <local-schema> come implementazione della BackingMap
	3. Definire le tre seguenti cache: CUSTOMERCACHE (CustomerKey, CustomerValue), ORDERCACHE (OrderKey, OrderValue) , PRODUCTCACHE (ProductKey, ProductValue)
	4. Aggiungere configurazione key-associator (step da eseguire dopo il punto 13)
2. [coherence-cache] -  Implementare i metodi dei serializzatori presenti nel package: it.reply.labcamp.coherence.serializer e valorizzare il file src/main/resources/lab-pof-config.xml
3. [coherence-client] - Implementare nel package it.reply.labcamp.coherence.client i seguenti metodi di put: CustomerLib.put(CustomerValue value); OrderLib.put(OrderValue value); ProductLib.put(ProductValue value);
4. [coherence-client] - Implementare nel package it.reply.labcamp.coherence.client i metodi di get: CustomerLib.getCustomerValue(Integer customerId); OrderLib.getOrder(Integer orderId, Integer customerId); ProductLib.getProductValue(Integer productId);
5. [coherence-client] - Implementare nel package it.reply.labcamp.coherence.client i metodi di getAll: CustomerLib.getAllCustomers(); OrderLib.getAllOrders(); ProductLib.getAllProducts();
6. [coherence-client] - Implementare nel package it.reply.labcamp.coherence.client i metodi di getAll(Set keys): CustomerLib.getCustomersByIds(List<Integer> customerId); ProductLib.getProductsByIds(List<Integer> productId);
7. [coherence-client] - Implementare nel package it.reply.labcamp.coherence.client il metodo che esegue una query (equals): CustomerLib.getCustomersByArea(GeoZone area);
8. [coherence-client] - Implementare nel package it.reply.labcamp.coherence.client il metodo che esegue una query (In Filter): OrderLib.getOrdersByTax(List<Integer> taxes);
9. [coherence-client] - Implementare nel package it.reply.labcamp.coherence.client il metodo che esegue una query (Greater Filter): ProductLib.getProductsWithPriceGreaterThan(Double price);
10. [coherence-client] - Implementare nel package it.reply.labcamp.coherence.client il metodo che esegue una query con più parametri: ProductLib.getProductsByCategoryAndPriceRange(Category category, PriceRange priceRange);
11. [coherence-client] - Implementare nel package it.reply.labcamp.coherence.client il metodo che esegue RemoteStream ed Aggregator: OrderLibcalculateTotalAverage();
12. [cohernece-cache] - Implementare nel package it.reply.labcamp.coherence.processor la classe UpdateOrderProcessor, implementando la classe astratta AbstractProcessor (punto 1)
13. [coherence-cache] - Implementare nel package it.reply.labcamp.coherence.keyaffinity la classe KeyCacheAssociator per configurare la KeyAssociation di Coherence
14. [coherence-cache] - Modificare nel package it.reply.labcamp.coherence.processor la classe UpdateOrderProcessor implementando i punto 2

**Esecuzione codice**
1. Lanciare un server Data Storage: posizionarsi nella cartella coherence-cache\target e lanciare il comando `java -Dcoherence.distributed.localstorage=true -cp  coherence-cache-1.0.0-SNAPSHOT-jar-with-dependencies.jar -Dcoherence.pof.enabled=true -Dcoherence.pof.config=lab-pof-config.xml -Dcoherence.cacheconfig=lab-coherence-cache-config.xml com.tangosol.net.DefaultCacheServer`
2. Lanciare nuovamente un secondo server Data Storage
3. Lanciare l'applicazione Client che si unirà al Cluster coherence: posizionarsi nella cartella coherence-client\target e lanciare il comando `java -Dcoherence.distributed.localstorage=false -Dcoherence.pof.enabled=true -Dcoherence.pof.config=lab-pof-config.xml -Dcoherence.cacheconfig=lab-coherence-cache-config.xml  -jar coherence-client-1.0.0-SNAPSHOT-jar-with-dependencies.jar`
4. Seguire le istruzioni da riga di comando per testare l'applicazione








