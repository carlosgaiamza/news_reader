#  Technical assignment: RSS Feed Reader
## To download an excecute the application locally, please follow the instructions bellow:
1. Download the source:

        $ git clone https://github.com/carlosgaiamza/news_reader.git

2. Go to the root folder and buld the application using maven or your prefered IDE:

        $ cd news_reader
        $ mvn clean install

3. Go to the target directory and run the jar:
        
        $ cd target
        $ java -jar assignment-0.0.1-SNAPSHOT.jar 
        
    **Note:** If you prefere to using a different feed url the url parameter can be provided by parameter:

        $ java -jar -DrssFeedsConsumer.url=https://www.feedforall.com/sample.xml assignment-0.0.1-SNAPSHOT.jar 

4. Open the GraphiQL ID on the Web browser:

        - http://localhost:9001/graphiql

5. Use the following sample query:
    ```
    {
        recentItems {
          title
          publicationDate
          updatedDate
          description  
        }
    }
   ```

